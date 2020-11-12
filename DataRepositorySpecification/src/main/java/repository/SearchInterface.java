package repository;

import model.Entity;
import model.SearchParameters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class SearchInterface {

    public boolean entityNameSearch(Entity entity,SearchParameters searchParameters){
        return entity.getName().equals(searchParameters.getEntityName());
    }

    public boolean entityIdSearch(Entity entity,SearchParameters searchParameters){
        //return Integer.toString(entity.getId()).equals(searchParameters.getEntityId());
        return entity.getId()==(Integer.parseInt(searchParameters.getEntityId()));
    }

    /**
     *
     * @param entity
     * @param searchParameters
     * @return true if not found, false otherwise
     */
    public boolean equalsSearch(Entity entity, SearchParameters searchParameters){
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
                        continue;
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
        return notFound;
    }
    public boolean startsWithSearch(Entity entity,SearchParameters searchParameters){
        boolean notFound = false;
        for(String equal: searchParameters.getStartsWith().split(";"))
        {
            String[] keyValue = equal.split(":");
            if (keyValue.length==2)
            {
                if(!(entity.getAttributes().containsKey(keyValue[0])))
                {
                    notFound = true;
                    break;
                }
                else if(!(entity.getAttributes().get(keyValue[0]).toString().startsWith(keyValue[1])))
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

//                                if(keyValue[1].equals("id")&&keyValue[2].equals(Integer.toString((int)map.get(keyValue[1]))))
//                                {
//                                    continue;
//                                }
                    if (keyValue[1].equals("name")&&map.get(keyValue[1]).toString().startsWith(keyValue[2]))
                    {
                        continue;
                    }
                    else if(((HashMap)map.get("attributes")).get(keyValue[1]).toString().startsWith(keyValue[2]))
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
        return notFound;
    }
    public boolean endsWithSearch(Entity entity, SearchParameters searchParameters){
        boolean notFound = false;
        for(String equal: searchParameters.getEndsWith().split(";"))
        {
            String[] keyValue = equal.split(":");
            if (keyValue.length==2)
            {
                if(!(entity.getAttributes().containsKey(keyValue[0])))
                {
                    notFound = true;
                    break;
                }
                else if(!(entity.getAttributes().get(keyValue[0]).toString().endsWith(keyValue[1])))
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

//                                if(keyValue[1].equals("id")&&keyValue[2].equals(Integer.toString((int)map.get(keyValue[1]))))
//                                {
//                                    continue;
//                                }
                    if (keyValue[1].equals("name")&&map.get(keyValue[1]).toString().endsWith(keyValue[2]))
                    {
                        continue;
                    }
                    else if(((HashMap)map.get("attributes")).get(keyValue[1]).toString().endsWith(keyValue[2]))
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
        return notFound;
    }
    public boolean greaterThanSearch(Entity entity, SearchParameters searchParameters){
        boolean notFound = false;
        for(String equal: searchParameters.getGreaterThan().split(";"))
        {
            String[] keyValue = equal.split(":");
            if (keyValue.length==2)
            {
                int greaterThan = Integer.parseInt(keyValue[1]);
                if(keyValue[0].equals("id"))
                {
                    if(!(entity.getId()>greaterThan))
                    {
                        notFound = true;
                        break;
                    }
                }
                else if(entity.getAttributes().containsKey(keyValue[0]))
                {
                    if(!(Integer.parseInt(entity.getAttributes().get(keyValue[0]).toString())>greaterThan))
                    {
                        notFound = true;
                        break;
                    }
                }
                else {
                    notFound = true;
                    break;
                }
            }
            else
            {
                int greaterThan = Integer.parseInt(keyValue[2]);
                if(entity.getAttributes().containsKey(keyValue[0]))
                {
                    HashMap map = ((HashMap)entity.getAttributes().get(keyValue[0]));

                    if(keyValue[1].equals("id")&&(int)map.get(keyValue[1])>greaterThan)
                    {
                        continue;
                    }
//                                else if (keyValue[1].equals("name")&&keyValue[2].equals(map.get(keyValue[1])))
//                                {
//                                    continue;
//                                }
                    else if(Integer.parseInt(((HashMap)map.get("attributes")).get(keyValue[1]).toString())>greaterThan)
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
        return notFound;
    }
    public boolean lesserThanSearch(Entity entity, SearchParameters searchParameters){
        boolean notFound = false;
        for(String equal: searchParameters.getLesserThan().split(";"))
        {
            String[] keyValue = equal.split(":");
            if (keyValue.length==2)
            {
                int lesserThan = Integer.parseInt(keyValue[1]);
                if(keyValue[0].equals("id"))
                {
                    if(!(entity.getId()<lesserThan))
                    {
                        notFound = true;
                        break;
                    }
                }
                else if(entity.getAttributes().containsKey(keyValue[0]))
                {
                    if(!(Integer.parseInt(entity.getAttributes().get(keyValue[0]).toString())<lesserThan))
                    {
                        notFound = true;
                        break;
                    }
                }
                else{
                    notFound=true;
                    break;
                }
            }
            else
            {
                int lesserThan = Integer.parseInt(keyValue[2]);
                if(entity.getAttributes().containsKey(keyValue[0]))
                {
                    HashMap map = ((HashMap)entity.getAttributes().get(keyValue[0]));

                    if(keyValue[1].equals("id")&&(int)map.get(keyValue[1])<lesserThan)
                    {
                        continue;
                    }
//                                else if (keyValue[1].equals("name")&&keyValue[2].equals(map.get(keyValue[1])))
//                                {
//                                    continue;
//                                }
                    else if(Integer.parseInt(((HashMap)map.get("attributes")).get(keyValue[1]).toString())<lesserThan)
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
        return notFound;
    }
    public boolean notSearch(Entity entity, SearchParameters searchParameters){
        boolean notFound = false;
        for(String equal: searchParameters.getNot().split(";"))
        {
            String[] keyValue = equal.split("!");
            switch (keyValue[0]){
                case "entityname":
                    SearchParameters searchParameters1 = new SearchParameters();
                    searchParameters1.setEntityName(keyValue[1]);
                    notFound = !entityNameSearch(entity,searchParameters1);
                    break;
                case "entityid":
                    SearchParameters searchParameters2 = new SearchParameters();
                    searchParameters2.setEntityId(keyValue[1]);
                    notFound = !entityIdSearch(entity,searchParameters2);
                    break;
                case "equals":
                    SearchParameters searchParameters3 = new SearchParameters();
                    searchParameters3.setEquals(keyValue[1]);
                    notFound = equalsSearch(entity,searchParameters3);
                    break;
                case "startsWith":
                    SearchParameters searchParameters4 = new SearchParameters();
                    searchParameters4.setStartsWith(keyValue[1]);
                    notFound = startsWithSearch(entity,searchParameters4);
                    break;
                case "endsWith":
                    SearchParameters searchParameters5 = new SearchParameters();
                    searchParameters5.setEndsWith(keyValue[1]);
                    notFound = endsWithSearch(entity,searchParameters5);
                    break;
                case "greaterThan":
                    SearchParameters searchParameters6 = new SearchParameters();
                    searchParameters6.setGreaterThan(keyValue[1]);
                    notFound = greaterThanSearch(entity,searchParameters6);
                    break;
                case "lesserThan":
                    SearchParameters searchParameters7 = new SearchParameters();
                    searchParameters7.setLesserThan(keyValue[1]);
                    notFound = lesserThanSearch(entity,searchParameters7);
                    break;
                default:
                    System.out.println("Neispravni parametri");
            }
        }
        return !notFound;
    }

    public ArrayList<Entity> find(List<Entity> entities, SearchParameters searchParameters){
        ArrayList<Entity> result = new ArrayList<Entity>();
        boolean isOr = searchParameters.getAndOr().equals("or");
        boolean notFound=false;
        for(Entity entity: entities)
        {
            if(searchParameters.getEntityName()!=null)
            {
                if (!(entityNameSearch(entity,searchParameters)))
                {
                    continue;
                }
                else if(isOr)
                {
                    result.add(entity);
                    continue;
                }
            }
            if(searchParameters.getEntityId()!=null)
            {
                if(!(entityIdSearch(entity,searchParameters)))
                {
                    continue;
                }
                else if(isOr)
                {
                    result.add(entity);
                    continue;
                }
            }
            if (!searchParameters.getEquals().equals(""))
            {
                notFound = equalsSearch(entity,searchParameters);
                if(notFound && !isOr)
                {

                    continue;
                }
                else if(!notFound && isOr)
                {
                    result.add(entity);
                    continue;
                }
            }
            if(!searchParameters.getStartsWith().equals(""))
            {
                notFound = startsWithSearch(entity,searchParameters);
                if(notFound && !isOr)
                {

                    continue;
                }
                else if(!notFound && isOr)
                {
                    result.add(entity);
                    continue;
                }
            }
            if(!searchParameters.getEndsWith().equals(""))
            {
                notFound = endsWithSearch(entity,searchParameters);
                if(notFound && !isOr)
                {

                    continue;
                }
                else if(!notFound && isOr)
                {
                    result.add(entity);
                    continue;
                }
            }
            if(!searchParameters.getGreaterThan().equals(""))
            {
                notFound = greaterThanSearch(entity,searchParameters);
                if(notFound && !isOr)
                {

                    continue;
                }
                else if(!notFound && isOr)
                {
                    result.add(entity);
                    continue;
                }
            }
            if(!searchParameters.getLesserThan().equals(""))
            {
                notFound = lesserThanSearch(entity,searchParameters);
                if(notFound && !isOr)
                {

                    continue;
                }
                else if(!notFound && isOr)
                {
                    result.add(entity);
                    continue;
                }
            }
            if(!searchParameters.getNot().equals("")){
                notFound = notSearch(entity,searchParameters);
                if(notFound && !isOr)
                {

                    continue;
                }
                else if(!notFound && isOr)
                {
                    result.add(entity);
                    continue;
                }
            }
            if(!notFound)
                result.add(entity);
        }
        return result;
    }
}
