package br.com.fiap.fintech.dao.user.impl;

import br.com.fiap.fintech.dao.user.UserDao;
import br.com.fiap.fintech.exceptions.FintechException;
import br.com.fiap.fintech.factory.ConnectionManager;
import br.com.fiap.fintech.model.Login;
import br.com.fiap.fintech.model.Note;
import br.com.fiap.fintech.model.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static br.com.fiap.fintech.constants.Constants.ERROR_LISTING_USERS;
import static br.com.fiap.fintech.constants.Constants.ERROR_REGISTERING_USER;
import static br.com.fiap.fintech.exceptions.ErrorTypeEnum.ERROR_DELETING_DATA;
import static br.com.fiap.fintech.exceptions.ErrorTypeEnum.ERROR_INSERTING_DATA;
import static br.com.fiap.fintech.exceptions.ErrorTypeEnum.ERROR_LISTING_DATA;
import static br.com.fiap.fintech.exceptions.ErrorTypeEnum.ERROR_UPDATING_DATA;

public class UserDaoImpl implements UserDao {

    private Connection connection;
    private PreparedStatement stmt = null;
    private User user = null;
    private ResultSet rs = null;
    private List<User> lista = new ArrayList<>();

    public UserDaoImpl() {
        connection = ConnectionManager.getInstance().getConnection();
    }

    private void checkConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            this.connection = ConnectionManager.getInstance().getConnection();
        }
    }

    @Override
    public void insert(User user) throws FintechException {

        try {

            String sql =
                    "INSERT INTO tb_fth_user (" +
                            "cd_user, " +
                            "nm_first_user, " +
                            "nm_last_user, " +
                            "ds_email, " +
                            "cd_password, " +
                            "ds_address, " +
                            "nr_telephone, " +
                            "ds_gender, " +
                            "ds_position, " +
                            "dt_registration," +
                            "cd_note) " +
                            "VALUES (seq_users.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            stmt = connection.prepareStatement(sql);

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getPhone());
            stmt.setString(7, user.getGender());
            stmt.setString(8, user.getPosition());
            stmt.setDate(9, Date.valueOf(user.getDateBirth()));
            stmt.setLong(10, user.getNote().getCode());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(ERROR_REGISTERING_USER + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_INSERTING_DATA);
        }
    }

    @Override
    public User getById(Long id) {

        try {
            String sql = "SELECT * FROM tb_fth_user " +
                    "INNER JOIN tb_fth_note " +
                    "ON tb_fth_user.cd_user = tb_fth_note.cd_note " +
                    "WHERE tb_fth_user.cd_user = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Long userId = rs.getLong("cd_user");
                String firstName = rs.getString("nm_first_user");
                String lastName = rs.getString("nm_last_user");
                String email = rs.getString("ds_email");
                String password = rs.getString("cd_password");
                String address = rs.getString("ds_address");
                String phone = rs.getString("nr_telephone");
                String gender = rs.getString("ds_gender");
                String position = rs.getString("ds_position");
                LocalDate dateBirth = rs.getDate("dt_registration").toLocalDate();

                Long code = rs.getLong("cd_note");
                String title = rs.getString("ds_title");

                user = new User(userId, firstName, lastName, email, password, address, phone, gender, position, dateBirth);

                Note note = new Note(code, title);

                user.setNote(note);
            }

        } catch (SQLException e) {
            System.err.println(ERROR_REGISTERING_USER + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_UPDATING_DATA);
        }

        return user;
    }

    @Override
    public List<User> getAll() {

        try {
            String sql =
                    "SELECT * FROM tb_fth_user " +
                            "INNER JOIN tb_fth_note " +
                            "ON tb_fth_user.cd_user = tb_fth_note.cd_note";

            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Long userId = rs.getLong("cd_user");
                String firstName = rs.getString("nm_first_user");
                String lastName = rs.getString("nm_last_user");
                String email = rs.getString("ds_email");
                String password = rs.getString("cd_password");
                String address = rs.getString("ds_address");
                String phone = rs.getString("nr_telephone");
                String gender = rs.getString("ds_gender");
                String position = rs.getString("ds_position");
                LocalDate dateBirth = rs.getDate("dt_registration").toLocalDate();

                Long code = rs.getLong("cd_note");
                String title = rs.getString("ds_title");

                user = new User(userId, firstName, lastName, email, password, address, phone, gender, position, dateBirth);

                Note note = new Note(code, title);

                user.setNote(note);

                lista.add(user);

            }

        } catch (SQLException e) {
            System.err.println(ERROR_LISTING_USERS + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_LISTING_DATA);
        }
        return lista;
    }

    @Override
    public void update(User user) throws FintechException {

        try {
            String sql =
                    "UPDATE tb_fth_user SET " +
                            "nm_first_user = ?," +
                            "nm_last_user = ?, " +
                            "ds_email = ?," +
                            "cd_password = ?," +
                            "ds_address = ?," +
                            "nr_telephone = ?," +
                            "ds_gender = ?," +
                            "ds_position = ?," +
                            "dt_registration = ?, " +
                            "cd_note = ? " +
                            "WHERE cd_user = ?";

            stmt = connection.prepareStatement(sql);

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getPhone());
            stmt.setString(7, user.getGender());
            stmt.setString(8, user.getPosition());
            stmt.setDate(9, Date.valueOf(user.getDateBirth()));
            stmt.setLong(10, user.getNote().getCode());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(ERROR_UPDATING_DATA + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_UPDATING_DATA);
        }
    }


    @Override
    public void delete(Long id) throws FintechException {

        try {
            String sql = "DELETE FROM tb_fth_user WHERE cd_user = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(ERROR_REGISTERING_USER + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_DELETING_DATA);
        }
    }

    @Override
    public boolean validateUser(Login login) {

        try {
            String sql =
                    "SELECT * FROM tb_fth_login " +
                    "WHERE ds_email = ? AND cd_password = ?";

            stmt = connection.prepareStatement(sql);

            stmt.setString(1, login.getEmail());
            stmt.setString(2, login.getPassword());

            rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
