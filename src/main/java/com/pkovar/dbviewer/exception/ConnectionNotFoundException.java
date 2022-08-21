package com.pkovar.dbviewer.exception;

public class ConnectionNotFoundException extends RuntimeException{
    public ConnectionNotFoundException(Long id) {
        super("Could not find connection " + id);
    }
}
