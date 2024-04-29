package com.rabo.yepp.repository;

import com.rabo.yepp.model.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositoryImpl<T extends Table> implements Repository<T>{
    
    List<T> rows = new ArrayList<>();

    // Generate new id in database
    public long getNextId(){
        return rows.size() + 1;
    }
    
    public T save( T t){
        t.setId( getNextId());
        rows.add( t);
        return t;
    }

    public T update( T t){

        Optional<T> found = rows
                .stream()
                .filter(x -> x.getId() == t.getId())
                .findFirst();
        if(found.isPresent()){
            rows.remove(found.get());
            rows.add( t);

        }
        return null;
    }

    public List<T> findAll(){
        return rows;
    }

    public Optional<T>findById( long id){
        Optional<T> found = rows
                .stream()
                .filter(x -> x.getId() == id)
                .findFirst();
        return found;
    }

    @Override
    public void deleteById( long id){

        Optional<T> found = rows
                .stream()
                .filter(x -> x.getId() == id)
                .findFirst();
        if(found.isPresent()){
            rows.remove(found.get());
        }

    }

}
