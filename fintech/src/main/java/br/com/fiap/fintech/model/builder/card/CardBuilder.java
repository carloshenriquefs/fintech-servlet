package br.com.fiap.fintech.model.builder.card;

import br.com.fiap.fintech.model.Card;
import br.com.fiap.fintech.model.User;

import java.time.LocalDate;

public class CardBuilder {
    private Long code;
    private String numberCard;
    private String flag;
    private LocalDate validate;
    private Double balance;
    private User user;

    public CardBuilder setCode(Long code) {
        this.code = code;
        return this;
    }

    public CardBuilder setNumberCard(String numberCard) {
        this.numberCard = numberCard;
        return this;
    }

    public CardBuilder setFlag(String flag) {
        this.flag = flag;
        return this;
    }

    public CardBuilder setValidate(LocalDate validate) {
        this.validate = validate;
        return this;
    }

    public CardBuilder setBalance(Double balance) {
        this.balance = balance;
        return this;
    }

    public CardBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public Card build() {
        return new Card(user, numberCard, flag, validate, balance);
    }
}
