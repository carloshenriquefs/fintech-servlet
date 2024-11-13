package br.com.fiap.fintech.model.builder.transaction;

import br.com.fiap.fintech.model.Transaction;
import br.com.fiap.fintech.model.User;

import java.time.LocalDate;

public class TransactionBuilder {

    private Long code;
    private String transactionType;
    private String description;
    private Double valueTransaction;
    private LocalDate dataTransacao;
    private User user;

    public TransactionBuilder setCode(Long code) {
        this.code = code;
        return this;
    }

    public TransactionBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public TransactionBuilder setTransactionType(String transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public TransactionBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public TransactionBuilder setValueTransaction(Double valueTransaction) {
        this.valueTransaction = valueTransaction;
        return this;
    }

    public TransactionBuilder setDataTransacao(LocalDate dataTransacao) {
        this.dataTransacao = dataTransacao;
        return this;
    }

    public Transaction build() {
        return new Transaction(user, transactionType, description, valueTransaction, dataTransacao);
    }
}
