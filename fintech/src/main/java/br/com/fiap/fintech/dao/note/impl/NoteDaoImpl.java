package br.com.fiap.fintech.dao.note.impl;

import br.com.fiap.fintech.dao.DaoAdapter;
import br.com.fiap.fintech.exceptions.FintechException;
import br.com.fiap.fintech.factory.ConnectionManager;
import br.com.fiap.fintech.model.Note;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static br.com.fiap.fintech.constants.Constants.ERROR_DELETING_NOTE;
import static br.com.fiap.fintech.constants.Constants.ERROR_LISTING_NOTE;
import static br.com.fiap.fintech.constants.Constants.ERROR_LOOKING_UP_NOTE_ID;
import static br.com.fiap.fintech.constants.Constants.ERROR_REGISTERING_NOTE;
import static br.com.fiap.fintech.constants.Constants.NOTES_NOT_FOUND;
import static br.com.fiap.fintech.exceptions.ErrorTypeEnum.ERROR_DELETING_DATA;
import static br.com.fiap.fintech.exceptions.ErrorTypeEnum.ERROR_INSERTING_DATA;
import static br.com.fiap.fintech.exceptions.ErrorTypeEnum.ERROR_SEARCHING_DATA;
import static br.com.fiap.fintech.exceptions.ErrorTypeEnum.ERROR_UPDATING_DATA;
import static br.com.fiap.fintech.factory.DaoFactory.parseNotes;

public class NoteDaoImpl implements DaoAdapter<Note> {

    private Connection connection;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    private List<Note> lista = new ArrayList<Note>();
    private Note note;

    public NoteDaoImpl() {
        connection = ConnectionManager.getInstance().getConnection();
    }

    private void checkConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            this.connection = ConnectionManager.getInstance().getConnection();
        }
    }

    @Override
    public void insert(Note note) throws FintechException {
        try {

            String sql =
                    "INSERT INTO tb_fth_note (" +
                            "cd_note, " +
                            "ds_title, " +
                            "ds_comentary, " +
                            "dt_note) " +
                            "VALUES (seq_note.nextval, ?, ?, ?)";

            stmt = connection.prepareStatement(sql);

            stmt.setString(1, note.getTitle());
            stmt.setString(2, note.getComentary());
            stmt.setDate(3, Date.valueOf(note.getNote()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(ERROR_REGISTERING_NOTE + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_INSERTING_DATA);
        }
    }


    @Override
    public Note getById(Long id) {
        try {

            String sql = "SELECT * FROM tb_fth_note " +
                    "INNER JOIN tb_fth_user " +
                    "ON tb_fth_note.cd_note = tb_fth_user.cd_user " +
                    "WHERE tb_fth_user.cd_note = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if (!rs.next())
                throw new FintechException(NOTES_NOT_FOUND + id, null, ERROR_SEARCHING_DATA);

            return parseNotes(rs);

        } catch (SQLException e) {
            System.err.println(ERROR_LOOKING_UP_NOTE_ID + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_SEARCHING_DATA);
        }
    }

    @Override
    public List<Note> getAll() {
        try {
            String sql = "SELECT * FROM tb_fth_note " +
                    "INNER JOIN tb_fth_user " +
                    "ON tb_fth_note.cd_note = tb_fth_user.cd_user";

            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(parseNotes(rs));
            }

        } catch (SQLException e) {
            System.err.println(ERROR_LISTING_NOTE+ e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_SEARCHING_DATA);
        }

        return lista;
    }

    @Override
    public void update(Note note) throws FintechException {

        try {

            String sql =
                    "UPDATE tb_fth_notes SET" +
                            "ds_title = ?," +
                            "ds_comentary = ?," +
                            "ds_note = ?," +
                            "WHERE cd_notes = ?";

            stmt = connection.prepareStatement(sql);

            stmt.setString(1, note.getTitle());
            stmt.setString(2, note.getComentary());
            stmt.setDate(3, Date.valueOf(note.getNote()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(ERROR_UPDATING_DATA + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_UPDATING_DATA);
        }
    }

    @Override
    public void delete(Long id) throws FintechException {


        try {
            String sql = "DELETE FROM tb_fth_notes WHERE cd_notes = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(ERROR_DELETING_NOTE + e.getMessage());
            throw new FintechException(e.getMessage(), e, ERROR_DELETING_DATA);
        }
    }

}
