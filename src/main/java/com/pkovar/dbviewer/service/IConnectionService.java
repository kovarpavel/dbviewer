package com.pkovar.dbviewer.service;

import com.pkovar.dbviewer.dto.ConnectionDTO;
import com.pkovar.dbviewer.entity.ConnectionEntity;
import java.util.List;

public interface IConnectionService {

    List<ConnectionEntity> getAllConnections();
    ConnectionEntity createNewConnection(ConnectionDTO newConnection) throws Exception;
    ConnectionEntity getConnection(Long id);
    void deleteConnection(Long id);
    ConnectionEntity updateConnection(Long id, ConnectionDTO updatedConnection);
}
