package com.example.psqljwt.domain;

public class User {
    private Integer userId;
    private String destinationNetworkId; //changed from resourceOwnerId
    private String transferable;
    private String metadata;
    private String clientId;   //important one like username
    private String password;
//Add two more parameters clientid and poa for OAuth implementation
    public User(Integer userId, String destinationNetworkId, String transferable, String metadata, String clientId, String password) {
        this.userId = userId;
        this.destinationNetworkId = destinationNetworkId;
        this.transferable = transferable;
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

    public String getDestinationNetworkId() {
        return destinationNetworkId;
    }

    public void setDestinationNetworkId(String destinationNetworkId) {
        this.destinationNetworkId = destinationNetworkId;
    }

    public String getTransferable() {
        return transferable;
    }

    public void setTransferable(String transferable) {
        this.transferable = transferable;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
