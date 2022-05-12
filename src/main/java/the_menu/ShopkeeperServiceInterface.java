package the_menu;

import java.util.ArrayList;

import features.Meal;
import features.User;

public interface ShopkeeperServiceInterface {
    public ArrayList<Meal> listMealReport(User user);

}
