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

                        if(!(entry.getValue() instanceof HashMap) && !(entry.getValue() instanceof Entity) ) {
                            text += "\t" + "\t" + (String) entry.getKey() + ":";
                            if (entry.getValue() instanceof String)
                                text += (String) entry.getValue() + ";" + "\n";
                            else {
                                text += (int) entry.getValue() + ";" + "\n";
                            }
                        }
                        else if((entry.getValue() instanceof HashMap))
                        {

                            HashMap<Object,Object> ugnjezdeni = (HashMap)entry.getValue();
                            text += "\t" + "\t" + (String) entry.getKey() + ":"+ "@uentity;" + "\n";
                            text += "\t" + "\t" +  "name" + ":" + (ugnjezdeni.get("name")) + ";" + "\n"
                                    + "\t" + "\t" +  "id" + ":" + (ugnjezdeni.get("id")) + ";" + "\n"
                                    + "\t" + "\t" +  "attributes" + ":" + "\n" + "\t" + "\t" +  "\t" +"@array:" + "\n";
                            HashMap<Object,Object> atributi = (HashMap)ugnjezdeni.get("attributes");
                            for (Map.Entry<Object, Object> entry1 :atributi.entrySet()) {

                                text += "\t" + "\t" + "\t" +  (String) entry1.getKey() + ":";
                                if(entry1.getValue() instanceof String)
                                    text += (String) entry1.getValue() + ";" + "\n";
                                else
                                {
                                    text += (int)entry1.getValue() + ";" + "\n";
                                }
                            }
                            text += "\t" + "\t" + "\t" + "$array;" + "\n";
                            text += "\t" + "\t" + "\t" + "$uentity;" + "\n";
                        }
                        else
                        {

                            text += "\t" + "\t" + (String) entry.getKey() + ":"+ "@uentity;" + "\n";
                            text += "\t" + "\t" +  "name" + ":" + ((Entity)entry.getValue()).getName() + ";" + "\n"
                                    + "\t" + "\t" +  "id" + ":" + ((Entity)entry.getValue()).getId() + ";" + "\n"
                                    + "\t" + "\t" +  "attributes" + ":" + "\n" + "\t" + "\t" +  "\t" +"@array:" + "\n";
                            for (Map.Entry<Object, Object> entry1 : ((Entity) entry.getValue()).getAttributes().entrySet()) {

                                text += "\t" + "\t" + "\t" +  (String) entry1.getKey() + ":";
                                if(entry1.getValue() instanceof String)
                                    text += (String) entry1.getValue() + ";" + "\n";
                                else
                                {
                                    text += (int)entry1.getValue() + ";" + "\n";
                                }
                            }
                            text += "\t" + "\t" + "\t" + "$array;" + "\n";
                            text += "\t" + "\t" + "\t" + "$uentity;" + "\n";
                        }

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
                        while(!((line = br.readLine()).contains("$array")))
                        {
                            HashMap<Object,Object> atributi = new HashMap<>();
                            String s[] = line.split(":");

                            if(s[1].trim().equals("@uentity;"))
                            {
                                HashMap<String,Object> entity1 = new HashMap<>();
                                String name1 = "";
                                int id1 = 0;
                                line = br.readLine();
                                if(line.contains("name"))
                                {
                                    String str[] = line.split(":");
                                    name1 = (str[1].trim().replace(";",""));
                                    entity1.put(str[0].trim(),name1);
                                }
                                line = br.readLine();
                                if(line.contains("id"))
                                {
                                    String str[] = line.split(":");
                                    id1 = (Integer.parseInt(str[1].trim().replace(";","")));
                                    entity1.put(str[0].trim(),id1);
                                }

                                line = br.readLine();
                                if(line.contains("attributes"))
                                {
                                    line = br.readLine();
                                    line = br.readLine();

                                    while (!line.contains("$array;"))
                                    {

                                        String s1[] = line.split(":");
                                        atributi.put(s1[0].trim(), s1[1].trim().replace(";", ""));
                                        line = br.readLine();
                                    }
                                }
                                entity1.put("attributes",atributi);
                                entity.getAttributes().put(s[0].trim(),entity1);
                                line = br.readLine();
                            }
                            else {
                                entity.getAttributes().put(s[0].trim(), s[1].trim().replace(";", ""));

                            }
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
