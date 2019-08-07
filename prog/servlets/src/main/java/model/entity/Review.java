package model.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Review {
    private Integer id;
    private Integer clientId;
    private Integer masterId;
    private Integer recordId;
    private String text;
    private LocalDateTime dateTime;

    private User client;
    private User master;
    private Record record;

    public Review(Integer id, Integer clientId, Integer masterId,
                  Integer recordId, String text,
                  LocalDateTime dateTime, User client, User master, Record record) {
        this.id = id;
        this.clientId = clientId;
        this.masterId = masterId;
        this.recordId = recordId;
        this.text = text;
        this.dateTime = dateTime;
        this.client = client;
        this.master = master;
        this.record = record;
    }

    public Review(Integer id, Integer clientId, Integer masterId,
                  Integer recordId, String text, LocalDateTime dateTime) {
        this.id = id;
        this.clientId = clientId;
        this.masterId = masterId;
        this.recordId = recordId;
        this.text = text;
        this.dateTime = dateTime;
    }

    public Review(Integer clientId, Integer masterId, Integer recordId,
                  String text, LocalDateTime dateTime) {
        this.clientId = clientId;
        this.masterId = masterId;
        this.recordId = recordId;
        this.text = text;
        this.dateTime = dateTime;
    }

    public Review() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id) &&
                Objects.equals(clientId, review.clientId) &&
                Objects.equals(masterId, review.masterId) &&
                Objects.equals(recordId, review.recordId) &&
                Objects.equals(text, review.text) &&
                Objects.equals(dateTime, review.dateTime) &&
                Objects.equals(client, review.client)&&
                Objects.equals(master, review.master)&&
                Objects.equals(record, review.record);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, masterId, recordId,
                            text, dateTime, client, master, record);
    }
}
