package br.com.fiap.fintech.model.builder.statistic;

import br.com.fiap.fintech.model.Statistic;
import br.com.fiap.fintech.model.User;

public class StatisticBuilder {

    private Long code;
    private Integer month;
    private Integer year;
    private Double budge;
    private Double cost;
    private Double economy;
    private User user;

    public StatisticBuilder setCode(Long code) {
        this.code = code;
        return this;
    }

    public StatisticBuilder setMonth(Integer month) {
        this.month = month;
        return this;
    }

    public StatisticBuilder setYear(Integer year) {
        this.year = year;
        return this;
    }

    public StatisticBuilder setBudge(Double budge) {
        this.budge = budge;
        return this;
    }

    public StatisticBuilder setCost(Double cost) {
        this.cost = cost;
        return this;
    }

    public StatisticBuilder setEconomy(Double economy) {
        this.economy = economy;
        return this;
    }

    public StatisticBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public Statistic build() {
        return new Statistic(user, month, year, budge, cost, economy);
    }
}
