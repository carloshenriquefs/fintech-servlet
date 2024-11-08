package br.com.fiap.fintech.model;

import java.time.LocalDate;

public class Note {

    private Long code;
    private String title;
    private String comentary;
    private LocalDate note;

    public Note() {
    }

    public Note(Long code, String title) {
        this.code = code;
        this.title = title;
    }

    public Note(Long code, String title, String comentary, LocalDate note) {
        this.code = code;
        this.title = title;
        this.comentary = comentary;
        this.note = note;
    }

    public Note(String title, String comentary, LocalDate note) {
        this.title = title;
        this.comentary = comentary;
        this.note = note;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComentary() {
        return comentary;
    }

    public void setComentary(String comentary) {
        this.comentary = comentary;
    }

    public LocalDate getNote() {
        return note;
    }

    public void setNote(LocalDate note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Note{" +
                "code=" + code +
                ", title='" + title + '\'' +
                ", comentary='" + comentary + '\'' +
                ", note=" + note +
                '}';
    }
}
