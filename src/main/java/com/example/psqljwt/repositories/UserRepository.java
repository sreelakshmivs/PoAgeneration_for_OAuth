package com.example.psqljwt.repositories;

import com.example.psqljwt.domain.User;
import com.example.psqljwt.exceptions.EtAuthException;

public interface UserRepository {
    Integer create(String resourceServerId, String metadata, String clientId, String password) throws EtAuthException;
    User findByEmailAndPassword(String clientId, String password) throws EtAuthException;

    Integer getCountByEmail(String clientId);

    User findById(Integer userId);

}