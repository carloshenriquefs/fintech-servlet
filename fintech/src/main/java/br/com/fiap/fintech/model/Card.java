package br.com.fiap.fintech.model;

import java.time.LocalDate;

public class Card {

    private Long code;
    private String numberCard;
    private String flag;
    private LocalDate validate;
    private Double balance;
    private User user;

    public Card(User user, String numberCard, String flag, LocalDate validate, Double balance) {
        this.user = user;
        this.numberCard = numberCard;
        this.flag = flag;
        this.validate = validate;
        this.balance = balance;
    }

    public Card(Long code, User user, String numberCard, String flag, LocalDate validate, Double balance) {
        this.code = code;
        this.user = user;
        this.numberCard = numberCard;
        this.flag = flag;
        this.validate = validate;
        this.balance = balance;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public LocalDate getValidate() {
        return validate;
    }

    public void setValidate(LocalDate validate) {
        this.validate = validate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Card{" +
                "code=" + code +
                ", numberCard='" + numberCard + '\'' +
                ", flag='" + flag + '\'' +
                ", validate=" + validate +
                ", balance=" + balance +
                ", user=" + user +
                '}';
    }
}
