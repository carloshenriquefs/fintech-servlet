package br.com.fiap.fintech.dao.card.impl;

import br.com.fiap.fintech.dao.DaoAdapter;
import br.com.fiap.fintech.exceptions.FintechException;
import br.com.fiap.fintech.factory.ConnectionManager;
import br.com.fiap.fintech.model.Card;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static br.com.fiap.fintech.constants.Constants.CARDS_NOT_FOUND;
import static br.com.fiap.fintech.constants.Constants.ERROR_LISTING_CARDS;
import static br.com.fiap.fintech.constants.Constants.ERROR_LOOKING_UP_CARD_ID;
import static br.com.fiap.fintech.constants.Constants.ERROR_REGISTERING_CARD;
import static br.com.fiap.fintech.constants.Constants.ERROR_REGISTERING_USER;
import static br.com.fiap.fintech.exceptions.ErrorTypeEnum.ERROR_DELETING_DATA;
import static br.com.fiap.fintech.exceptions.ErrorTypeEnum.ERROR_INSERTING_DATA;
import static br.com.fiap.fintech.exceptions.ErrorTypeEnum.ERROR_UPDATING_DATA;
import static br.com.fiap.fintech.exceptions.ErrorTypeEnum.ERROR_SEARCHING_DATA;
import static br.com.fiap.fintech.factory.DaoFactory.parseCard;

public class CardDaoImpl implements DaoAdapter<Card> {

    private Connection connection;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    private List<Card> lista = new ArrayList<>();

    public CardDaoImpl() throws SQLException {
        connection = ConnectionManager.getInstance().getConnection();
    }

    private void checkConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            this.connection = ConnectionManager.getInstance().getConnection();
        }
    }

    @Override
    public void insert(Card card) throws FintechException {
        try {
            String sql =
                    "INSERT INTO tb_fth_cards (" +
                            "cd_card, " +
                            "cd_user, " +
                            "nm_card, " +
                            "nm_flag, " +
                            "dt_validate, " +
                            "vl_balance) " +
                            "VALUES (seq_card.nextval, ?, ?, ?, ?, ?)";

            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, card.getUser().getUserId());
            stmt.setString(2, card.getNumberCard());
            stmt.setString(3, card.getFlag());
            stmt.setDate(4, Date.valueOf(card.getValidate()));
            stmt.setDouble(5, card.getBalance());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(ERROR_REGISTERING_CARD + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_INSERTING_DATA);
        }
    }

    @Override
    public Card getById(Long id) {
        try {
            String sql = "SELECT * FROM tb_fth_cards WHERE cd_card = ?";

            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if (!rs.next())
                throw new FintechException(CARDS_NOT_FOUND + id, null, ERROR_SEARCHING_DATA);

            return parseCard(rs);

        } catch (SQLException e) {
            System.err.println(ERROR_LOOKING_UP_CARD_ID + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_SEARCHING_DATA);
        }
    }

    @Override
    public List<Card> getAll() {
        try {
            String sql = "SELECT * FROM tb_fth_cards";

            stmt = connection.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(parseCard(rs));
            }

            return lista;

        } catch (SQLException e) {
            System.err.println(ERROR_LISTING_CARDS + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_SEARCHING_DATA);
        }
    }

    @Override
    public void update(Card card) throws FintechException {

        try {

            String sql =
                    "UPDATE tb_fth_cards SET" +
                            "nm_card = ?," +
                            "nm_flag = ?," +
                            "dt_validate = ?," +
                            "vl_balance = ? " +
                            "cd_user = ? " +
                            "WHERE cd_card = ?";

            stmt = connection.prepareStatement(sql);

            stmt.setString(1, card.getNumberCard());
            stmt.setString(2, card.getFlag());
            stmt.setDate(3, Date.valueOf(card.getValidate()));
            stmt.setDouble(4, card.getBalance());
            stmt.setLong(5, card.getUser().getUserId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(ERROR_UPDATING_DATA + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_UPDATING_DATA);
        }
    }

    @Override
    public void delete(Long id) throws FintechException {
        try {
            String sql = "DELETE FROM tb_fth_cards WHERE cd_card = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(ERROR_REGISTERING_USER + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_DELETING_DATA);
        }
    }

}
