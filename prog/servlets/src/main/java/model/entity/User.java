package model.entity;

import java.util.List;
import java.util.Objects;

public class User {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String role;
    private String username;
    private String password;

    private Schedule schedule;
    private List<Record> records;
    private List<Review> reviews;

    public User(Integer id, String name, String surname, String email,
                String role, String username, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public User(String name, String surname, String email, String role, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(role, user.role) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(schedule, user.schedule) &&
                Objects.equals(records, user.records) &&
                Objects.equals(reviews, user.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, role, username, password, schedule, records, reviews);
    }
}
