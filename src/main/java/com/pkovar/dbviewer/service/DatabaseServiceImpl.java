package com.pkovar.dbviewer.service;

import com.pkovar.dbviewer.dto.TableColumnDTO;
import com.pkovar.dbviewer.dto.TableDTO;
import com.pkovar.dbviewer.entity.ConnectionEntity;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Service
public class DatabaseServiceImpl implements IDatabaseService {

    private final Map<String, DataSource> datasources = new HashMap<>();

    @Override
    public List<String> getSchemaList(ConnectionEntity connectionEntity) throws SQLException {
        Connection con = getConnection(connectionEntity);
        PreparedStatement pst = con.prepareStatement( "SELECT nspname" +
                " FROM pg_catalog.pg_namespace;" );
        ResultSet rs = pst.executeQuery();
        List<String> schemas = new ArrayList<>();
        while ( rs.next() ) {
            schemas.add(rs.getString("nspname"));
        }
        return schemas;
    }

    @Override
    public List<TableDTO> getTableList(ConnectionEntity connectionEntity, String schemaName) throws SQLException {
        Connection con = getConnection(connectionEntity);
        PreparedStatement pst = con.prepareStatement( "SELECT table_name, table_type FROM information_schema.tables \n" +
                "WHERE table_schema = '" + schemaName + "'");
        ResultSet rs = pst.executeQuery();
        List<TableDTO> tables = new ArrayList<>();
        while ( rs.next() ) {
            TableDTO table = new TableDTO(
                    rs.getString("table_name"),
                    rs.getString("table_type"));
            tables.add(table);
        }
        return tables;
    }

    @Override
    public List<TableColumnDTO> getTableColumns(ConnectionEntity connectionEntity, String schemaName, String tableName) throws SQLException {
        Connection con = getConnection(connectionEntity);
        PreparedStatement pst = con.prepareStatement(
                "SELECT column_name, is_nullable, data_type " +
                        " FROM information_schema.columns " +
                        " WHERE table_schema = '" + schemaName + "' " +
                        " AND table_name   = '" + tableName + "'");
        ResultSet rs = pst.executeQuery();
        List<TableColumnDTO> columns = new ArrayList<>();
        while ( rs.next() ) {
            TableColumnDTO table = new TableColumnDTO(
                    rs.getString("column_name"),
                    rs.getString("data_type"),
                    rs.getString("is_nullable"));
            columns.add(table);
        }
        return columns;
    }

    @Override
    public List<String> getDataPreview(ConnectionEntity connectionEntity, String schemaName, String tableName) throws SQLException {
        Connection con = getConnection(connectionEntity);
        PreparedStatement pst = con.prepareStatement(
                "SELECT * FROM " + schemaName + "." + tableName + " LIMIT 5");
        ResultSet rs = pst.executeQuery();
        List<String> rows = new ArrayList<>();
        while ( rs.next() ) {
            StringBuilder sb = new StringBuilder();
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();
            for (int i = 1; i <= numberOfColumns; i++) {
                sb.append(rs.getString(i));
                if (i < numberOfColumns) {
                    sb.append(", ");
                }
            }
            rows.add(sb.toString());
        }
        return rows;
    }
    private Connection getConnection(ConnectionEntity connectionEntity) throws SQLException {
        String jdbcUrl = new StringBuilder()
                .append("jdbc:postgresql://")
                .append(connectionEntity.getHostName())
                .append(":")
                .append(connectionEntity.getPort())
                .append("/")
                .append(connectionEntity.getDatabaseName())
                .toString();

        DataSource ds = datasources.get(jdbcUrl);
        if (ds != null) {
            return ds.getConnection();
        } else {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(jdbcUrl);
            config.setUsername(connectionEntity.getUsername());
            config.setPassword(connectionEntity.getPassword());
            config.setDriverClassName("org.postgresql.Driver");
            config.setKeepaliveTime(1000 * 60);
            config.setMaxLifetime(1000 * 120);
            HikariDataSource hds = new HikariDataSource(config);
            datasources.put(jdbcUrl, hds);
            return hds.getConnection();
        }
    }

}
