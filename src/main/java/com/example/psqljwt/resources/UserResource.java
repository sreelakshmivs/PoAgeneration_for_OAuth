package com.example.psqljwt.resources;

import com.example.psqljwt.Constants;
import com.example.psqljwt.domain.User;
import com.example.psqljwt.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap) {
        String clientId = (String) userMap.get("clientId");
        String password = (String) userMap.get("password");
        User user = userService.validateUser(clientId, password);
        //return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);

        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
    }

    @PostMapping("/client") //register
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, Object> userMap) {
        String resourceServerId = (String) userMap.get("resourceServerId"); //fname
        String metadata = (String) userMap.get("metadata"); //lname
        String clientId = (String) userMap.get("clientId"); //email
        String password = (String) userMap.get("password"); //password
        User user = userService.registerUser(resourceServerId, metadata, clientId, password);
        //Map<String, String> map = new HashMap<>();
        //map.put("message", "registered successfully");

        //return new ResponseEntity<>(map, HttpStatus.OK);
        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
    }
    private Map<String, String> generateJWTToken(User user) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("userId", user.getUserId())
                .claim("clientId", user.getEmail())
                .claim("resourceServerId", user.getFirstName())
                .claim("metadata", user.getLastName())
                .compact(); //to store token variable as a string
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }
}