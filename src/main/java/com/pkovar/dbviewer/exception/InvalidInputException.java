package com.pkovar.dbviewer.exception;

import com.pkovar.dbviewer.dto.ConnectionDTO;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(ConnectionDTO dto) {
        super("Connection information is not complete. Please provide all attributes: name, databaseName, hostName, port, username, password. " +
                "Provided: " + dto);
    }
}
