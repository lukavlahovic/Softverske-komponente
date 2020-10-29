package CusstomDataRepositoryParser;

import model.Entity;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomParser {

    public CustomParser() {
        super();
    }

    public void writer(File file, Object object) {
        String text = "";

        if (object instanceof ArrayList) {
            ArrayList<Object> list = (ArrayList) (object);
            for (Object obj : list) {
                text += "@array:" + "\n";
                if (obj instanceof Entity) {
                    text += "\t" + "name" + ":" + ((Entity) obj).getName() + ";" + "\n"
                            + "\t" + "id" + ":" + ((Entity) obj).getId() + ";" + "\n"
                            + "\t" + "attributes" + ":" + "\n" + "\t" + "\t" +"@array:" + "\n";
                    for (Map.Entry<Object, Object> entry : ((Entity) obj).getAttributes().entrySet()) {
                        text += "\t" + "\t" + (String) entry.getKey() + ":" + (String) entry.getValue() + ";" + "\n";
                    }
                    text += "\t" + "\t" + "$array;" + "\n";
                }
                text += "$array;" + "\n" ;
            }
        }

        try {
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(text);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Entity> reader(File file) {

        try {

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            ArrayList<Entity> lista_entiteta = new ArrayList<>();
            while ((line = br.readLine()) != null)
            {
                if(line.equals("@array:"))
                {
                    String name = "";
                    int id = 0;
                    line = br.readLine();
                    if(line.contains("name"))
                    {
                        String s[] = line.split(":");
                        name = (s[1].trim().replace(";",""));
                    }
                    line = br.readLine();
                    if(line.contains("id"))
                    {
                        String s[] = line.split(":");
                        id = (Integer.parseInt(s[1].trim().replace(";","")));
                    }
                    Entity entity = new Entity(name,id);
                    line = br.readLine();
                    if(line.contains("attributes"))
                    {
                        line = br.readLine();
                        line = br.readLine();
                        while(!line.contains("$array;"))
                        {
                            System.out.println("Usao");
                            String s[] = line.split(":");
                            entity.getAttributes().put(s[0].trim(),s[1].trim().replace(";",""));
                            line = br.readLine();
                        }
                    }
                    lista_entiteta.add(entity);
                }


            }
            return lista_entiteta;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
