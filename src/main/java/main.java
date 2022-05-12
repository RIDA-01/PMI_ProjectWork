import features.User;
import finall.Entering;


public class main {
    public static String username;

    public static void main(String[] args) {
        Entering.active_user = new User();
        ShowTitle();
        Entering.ShowEntering();
        Entering.ShowMenuChefs();
        Entering.ShowMenuShopkeeper();
    }

    public static void ShowTitle(){
        System.out.println("\nwelcome to rida restaurant");
    }
}
