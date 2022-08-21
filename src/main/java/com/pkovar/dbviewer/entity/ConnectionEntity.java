package com.pkovar.dbviewer.entity;

import javax.persistence.*;

@Entity
public class ConnectionEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String hostName;

    @Column(unique = true)
    private String databaseName;
    private String port;
    private String username;
    private String password;

    public ConnectionEntity() {
    }

    public ConnectionEntity(String name, String databaseName, String hostName, String port, String username, String password) {
        this.name = name;
        this.databaseName = databaseName;
        this.hostName = hostName;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
}
