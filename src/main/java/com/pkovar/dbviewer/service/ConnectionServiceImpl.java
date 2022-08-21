package com.pkovar.dbviewer.service;

import com.pkovar.dbviewer.exception.ConnectionNotFoundException;
import com.pkovar.dbviewer.dto.ConnectionDTO;
import com.pkovar.dbviewer.entity.ConnectionEntity;
import com.pkovar.dbviewer.exception.InvalidInputException;
import com.pkovar.dbviewer.repository.ConnectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionServiceImpl implements IConnectionService {

    private ConnectionRepository connectionRepository;

    public ConnectionServiceImpl(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    @Override
    public List<ConnectionEntity> getAllConnections() {
        return (List<ConnectionEntity>) connectionRepository.findAll();
    }

    @Override
    public ConnectionEntity createNewConnection(ConnectionDTO newConnection){
        if (!isInputValid(newConnection)) {
            throw new InvalidInputException(newConnection);
        } else {
            return connectionRepository.save(new ConnectionEntity(
                    newConnection.name(),
                    newConnection.databaseName(),
                    newConnection.hostName(),
                    newConnection.port(),
                    newConnection.username(),
                    newConnection.password()
            ));
        }
    }

    @Override
    public ConnectionEntity getConnection(Long id) {
        return connectionRepository.findById(id).orElseThrow(() -> new ConnectionNotFoundException(id));
    }

    @Override
    public void deleteConnection(Long id)  {
        ConnectionEntity connectionEntity =
                connectionRepository.findById(id).orElseThrow(() -> new ConnectionNotFoundException(id));
        connectionRepository.deleteById(id);
    }

    @Override
    public ConnectionEntity updateConnection(Long id, ConnectionDTO updatedConnection) {
        ConnectionEntity connectionEntity =
                connectionRepository.findById(id).orElseThrow(() -> new ConnectionNotFoundException(id));

        if (updatedConnection.name() != null) {
            connectionEntity.setName(updatedConnection.name());
        }
        if (updatedConnection.databaseName() != null) {
            connectionEntity.setDatabaseName(updatedConnection.databaseName());
        }
        if (updatedConnection.hostName() != null) {
            connectionEntity.setHostName(updatedConnection.hostName());
        }
        if (updatedConnection.port() != null) {
            connectionEntity.setPort(updatedConnection.port());
        }
        if (updatedConnection.username() != null) {
            connectionEntity.setUsername(updatedConnection.username());
        }
        if (updatedConnection.password() != null) {
            connectionEntity.setPassword(updatedConnection.password());
        }

        return connectionRepository.save(connectionEntity);
    }

    private Boolean isInputValid(ConnectionDTO newConnection) {
        return newConnection.name() != null
                && newConnection.databaseName() != null
                && newConnection.hostName() != null
                && newConnection.port() != null
                && newConnection.username() != null
                && newConnection.password() != null;
    }
}
