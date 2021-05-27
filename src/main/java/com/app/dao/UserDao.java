package com.app.dao;

import com.app.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers(){
        RowMapper<User> userRowMapper = (resultSet, RowNumber) -> mapUser(resultSet);
        return jdbcTemplate.query("SELECT * FROM users", userRowMapper);
    }

    public boolean checkUserNameAvailability(String username){
        String sql = "SELECT 1 FROM users WHERE name = '" + username + "' LIMIT 1";
        RowCountCallbackHandler rowCountCallbackHandler = new RowCountCallbackHandler();
        jdbcTemplate.query(sql, rowCountCallbackHandler);
        int rowCount = rowCountCallbackHandler.getRowCount();
        return rowCount == 0;
    }

    private User mapUser(ResultSet resultSet) throws SQLException{
        User user = new User();
        user.setUserId(resultSet.getLong("id"));
        user.setUserName(resultSet.getString("name"));
        user.setUserPassword(resultSet.getString("password"));
        return user;
    }

    public void storeUser(User user){
        jdbcTemplate.update("INSERT INTO users (name, password) VALUES (?, ?)", user.getUserName(), user.getUserPassword());
    }
}
