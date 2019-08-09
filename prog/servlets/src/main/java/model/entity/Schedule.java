package model.entity;

import java.util.Objects;

public class Schedule {
    private Integer id;
    private Integer masterId;
    private Boolean sunday;
    private Boolean monday;
    private Boolean tuesday;
    private Boolean wednesday;
    private Boolean thursday;
    private Boolean friday;
    private Boolean saturday;

    private User master;

    public Schedule(Integer id, Integer masterId, Boolean sunday, Boolean monday, Boolean tuesday,
                    Boolean wednesday, Boolean thursday, Boolean friday, Boolean saturday, User master) {
        this.id = id;
        this.masterId = masterId;
        this.sunday = sunday;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.master = master;
    }

    public Schedule(Integer id, Integer masterId, Boolean sunday, Boolean monday, Boolean tuesday,
                    Boolean wednesday, Boolean thursday, Boolean friday, Boolean saturday) {
        this.id = id;
        this.masterId = masterId;
        this.sunday = sunday;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
    }

    public Schedule() {
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

    public Boolean getSunday() {
        return sunday;
    }

    public void setSunday(Boolean sunday) {
        this.sunday = sunday;
    }

    public Boolean getMonday() {
        return monday;
    }

    public void setMonday(Boolean monday) {
        this.monday = monday;
    }

    public Boolean getTuesday() {
        return tuesday;
    }

    public void setTuesday(Boolean tuesday) {
        this.tuesday = tuesday;
    }

    public Boolean getWednesday() {
        return wednesday;
    }

    public void setWednesday(Boolean wednesday) {
        this.wednesday = wednesday;
    }

    public Boolean getThursday() {
        return thursday;
    }

    public void setThursday(Boolean thursday) {
        this.thursday = thursday;
    }

    public Boolean getFriday() {
        return friday;
    }

    public void setFriday(Boolean friday) {
        this.friday = friday;
    }

    public Boolean getSaturday() {
        return saturday;
    }

    public void setSaturday(Boolean saturday) {
        this.saturday = saturday;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(id, schedule.id) &&
                Objects.equals(masterId, schedule.masterId) &&
                Objects.equals(sunday, schedule.sunday) &&
                Objects.equals(monday, schedule.monday) &&
                Objects.equals(tuesday, schedule.tuesday) &&
                Objects.equals(wednesday, schedule.wednesday) &&
                Objects.equals(thursday, schedule.thursday) &&
                Objects.equals(friday, schedule.friday) &&
                Objects.equals(saturday, schedule.saturday) &&
                Objects.equals(master, schedule.master);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, masterId, sunday, monday, tuesday, wednesday, thursday, friday, saturday, master);
    }
}
