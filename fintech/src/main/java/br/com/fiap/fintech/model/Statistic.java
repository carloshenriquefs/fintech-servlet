package br.com.fiap.fintech.model;

public class Statistic {

    private Long code;
    private Integer month;
    private Integer year;
    private Double budge;
    private Double cost;
    private Double economy;
    private User user;

    public Statistic(Long code, User user, Integer month, Integer year, Double budge, Double cost, Double economy) {
        this.code = code;
        this.user= user;
        this.month = month;
        this.year = year;
        this.budge = budge;
        this.cost = cost;
        this.economy = economy;
    }

    public Statistic(User user, Integer month, Integer year, Double budge, Double cost, Double economy) {
        this.user = user;
        this.month = month;
        this.year = year;
        this.budge = budge;
        this.cost = cost;
        this.economy = economy;
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

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getBudge() {
        return budge;
    }

    public void setBudge(Double budge) {
        this.budge = budge;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getEconomy() {
        return economy;
    }

    public void setEconomy(Double economy) {
        this.economy = economy;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "code=" + code +
                ", month=" + month +
                ", year=" + year +
                ", budge=" + budge +
                ", cost=" + cost +
                ", economy=" + economy +
                ", user=" + user +
                '}';
    }
}
