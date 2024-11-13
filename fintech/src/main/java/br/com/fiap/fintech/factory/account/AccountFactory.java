package br.com.fiap.fintech.factory.account;

import br.com.fiap.fintech.dao.DaoAdapter;
import br.com.fiap.fintech.dao.account.impl.AccountDaoImpl;
import br.com.fiap.fintech.factory.user.UserFactory;
import br.com.fiap.fintech.model.Account;
import br.com.fiap.fintech.model.builder.account.AccountBuilder;

public class AccountFactory {

    public static DaoAdapter<Account> getAccountDao() {
        return new AccountDaoImpl();
    }

    public static Account createAccount() {
        return new AccountBuilder()
                .setUser(UserFactory.createUser())
                .setAccountNumber("133")
                .setBalance(2400.0)
                .build();
    }
}
