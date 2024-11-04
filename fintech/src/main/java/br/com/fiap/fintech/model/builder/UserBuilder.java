package br.com.fiap.fintech.model.builder;

import br.com.fiap.fintech.model.User;

import java.time.LocalDate;

public class UserBuilder {

    private String username;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String gender;
    private String position;
    private LocalDate date;

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public UserBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserBuilder setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public UserBuilder setPosition(String position) {
        this.position = position;
        return this;
    }

    public UserBuilder setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public User build() {
        return new User(username, lastName, email, password, address, phone, gender, position, date);
    }
}
