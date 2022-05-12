package finall;

import java.util.Scanner;

import the_menu.MessageService;
import the_menu.ValidateService;

public abstract class Menumainchef {
    private static ValidateService validateService;
    public static void ShowNewMealOrder(){
        MenuMeal.ShowAddMeal();
    }

    public static void ShowMenuMeal() {
        validateService = new ValidateService();
        System.out.println("\nMain menu");
        System.out.print("\n" +
                "1) Adding a Meal\n" +
                "2) Removing a  Meal\n" +
                "3) Updating a Meal\n" +
                "4) Listing a Meal\n" +
                "5) Back \n" +
                "6) Exit\n" +
                "choose please : ");

        Scanner sc = new Scanner(System.in);
        String times = "";
        int n;
        char option = sc.next().charAt(0);
        switch (option){
            case '1':
                times = "";
                do{
                    System.out.print("\nHow many times to you want to execute this operation? R: ");
                    times = sc.next();
                    if(!validateService.checkIntegerNumber(times)){
                        MessageService.ShowInvalidNumber();
                    }
                } while(!validateService.checkIntegerNumber(times));

                n = Integer.parseInt(times);
                for (int i = 0; i<n; i++){
                    MenuMeal.ShowAddMeal();
                }

                break;
            case '2':
                times = "";
                do{
                    System.out.print("\nHow many times to you want to execute this operation? R: ");
                    times = sc.next();
                    if(!validateService.checkIntegerNumber(times)){
                        MessageService.ShowInvalidNumber();
                    }
                } while(!validateService.checkIntegerNumber(times));

                n = Integer.parseInt(times);
                for (int i = 0; i<n; i++){
                    MenuMeal.ShowRemoveMeal();
                }

                break;
            case '3':
                times = "";
                do{
                    System.out.print("\nHow many times to you want to execute this operation? R: ");
                    times = sc.next();
                    if(!validateService.checkIntegerNumber(times)){
                        MessageService.ShowInvalidNumber();
                    }
                } while(!validateService.checkIntegerNumber(times));

                n = Integer.parseInt(times);
                for (int i = 0; i<n; i++){
                    MenuMeal.ShowUpdateMeal();
                }
                break;
            case '4':
                MenuMeal.ShowListMeal();
                ShowMenuMeal();
                break;
            case '5':
                Entering.ShowMenuChefs();
                break;
            case '6':
                System.exit(0);
                break;
            default:
                ShowMenuMeal();
                break;
        }

        ShowMenuMeal();
    }


    public static void ShowMenuShopkeeper() {
        validateService = new ValidateService();
        System.out.println("\noriginal countries of our meals : ");
        System.out.print("\n" +
                "1) List the original countries :\n" +
                "2) Back \n" +
                "3) Exit\n" +
                "choose please : ");

        Scanner sc = new Scanner(System.in);
        char option = sc.next().charAt(0);
        switch (option){
            case '1':
                MenuShopkeeper.ShowListOfReportShopkeeper();
                break;
            case '2':
            Entering.ShowMenuChefs();
            break;
            case '3':
                System.exit(0);
                break;
            default:
                ShowMenuShopkeeper();
                break;
        }
        ShowMenuShopkeeper();
    }

    public static void ShowMenuChefs() {
        validateService = new ValidateService();
        System.out.println("\nchefs of our restaurant : ");
        System.out.print("\n" +
                "1) Adding a chef :\n" +
                "2) Removing a chef:\n" +
                "3) Updating info about a chef :\n" +
                "4) Listing a chef:\n" +
                "5) Back \n" +
                "6) Exit\n" +
                "choose please: ");

        Scanner sc = new Scanner(System.in);
        String times = "";
        int n;
        char option = sc.next().charAt(0);
        switch (option){
            case '1':
                times = "";
                do{
                    System.out.print("\nHow many times to you want to execute this operation? R: ");
                    times = sc.next();
                    if(!validateService.checkIntegerNumber(times)){
                        MessageService.ShowInvalidNumber();
                    }
                } while(!validateService.checkIntegerNumber(times));

                n = Integer.parseInt(times);
                for (int i = 0; i<n; i++){
                    MenuChef.ShowAddChef();
                }
                break;
            case '2':
                times = "";
                do{
                    System.out.print("\nHow many times to you want to execute this operation? R: ");
                    times = sc.next();
                    if(!validateService.checkIntegerNumber(times)){
                        MessageService.ShowInvalidNumber();
                    }
                } while(!validateService.checkIntegerNumber(times));

                n = Integer.parseInt(times);
                for (int i = 0; i<n; i++){
                    MenuChef.ShowRemoveChef();
                }
                break;
            case '3':
                times = "";
                do{
                    System.out.print("\nHow many times to you want to execute this operation? R: ");
                    times = sc.next();
                    if(!validateService.checkIntegerNumber(times)){
                        MessageService.ShowInvalidNumber();
                    }
                } while(!validateService.checkIntegerNumber(times));

                n = Integer.parseInt(times);
                for (int i = 0; i<n; i++){
                    MenuChef.ShowUpdateChef();
                }
                break;
            case '4':
                MenuChef.ShowListChef();
                break;
            case '5':
                Entering.ShowMenuChefs();
                break;
            case '6':
                System.exit(0);
                break;
            default:

                break;
        }
        ShowMenuChefs();
    }

