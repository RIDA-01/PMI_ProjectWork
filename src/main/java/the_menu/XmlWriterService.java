package the_menu;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import features.Meal;
import features.Reciept;
import features.User;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XmlWriterService implements XmlWriterServiceInterface{
    private final String userPath = "src/main/resouces/users.xml";
    private final String mealsPath = "src/main/resouces/meals.xml";
    private final String recieptPath = "src/main/resouces/reciepts.xml";


    @Override
    public Boolean addUserXML(User user) {
        XmlReaderService xmlReaderService = new XmlReaderService();
        if(xmlReaderService.existsUserXML()){
            File file = new File(userPath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(file);
                document.getDocumentElement().normalize();
                Element rootElement = document.getDocumentElement();
                Element superuser = document.createElement("user");
                rootElement.appendChild(superuser);
                Element element_name = document.createElement("name");
                element_name.appendChild(document.createTextNode(user.getName()));
                superuser.appendChild(element_name);

                Element element_username = document.createElement("username");
                element_username.appendChild(document.createTextNode(user.getUsername()));
                superuser.appendChild(element_username);

                Element element_password = document.createElement("password");
                element_password.appendChild(document.createTextNode(user.getPassword()));
                superuser.appendChild(element_password);

                Element element_level = document.createElement("level");
                element_level.appendChild(document.createTextNode(String.valueOf(user.getLevel())));
                superuser.appendChild(element_level);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();

                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");


                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(file);
                transformer.transform(source, result);

                return true;
            } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
                e.printStackTrace();
            }
        } else{
            if(xmlReaderService.createUserXML()){
                addUserXML(user);
            } else {
                return false;
            }
        }

        return false;
    }

    @Override
    public Boolean deleteUserXML(String username) {
        XmlReaderService service = new XmlReaderService();
        if(service.existsUserXML()){
            File file = new File(userPath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(file);
                document.getDocumentElement().normalize();

                Element rootElement = document.getDocumentElement();

                NodeList nodeList = rootElement.getChildNodes();
                Node node;
                int index = -1;
                for (int i = 0; i<nodeList.getLength(); i++){
                    node = nodeList.item(i);

                    if(node.getNodeType() == Node.ELEMENT_NODE){
                        NodeList childNodesOfUserTag = node.getChildNodes();
                        for (int j = 0; j< childNodesOfUserTag.getLength(); j++){
                            Node childNodeOfUserTag = childNodesOfUserTag.item(j);
                            if(childNodeOfUserTag.getNodeType() == Node.ELEMENT_NODE){
                                if(childNodeOfUserTag.getNodeName().equals("username")){
                                    if(childNodeOfUserTag.getTextContent().equals(username)){
                                        index = i;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }

                if(index!=-1){
                    rootElement.removeChild(nodeList.item(index));
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();

                    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                    DOMSource source = new DOMSource(document);
                    StreamResult result = new StreamResult(file);
                    transformer.transform(source, result);

                    return true;
                } else
                    return false;
            } catch (IOException | SAXException | ParserConfigurationException | TransformerException e) {
                e.printStackTrace();
            }

        } else{
            if(service.createUserXML()){
                service.readUsers();
            } else {
                return false;
            }
        }

        return false;
    }

    @Override
    public Boolean updateUserXML(String username, User user) {
        XmlReaderService service = new XmlReaderService();
        if(service.existsUserXML()){
            File file = new File(userPath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(file);
                document.getDocumentElement().normalize();

                Element rootElement = document.getDocumentElement();

                NodeList nodeList = rootElement.getChildNodes();
                Node node;

                for (int i = 0; i<nodeList.getLength(); i++) {
                    node = nodeList.item(i);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        NodeList childNodesOfUserTag = node.getChildNodes();
                        for (int j = 0; j < childNodesOfUserTag.getLength(); j++) {
                            Node childNodeOfUserTag = childNodesOfUserTag.item(j);
                            if (childNodeOfUserTag.getNodeType() == Node.ELEMENT_NODE) {
                                if (childNodeOfUserTag.getTextContent().equals(username)) {

                                    if (!user.getUsername().isEmpty() && "username".equals(childNodeOfUserTag.getNodeName())) {
                                        childNodeOfUserTag.setTextContent(user.getUsername());
                                    }

                                    if (!user.getName().isEmpty() && "name".equals(childNodeOfUserTag.getNodeName())) {
                                        childNodeOfUserTag.setTextContent(user.getName());
                                    }

                                    if (!user.getPassword().isEmpty() && "password".equals(childNodeOfUserTag.getNodeName())) {
                                        childNodeOfUserTag.setTextContent(user.getPassword());
                                    }

                                    if (user.getLevel() != 0 && "level".equals(childNodeOfUserTag.getNodeName())) {
                                        childNodeOfUserTag.setTextContent(String.valueOf(user.getLevel()));
                                    }
                                }
                            }
                        }
                    }
                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();

                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(file);
                transformer.transform(source, result);

                return true;
            } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
                e.printStackTrace();
            }
        } else{
            if(service.createUserXML()){
                service.readUsers();
            } else {
                return false;
            }
        }

        return false;
    }

    @Override
    public Boolean addMealXML(Meal meal) {
        XmlReaderService xmlReaderService = new XmlReaderService();
        if(xmlReaderService.existsUserXML()){
            File file = new File(mealsPath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(file);
                document.getDocumentElement().normalize();
                Element rootElement = document.getDocumentElement();
                Element superuser = document.createElement("meal");
                rootElement.appendChild(superuser);
                Element element_name = document.createElement("name");
                element_name.appendChild(document.createTextNode(meal.getName()));
                superuser.appendChild(element_name);

                Element element_shopkeeper = document.createElement("shopkeeper");
                element_shopkeeper.appendChild(document.createTextNode(meal.getShopkeeper()));
                superuser.appendChild(element_shopkeeper);

                Element element_price = document.createElement("price");
                element_price.appendChild(document.createTextNode(meal.getShopkeeper()));
                superuser.appendChild(element_price);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();

                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(file);
                transformer.transform(source, result);

                return true;
            } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
                e.printStackTrace();
            }
        } else{
            if(xmlReaderService.createUserXML()){
                addMealXML(meal);
            } else {
                return false;
            }
        }

        return false;
    }

    @Override
    public Boolean deleteMealXML(String name) {
        XmlReaderService service = new XmlReaderService();
        if(service.existsUserXML()){
            File file = new File(mealsPath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(file);
                document.getDocumentElement().normalize();

                Element rootElement = document.getDocumentElement();

                NodeList nodeList = rootElement.getChildNodes();
                Node node;
                int index = -1;
                for (int i = 0; i<nodeList.getLength(); i++){
                    node = nodeList.item(i);

                    if(node.getNodeType() == Node.ELEMENT_NODE){
                        NodeList childNodesOfUserTag = node.getChildNodes();
                        for (int j = 0; j< childNodesOfUserTag.getLength(); j++){
                            Node childNodeOfUserTag = childNodesOfUserTag.item(j);
                            if(childNodeOfUserTag.getNodeType() == Node.ELEMENT_NODE){
                                if(childNodeOfUserTag.getNodeName().equals("name")){
                                    if(childNodeOfUserTag.getTextContent().equals(name)){
                                        index = i;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }

                if(index!=-1){
                    rootElement.removeChild(nodeList.item(index));
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();

                    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                    DOMSource source = new DOMSource(document);
                    StreamResult result = new StreamResult(file);
                    transformer.transform(source, result);

                    return true;
                } else
                    return false;
            } catch (IOException | SAXException | ParserConfigurationException | TransformerException e) {
                e.printStackTrace();
            }

        } else{
            if(service.createMealsXML()){
                service.readMeals();
            } else {
                return false;
            }
        }

        return false;
    }

    @Override
    public Boolean updateMealXML(String name, Meal meal) {
        XmlReaderService service = new XmlReaderService();
        if(service.existsUserXML()){
            File file = new File(mealsPath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(file);
                document.getDocumentElement().normalize();

                NodeList medList = document.getElementsByTagName("meal");
                Element med = null;
                for (int i = 0; i < medList.getLength(); i++) {
                    med = (Element) medList.item(i);
                    if(med.getElementsByTagName("name").item(0).getTextContent().equals(name)){

                        Node name_meal = med.getElementsByTagName("name").item(0).getFirstChild();
                        name_meal.setNodeValue(meal.getName());

                        Node shopkeeper_meal = med.getElementsByTagName("shopkeeper").item(0).getFirstChild();
                        shopkeeper_meal.setNodeValue(meal.getShopkeeper());

                        Node price_meal = med.getElementsByTagName("price").item(0).getFirstChild();
                        price_meal.setNodeValue(String.valueOf(meal.getPrice()));
                    }
                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();

                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(file);
                transformer.transform(source, result);

                return true;

            } catch (IOException | SAXException | ParserConfigurationException | TransformerException e) {
                e.printStackTrace();
            }

        } else{
            if(service.createMealsXML()){
                service.readMeals();
            } else {
                return false;
            }
        }

        return false;
    }

    @Override
    public Boolean addRecieptXML(Reciept reciept) {
        XmlReaderService xmlReaderService = new XmlReaderService();
        if(xmlReaderService.existsRecieptXML()){
            File file = new File(recieptPath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(file);
                document.getDocumentElement().normalize();
                Element rootElement = document.getDocumentElement();
                Element superuser = document.createElement("reciept");
                rootElement.appendChild(superuser);
                Element element_id = document.createElement("id");
                element_id.appendChild(document.createTextNode(String.valueOf(reciept.getId())));
                superuser.appendChild(element_id);

                Element element_costumerName = document.createElement("costumer Name");
                element_costumerName.appendChild(document.createTextNode(reciept.getCostumerName()));
                superuser.appendChild(element_costumerName);

                Element element_mealName = document.createElement("meal Name");
                element_mealName.appendChild(document.createTextNode(reciept.getmeal()));
                superuser.appendChild(element_mealName);

                Element element_totalPrice = document.createElement("total Price");
                element_totalPrice.appendChild(document.createTextNode(String.valueOf(reciept.getPrice())));
                superuser.appendChild(element_totalPrice);

                Element element_date = document.createElement("date");
                element_date.appendChild(document.createTextNode(String.valueOf(reciept.getDate())));
                superuser.appendChild(element_date);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();

                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(file);
                transformer.transform(source, result);

                return true;
            } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
                e.printStackTrace();
            }
        } else{
            if(xmlReaderService.createRecieptsXML()){
                addRecieptXML(reciept);
            } else {
                return false;
            }
        }

        return false;
    }

    @Override
    public Boolean deleterecieptXML(Integer id) {
        XmlReaderService service = new XmlReaderService();
        if(service.existsRecieptXML()){
            File file = new File(recieptPath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(file);
                document.getDocumentElement().normalize();

                Element rootElement = document.getDocumentElement();

                NodeList nodeList = rootElement.getChildNodes();
                Node node;
                int index = -1;
                for (int i = 0; i<nodeList.getLength(); i++){
                    node = nodeList.item(i);

                    if(node.getNodeType() == Node.ELEMENT_NODE){
                        NodeList childNodesOfUserTag = node.getChildNodes();
                        for (int j = 0; j< childNodesOfUserTag.getLength(); j++){
                            Node childNodeOfUserTag = childNodesOfUserTag.item(j);
                            if(childNodeOfUserTag.getNodeType() == Node.ELEMENT_NODE){
                                if(childNodeOfUserTag.getNodeName().equals("id")){
                                    if(childNodeOfUserTag.getTextContent().equals(String.valueOf(id))){
                                        index = i;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }

                if(index!=-1){
                    rootElement.removeChild(nodeList.item(index));
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();

                    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                    DOMSource source = new DOMSource(document);
                    StreamResult result = new StreamResult(file);
                    transformer.transform(source, result);

                    return true;
                } else
                    return false;
            } catch (IOException | SAXException | ParserConfigurationException | TransformerException e) {
                e.printStackTrace();
            }

        } else{
            if(service.createMealsXML()){
                service.readMeals();
            } else {
                return false;
            }
        }

        return false;
    }

    @Override
    public Boolean updaterecieptXML(Integer id, Reciept reciept) {
        XmlReaderService service = new XmlReaderService();
        if(service.existsUserXML()){
            File file = new File(recieptPath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(file);
                document.getDocumentElement().normalize();

                NodeList medList = document.getElementsByTagName("reciept");
                Element med = null;
                for (int i = 0; i < medList.getLength(); i++) {
                    med = (Element) medList.item(i);
                    if(Integer.parseInt(med.getElementsByTagName("id").item(0).getTextContent()) == id){

                        Node name_costumerName = med.getElementsByTagName("costumer Name").item(0).getFirstChild();
                        name_costumerName.setNodeValue(reciept.getCostumerName());

                        Node name_meal = med.getElementsByTagName("meal Name").item(0).getFirstChild();
                        name_meal.setNodeValue(reciept.getmeal());

                        Node price_meal = med.getElementsByTagName("totalPrice").item(0).getFirstChild();
                        price_meal.setNodeValue(String.valueOf(reciept.getPrice()));

                    }
                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();

                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(file);
                transformer.transform(source, result);

                return true;

            } catch (IOException | SAXException | ParserConfigurationException | TransformerException e) {
                e.printStackTrace();
            }

        } else{
            if(service.createMealsXML()){
                service.readMeals();
            } else {
                return false;
            }
        }

        return false;
    }
}