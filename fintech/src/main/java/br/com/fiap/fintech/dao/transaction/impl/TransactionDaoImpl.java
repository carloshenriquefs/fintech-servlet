package br.com.fiap.fintech.dao.transaction.impl;

import br.com.fiap.fintech.dao.DaoAdapter;
import br.com.fiap.fintech.exceptions.FintechException;
import br.com.fiap.fintech.factory.ConnectionManager;
import br.com.fiap.fintech.model.Transaction;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static br.com.fiap.fintech.constants.Constants.ERROR_DELETING_TRANSACTIONS;
import static br.com.fiap.fintech.constants.Constants.ERROR_LISTING_TRANSACTIONS;
import static br.com.fiap.fintech.constants.Constants.ERROR_LOOKING_UP_TRANSACTION_ID;
import static br.com.fiap.fintech.constants.Constants.ERROR_REGISTERING_TRANSACTION;
import static br.com.fiap.fintech.constants.Constants.TRANSACTIONS_NOT_FOUND;
import static br.com.fiap.fintech.exceptions.ErrorTypeEnum.ERROR_DELETING_DATA;
import static br.com.fiap.fintech.exceptions.ErrorTypeEnum.ERROR_INSERTING_DATA;
import static br.com.fiap.fintech.exceptions.ErrorTypeEnum.ERROR_SEARCHING_DATA;
import static br.com.fiap.fintech.exceptions.ErrorTypeEnum.ERROR_UPDATING_DATA;
import static br.com.fiap.fintech.factory.DaoFactory.parseTransaction;

public class TransactionDaoImpl implements DaoAdapter<Transaction> {

    private Connection connection;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    private List<Transaction> lista = new ArrayList<>();

    public TransactionDaoImpl() throws SQLException {
        connection = ConnectionManager.getInstance().getConnection();
    }

    @Override
    public void insert(Transaction transaction) throws FintechException {
        try {

            String transactionType = transaction.getTransactionType();

            if (!transactionType.equals("expenses") && !transactionType.equals("income")) {
                throw new IllegalArgumentException("Tipo de transação invalida: " + transactionType);
            }

            String sql =
                    "INSERT INTO tb_fth_transactions (" +
                            "cd_transactions, " +
                            "cd_user, " +
                            "ds_transaction_type, " +
                            "ds_description, " +
                            "vl_transaction, " +
                            "dt_transaction) " +
                            "VALUES (seq_transaction.nextval, ?, ?, ?, ?, ?)";

            stmt = connection.prepareStatement(sql);

            stmt.setString(1, transaction.getTransactionType());
            stmt.setString(2, transaction.getDescription());
            stmt.setDouble(3, transaction.getValueTransaction());
            stmt.setDate(4, Date.valueOf(transaction.getDataTransacao()));
            stmt.setLong(5, transaction.getUser().getUserId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(ERROR_REGISTERING_TRANSACTION + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_INSERTING_DATA);
        }
    }

    @Override
    public Transaction getById(Long id) {
        try {
            String sql = "SELECT * FROM tb_fth_transactions WHERE cd_transactions = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if (!rs.next())
                throw new FintechException(TRANSACTIONS_NOT_FOUND + id, null, ERROR_SEARCHING_DATA);

            return parseTransaction(rs);

        } catch (SQLException e) {
            System.err.println(ERROR_LOOKING_UP_TRANSACTION_ID + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_SEARCHING_DATA);
        }
    }

    @Override
    public List<Transaction> getAll() {
        try {
            String sql = "SELECT * FROM tb_fth_transactions";

            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(parseTransaction(rs));
            }

            return lista;

        } catch (SQLException e) {
            System.err.println(ERROR_LISTING_TRANSACTIONS + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_SEARCHING_DATA);
        }
    }

    @Override
    public void update(Transaction transaction) throws FintechException {
        try {

            String sql =
                    "UPDATE tb_fth_transactions SET" +
                            "ds_transaction = ?," +
                            "ds_description = ?," +
                            "vl_transaction = ? " +
                            "dt_transaction = ?" +
                            "WHERE cd_transactions = ?";

            stmt = connection.prepareStatement(sql);

            stmt.setString(1, transaction.getTransactionType());
            stmt.setString(2, transaction.getDescription());
            stmt.setDouble(3, transaction.getValueTransaction());
            stmt.setDate(4, Date.valueOf(transaction.getDataTransacao()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(ERROR_UPDATING_DATA + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_UPDATING_DATA);
        }
    }

    @Override
    public void delete(Long id) throws FintechException {

        try {
            String sql = "DELETE FROM tb_fth_transactions WHERE cd_transactions = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(ERROR_DELETING_TRANSACTIONS + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_DELETING_DATA);
        }
    }

}
