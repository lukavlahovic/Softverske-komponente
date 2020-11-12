package controller;

import main.MainFrame;
import model.Entity;
import model.SearchParameters;
import model.TableModel;
import view.TablePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SearchListener implements ActionListener {

    private TableModel tableModel;
    private TablePanel tablePanel;

    public SearchListener(TableModel tableModel, TablePanel tablePanel){
        this.tableModel = tableModel;
        this.tablePanel = tablePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Properties p = MainFrame.getInstance().getProperty();
        String query = tablePanel.getQuery().getText();
        SearchParameters searchParameters = new SearchParameters();
        String equals = "";
        String not = "";
        String greaterThan="";
        String lesserThan="";
        String startsWith="";
        String endsWith="";
        if(query.startsWith("select")){
            query = query.substring(7);
            String[] parameters = query.split(",");
            //za i
            if(parameters[0].startsWith("and")){
                searchParameters.setAndOr("and");
                parameters[0] = parameters[0].substring(4);
                for(String parameter:parameters){
                    //entityName - entityname=student
                    if(parameter.contains("entityname")){
                        searchParameters.setEntityName(parameter.split("=")[1]);
                    }
                    //entityid - entityid=5
                    if(parameter.contains("entityid")){
                        searchParameters.setEntityId(parameter.split("=")[1]);
                    }
                    //equal
                    if(parameter.contains("=")) {
                        String[] poljeVrednost = parameter.split("=");
                        //negacija equala
                        if(parameter.contains("!")){
                            not+="equals!";
                            poljeVrednost[0] = poljeVrednost[0].substring(0,poljeVrednost[0].length()-1);
                            for(String key: poljeVrednost[0].split("\\."))
                                not+=key+":";
                            not+=poljeVrednost[1]+";";
                        }
                        else{
                            for(String key: poljeVrednost[0].split("\\."))
                                equals+=key+":";
                            equals+=poljeVrednost[1]+";";
                        }
                    }
                    //greaterThan
                    if(parameter.contains(">")) {
                        String[] poljeVrednost = parameter.split(">");
                        //negacija greaterThan
                        if(parameter.contains("!")){
                            not+="greaterThan!";
                            poljeVrednost[0] = poljeVrednost[0].substring(0,poljeVrednost[0].length()-1);
                            for(String key: poljeVrednost[0].split("\\."))
                                not+=key+":";
                            not+=poljeVrednost[1]+";";
                        }
                        else{
                            if(poljeVrednost[0].contains(".")) {
                                for (String key : poljeVrednost[0].split("\\."))
                                    greaterThan += key + ":";
                            }
                            else
                                greaterThan+=poljeVrednost[0]+":";
                            greaterThan+=poljeVrednost[1]+";";
                        }
                    }
                    //lesserThan
                    if(parameter.contains("<")) {
                        String[] poljeVrednost = parameter.split("<");
                        //negacija lesserThan
                        if(parameter.contains("!")){
                            not+="lesserThan!";
                            poljeVrednost[0] = poljeVrednost[0].substring(0,poljeVrednost[0].length()-1);
                            for(String key: poljeVrednost[0].split("\\."))
                                not+=key+":";
                            not+=poljeVrednost[1]+";";
                        }
                        else{
                            if(poljeVrednost[0].contains(".")) {
                                for (String key : poljeVrednost[0].split("\\."))
                                    lesserThan += key + ":";
                            }
                            else
                                lesserThan+=poljeVrednost[0]+":";
                            lesserThan+=poljeVrednost[1]+";";
                        }
                    }
                    //startsWith i endsWith
                    if(parameter.contains("-")){
                        //startsWith
                        //univerzitet.name-startWith?un
                        if(parameter.contains("startsWith"))
                        {
                            String[] poljeVrednost = parameter.split("-");
                            System.out.println("startsWith if "+poljeVrednost[0]+" "+poljeVrednost[1]);
                            //negacija startsWith
                            if (parameter.contains("!")) {
                                not += "startsWith!";
                                poljeVrednost[0] = poljeVrednost[0].substring(0, poljeVrednost[0].length() - 1);
                            } else {
                                System.out.println("USAO U ELESE");
//                                String[] s = poljeVrednost[0].split("\\.");
//                                System.out.println(s.length);
                                for (String key : poljeVrednost[0].split("\\.")) {
                                    System.out.println(key);
                                    startsWith += key + ":";
                                }
                                startsWith+=poljeVrednost[1].substring(11) + ";";
                            }
                        }
                        else if(parameter.contains("endsWith"))
                        {
                            String[] poljeVrednost = parameter.split("-");
                            System.out.println("endsWith if "+poljeVrednost[0]+" "+poljeVrednost[1]);
                            //negacija startsWith
                            if (parameter.contains("!")) {
                                not += "endsWith!";
                                poljeVrednost[0] = poljeVrednost[0].substring(0, poljeVrednost[0].length() - 1);
                            } else {
                                System.out.println("USAO U ENDS ELESE");
                                for (String key : poljeVrednost[0].split("\\.")) {
                                    System.out.println(key);
                                    endsWith += key + ":";
                                }
                                endsWith+=poljeVrednost[1].substring(9) + ";";
                            }
                        }
                    }
                }
            }
            //za ili
            //select or univerzitet.founded=2000,univerzitet.name!=union
            //equals!univerzitet:name:union;
            else if(parameters[0].startsWith("or")){
                searchParameters.setAndOr("or");
                parameters[0] = parameters[0].substring(3);
                for(String parameter:parameters){
                    //entityName - entityname=student
                    if(parameter.contains("entityname")){
                        searchParameters.setEntityName(parameter.split("=")[1]);
                    }
                    //entityid - entityid=5
                    if(parameter.contains("entityid")){
                        searchParameters.setEntityId(parameter.split("=")[1]);
                    }
                    //equal
                    if(parameter.contains("=")) {
                        String[] poljeVrednost = parameter.split("=");
                        //negacija equala
                        if(parameter.contains("!")){
                            not+="equals!";
                            poljeVrednost[0] = poljeVrednost[0].substring(0,poljeVrednost[0].length()-1);
                            for(String key: poljeVrednost[0].split("\\."))
                                not+=key+":";
                            not+=poljeVrednost[1]+";";
                        }
                        else{
                            for(String key: poljeVrednost[0].split("\\."))
                                equals+=key+":";
                            equals+=poljeVrednost[1]+";";
                        }
                    }
                    //greaterThan
                    if(parameter.contains(">")) {
                        String[] poljeVrednost = parameter.split(">");
                        //negacija greaterThan
                        if(parameter.contains("!")){
                            not+="greaterThan!";
                            poljeVrednost[0] = poljeVrednost[0].substring(0,poljeVrednost[0].length()-1);
                            for(String key: poljeVrednost[0].split("\\."))
                                not+=key+":";
                            not+=poljeVrednost[1]+";";
                        }
                        else{
                            if(poljeVrednost[0].contains(".")) {
                                for (String key : poljeVrednost[0].split("\\."))
                                    greaterThan += key + ":";
                            }
                            else
                                greaterThan+=poljeVrednost[0]+":";
                            greaterThan+=poljeVrednost[1]+";";
                        }
                    }
                    //lesserThan
                    if(parameter.contains("<")) {
                        String[] poljeVrednost = parameter.split("<");
                        //negacija lesserThan
                        if(parameter.contains("!")){
                            not+="lesserThan!";
                            poljeVrednost[0] = poljeVrednost[0].substring(0,poljeVrednost[0].length()-1);
                            for(String key: poljeVrednost[0].split("\\."))
                                not+=key+":";
                            not+=poljeVrednost[1]+";";
                        }
                        else{
                            if(poljeVrednost[0].contains(".")) {
                                for (String key : poljeVrednost[0].split("\\."))
                                    lesserThan += key + ":";
                            }
                            else
                                lesserThan+=poljeVrednost[0]+":";
                            lesserThan+=poljeVrednost[1]+";";
                        }
                    }
                    //startsWith i endsWith
                    if(parameter.contains("-")){
                        //startsWith
                        //univerzitet.name-startWith?un
                        if(parameter.contains("startsWith"))
                        {
                            String[] poljeVrednost = parameter.split("-");
                            System.out.println("startsWith if "+poljeVrednost[0]+" "+poljeVrednost[1]);
                            //negacija startsWith
                            if (parameter.contains("!")) {
                                not += "startsWith!";
                                poljeVrednost[0] = poljeVrednost[0].substring(0, poljeVrednost[0].length() - 1);
                            } else {
                                System.out.println("USAO U ELESE");
//                                String[] s = poljeVrednost[0].split("\\.");
//                                System.out.println(s.length);
                                for (String key : poljeVrednost[0].split("\\.")) {
                                    System.out.println(key);
                                    startsWith += key + ":";
                                }
                                startsWith+=poljeVrednost[1].substring(11) + ";";
                            }
                        }
                        else if(parameter.contains("endsWith"))
                        {
                            String[] poljeVrednost = parameter.split("-");
                            System.out.println("endsWith if "+poljeVrednost[0]+" "+poljeVrednost[1]);
                            //negacija startsWith
                            if (parameter.contains("!")) {
                                not += "endsWith!";
                                poljeVrednost[0] = poljeVrednost[0].substring(0, poljeVrednost[0].length() - 1);
                            } else {
                                System.out.println("USAO U ENDS ELESE");
                                for (String key : poljeVrednost[0].split("\\.")) {
                                    System.out.println(key);
                                    endsWith += key + ":";
                                }
                                endsWith+=poljeVrednost[1].substring(9) + ";";
                            }
                        }
                    }
                }
            }
            else{
                if(parameters.length==1) {
                    searchParameters.setAndOr("or");
                    for(String parameter:parameters){
                        //entityName - entityname=student
                        if(parameter.contains("entityname")){
                            searchParameters.setEntityName(parameter.split("=")[1]);
                        }
                        //entityid - entityid=5
                        if(parameter.contains("entityid")){
                            searchParameters.setEntityId(parameter.split("=")[1]);
                        }
                        //equal
                        if(parameter.contains("=")) {
                            String[] poljeVrednost = parameter.split("=");
                            //negacija equala
                            if(parameter.contains("!")){
                                not+="equals!";
                                poljeVrednost[0] = poljeVrednost[0].substring(0,poljeVrednost[0].length()-1);
                                for(String key: poljeVrednost[0].split("\\."))
                                    not+=key+":";
                                not+=poljeVrednost[1]+";";
                            }
                            else{
                                for(String key: poljeVrednost[0].split("\\."))
                                    equals+=key+":";
                                equals+=poljeVrednost[1]+";";
                            }
                        }
                        //greaterThan
                        if(parameter.contains(">")) {
                            String[] poljeVrednost = parameter.split(">");
                            //negacija greaterThan
                            if(parameter.contains("!")){
                                not+="greaterThan!";
                                poljeVrednost[0] = poljeVrednost[0].substring(0,poljeVrednost[0].length()-1);
                                for(String key: poljeVrednost[0].split("\\."))
                                    not+=key+":";
                                not+=poljeVrednost[1]+";";
                            }
                            else{
                                if(poljeVrednost[0].contains(".")) {
                                    for (String key : poljeVrednost[0].split("\\."))
                                        greaterThan += key + ":";
                                }
                                else
                                    greaterThan+=poljeVrednost[0]+":";
                                greaterThan+=poljeVrednost[1]+";";
                            }
                        }
                        //lesserThan
                        if(parameter.contains("<")) {
                            String[] poljeVrednost = parameter.split("<");
                            //negacija lesserThan
                            if(parameter.contains("!")){
                                not+="lesserThan!";
                                poljeVrednost[0] = poljeVrednost[0].substring(0,poljeVrednost[0].length()-1);
                                for(String key: poljeVrednost[0].split("\\."))
                                    not+=key+":";
                                not+=poljeVrednost[1]+";";
                            }
                            else{
                                if(poljeVrednost[0].contains(".")) {
                                    for (String key : poljeVrednost[0].split("\\."))
                                        lesserThan += key + ":";
                                }
                                else
                                    lesserThan+=poljeVrednost[0]+":";
                                lesserThan+=poljeVrednost[1]+";";
                            }
                        }
                        //startsWith i endsWith
                        if(parameter.contains("-")){
                            //startsWith
                            //univerzitet.name-startWith?un
                            if(parameter.contains("startsWith"))
                            {
                                String[] poljeVrednost = parameter.split("-");
                                System.out.println("startsWith if "+poljeVrednost[0]+" "+poljeVrednost[1]);
                                //negacija startsWith
                                if (parameter.contains("!")) {
                                    not += "startsWith!";
                                    poljeVrednost[0] = poljeVrednost[0].substring(0, poljeVrednost[0].length() - 1);
                                } else {
                                    System.out.println("USAO U ELESE");
//                                String[] s = poljeVrednost[0].split("\\.");
//                                System.out.println(s.length);
                                    for (String key : poljeVrednost[0].split("\\.")) {
                                        System.out.println(key);
                                        startsWith += key + ":";
                                    }
                                    startsWith+=poljeVrednost[1].substring(11) + ";";
                                }
                            }
                            else if(parameter.contains("endsWith"))
                            {
                                String[] poljeVrednost = parameter.split("-");
                                System.out.println("endsWith if "+poljeVrednost[0]+" "+poljeVrednost[1]);
                                //negacija startsWith
                                if (parameter.contains("!")) {
                                    not += "endsWith!";
                                    poljeVrednost[0] = poljeVrednost[0].substring(0, poljeVrednost[0].length() - 1);
                                } else {
                                    System.out.println("USAO U ENDS ELESE");
                                    for (String key : poljeVrednost[0].split("\\.")) {
                                        System.out.println(key);
                                        endsWith += key + ":";
                                    }
                                    endsWith+=poljeVrednost[1].substring(9) + ";";
                                }
                            }
                        }
                    }
                }
                else
                    System.out.println("GRESKA PRI UNOSU, FALI AND/OR");
            }
            //System.out.println("equals="+equals);
            //System.out.println("not="+endsWith);
            System.out.println("greaterThan="+greaterThan);
            System.out.println("endsWith="+endsWith);
            searchParameters.setNot(not);
            searchParameters.setEquals(equals);
            searchParameters.setGreaterThan(greaterThan);
            searchParameters.setLesserThan(lesserThan);
            searchParameters.setStartsWith(startsWith);
            searchParameters.setEndsWith(endsWith);
            String pathToDirectory = p.getProperty("pathToDirectory");
            ArrayList<Entity> result = MainFrame.getInstance().getDataRepository().find(searchParameters,pathToDirectory);
            for(Entity entity:result){
                tableModel.addRow(new Object[]{entity.getId(),entity.getName(),entity.getAttributes()});
            }
        }
    }
}
