package com.example.psqljwt.services;

import com.example.psqljwt.domain.User;
import com.example.psqljwt.exceptions.EtAuthException;
import com.example.psqljwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service  //Spring automatically identify this class as a bean
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String clientId, String password) throws EtAuthException {
        if(clientId != null) clientId = clientId.toLowerCase();
        return userRepository.findByEmailAndPassword(clientId, password);
    }

    @Override
    public User registerUser(String destinationNetworkId, String metadata, String clientId, String password) throws EtAuthException {
        if(clientId != null) clientId = clientId.toLowerCase();
        Integer count = userRepository.getCountByEmail(clientId);
        if(count > 0)
            throw new EtAuthException("ClientId already in use");
        Integer userId = userRepository.create(destinationNetworkId, metadata, clientId, password);
        return userRepository.findById(userId);    }
}
