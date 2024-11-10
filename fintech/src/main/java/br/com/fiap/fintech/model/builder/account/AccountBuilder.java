package br.com.fiap.fintech.model.builder.account;

import br.com.fiap.fintech.model.Account;
import br.com.fiap.fintech.model.User;

public class AccountBuilder {

    private Long code;
    private String accountNumber;
    private Double balance;
    private User user;

    public void setCode(Long code) {
        this.code = code;
    }

    public AccountBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public AccountBuilder setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public AccountBuilder setBalance(Double balance) {
        this.balance = balance;
        return this;
    }

    public Account build() {
        return new Account(code, accountNumber, balance, user);
    }
}
