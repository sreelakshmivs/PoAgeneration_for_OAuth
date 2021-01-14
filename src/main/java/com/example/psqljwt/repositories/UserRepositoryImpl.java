package com.example.psqljwt.repositories;

import com.example.psqljwt.domain.User;
import com.example.psqljwt.exceptions.EtAuthException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String SQL_CREATE = "INSERT INTO et_poa(USER_ID, resourceServerId, metadata, clientId, PASSWORD) VALUES(NEXTVAL('et_poa_SEQ'), ?, ?, ?, ?)";
    private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM et_poa WHERE clientId = ?";
    private static final String SQL_FIND_BY_ID = "SELECT USER_ID, resourceServerId, metadata, clientId, PASSWORD " +
            "FROM et_poa WHERE USER_ID = ?";
    private static final String SQL_FIND_BY_EMAIL = "SELECT USER_ID, resourceServerId, metadata, clientId, PASSWORD " +
            "FROM et_poa WHERE clientId = ?";
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String resourceServerId, String metadata, String clientId, String password) throws EtAuthException {

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));


        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, resourceServerId);
                ps.setString(2, metadata);
                ps.setString(3, clientId);
                ps.setString(4, hashedPassword);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("USER_ID");
        }catch (Exception e) {
            throw new EtAuthException("Invalid details. Failed to create PoA");
        }
    }
    @Deprecated
    @Override
    public User findByEmailAndPassword(String clientId, String password) throws EtAuthException {
        try {
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[]{clientId}, userRowMapper);
            if(!BCrypt.checkpw(password, user.getPassword()))
                throw new EtAuthException("Invalid clientId/password");
            return user;
        }catch (EmptyResultDataAccessException e) {
            throw new EtAuthException("Invalid clientId/password");
        }
    }
    @Deprecated
    @Override
    public Integer  getCountByEmail(String clientId) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, new Object[]{clientId}, Integer.class);
    }
    @Deprecated
    @Override
    public User findById(Integer userId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId}, userRowMapper);
    }
    private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
        return new User(rs.getInt("USER_ID"),
                rs.getString("resourceServerId"),
                rs.getString("metadata"),
                rs.getString("clientId"),
                rs.getString("PASSWORD"));
    });
}
