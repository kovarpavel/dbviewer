package com.pkovar.dbviewer.controller;

import com.pkovar.dbviewer.dto.TableColumnDTO;
import com.pkovar.dbviewer.dto.TableDTO;
import com.pkovar.dbviewer.entity.ConnectionEntity;
import com.pkovar.dbviewer.service.DatabaseServiceImpl;
import com.pkovar.dbviewer.service.IConnectionService;
import com.pkovar.dbviewer.service.IDatabaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
class DatabaseController {

    private IConnectionService connectionService;
    private IDatabaseService databaseServiceImpl;

    public DatabaseController(IConnectionService connectionService, IDatabaseService databaseServiceImpl) {
        this.connectionService = connectionService;
        this.databaseServiceImpl = databaseServiceImpl;
    }

    @GetMapping("/connection/{connectionId}/schemas")
    public List<String> getSchemas(@PathVariable Long connectionId) throws SQLException {
        ConnectionEntity connectionEntity = connectionService.getConnection(connectionId);
        return databaseServiceImpl.getSchemaList(connectionEntity);
    }

    @GetMapping("/connection/{connectionId}/{schema}/tables")
    public List<TableDTO> getTables(@PathVariable Long connectionId, @PathVariable String schema) throws SQLException {
        ConnectionEntity connectionEntity = connectionService.getConnection(connectionId);
        return databaseServiceImpl.getTableList(connectionEntity, schema);
    }

    @GetMapping("/connection/{connectionId}/{schema}/{table}/columns")
    public List<TableColumnDTO> getTableColumns(
            @PathVariable Long connectionId,
            @PathVariable String schema,
            @PathVariable String table) throws SQLException {
        ConnectionEntity connectionEntity = connectionService.getConnection(connectionId);
        return databaseServiceImpl.getTableColumns(connectionEntity, schema, table);
    }

    @GetMapping("/connection/{connectionId}/{schema}/{table}/data")
    public List<String> getDataPreview(
            @PathVariable Long connectionId,
            @PathVariable String schema,
            @PathVariable String table) throws SQLException {
        ConnectionEntity connectionEntity = connectionService.getConnection(connectionId);
        return databaseServiceImpl.getDataPreview(connectionEntity, schema, table);
    }

}