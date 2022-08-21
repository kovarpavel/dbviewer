package com.pkovar.dbviewer.dto;

public record ConnectionDTO(
        String name,
        String databaseName,
        String hostName,
        String port,
        String username,
        String password) {
}
