package finall;

import java.util.*;
import java.util.stream.Stream;

import features.*;
import the_menu.MealsService;
import the_menu.MessageService;
import the_menu.ValidateService;


public abstract class MenuMeal {
    private static MealsService service;
    private static ValidateService validateService;
    public static void ShowAddMeal() {
        validateService = new ValidateService();

        service = new MealsService();

        System.out.print("\nadd a meal to our menu : \n");
        Scanner sc = new Scanner(System.in);
        Meal meal = new Meal();
        System.out.print("\nname of the meal please: ");
        meal.setName(sc.next());

        System.out.print("Enter the original country of this meal: ");
        meal.setShopkeeper(sc.next());


        String number = "";
        do{
            System.out.print("Enter the price: ");
            number = sc.next();
            if(!validateService.checkDoubleNumber(number)){
                MessageService.ShowInvalidNumber();
            }
        } while(!validateService.checkDoubleNumber(number));

        meal.setPrice(Double.parseDouble(number));


      

    }

    public static void ShowRemoveMeal() {
        service = new MealsService();
        validateService = new ValidateService();

        System.out.print("\nremove a meal from our menu : \n");
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter the name: ");
        String name = sc.next();

        if(service.deleteMeal(name)){
            MessageService.ShowSuccessfulMealDeletedMessage();
        } else{
            MessageService.ShowFailedDeleteMessage();
        }

    }

    public static void ShowUpdateMeal() {
        service = new MealsService();
        validateService = new ValidateService();

        System.out.print("\nupdating a meal from our menu : \n");
        Scanner sc = new Scanner(System.in);

        Meal meal = new Meal();

        System.out.print("old name of the meal :  ");
        String name = sc.next();

        System.out.print("new name of the meal :  ");
        meal.setName(sc.next());

        System.out.print("new original country of the meal : ");
        meal.setShopkeeper(sc.next());

        String number = "";
        do{
            System.out.print("new price of the meal: ");
            number = sc.next();
            if(!validateService.checkDoubleNumber(number)){
                MessageService.ShowInvalidNumber();
            }
        } while(!validateService.checkDoubleNumber(number));

        meal.setPrice(Double.parseDouble(number));

    

    }

    public static void ShowListMeal() {
        service = new MealsService();
        ArrayList<Meal> meals = service.listMeals();

        System.out.println("\nlisting the meals : \n");
    
        boolean leftJustifiedRows = true;

        int n = meals.size();
        String [][] table = new String[n+1][5];
        table[0][0] = "Id";
        table[0][1] = "name of the meal : ";
        table[0][2] = "original country of the meal: ";
        table[0][3] = "Price";
   

        int index = 1;
        for (Meal meal : meals){
            table[index][0] = String.valueOf(index);
            table[index][1] = meal.getName();
            table[index][2] = meal.getShopkeeper();
            table[index][3] = String.valueOf(meal.getPrice());
            index++;
        }

    
        Map<Integer, Integer> columnLengths = new HashMap<>();
        Arrays.stream(table).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
            if (columnLengths.get(i) == null) {
                columnLengths.put(i, 0);
            }
            if (columnLengths.get(i) < a[i].length()) {
                columnLengths.put(i, a[i].length());
            }
        }));
       
        final StringBuilder formatString = new StringBuilder("");
        String flag = leftJustifiedRows ? "-" : "";
        columnLengths.entrySet().stream().forEach(e -> formatString.append("| %" + flag + e.getValue() + "s "));
        formatString.append("|\n");
       
        Stream.iterate(0, (i -> i < table.length), (i -> ++i))
                .forEach(a -> System.out.printf("" + formatString.toString(), table[a]));

    }
}
