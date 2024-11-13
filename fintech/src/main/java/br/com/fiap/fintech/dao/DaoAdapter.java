package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.exceptions.FintechException;

import java.util.List;

public interface DaoAdapter<T> {

    void insert(T t) throws FintechException;
    T getById(Long t);
    List<T> getAll();
    void update(T t) throws FintechException;
    void delete(Long t) throws FintechException;

}
