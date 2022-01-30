package com.io.dbprops;

import java.io.Serializable;
import java.util.List;

public interface Dao<T extends Serializable> {

    void insert(T t);

    List<T> get(String hqlQuery);

    void update(T t);

}
