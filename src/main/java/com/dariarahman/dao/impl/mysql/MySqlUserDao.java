package com.dariarahman.dao.impl.mysql;

import com.dariarahman.exceptions.DBException;
import com.dariarahman.dao.DBManager;
import com.dariarahman.dao.UserDao;
import com.dariarahman.entity.Gender;
import com.dariarahman.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MySqlUserDao implements UserDao {

    private static final Logger log = LogManager.getLogger(MySqlUserDao.class);

    private static final String FIND_ALL_USERS = "SELECT * FROM users WHERE role_id=1";
    private static final String FIND_USER_BY_ID = "SELECT * FROM users WHERE user_id=?";

    private static final String FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";
    private static final String INSERT_USER = "INSERT INTO users VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, DEFAULT, DEFAULT)";

    private static final String UPDATE_USER = "UPDATE users SET password=?, first_name=?, last_name=?, gender=?, balance=?, user_status=? WHERE email=?";
    private static final String UPDATE_USER_WITHOUT_BALANCE = "UPDATE users SET password=?, first_name=?, last_name=?, gender=? WHERE user_id=?";

    @Override
    public User findUserByID(long id) throws DBException {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(FIND_USER_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }

            con.commit();
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t find user by id";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws DBException {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(FIND_USER_BY_EMAIL);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }

            con.commit();
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t find user by email";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return user;
    }

    @Override
    public List<User> findAllUsers() throws DBException {
        List<User> users = new CopyOnWriteArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(FIND_ALL_USERS);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                users.add(extractUser(rs));
            }

            con.commit();
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t find all users";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return users;
    }

    @Override
    public boolean addUser(User user) throws DBException {
        boolean result = false;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, user.getRoleId());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getFirstName());
            pstmt.setString(5, user.getLastName());
            pstmt.setString(6, user.getGender());
            pstmt.executeUpdate();
            con.commit();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getLong(1));
            }
            result = true;

            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t add user";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return result;
    }

    @Override
    public boolean updateUser(User user) throws DBException {
        boolean result = false;
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(UPDATE_USER);

            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getFirstName());
            pstmt.setString(3, user.getLastName());
            pstmt.setString(4, user.getGender());
            pstmt.setBigDecimal(5, user.getBalance());
            pstmt.setBoolean(6, user.isStatus());
            pstmt.setString(7, user.getEmail());
            pstmt.executeUpdate();
            con.commit();

            result = true;

            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t update user";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return result;
    }

    @Override
    public boolean updateUserWithoutBalance(User user) throws DBException {
        boolean result = false;
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(UPDATE_USER_WITHOUT_BALANCE);

            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getFirstName());
            pstmt.setString(3, user.getLastName());
            pstmt.setString(4, user.getGender());
            pstmt.setLong(5, user.getId());
            pstmt.executeUpdate();
            con.commit();

            result = true;

            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t update user without balance";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return result;
    }

    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setRoleId(rs.getInt("role_id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setGender(Gender.valueOf(rs.getString("gender")));
        user.setBalance(rs.getBigDecimal("balance"));
        user.setStatus(rs.getBoolean("user_status"));
        return user;
    }

    private Connection getConnection() throws SQLException {
        return DBManager.getInstance().getConnection();
    }
}
