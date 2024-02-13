package com.dariarahman.dao.impl.mysql;

import com.dariarahman.exceptions.DBException;
import com.dariarahman.dao.DBManager;
import com.dariarahman.dao.SubscriptionDao;
import com.dariarahman.entity.Subscription;
import com.dariarahman.entity.SubscriptionInfo;
import com.dariarahman.entity.SubscriptionPeriod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MySqlSubscriptionDao implements SubscriptionDao {

    private static final Logger log = LogManager.getLogger(MySqlSubscriptionDao.class);

    private static final String SQL_FIND_SUBSCRIPTIONS_BY_USER_ID = "SELECT * FROM subscriptions WHERE user_id=? AND subscription_status=true";
    private static final String SQL_FIND_SUBSCRIPTION = "SELECT * FROM subscriptions WHERE user_id=? AND periodical_id=? AND subscription_status=true";
    private static final String SQL_INSERT_SUBSCRIPTION = "INSERT INTO subscriptions VALUES(DEFAULT, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_SUBSCRIPTIONS_INFO =
            "SELECT periodicals.periodical_name, periodicals.periodical_type, periodicals.frequency, subscriptions.start_date, subscriptions.end_date " +
                    "FROM subscriptions JOIN periodicals ON periodicals.periodical_id = subscriptions.periodical_id " +
                    "WHERE subscriptions.user_id = ?";

    @Override
    public boolean addSubscription(Subscription subscription) throws DBException {
        boolean result = false;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_INSERT_SUBSCRIPTION, Statement.RETURN_GENERATED_KEYS);

            pstmt.setBoolean(1, subscription.isStatus());
            pstmt.setLong(2, subscription.getUserId());
            pstmt.setLong(3, subscription.getPeriodicalId());
            pstmt.setString(4, subscription.getPeriod().getPeriodDescription());
            pstmt.setTimestamp(5, subscription.getStartDate());
            pstmt.setTimestamp(6, subscription.getEndDate());
            pstmt.executeUpdate();
            con.commit();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                subscription.setId(rs.getLong(1));
            }
            result = true;

            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t add subscription";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return result;
    }

    @Override
    public List<Subscription> findSubscriptionsByUserId(long userId) throws DBException {
        List<Subscription> subscriptions = new CopyOnWriteArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_SUBSCRIPTIONS_BY_USER_ID);
            pstmt.setLong(1, userId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                subscriptions.add(extractSubscription(rs));
            }

            con.commit();
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t find subscription bu user id";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return subscriptions;
    }

    @Override
    public boolean isSubscribed(long userId, long periodicalId) throws DBException {
        boolean result = false;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_SUBSCRIPTION);
            pstmt.setLong(1, userId);
            pstmt.setLong(2, periodicalId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                result = true;
            }

            con.commit();
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t find subscribed...";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return result;
    }

    public List<SubscriptionInfo> getSubscriptionsInfo(long userId) throws DBException {
        List<SubscriptionInfo> subscriptionsInfo = new CopyOnWriteArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_SELECT_SUBSCRIPTIONS_INFO);
            pstmt.setLong(1, userId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                subscriptionsInfo.add(extractSubscriptionsInfo(rs));
            }

            con.commit();
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t get subscriptions info";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return subscriptionsInfo;
    }


    private Subscription extractSubscription(ResultSet rs) throws SQLException {
        Subscription subscription = new Subscription();
        subscription.setId(rs.getLong("subscription_id"));
        subscription.setStatus(rs.getBoolean("subscription_status"));
        subscription.setUserId(rs.getLong("user_id"));
        subscription.setPeriodicalId(rs.getLong("periodical_id"));
        subscription.setPeriod(SubscriptionPeriod.valueOf(rs.getString("subscription_period")));
        subscription.setStartDate(rs.getTimestamp("start_date"));
        subscription.setEndDate(rs.getTimestamp("end_date"));
        return subscription;
    }

    private SubscriptionInfo extractSubscriptionsInfo(ResultSet rs) throws SQLException {
        SubscriptionInfo subscriptionInfo = new SubscriptionInfo();
        subscriptionInfo.setPeriodicalName(rs.getString("periodical_name"));
        subscriptionInfo.setPeriodicalType(rs.getString("periodical_type"));
        subscriptionInfo.setFrequency(rs.getString("frequency"));
        subscriptionInfo.setStartDate(new SimpleDateFormat("yyyy-MM-dd").format(rs.getTimestamp("start_date")));
        subscriptionInfo.setEndDate(new SimpleDateFormat("yyyy-MM-dd").format(rs.getTimestamp("end_date")));
        return subscriptionInfo;
    }

    private Connection getConnection() throws SQLException {
        return DBManager.getInstance().getConnection();
    }
}
