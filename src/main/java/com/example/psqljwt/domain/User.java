package com.example.psqljwt.domain;

public class User {
    private Integer userId;
    private String resourceServerId;
    private String metadata;
    private String clientId;   //important one like username
    private String password;
//Add two more parameters clientid and poa for OAuth implementation
    public User(Integer userId, String resourceServerId, String metadata, String clientId, String password) {
        this.userId = userId;
        this.resourceServerId = resourceServerId;
        this.metadata = metadata;
        this.clientId = clientId;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return resourceServerId;
    }

    public void setFirstName(String resourceServerId) {
        this.resourceServerId = resourceServerId;
    }

    public String getLastName() {
        return metadata;
    }

    public void setLastName(String metadata) {
        this.metadata = metadata;
    }

    public String getEmail() {
        return clientId;
    }

    public void setEmail(String clientId) {
        this.clientId = clientId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
