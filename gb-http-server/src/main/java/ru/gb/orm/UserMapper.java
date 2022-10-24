package ru.gb.orm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserMapper {

    private final Connection connection;

    private final PreparedStatement selectUser;
    private final PreparedStatement selectUserAll;
    private final PreparedStatement insertUser;
    private final PreparedStatement updateUser;
    private final PreparedStatement deleteUser;

    private final Map<Long, User> identityMap = new HashMap<>();

    public UserMapper(Connection connection) {
        this.connection = connection;
        try {
            this.selectUser = connection.prepareStatement("select id, username, password from users where id = ?;");
            this.selectUserAll = connection.prepareStatement("select * from users");
            this.insertUser = connection.prepareStatement("insert into users (id, username, password) values (?, ?, ?);");
            this.updateUser = connection.prepareStatement("update users set id = ?, username = ?, password = ? where id = ?;");
            this.deleteUser = connection.prepareStatement("delete from users where id = ?;");
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Optional<User> findById(long id) {
        User user = identityMap.get(id);
        if (user != null) {
            return Optional.of(user);
        }
        try {
            selectUser.setLong(1, id);
            ResultSet rs = selectUser.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
                identityMap.put(id, user);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            ResultSet rs = selectUserAll.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
            return users;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void insert(User user) {
        try {
            insertUser.setLong(1, user.getId());
            insertUser.setString(2, user.getLogin());
            insertUser.setString(3, user.getPassword());
            insertUser.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(User user) {
        try {
            updateUser.setLong(1, user.getId());
            updateUser.setString(2, user.getLogin());
            updateUser.setString(3, user.getPassword());
            updateUser.setLong(4, user.getId());
            updateUser.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(User user) {
        try {
            deleteUser.setLong(1, user.getId());
            deleteUser.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
