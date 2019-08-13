package container;

public interface Query {
    String FUTURE_RECORDS = "select * from record_table where client_id=? and record_date > ? or record_date = ? and `time` > ?";
    String PAST_RECORDS = "select * from record_table where client_id=? and record_date < ? or record_date = ? and `time` < ?";
}
