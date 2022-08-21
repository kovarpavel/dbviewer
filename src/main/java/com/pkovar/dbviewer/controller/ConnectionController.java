package com.pkovar.dbviewer.controller;

import com.pkovar.dbviewer.dto.ConnectionDTO;
import com.pkovar.dbviewer.entity.ConnectionEntity;
import com.pkovar.dbviewer.service.IConnectionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ConnectionController {
    private IConnectionService connectionService;

    public ConnectionController(IConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @GetMapping("/connection")
    public List<ConnectionEntity> getConnections() {
        return connectionService.getAllConnections();
    }

    @GetMapping("/connection/{id}")
    public ConnectionEntity getConnection(@PathVariable Long id) {
        return connectionService.getConnection(id);
    }

    @PostMapping("/connection")
    public ConnectionEntity saveConnection(@RequestBody ConnectionDTO newConnection) throws Exception {
       return connectionService.createNewConnection(newConnection);
    }

    @DeleteMapping("/connection/{id}")
    public String deleteConnection(@PathVariable Long id) {
        connectionService.deleteConnection(id);
        return "Connection with id " + id + " deleted.";
    }

    @PutMapping("/connection/{id}")
    public ConnectionEntity updateConnection(@RequestBody ConnectionDTO updatedConnection, @PathVariable Long id) {
        return connectionService.updateConnection(id, updatedConnection);
    }

}
