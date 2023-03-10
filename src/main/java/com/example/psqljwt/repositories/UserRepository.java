package com.example.psqljwt.repositories;

import com.example.psqljwt.domain.User;
import com.example.psqljwt.exceptions.EtAuthException;

public interface UserRepository {
    Integer create(String destinationNetworkId, String metadata, String clientId, String password, String transferable) throws EtAuthException;
    User findByClientidAndPassword(String clientId, String password) throws EtAuthException;

    Integer getCountByClientId(String clientId);

    User findById(Integer userId);

}
