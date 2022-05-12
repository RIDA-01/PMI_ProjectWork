package the_menu;

import java.util.ArrayList;

import features.Meal;
import features.User;

public class ShopkeeperService implements ShopkeeperServiceInterface{

    @Override
    public ArrayList<Meal> listMealReport(User user) {
        ArrayList<Meal> list = new ArrayList<>();
        for (Meal meal : new MealsService().listMeals()){
            if(meal.getShopkeeper().equals(user.getName())){
                list.add(meal);
            }
        }
        return list;
    }

   
}