    public static void ShowMenuReciept() {
        validateService = new ValidateService();
        System.out.println("\nreciept menu");
        System.out.print("\n" +
                "1) Adding a  reciept:\n" +
                "2) Removing a reciept:\n" +
                "3) Updating a reciept:\n" +
                "4) Listing a reciept:\n" +
                "5) Back\n" +
                "6) Exit\n" +
                "choose please : ");

        Scanner sc = new Scanner(System.in);
        String times = "";
        int n;
        char option = sc.next().charAt(0);
        switch (option){
            case '1':
                times = "";
                do{
                    System.out.print("\nHow many times to you want to execute this operation? R: ");
                    times = sc.next();
                    if(!validateService.checkIntegerNumber(times)){
                        MessageService.ShowInvalidNumber();
                    }
                } while(!validateService.checkIntegerNumber(times));

                n = Integer.parseInt(times);
                for (int i = 0; i<n; i++){
                    MenuReciept.ShowAddReciept();
                }
                break;
            case '2':
                times = "";
                do{
                    System.out.print("\nHow many times to you want to execute this operation? R: ");
                    times = sc.next();
                    if(!validateService.checkIntegerNumber(times)){
                        MessageService.ShowInvalidNumber();
                    }
                } while(!validateService.checkIntegerNumber(times));

                n = Integer.parseInt(times);
                for (int i = 0; i<n; i++){
                    MenuReciept.ShowRemoveReciept();
                }
                break;
            case '3':
                times = "";
                do{
                    System.out.print("\nHow many times to you want to execute this operation? R: ");
                    times = sc.next();
                    if(!validateService.checkIntegerNumber(times)){
                        MessageService.ShowInvalidNumber();
                    }
                } while(!validateService.checkIntegerNumber(times));

                n = Integer.parseInt(times);
                for (int i = 0; i<n; i++){
                    MenuReciept.ShowUpdateReciept();
                }
                break;
            case '4':
                MenuReciept.ShowListReciept();
                break;
            case '5':
                Entering.ShowMenuChefs();
                break;
            case '6':
                System.exit(0);
                break;
            default:

                break;
        }
        ShowMenuReciept();
    }

    public static void ShowMenuUser() {
        validateService = new ValidateService();
        System.out.println("\nusers menu : ");
        System.out.print("\n" +
                "1) Add User:\n" +
                "2) Remove User:\n" +
                "3) Update User:\n" +
                "4) List User:\n" +
                "5) Back <=::\n" +
                "6) Exit.\n" +
                "choose please :  ");

        Scanner sc = new Scanner(System.in);
        String times = "";
        int n;
        char option = sc.next().charAt(0);
        switch (option){
            case '1':
                times = "";
                do{
                    System.out.print("\nHow many times to you want to execute this operation? R: ");
                    times = sc.next();
                    if(!validateService.checkIntegerNumber(times)){
                        MessageService.ShowInvalidNumber();
                    }
                } while(!validateService.checkIntegerNumber(times));

                n = Integer.parseInt(times);
                for (int i = 0; i<n; i++){
                    MenuUser.ShowAddUser();
                }
                break;
            case '2':
                times = "";
                do{
                    System.out.print("\nHow many times to you want to execute this operation? R: ");
                    times = sc.next();
                    if(!validateService.checkIntegerNumber(times)){
                        MessageService.ShowInvalidNumber();
                    }
                } while(!validateService.checkIntegerNumber(times));

                n = Integer.parseInt(times);
                for (int i = 0; i<n; i++){
                    MenuUser.ShowRemoveUser();
                }
                break;
            case '3':
                times = "";
                do{
                    System.out.print("\nHow many times to you want to execute this operation? R: ");
                    times = sc.next();
                    if(!validateService.checkIntegerNumber(times)){
                        MessageService.ShowInvalidNumber();
                    }
                } while(!validateService.checkIntegerNumber(times));

                n = Integer.parseInt(times);
                for (int i = 0; i<n; i++){
                    MenuUser.ShowUpdateUser();
                }
                break;
            case '4':
                MenuUser.ShowListUser();
                ShowMenuUser();
                break;
            case '5':
                Entering.ShowMenuChefs();
                break;
            case '6':
                System.exit(0);
                break;
            default:

                break;
        }
        ShowMenuUser();
    }
}
