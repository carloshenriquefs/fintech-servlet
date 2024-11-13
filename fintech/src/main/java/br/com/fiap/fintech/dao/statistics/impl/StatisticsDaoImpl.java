package br.com.fiap.fintech.dao.statistics.impl;

import br.com.fiap.fintech.dao.DaoAdapter;
import br.com.fiap.fintech.exceptions.FintechException;
import br.com.fiap.fintech.factory.ConnectionManager;
import br.com.fiap.fintech.model.Statistic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static br.com.fiap.fintech.constants.Constants.ERROR_DELETING_STATISTICS;
import static br.com.fiap.fintech.constants.Constants.ERROR_LISTING_STATISTICS;
import static br.com.fiap.fintech.constants.Constants.ERROR_LOOKING_UP_STATISTIC_ID;
import static br.com.fiap.fintech.constants.Constants.ERROR_REGISTERING_STATISTIC;
import static br.com.fiap.fintech.constants.Constants.STATISTICS_NOT_FOUND;
import static br.com.fiap.fintech.exceptions.ErrorTypeEnum.ERROR_DELETING_DATA;
import static br.com.fiap.fintech.exceptions.ErrorTypeEnum.ERROR_INSERTING_DATA;
import static br.com.fiap.fintech.exceptions.ErrorTypeEnum.ERROR_SEARCHING_DATA;
import static br.com.fiap.fintech.exceptions.ErrorTypeEnum.ERROR_UPDATING_DATA;
import static br.com.fiap.fintech.factory.DaoFactory.parseStatistics;

public class StatisticsDaoImpl implements DaoAdapter<Statistic> {

    private Connection connection;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    private List<Statistic> lista = new ArrayList<>();

    public StatisticsDaoImpl() throws SQLException {
        connection = ConnectionManager.getInstance().getConnection();
    }

    private void checkConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            this.connection = ConnectionManager.getInstance().getConnection();
        }
    }

    @Override
    public void insert(Statistic statistic) throws FintechException {
        try {
            String sql =
                    "INSERT INTO tb_fth_statistics (" +
                            "cd_statistics, " +
                            "dt_month, " +
                            "dt_year, " +
                            "vl_budge, " +
                            "vl_cost, " +
                            "vl_economy, " +
                            "cd_user) " +
                            "VALUES (seq_statistic.nextval, ?, ?, ?, ?, ?, ?)";

            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, statistic.getMonth());
            stmt.setInt(2, statistic.getYear());
            stmt.setDouble(3, statistic.getBudge());
            stmt.setDouble(4, statistic.getCost());
            stmt.setDouble(5, statistic.getEconomy());
            stmt.setLong(6, statistic.getUser().getUserId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(ERROR_REGISTERING_STATISTIC + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_INSERTING_DATA);
        }
    }

    @Override
    public Statistic getById(Long id) {
        try {
            String sql = "SELECT * FROM tb_fth_statistics WHERE cd_statistics = ?";

            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if (!rs.next())
                throw new FintechException(STATISTICS_NOT_FOUND + id, null, ERROR_SEARCHING_DATA);

            return parseStatistics(rs);

        } catch (SQLException e) {
            System.err.println(ERROR_LOOKING_UP_STATISTIC_ID + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_SEARCHING_DATA);
        }
    }

    @Override
    public List<Statistic> getAll() {
        try {
            String sql = "SELECT * FROM tb_fth_statistics";

            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(parseStatistics(rs));
            }

            return lista;

        } catch (SQLException e) {
            System.err.println(ERROR_LISTING_STATISTICS + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_SEARCHING_DATA);
        }
    }

    @Override
    public void update(Statistic statistic) throws FintechException {
        try {

            String sql =
                    "UPDATE tb_fth_statistics SET" +
                            "dt_month = ?," +
                            "dt_year = ?," +
                            "vl_budge = ?," +
                            "vl_cost = ?," +
                            "vl_economy = ?," +
                            "WHERE cd_statistics = ?";

            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, statistic.getMonth());
            stmt.setInt(2, statistic.getYear());
            stmt.setDouble(3, statistic.getBudge());
            stmt.setDouble(3, statistic.getCost());
            stmt.setDouble(3, statistic.getEconomy());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(ERROR_UPDATING_DATA + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_UPDATING_DATA);
        }
    }

    @Override
    public void delete(Long id) throws FintechException {
        try {
            String sql = "DELETE FROM tb_fth_statistics WHERE cd_statistics = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(ERROR_DELETING_STATISTICS + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_DELETING_DATA);
        }
    }

}
