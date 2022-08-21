package com.pkovar.dbviewer.service;

import com.pkovar.dbviewer.dto.TableColumnDTO;
import com.pkovar.dbviewer.dto.TableDTO;
import com.pkovar.dbviewer.entity.ConnectionEntity;

import java.sql.SQLException;
import java.util.List;

public interface IDatabaseService {
    List<String> getSchemaList(ConnectionEntity connectionEntity) throws SQLException;
    List<TableDTO> getTableList(ConnectionEntity connectionEntity, String schemaName) throws SQLException;
    List<TableColumnDTO> getTableColumns(ConnectionEntity connectionEntity, String schemaName, String tableName) throws SQLException;
    List<String> getDataPreview(ConnectionEntity connectionEntity, String schemaName, String tableName) throws SQLException;
}
