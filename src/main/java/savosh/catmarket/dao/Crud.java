package savosh.catmarket.dao;

import java.util.Set;

public interface Crud<T> {

    T create(T object);
    Set<T> readAll();
    T read(T emptyObjectWithPk);
    void update(T object);
    void delete(T objectWithPk);

}
