package com.dariarahman.dao.impl.mysql;

import com.dariarahman.exceptions.DBException;
import com.dariarahman.dao.DBManager;
import com.dariarahman.dao.PaymentDao;
import com.dariarahman.entity.Payment;
import com.dariarahman.entity.Subscription;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class MySqlPaymentDao implements PaymentDao {

    private static final Logger log = LogManager.getLogger(MySqlPaymentDao.class);

    private static final String INSERT_PAYMENT = "INSERT INTO payments VALUES(DEFAULT, ?, ?, ?, ?)";
    private static final String INSERT_SUBSCRIPTION = "INSERT INTO subscriptions VALUES(DEFAULT, ?, ?, ?, ?, ?, ?)";

    @Override
    public boolean addPayment(Payment payment) throws DBException {
        boolean result = false;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(INSERT_PAYMENT, Statement.RETURN_GENERATED_KEYS);

            pstmt.setLong(1, payment.getUserId());
            pstmt.setLong(2, payment.getSubscriptionId());
            pstmt.setTimestamp(3, payment.getPaymentDateTime());
            pstmt.setBigDecimal(4, payment.getTotalPrice());
            pstmt.executeUpdate();
            con.commit();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                payment.setId(rs.getLong(1));
            }

            result = true;
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t add payment";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return result;
    }

    @Override
    public boolean createPayment(Payment payment, Subscription subscription) throws DBException {
        boolean result = false;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();

            pstmt = con.prepareStatement(INSERT_SUBSCRIPTION, Statement.RETURN_GENERATED_KEYS);
            pstmt.setBoolean(1, subscription.isStatus());
            pstmt.setLong(2, subscription.getUserId());
            pstmt.setLong(3, subscription.getPeriodicalId());
            pstmt.setString(4, subscription.getPeriod().toString());
            pstmt.setTimestamp(5, subscription.getStartDate());
            pstmt.setTimestamp(6, subscription.getEndDate());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                subscription.setId(rs.getLong(1));
            }

            payment.setSubscriptionId(subscription.getId());

            pstmt = con.prepareStatement(INSERT_PAYMENT, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, payment.getUserId());
            pstmt.setLong(2, payment.getSubscriptionId());
            pstmt.setTimestamp(3, payment.getPaymentDateTime());
            pstmt.setBigDecimal(4, payment.getTotalPrice());
            pstmt.executeUpdate();
            if (rs.next()) {
                payment.setId(rs.getLong(1));
            }
            con.commit();

            result = true;
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t create payment";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return result;
    }

    @Override
    public boolean addPaymentBySubscription(Payment payment, Subscription subscription) throws DBException {
        boolean result = false;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(INSERT_PAYMENT, Statement.RETURN_GENERATED_KEYS);

            pstmt.setLong(1, payment.getUserId());
            pstmt.setTimestamp(2, payment.getPaymentDateTime());
            pstmt.setBigDecimal(3, payment.getTotalPrice());
            pstmt.executeUpdate();
            con.commit();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                payment.setId(rs.getLong(1));
            }

            result = true;
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t add payment by subscription";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return result;
    }

    private Connection getConnection() throws SQLException {
        return DBManager.getInstance().getConnection();
    }
}
