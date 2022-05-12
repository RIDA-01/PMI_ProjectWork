package finall;

import java.util.*;
import java.util.stream.Stream;

import features.*;
import the_menu.*;

public abstract class MenuShopkeeper {
    private static ShopkeeperService shopkeeperService;
    private static UserService userService;

    public static void ShowListOfReportShopkeeper() {

        shopkeeperService = new ShopkeeperService();
        ArrayList<Meal> meals = shopkeeperService.listMealReport(Entering.active_user);

        System.out.println("\nshow the original country of the meals : \n");

        if(meals!=null){
    
            boolean leftJustifiedRows = true;
            int n = meals.size();
            String [][] table = new String[n+1][4];
            table[0][0] = "Id";
            table[0][1] = "Name";
            table[0][2] = "Price";

            int index = 1;
            for (Meal meal : meals){
                table[index][0] = String.valueOf(index);
                table[index][1] = meal.getName();
                table[index][2] = String.valueOf(meal.getPrice());
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

        } else{
            MessageService.EmptyMessage();
        }
    }

    public static void ShowPersonalInformationShopkeeper() {
        userService = new UserService();
        ArrayList<User> users = userService.getUserInformation(Entering.active_user);

        System.out.println("\n\n");

        if(users!=null){
           
            boolean leftJustifiedRows = true;

            int n = users.size();
            String [][] table = new String[n+1][4];
            table[0][0] = "Id";
            table[0][1] = "Name";
            table[0][2] = "Username";
            table[0][3] = "Type";

            int index = 1;
            for (User user : users){
                table[index][0] = String.valueOf(index);
                table[index][1] = user.getName();
                table[index][2] = user.getUsername();
                String type = "";
                switch (user.getLevel()){
                    case 1:
                        type = "chef";
                        break;
                    case 2:
                        type = "Costumer";
                        break;
                    case 3:
                        type = "orginal country ";
                }
                table[index][3] = type;
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


        } else{
            MessageService.EmptyMessage();
        }
    }

}
