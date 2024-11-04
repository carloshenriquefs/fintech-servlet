package br.com.fiap.fintech.model;

import java.time.LocalDate;

public class User {

    private Long userId;
    private String username;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String gender;
    private String position;
    private LocalDate dateBirth;

    public User() {
    }

    public User(String username, String lastName, String email, String password, String address, String phone,
                String gender, String position, LocalDate dateBirth) {
        this.username = username;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.position = position;
        this.dateBirth = dateBirth;
    }

    public User(Long userId, String username, String lastName, String email, String password, String address,
                String phone, String gender, String position, LocalDate dateBirth) {
        this.userId = userId;
        this.username = username;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.position = position;
        this.dateBirth = dateBirth;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }
}
