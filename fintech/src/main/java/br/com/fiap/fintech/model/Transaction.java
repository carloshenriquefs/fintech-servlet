package br.com.fiap.fintech.model;

import java.time.LocalDate;

public class Transaction {

    private Long code;
    private String transactionType;
    private String description;
    private Double valueTransaction;
    private LocalDate dataTransacao;
    private User user;

    public Transaction(User user, String transactionType, String description, Double valueTransaction, LocalDate dataTransacao) {
        this.user = user;
        this.transactionType = transactionType;
        this.description = description;
        this.valueTransaction = valueTransaction;
        this.dataTransacao = dataTransacao;
    }

    public Transaction(Long code, User user, String transactionType, String description, Double valueTransaction, LocalDate dataTransacao) {
        this.code = code;
        this.user = user;
        this.transactionType = transactionType;
        this.description = description;
        this.valueTransaction = valueTransaction;
        this.dataTransacao = dataTransacao;
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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValueTransaction() {
        return valueTransaction;
    }

    public void setValueTransaction(Double valueTransaction) {
        this.valueTransaction = valueTransaction;
    }

    public LocalDate getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDate dataTransacao) {
        this.dataTransacao = dataTransacao;
    }
}
