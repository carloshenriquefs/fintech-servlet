package br.com.fiap.fintech.dao.account.impl;

import br.com.fiap.fintech.dao.DaoAdapter;
import br.com.fiap.fintech.exceptions.FintechException;
import br.com.fiap.fintech.factory.ConnectionManager;
import br.com.fiap.fintech.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static br.com.fiap.fintech.constants.Constants.ERROR_DELETING_ACCOUNT;
import static br.com.fiap.fintech.constants.Constants.ERROR_LISTING_ACCOUNT;
import static br.com.fiap.fintech.constants.Constants.ERROR_LOOKING_UP_ACCOUNT_ID;
import static br.com.fiap.fintech.constants.Constants.ERROR_REGISTERING_ACCOUNT;
import static br.com.fiap.fintech.constants.Constants.ID_ACCOUNT_NOT_FOUND;
import static br.com.fiap.fintech.exceptions.ErrorTypeEnum.ERROR_DELETING_DATA;
import static br.com.fiap.fintech.exceptions.ErrorTypeEnum.ERROR_INSERTING_DATA;
import static br.com.fiap.fintech.exceptions.ErrorTypeEnum.ERROR_SEARCHING_DATA;
import static br.com.fiap.fintech.exceptions.ErrorTypeEnum.ERROR_UPDATING_DATA;
import static br.com.fiap.fintech.factory.DaoFactory.parseAccount;

public class AccountDaoImpl implements DaoAdapter<Account> {

    private Connection connection;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    private List<Account> lista = new ArrayList<Account>();
    private Account account;

    public AccountDaoImpl() {
        connection = ConnectionManager.getInstance().getConnection();
    }

    private void checkConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            this.connection = ConnectionManager.getInstance().getConnection();
        }
    }

    @Override
    public void insert(Account account) throws FintechException {

        try {

            String sql =
                    "INSERT INTO tb_fth_accounts (" +
                            "cd_account, " +
                            "ds_account, " +
                            "vl_balance, " +
                            "cd_user) " +
                            "VALUES " +
                            "(seq_account.nextval, ?, ?, ?, ?)";

            stmt = connection.prepareStatement(sql);

            stmt.setString(1, account.getAccountNumber());
            stmt.setDouble(2, account.getBalance());
            stmt.setLong(3, account.getUser().getUserId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(ERROR_REGISTERING_ACCOUNT + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_INSERTING_DATA);
        }
    }

    @Override
    public Account getById(Long id) {

        try {
            String sql =
                    "SELECT * FROM tb_fth_accounts " +
                            "INNER JOIN tb_fth_user " +
                            "ON tb_fth_account.cd_account = tb_fth_user.cd_user " +
                            "WHERE tb_fth_user.cd_account = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return parseAccount(rs, account);

            } else {
                throw new FintechException(ID_ACCOUNT_NOT_FOUND + id);
            }

        } catch (SQLException e) {
            System.err.println(ERROR_LOOKING_UP_ACCOUNT_ID + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_SEARCHING_DATA);
        }
    }

    @Override
    public List<Account> getAll() {
        try {
            String sql =
                    "SELECT * FROM tb_fth_accounts " +
                            "INNER JOIN tb_fth_user " +
                            "ON tb_fth_accounts.cd_account = tb_fth_user.cd_user";

            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(parseAccount(rs, account));
            }

        } catch (SQLException e) {
            System.err.println(ERROR_LISTING_ACCOUNT + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_SEARCHING_DATA);
        }

        return lista;
    }

    @Override
    public void update(Account account) throws FintechException {

        try {

            String sql =
                    "UPDATE tb_fth_accounts SET" +
                            "ds_account = ?," +
                            "vl_balance = ?," +
                            "cd_user = ? " +
                            "WHERE cd_account = ?";

            stmt = connection.prepareStatement(sql);

            stmt.setString(1, account.getAccountNumber());
            stmt.setDouble(2, account.getBalance());
            stmt.setLong(3, account.getUser().getUserId());
            stmt.setLong(4, account.getId());

            stmt.executeUpdate();


        } catch (SQLException e) {
            System.err.println(ERROR_UPDATING_DATA + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_UPDATING_DATA);
        }
    }

    @Override
    public void delete(Long id) throws FintechException {

        try {
            String sql = "DELETE FROM tb_fth_account WHERE cd_account = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(ERROR_DELETING_ACCOUNT + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_DELETING_DATA);
        }

    }

}
