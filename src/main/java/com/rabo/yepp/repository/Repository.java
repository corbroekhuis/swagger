package com.rabo.yepp.repository;

import com.rabo.yepp.model.Table;

import java.util.List;
import java.util.Optional;

public interface Repository <T extends Table>{

    T save(T t);

    T update(T t);

    List<T> findAll();

    Optional<T> findById(long id);

    void deleteById(long id);
}
