package model.dao.impl;

import model.dao.FeedbackDao;
import model.entity.Feedback;
import model.mapper.impl.FeedbackMapper;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

public class FeedbackJdbcDao implements FeedbackDao {
    final static Logger LOGGER = Logger.getLogger(FeedbackJdbcDao.class);

    private FeedbackMapper feedbackMapper = new FeedbackMapper();
    private Map<Integer, Feedback> reviewMap = new HashMap<>();
    private Connection connection;

    public FeedbackJdbcDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Feedback feedback) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO feedback_table(client_id, master_id, record_id, text, date_time) " +
                        "VALUES (?, ?, ?, ?, ?)")) {
            statement.setInt(1, feedback.getClientId());
            statement.setInt(2, feedback.getMasterId());
            statement.setInt(3, feedback.getRecordId());
            statement.setString(4, feedback.getText());
            statement.setTimestamp(5, Timestamp.valueOf(feedback.getDateTime()));

            statement.execute();

        } catch (SQLException e) {
            LOGGER.error("User can`t leave feedback");
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            LOGGER.debug("Connection closed");
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Can't close connection!");
            e.printStackTrace();
        }
    }

    @Override
    public Feedback findReviewByRecordId(Integer recordId) {
        Feedback feedback = null;

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM feedback_table WHERE record_id=?")) {
            statement.setInt(1, recordId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                feedback = feedbackMapper.extractFromResultSet(resultSet);
            }
            if (Objects.nonNull(feedback)) {
                feedbackMapper.makeUnique(reviewMap, feedback);
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedback;
    }

    @Override
    public List<Feedback> findReviewsByMasterId(Integer masterId) {
        List<Feedback> feedbacks = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM feedback_table WHERE master_id=?")) {
            statement.setInt(1, masterId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Feedback feedback = feedbackMapper.extractFromResultSet(resultSet);
                feedbackMapper.makeUnique(reviewMap, feedback);
            }

            resultSet.close();

            feedbacks = new ArrayList<>(reviewMap.values());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbacks;
    }
}
