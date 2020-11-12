package repository;

import model.Entity;
import model.SearchParameters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface DataRepository {

    ArrayList<Entity> loadRepository(String path);
    /**
     * Save object in the specified collection of the storage.
     *
     * @param collection name of the collection
     * @param object     data
     */
    boolean save(String pathToDirectory, Object object, String pathToProperties);

    /**
     * Get the object with the specified id.
     *
     * @param collection name of the collection
     * @param id         id of the object we want to get
     * @param type       type of the object
     * @return object with specified id
     */
    ArrayList<Entity> find(SearchParameters parameters, String pathToDirectory);

    /**
     * Get all objects from specified collection
     *
     * @param collection name of the collection
     * @param type       of objects
     * @return list off objects from specified collection
     */
    <T> List<T> findAll(String collection, Class<T> type);

    /**
     *
     * @param collection name of the collection
     * @param id         index
     */
    void delete(String collection, int id);

    /**
     *
     * @param collection name of the collection
     * @param id         index of Entity
     * @param newData    HashMap with changed values
     */
    void update(String collection, int id,HashMap<Object,Object> newData);
}
