package DataRepository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import model.Entity;
import model.SearchParameters;
import repository.DataRepository;
import repository.Search;

import java.io.*;
import java.util.*;

public class YamlDataRepository implements DataRepository {

    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    Search yamlSearch = new Search();


    @Override
    public ArrayList<Entity> loadRepository(String path) {
        File directoryPath = new File(path);
        File filesList[] = directoryPath.listFiles();
        ArrayList<Entity> allEntities = new ArrayList<>();
        for(File file : filesList) {
            try {
                List<Entity> objects = mapper.readValue(file, new TypeReference<List<Entity>>() {
                });
                allEntities.addAll(objects);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return allEntities;
    }

    @Override
    public boolean save(String pathToDirectory, Object entity,String pathToConfig) {
        Properties properties = new Properties();
        FileReader reader= null;
        try {
            reader = new FileReader(pathToConfig);
            properties.load(reader);
            reader.close();
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        if (properties.getProperty("autoGenerateId").equals("false"))
        {
            SearchParameters sp = new SearchParameters();
            sp.setAndOr("or");
            sp.setEntityId(Integer.toString(((Entity)entity).getId()));

            HashMap<Object,Object> map = (HashMap)((Entity) entity).getAttributes();
            for(Map.Entry entry : map.entrySet()){
                if(entry.getValue() instanceof Entity){
                    SearchParameters sp2 = new SearchParameters();
                    sp2.setAndOr("or");
                    sp2.setEntityId(Integer.toString(((Entity) entry.getValue()).getId()));
                    sp2.setEquals(entry.getKey().toString()+":id:"+((Entity) entry.getValue()).getId());
                    sp.setEquals(entry.getKey().toString()+":id:"+((Entity)entity).getId());
                    if(!find(sp,pathToDirectory).isEmpty())
                    {
                        System.out.println("Entitet sa datim ID vec postoji");
                        return false;
                    }
                    if(!find(sp2,pathToDirectory).isEmpty())
                    {
                        System.out.println("Entitet sa datim ID vec postoji");
                        return false;
                    }
                }
            }
            if(!find(sp,pathToDirectory).isEmpty())
            {
                System.out.println("Entitet sa datim ID vec postoji");
                return false;
            }
        }
        try {
            List<Entity> objects = mapper.readValue(new File(pathToDirectory+File.separator+properties.getProperty("lastFileName")), new TypeReference<List<Entity>>() {
            });
            if(objects.size()<(Integer.parseInt(properties.getProperty("limitPerFile")))) {
                objects.add((Entity) entity);
                mapper.writeValue(new File(pathToDirectory+File.separator+properties.getProperty("lastFileName")), objects);
                boolean imaUgnjezdeni = false;
                if (properties.getProperty("autoGenerateId").equals("true"))
                    for(Map.Entry entry:(((Entity) entity).getAttributes().entrySet())){
                        if(entry.getValue() instanceof Entity){
                            imaUgnjezdeni = true;
                            break;
                        }
                    }
                if (imaUgnjezdeni)
                    editPropertyFile(pathToConfig,"lastAvailableId",Integer.toString(((Entity) entity).getId()+1));
                else
                    editPropertyFile(pathToConfig,"lastAvailableId",Integer.toString(((Entity) entity).getId()));
            }
            else
            {
                File newFile = new File(pathToDirectory+File.separator+"newfile.yml");
                List<Entity> objects1 = new ArrayList<>();
                objects1.add((Entity)entity);
                try {
                    mapper.writeValue(newFile,objects1);
                    editPropertyFile(pathToConfig,"lastFileName", newFile.getName());
                    boolean imaUgnjezdeni = false;
                    if (properties.getProperty("autoGenerateId").equals("true"))
                        for(Map.Entry entry:(((Entity) entity).getAttributes().entrySet())){
                            if(entry.getValue() instanceof Entity){
                                imaUgnjezdeni = true;
                                break;
                            }
                        }
                    if (imaUgnjezdeni)
                        editPropertyFile(pathToConfig,"lastAvailableId",Integer.toString(((Entity) entity).getId()+1));
                    else
                        editPropertyFile(pathToConfig,"lastAvailableId",Integer.toString(((Entity) entity).getId()));

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
//            objects.add((Entity)o);
//            mapper.writeValue(new File(s),objects);

        } catch (IOException e) {
            List<Entity> objects1 = new ArrayList<>();
            objects1.add((Entity)entity);
            try {
                mapper.writeValue(new File(pathToDirectory+File.separator+properties.getProperty("lastFileName")),objects1);
                boolean imaUgnjezdeni = false;
                if (properties.getProperty("autoGenerateId").equals("true"))
                    for(Map.Entry entry:(((Entity) entity).getAttributes().entrySet())){
                        if(entry.getValue() instanceof Entity){
                            imaUgnjezdeni = true;
                            break;
                        }
                    }
                if (imaUgnjezdeni)
                    editPropertyFile(pathToConfig,"lastAvailableId",Integer.toString(((Entity) entity).getId()+1));
                else
                    editPropertyFile(pathToConfig,"lastAvailableId",Integer.toString(((Entity) entity).getId()));
            } catch (Exception exception)
            {
               // exception.printStackTrace();
            }
           // e.printStackTrace();
        }
        return true;
    }

    @Override
    public ArrayList<Entity> find(SearchParameters searchParameters, String pathToDirectory) {
        ArrayList<Entity> result = new ArrayList<>();
        File directoryPath = new File(pathToDirectory);
        File filesList[] = directoryPath.listFiles();
        for(File file:filesList)
        {
            try {
                List<Entity> entities = mapper.readValue(file, new TypeReference<List<Entity>>() {
                });
                result.addAll(yamlSearch.find(entities,searchParameters));
            } catch (IOException e) {
                // e.printStackTrace();
            }
        }
        return result;
    }


    @Override
    public <T> List<T> findAll(String s, Class<T> aClass) {
        return null;
    }

    @Override
    public void delete(String pathToDirectory, int id) {
        File directoryPath = new File(pathToDirectory);
        File filesList[] = directoryPath.listFiles();
        for(File file:filesList) {
            try {
                List<Entity> objects = mapper.readValue(file, new TypeReference<List<Entity>>() {
                });
                for (Entity entity : objects) {
                    if (entity.getId() == id) {
                        objects.remove(entity);
                        break;
                    }
                }
                mapper.writeValue(file, objects);
                break;
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
    }

    @Override
    public void update(String pathToDirectory, int id, HashMap<Object, Object> updatedMap) {
        File directoryPath = new File(pathToDirectory);
        File filesList[] = directoryPath.listFiles();
        for(File file:filesList) {
            try {
                List<Entity> objects = mapper.readValue(file, new TypeReference<List<Entity>>() {
                });
                for (Entity entity : objects) {
                    if (entity.getId() == id) {
                        updatedMap.forEach((key, value) -> entity.getAttributes().put(key, value));
                        break;
                    }
                }
                mapper.writeValue(file, objects);
                break;
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
    }

    public void editPropertyFile(String pathToProperty, String updatedKey, String updatedValue)
    {
        Properties properties = new Properties();
        FileReader reader= null;
        try {
            reader = new FileReader(pathToProperty);
            properties.load(reader);
            FileOutputStream out = new FileOutputStream(pathToProperty);
            HashMap<String,String> oldValues = new HashMap<>();
            oldValues.put("lastFileName",properties.getProperty("lastFileName"));
            oldValues.put("limitPerFile",properties.getProperty("limitPerFile"));
            oldValues.put("pathToDirectory",properties.getProperty("pathToDirectory"));
            oldValues.put("autoGenerateId",properties.getProperty("autoGenerateId"));
            oldValues.put("lastAvailableId",properties.getProperty("lastAvailableId"));
            oldValues.put(updatedKey,updatedValue);
            oldValues.forEach((key,value) -> properties.setProperty(key,value));

            properties.store(out,null);
            reader.close();
            out.close();

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        }


    }
}
