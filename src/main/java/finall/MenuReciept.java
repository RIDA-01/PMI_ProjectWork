package finall;

import java.util.*;
import java.util.stream.Stream;

import features.*;
import the_menu.*;

public abstract class MenuReciept {
    private static RecieptService service;
    private static ValidateService validateService;

    public static void ShowAddReciept() {
        validateService = new ValidateService();
        service = new RecieptService();

        System.out.print("\nadding a recipet : \n");
        Scanner sc = new Scanner(System.in);
        Reciept reciept = new Reciept();
       
        System.out.print("what is the name of the meal: ");
        reciept.setMealName(sc.next());

        String number = "";

        reciept.setPrice(service.getTotalPrice(reciept.getmeal()));
        System.out.print("Total Price = " + reciept.getPrice() + "\n");

        if(service.addReciept(reciept)){
            MessageService.ShowSuccessfulRecieptCreateMessage();
        } else{
            MessageService.ShowFailedCreateMessage();
        }

    }

    public static void ShowRemoveReciept() {
        service = new RecieptService();
        validateService = new ValidateService();

        System.out.print("\nto remove a reciept \n");
        Scanner sc = new Scanner(System.in);


        String number = "";
        do{
            System.out.print("\nEnter the ID: ");
            number = sc.next();
            if(!validateService.checkIntegerNumber(number)){
                MessageService.ShowInvalidNumber();
            }
        } while(!validateService.checkIntegerNumber(number));

        Integer id = Integer.parseInt(number);

        if(service.deleteReciept(id)){
            MessageService.ShowSuccessfulReceiptDeletedMessage();
        } else{
            MessageService.ShowFailedDeleteMessage();
        }

    }

    public static void ShowUpdateReciept() {
        service = new RecieptService();
        validateService = new ValidateService();

        System.out.print("\nto update a reciept\n");
        Scanner sc = new Scanner(System.in);

        Reciept reciept = new Reciept();


        String number = "";
        do{
            System.out.print("\nEnter the ID: ");
            number = sc.next();
            if(!validateService.checkIntegerNumber(number)){
                MessageService.ShowInvalidNumber();
            }
        } while(!validateService.checkIntegerNumber(number));

        Integer id = Integer.parseInt(number);

        number = "";
       

        if(service.updateReciept(id, reciept)){
            MessageService.ShowSuccessfulMealUpdatedMessage();
        } else{
            MessageService.ShowFailedUpdateMessage();
        }

        Menumainchef.ShowMenuMeal();
    }


    public static void ShowListReciept() {
        service = new RecieptService();
        validateService = new ValidateService();
        ArrayList<Reciept>reciepts = service.listReciept();

        System.out.println("\no list the reciept\n");

        boolean leftJustifiedRows = true;

        int n = reciepts.size();
        String [][] table = new String[n+1][7];
        table[0][0] = "Ord";
        table[0][1] = "Id";
        table[0][2] = "Meal";
        table[0][3] = "Total_Price";
        table[0][4] = "Date";

        int index = 1;
        for (Reciept reciept : reciepts){
            table[index][0] = String.valueOf(index);
            table[index][1] = String.valueOf(reciept.getId());
            table[index][2] = reciept.getmeal();
            table[index][3] = String.valueOf(reciept.getPrice());
            table[index][4] = reciept.getDate();
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
