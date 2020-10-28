package DataRepository;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.Entity;
import repository.DataRepository;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class JsonDataRepository implements DataRepository {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void save(String s, Object entity) {
        try {
            List<Entity> objects = objectMapper.readValue(new File(s), new TypeReference<List<Entity>>() {
            });
            objects.add((Entity)entity);
            objectMapper.writeValue(new File(s), objects);
        } catch (IOException e) {
            List<Entity> objects1 = new ArrayList<>();
            objects1.add((Entity)entity);
            try {
                objectMapper.writeValue(new File(s),objects1);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            //e.printStackTrace();
        }
    }

    @Override
    public <T> T findById(String s, String s1, Class<T> aClass) {
        return null;
    }

    @Override
    public <T> List<T> findAll(String s, Class<T> aClass) {
        return null;
    }

    @Override
    public void delete(String s, int id) {
        try {
            List<Entity> objects = objectMapper.readValue(new File(s), new TypeReference<List<Entity>>() {
            });
            for(Entity entity : objects){
                if(entity.getId()==id) {
                    objects.remove(entity);
                    break;
                }
            }
            objectMapper.writeValue(new File(s), objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String s, int id, HashMap<Object,Object> updatedMap) {
        try {
            List<Entity> objects = objectMapper.readValue(new File(s), new TypeReference<List<Entity>>() {
            });
            for(Entity entity : objects){
                if(entity.getId()==id) {
                    updatedMap.forEach((key,value)-> entity.getAttributes().put(key,value));
                    break;
                }
            }
            objectMapper.writeValue(new File(s), objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
