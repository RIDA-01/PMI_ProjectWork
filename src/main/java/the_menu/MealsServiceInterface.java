package the_menu;

import java.util.ArrayList;

import features.Meal;

public interface MealsServiceInterface {
    public Boolean addMeal(Meal medicine);
    public Boolean deleteMeal(String name);
    public Boolean updateMeal(String name, Meal meal);
    public ArrayList<Meal> listMeals();
    public Boolean existsMeal(String name);
    public Double priceMeale(String name);
    public ArrayList<Meal> listMealsshopkeeper(String name);
}
