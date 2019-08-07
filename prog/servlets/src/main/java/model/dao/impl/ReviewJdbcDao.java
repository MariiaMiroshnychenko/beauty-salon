package model.dao.impl;

import model.dao.ReviewDao;
import model.entity.Review;
import model.mapper.impl.ReviewMapper;

import java.sql.*;
import java.util.*;

public class ReviewJdbcDao implements ReviewDao {
    private ReviewMapper reviewMapper = new ReviewMapper();
    private Map<Integer, Review> reviewMap = new HashMap<>();
    private Connection connection;

    public ReviewJdbcDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Review review) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO review (client_id, master_id, record_id, text, date_time) " +
                        "VALUES (?, ?, ?, ?, ?)")) {
            statement.setInt(1, review.getClientId());
            statement.setInt(2, review.getMasterId());
            statement.setInt(3, review.getRecordId());
            statement.setString(4, review.getText());
            statement.setTimestamp(5, Timestamp.valueOf(review.getDateTime()));

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Review review) {

    }

    @Override
    public void delete() {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Review findReviewByRecordId(Integer recordId) {
        Review review = null;

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM review WHERE record_id=?")) {
            statement.setInt(1, recordId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                review = reviewMapper.extractFromResultSet(resultSet);
            }
            if (Objects.nonNull(review)) {
                reviewMapper.makeUnique(reviewMap, review);
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return review;
    }

    @Override
    public List<Review> findReviewsByMasterId(Integer masterId) {
        List<Review> reviews = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM review WHERE master_id=?")) {
            statement.setInt(1, masterId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Review review = reviewMapper.extractFromResultSet(resultSet);
                reviewMapper.makeUnique(reviewMap, review);
            }

            resultSet.close();

            reviews = new ArrayList<>(reviewMap.values());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }
}
