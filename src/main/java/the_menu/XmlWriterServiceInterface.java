package the_menu;

import features.Meal;
import features.Reciept;
import features.User;

public interface XmlWriterServiceInterface {
    public Boolean addUserXML(User user);
    public Boolean deleteUserXML(String username);
    public Boolean updateUserXML(String username, User user);

    public Boolean addMealXML(Meal meal);
    public Boolean deleteMealXML(String username);
    public Boolean updateMealXML(String username, Meal meal);

    public Boolean addRecieptXML(Reciept meal);
    public Boolean deleterecieptXML(Integer id);
    public Boolean updaterecieptXML(Integer id, Reciept reciept);
}
