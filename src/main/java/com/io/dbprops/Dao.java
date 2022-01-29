package com.io.dbprops;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<String> insert(T t);

    List<T> get(String hqlQuery);

    void deleteUsingId(String id);

}
