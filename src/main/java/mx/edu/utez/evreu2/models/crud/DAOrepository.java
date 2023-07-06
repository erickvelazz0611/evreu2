package mx.edu.utez.evreu2.models.crud;

import java.util.List;

public interface DAOrepository<T> {
    List <T> findAll();
    T findOne(Long id);
    boolean save(T object);
    boolean update(T object);
    boolean delete(Long id);
}

