package com.example.psqljwt.services;

import com.example.psqljwt.domain.User;
import com.example.psqljwt.exceptions.EtAuthException;

public interface UserService {
    User validateUser(String clientId, String password) throws EtAuthException;

    User registerUser(String resourceServerId, String metadata, String clientId, String password) throws EtAuthException;

}
