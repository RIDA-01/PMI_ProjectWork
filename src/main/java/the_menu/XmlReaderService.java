package the_menu;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import features.*;

import javax.xml.parsers.*;
import java.io.*;

import java.util.ArrayList;

public class XmlReaderService implements XmlReaderServiceInterface{
    private final String userPath = "src/main/resources/users.xml";
    private final String mealsPath = "src/main/resources/meals.xml";
    private final String recieptPath = "src/main/resources/reciept.xml";

    @Override
    public Boolean existsUserXML() {
        File file = new File(userPath);
        return file.exists();
    }


    @Override
    public Boolean existsMealsXML() {
        File file = new File(mealsPath);
        return file.exists();
    }


    @Override
    public Boolean createUserXML() {
        if(!existsUserXML()){
            File file = new File(userPath);
            try {
                if(file.createNewFile()){
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    @Override
    public Boolean createMealsXML() {
        if(!existsUserXML()){
            File file = new File(mealsPath);
            try {
                if(file.createNewFile()){
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

  

    @Override
    public ArrayList<User> readUsers() {
        ArrayList<User> users = new ArrayList<>();

        if(existsUserXML()){
            File file = new File(userPath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(file);
                document.getDocumentElement().normalize();

                Element rootElement = document.getDocumentElement();

                NodeList nodeList = rootElement.getChildNodes();
                Node node;
                for (int i = 0; i<nodeList.getLength(); i++){
                    node = nodeList.item(i);

                    if(node.getNodeType() == Node.ELEMENT_NODE){
                        NodeList childNodesOfUserTag = node.getChildNodes();
                        User user = new User();
                        for (int j = 0; j< childNodesOfUserTag.getLength(); j++){
                            Node childNodeOfUserTag = childNodesOfUserTag.item(j);
                            if(childNodeOfUserTag.getNodeType() == Node.ELEMENT_NODE){
                                switch (childNodeOfUserTag.getNodeName()){
                                    case "name" -> user.setName(childNodeOfUserTag.getTextContent());
                                    case "username" -> user.setUsername(childNodeOfUserTag.getTextContent());
                                    case "password" -> user.setPassword(childNodeOfUserTag.getTextContent());
                                    case "level" -> user.setLevel(Integer.parseInt(childNodeOfUserTag.getTextContent()));
                                }
                            }
                        }

                        users.add(user);
                    }
                }
            } catch (ParserConfigurationException | IOException | SAXException e) {
                e.printStackTrace();
            }
        } else{
            if(createUserXML()){
                readUsers();
            } else {
                return null;
            }
        }

        return users;
    }

    @Override
    public ArrayList<Meal> readMeals() {
        ArrayList<Meal> meals = new ArrayList<>();

        if(existsMealsXML()){
            File file = new File(mealsPath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(file);
                document.getDocumentElement().normalize();

                Element rootElement = document.getDocumentElement();

                NodeList nodeList = rootElement.getChildNodes();
                Node node;
                for (int i = 0; i<nodeList.getLength(); i++){
                    node = nodeList.item(i);

                    if(node.getNodeType() == Node.ELEMENT_NODE){
                        NodeList childNodesOfUserTag = node.getChildNodes();
                        Meal meal = new Meal();
                        for (int j = 0; j< childNodesOfUserTag.getLength(); j++){
                            Node childNodeOfUserTag = childNodesOfUserTag.item(j);
                            if(childNodeOfUserTag.getNodeType() == Node.ELEMENT_NODE){
                                switch (childNodeOfUserTag.getNodeName()){
                                    case "name" -> meal.setName(childNodeOfUserTag.getTextContent());
                                    case "shopkeeper" -> meal.setShopkeeper(childNodeOfUserTag.getTextContent());
                                    case "price" -> meal.setPrice(Double.parseDouble(childNodeOfUserTag.getTextContent()));
                                }
                            }
                        }

                        meals.add(meal);
                    }
                }
            } catch (ParserConfigurationException | IOException | SAXException e) {
                e.printStackTrace();
            }
        } else{
            if(createMealsXML()){
                readMeals();
            } else {
                return null;
            }
        }

        return meals;
    }

    @Override
    public ArrayList<Reciept> readReciepts() {
        ArrayList<Reciept> reciepts = new ArrayList<>();

        if(existsRecieptXML()){
            File file = new File(recieptPath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(file);
                document.getDocumentElement().normalize();

                Element rootElement = document.getDocumentElement();

                NodeList nodeList = rootElement.getChildNodes();
                Node node;
                for (int i = 0; i<nodeList.getLength(); i++){
                    node = nodeList.item(i);

                    if(node.getNodeType() == Node.ELEMENT_NODE){
                        NodeList childNodesOfUserTag = node.getChildNodes();
                        Reciept reciept = new Reciept();
                        for (int j = 0; j < childNodesOfUserTag.getLength(); j++){
                            Node childNodeOfUserTag = childNodesOfUserTag.item(j);
                            if(childNodeOfUserTag.getNodeType() == Node.ELEMENT_NODE){
                                switch (childNodeOfUserTag.getNodeName()){
                                    case "id" -> reciept.setId(Integer.parseInt(childNodeOfUserTag.getTextContent()));
                                    case "costumerName" -> reciept.setCostumerName(childNodeOfUserTag.getTextContent());
                                    case "mealName" -> reciept.setMealName(childNodeOfUserTag.getTextContent());
                                    case "totalPrice" -> reciept.setPrice(Double.parseDouble(childNodeOfUserTag.getTextContent()));
                                    case "date" -> reciept.setDate(childNodeOfUserTag.getTextContent());
                                }
                            }
                        }

                        reciept.add(reciept);
                    }
                }
            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }
        } else{
            if(createRecieptsXML()){
                readReciepts();
            } else {
                return null;
            }
        }

        return reciepts;
    }


    @Override
    public Boolean existsRecieptXML() {
        File file = new File(recieptPath);
        return file.exists();
    }


    @Override
    public Boolean createRecieptsXML() {
        if(!existsUserXML()){
            File file = new File(recieptPath);
            try {
                if(file.createNewFile()){
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
