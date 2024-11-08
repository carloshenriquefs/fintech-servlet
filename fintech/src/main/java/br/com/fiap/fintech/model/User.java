package br.com.fiap.fintech.model;

import java.time.LocalDate;

import static br.com.fiap.fintech.utils.CriptografiaUtils.criptografar;

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
    private Note note;

    public User() {
    }

    public User(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public User(String email, String password) {
        this.email = email;
        setPassword(password);
    }

    public User(String username, String lastName, String email, String password, String address, String phone, String gender, String position, LocalDate dateBirth, Note note) {
        this.username = username;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.position = position;
        this.dateBirth = dateBirth;
        this.note = note;
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
                String phone, String gender, String position, LocalDate dateBirth, Note note) {
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
        this.note = note;
    }

    public User(Long userId, String username, String lastName, String email, String password, String address, String phone, String gender, String position, LocalDate dateBirth) {
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
        try {
            this.password = criptografar(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}
