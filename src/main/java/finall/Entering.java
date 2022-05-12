package finall;

import java.util.Scanner;

import entrance.entering;
import features.User;
import the_menu.MessageService;
import the_menu.UserService;

public abstract class Entering {
    public static User active_user = null;

    public static void ShowEntering(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\nPlease enter the followings:\n");
        User user = new User();
        System.out.print("Username: ");
        user.setUsername(sc.next());
        System.out.print("Password: ");
        user.setPassword(sc.next());

        entering enteringController = new entering();
        int acessLevel = enteringController.successfulEntering(user);

        active_user = new UserService().getUserInformation(user.getUsername());
        switch (acessLevel){
            case 1:
                ShowMenuChefs(); break;
            case 2:
                ShowMenuCostumer(); break;
            case 3:
                ShowMenuShopkeeper(); break;
            default:
                MessageService.ShowDeniedMessage();

                Character choice = 0;

                do {
                    MessageService.ShowTryAgainMessage();
                    choice = sc.next().charAt(0);
                } while(choice!= '1' && choice != '2');

                if(choice == '1'){
                    ShowEntering();
                } else{
                    System.exit(0);
                }

                break;
        }
    }

    private static void ShowMenuCostumer() {
    }

    public static void ShowMenuChefs(){
        System.out.println("\nhere is our menu :");
        System.out.print("\n" +
                "1) New meal order:\n" +
                "2) Meals:\n" +
                "3) origin(country):\n" +
                "4) Chefs:\n" +
                "5) Reciept:\n" +
                "6) Exit\n" +
                "choose please: ");
        Scanner sc = new Scanner(System.in);
        int option = sc.next().charAt(0);
        switch (option){
            case '1':
                Menumainchef.ShowNewMealOrder();
                break;
            case '2':
                Menumainchef.ShowMenuMeal();
                break;
            case '3':
                Menumainchef.ShowMenuShopkeeper();
                break;
            case '4':
                Menumainchef.ShowMenuChefs();
                break;
            case '5':
                Menumainchef.ShowMenuReciept();
                break;
            case '6':
                System.exit(0);
                break;
            default:
                ShowMenuChefs();
                break;
        }
        ShowMenuChefs();
    }

   

    public static void ShowMenuShopkeeper(){
        System.out.println("\n : MAIN MENU  :");
        System.out.print("\n" +
                "1)feedback : :\n" +
                "2) Personal Information:\n" +
                "3) Back \n" +
                "4) Exit\n" +
                "Operation: ");
        Scanner sc = new Scanner(System.in);
        int option = sc.next().charAt(0);
        switch (option){
            case '1':
                MenuShopkeeper.ShowListOfReportShopkeeper();
                ShowMenuShopkeeper();
                break;
            case '2':
                MenuShopkeeper.ShowPersonalInformationShopkeeper();
                ShowMenuShopkeeper();
                break;
            case '3':
                Entering.ShowEntering();
                break;
            case '4':
                System.exit(0);
                break;
            default:
                ShowMenuShopkeeper();
                break;
        }
        ShowMenuShopkeeper();
    }
}
