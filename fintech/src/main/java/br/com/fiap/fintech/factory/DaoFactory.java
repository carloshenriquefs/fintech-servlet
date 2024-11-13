package br.com.fiap.fintech.factory;

import br.com.fiap.fintech.model.Account;
import br.com.fiap.fintech.model.Card;
import br.com.fiap.fintech.model.Note;
import br.com.fiap.fintech.model.Statistic;
import br.com.fiap.fintech.model.Transaction;
import br.com.fiap.fintech.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DaoFactory {

    public static Card parseCard(ResultSet result) throws SQLException {
        Long id = result.getLong("cd_user");
        String card = result.getString("nm_card");
        String flag = result.getString("nm_flag");
        LocalDate validate = result.getDate("dt_validate").toLocalDate();
        Double balance = result.getDouble("vl_balance");

        User user = parseUser(result);

        return new Card(user, card, flag, validate, balance);
    }

    public static Account parseAccount(ResultSet result, Account account) throws SQLException {
        Long accountId = result.getLong("cd_account");
        String accountNumber = result.getString("ds_account");
        Double balance = result.getDouble("vl_balance");

        Long userId = result.getLong("cd_user");
        String firstName = result.getString("nm_first_user");

        account = new Account(accountId, accountNumber, balance);

        User user = new User(userId, firstName);

        account.setUser(user);

        return new Account(accountId, accountNumber, balance);
    }

    public static Note parseNotes(ResultSet result) throws SQLException {
        Long id = result.getLong("cd_note");
        String title = result.getString("ds_title");
        String comentary = result.getString("ds_comentary");
        LocalDate budge = result.getDate("dt_note").toLocalDate();

        return new Note(id, title, comentary, budge);
    }

    public static Statistic parseStatistics(ResultSet result) throws SQLException {
        Long id = result.getLong("cd_statistics");
        Integer month = result.getInt("dt_month");
        Integer year = result.getInt("dt_year");
        Double budge = result.getDouble("vl_budge");
        Double cost = result.getDouble("vl_cost");
        Double economy = result.getDouble("vl_economy");

        User user = parseUser(result);

        return new Statistic(user, month, year, budge, cost, economy);
    }

    public static Transaction parseTransaction(ResultSet result) throws SQLException {
        Long id = result.getLong("cd_transactions");
        String transactionType = result.getString("ds_transaction_type");
        String description = result.getString("ds_description");
        Double transactionValue = result.getDouble("vl_transaction");
        LocalDate transactionDate = result.getDate("dt_transaction").toLocalDate();

        User user = parseUser(result);

        return new Transaction(user, transactionType, description, transactionValue, transactionDate);
    }

    private static User parseUser(ResultSet result) throws SQLException {
        Long id = result.getLong("cd_user");
        String username = result.getString("nm_first_user");
        String lastname = result.getString("nm_last_user");
        String email = result.getString("ds_email");
        String password = result.getString("cd_password");
        String address = result.getString("ds_address");
        String phone = result.getString("nr_telephone");
        String gender = result.getString("ds_gender");
        String position = result.getString("ds_position");
        LocalDate date = result.getDate("dt_registration").toLocalDate();

        return new User(id, username, lastname, email, password, address, phone, gender, position, date);
    }
}
