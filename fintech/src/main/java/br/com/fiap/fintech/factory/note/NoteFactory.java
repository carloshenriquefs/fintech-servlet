package br.com.fiap.fintech.factory.note;

import br.com.fiap.fintech.dao.DaoAdapter;
import br.com.fiap.fintech.dao.note.impl.NoteDaoImpl;
import br.com.fiap.fintech.model.Note;
import br.com.fiap.fintech.model.builder.note.NoteBuilder;

import java.time.LocalDate;

public class NoteFactory {

    public static DaoAdapter<Note> getNotesDao() {
        return new NoteDaoImpl();
    }

    public static Note createNotes() {
        return new NoteBuilder()
                .setCode(1L)
                .setTitle("Pagamento Em Debito")
                .setComentary("Pagamento Efetuado")
                .setNote(LocalDate.of(2021, 6, 25))
                .build();
    }
}
