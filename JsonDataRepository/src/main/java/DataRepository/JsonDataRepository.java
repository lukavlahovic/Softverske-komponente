package DataRepository;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Entity;
import model.SearchParameters;
import repository.DataRepository;
import repository.Search;

import java.io.*;


import java.util.*;

public class JsonDataRepository implements DataRepository {

    private ObjectMapper objectMapper = new ObjectMapper();
    Search jsonSearch = new Search();

    @Override
    public ArrayList<Entity> loadRepository(String path) {
        File directoryPath = new File(path);
        File filesList[] = directoryPath.listFiles();
        //System.out.println("List of files and directories in the specified directory:");
        ArrayList<Entity> allEntities = new ArrayList<>();
        for(File file : filesList) {
            try {
                List<Entity> objects = objectMapper.readValue(file, new TypeReference<List<Entity>>() {
                });
                allEntities.addAll(objects);
            } catch (IOException e) {

            }
        }
        return allEntities;
    }

    @Override
    public void save(String pathToDirectory, Object entity, String pathToConfig) {
        Properties properties = new Properties();
        FileReader reader= null;
        try {
            reader = new FileReader(pathToConfig);
            properties.load(reader);
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        if (properties.getProperty("autoGenerateId").equals("false"))
        {
            SearchParameters sp = new SearchParameters();
            sp.setEntityId(Integer.toString(((Entity)entity).getId()));

            if(!find(sp,pathToDirectory).isEmpty())
            {
                System.out.println("Entitet sa datim ID vec postoji");
                return;
            }
            HashMap<Object,Object> map = (HashMap)((Entity) entity).getAttributes();
            for(Map.Entry entry : map.entrySet()){
                if(entry.getValue() instanceof Entity){
                    SearchParameters sp2 = new SearchParameters();
                    sp2.setEquals(entry.getKey().toString()+":id:"+((Entity) entry.getValue()).getId());
                    if(!find(sp2,pathToDirectory).isEmpty())
                    {
                        System.out.println("Entitet sa datim ID vec postoji");
                        return;
                    }
                }
            }
        }

        try {
            List<Entity> objects = objectMapper.readValue(new File(pathToDirectory+File.separator+properties.getProperty("lastFileName")), new TypeReference<List<Entity>>() {
            });
            if(objects.size()<(Integer.parseInt(properties.getProperty("limitPerFile")))) {
                objects.add((Entity) entity);
                objectMapper.writeValue(new File(pathToDirectory+File.separator+properties.getProperty("lastFileName")), objects);
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
              File newFile = new File(pathToDirectory+File.separator+"newfile.json");
              List<Entity> objects1 = new ArrayList<>();
              objects1.add((Entity)entity);
                try {
                    objectMapper.writeValue(newFile,objects1);
                    editPropertyFile(pathToConfig,"lastFileName", newFile.getName());
                    if (properties.getProperty("autoGenerateId").equals("true"))
                        editPropertyFile(pathToConfig,"lastAvailableId",Integer.toString(((Entity) entity).getId()));

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }

        } catch (IOException e) {
            List<Entity> objects1 = new ArrayList<>();
            objects1.add((Entity)entity);
            try {
                objectMapper.writeValue(new File(pathToDirectory+File.separator+properties.getProperty("lastFileName")),objects1);
                if (properties.getProperty("autoGenerateId").equals("true"))
                    editPropertyFile(pathToConfig,"lastAvailableId",Integer.toString(((Entity) entity).getId()));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            //e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Entity> find(SearchParameters searchParameters, String pathToDirectory) {

        ArrayList<Entity> result = new ArrayList<>();
        File directoryPath = new File(pathToDirectory);
        File filesList[] = directoryPath.listFiles();
        for(File file:filesList)
        {
            try {
                List<Entity> entities = objectMapper.readValue(file, new TypeReference<List<Entity>>() {
                });

                result.addAll(jsonSearch.find(entities,searchParameters));
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }


        return result;
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
