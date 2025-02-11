package com.hyn.pojo;

import java.time.LocalDate;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private LocalDate register_time;
    private String description;
    private String sex;
    public User() {
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public User(int id, String username, String password, String email, LocalDate register_time, String description, String sex) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.register_time = register_time;
        this.description = description;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", register_time=" + register_time +
                ", description='" + description + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegister_time() {
        return register_time;
    }

    public void setRegister_time(LocalDate register_time) {
        this.register_time = register_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
