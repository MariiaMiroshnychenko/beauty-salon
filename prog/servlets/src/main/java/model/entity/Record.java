package model.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Record {
    private Integer id;
    private Integer masterId;
    private Integer procedureId;
    private LocalDate recordDate;
    private LocalTime time;
    private Integer clientId;

    private User master;
    private Procedure procedure;
    private User client;
    private Feedback feedback;

    public Record(Integer id, Integer masterId, Integer procedureId,
                  LocalDate recordDate, LocalTime time, Integer clientId,
                  User master, Procedure procedure, User client, Feedback feedback) {
        this.id = id;
        this.masterId = masterId;
        this.procedureId = procedureId;
        this.recordDate = recordDate;
        this.time = time;
        this.clientId = clientId;
        this.master = master;
        this.procedure = procedure;
        this.client = client;
        this.feedback = feedback;
    }

    public Record(Integer id, Integer masterId, Integer procedureId,
                  LocalDate recordDate, LocalTime time, Integer clientId) {
        this.id = id;
        this.masterId = masterId;
        this.procedureId = procedureId;
        this.recordDate = recordDate;
        this.time = time;
        this.clientId = clientId;
    }

    public Record(Integer masterId, Integer procedureId, LocalDate recordDate, LocalTime time, Integer clientId) {
        this.masterId = masterId;
        this.procedureId = procedureId;
        this.recordDate = recordDate;
        this.time = time;
        this.clientId = clientId;
    }

    public Record() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public Integer getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(Integer procedureId) {
        this.procedureId = procedureId;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(id, record.id) &&
                Objects.equals(masterId, record.masterId) &&
                Objects.equals(procedureId, record.procedureId) &&
                Objects.equals(recordDate, record.recordDate) &&
                Objects.equals(time, record.time) &&
                Objects.equals(clientId, record.clientId) &&
                Objects.equals(master, record.master) &&
                Objects.equals(procedure, record.procedure) &&
                Objects.equals(client, record.client)&&
                Objects.equals(feedback, record.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, masterId, procedureId, recordDate, time, clientId, master, procedure, client, feedback);
    }
}
