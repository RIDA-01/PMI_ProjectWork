package the_menu;


import java.util.ArrayList;

import features.*;

public class MealsService implements MealsServiceInterface{
    private ArrayList<Meal>  meals = null;

    public MealsService() {
        this.meals = new XmlReaderService().readMeals();
    }

    @Override
    public Boolean addMeal(Meal meal) {

        if(meal.getPrice()<0.0)
            meal.setPrice(0.0);

        if(!existsMeal(meal.getName()))
            return new XmlWriterService().addMealXML(meal);
        return false;
    }

    @Override
    public Boolean deleteMeal(String username) {
        return new XmlWriterService().deleteMealXML(username);
    }

    @Override
    public Boolean updateMeal(String name, Meal meal) {
        if(existsMeal(name))
            return new XmlWriterService().updateMealXML(name, meal);
        return false;
    }

    @Override
    public ArrayList<Meal> listMeals() {
        meals = new XmlReaderService().readMeals();
        return this.meals;
    }

    @Override
    public Boolean existsMeal(String name) {
        for (Meal m : meals){
            if(m.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Double priceMeale(String name) {
        for (Meal m : meals){
            if(m.getName().equals(name)){
                return m.getPrice();
            }
        }
        return -1.0;
    }

    @Override
    public ArrayList<Meal> listMealsshopkeeper(String name) {
        meals = new XmlReaderService().readMeals();
        ArrayList<Meal> list = new ArrayList<>();
        for (Meal m : meals){
            if(m.getShopkeeper().equals(name)){
                list.add(m);
            }
        }
        return list;
    }
}
