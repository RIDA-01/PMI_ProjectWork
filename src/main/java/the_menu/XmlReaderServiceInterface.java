package the_menu;

import java.util.ArrayList;

import features.*;

public interface XmlReaderServiceInterface {
    public Boolean existsUserXML();
    public Boolean existsMealsXML();
    public Boolean existsRecieptXML();

    public Boolean createUserXML();
    public Boolean createMealsXML();
    public Boolean createRecieptsXML();

    public ArrayList<User> readUsers();
    public ArrayList<Meal> readMeals();
    public ArrayList<Reciept> readReciepts();
}
