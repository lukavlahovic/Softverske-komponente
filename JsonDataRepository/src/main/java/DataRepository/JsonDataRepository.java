package DataRepository;

import repository.DataRepository;

import java.util.List;

public class JsonDataRepository implements DataRepository {
    @Override
    public void save(String s, Object o) {

    }

    @Override
    public <T> T findById(String s, String s1, Class<T> aClass) {
        return null;
    }

    @Override
    public <T> List<T> findAll(String s, Class<T> aClass) {
        return null;
    }
}
