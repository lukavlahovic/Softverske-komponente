package DataRepository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import model.Entity;
import model.SearchParameters;
import repository.DataRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YamlDataRepository implements DataRepository {

    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());


    @Override
    public void save(String s, Object o) {

        try {
            List<Entity> objects = mapper.readValue(new File(s), new TypeReference<List<Entity>>() {
            });
            objects.add((Entity)o);
            mapper.writeValue(new File(s),objects);

        } catch (IOException e) {
            List<Entity> objects1 = new ArrayList<>();
            objects1.add((Entity)o);
            try {
                mapper.writeValue(new File(s),objects1);
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
           // e.printStackTrace();
        }


    }

    @Override
    public ArrayList<Entity> find(SearchParameters searchParameters) {

        try {
            List<Entity> entities = mapper.readValue(new File("//home//luka//Desktop//yaml.yml"), new TypeReference<List<Entity>>() {
            });
            ArrayList<Entity> result = new ArrayList<>();
            for(Entity entity: entities)
            {
                if(searchParameters.getEntityName()!=null)
                {
                    if (!(entity.getName().equals(searchParameters.getEntityName())))
                    {
                        continue;
                    }
                }
                if(searchParameters.getEntityId()!=null)
                {
                    if(!(Integer.toString(entity.getId()).equals(searchParameters.getEntityId())))
                    {
                        continue;
                    }
                }
                if (searchParameters.getEquals()!=null)
                {

                    boolean notFound = false;
                    for(String equal: searchParameters.getEquals().split(";"))
                    {
                        String[] keyValue = equal.split(":");
                        if (keyValue.length==2)
                        {
                            if(!(keyValue[1].equals(entity.getAttributes().get(keyValue[0]))))
                            {
                                notFound = true;
                                break;
                            }
                        }
                        else
                        {
                            if(entity.getAttributes().containsKey(keyValue[0]))
                            {
                                HashMap map = ((HashMap)entity.getAttributes().get(keyValue[0]));

                                if(keyValue[1].equals("id")&&keyValue[2].equals(Integer.toString((int)map.get(keyValue[1]))))
                                {
                                    continue;
                                }
                                else if (keyValue[1].equals("name")&&keyValue[2].equals(map.get(keyValue[1])))
                                {

                                }
                                else if((keyValue[2].equals(((HashMap)map.get("attributes")).get(keyValue[1]))))
                                {
                                    continue;
                                }
                                else
                                {
                                    notFound = true;
                                    break;
                                }
                            }
                            else
                            {
                                notFound = true;
                                break;
                            }
                        }
                    }
                    if(notFound)
                    {

                        continue;
                    }
                }
                result.add(entity);
            }
            return result;
        } catch (IOException e) {

        }

        return null;
    }


    @Override
    public <T> List<T> findAll(String s, Class<T> aClass) {
        return null;
    }

    @Override
    public void delete(String s, int id) {

        try {
            List<Entity> objects = mapper.readValue(new File(s), new TypeReference<List<Entity>>() {
            });
            for(Entity entity : objects){
                if(entity.getId()==id) {
                    objects.remove(entity);
                    break;
                }
            }
            mapper.writeValue(new File(s), objects);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void update(String s, int id, HashMap<Object, Object> updatedMap) {

        try {
            List<Entity> objects = mapper.readValue(new File(s), new TypeReference<List<Entity>>() {
            });
            for(Entity entity : objects){
                if(entity.getId()==id) {
                    updatedMap.forEach((key,value)-> entity.getAttributes().put(key,value));
                    break;
                }
            }
            mapper.writeValue(new File(s), objects);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
