package finall;

import java.util.*;
import java.util.stream.Stream;

import features.User;
import the_menu.MessageService;
import the_menu.UserService;

public abstract class MenuChef {
    private static UserService service;
    public static void ShowAddChef() {
        service = new UserService();

        System.out.print("\nadding a chef to our restaurant : \n");
        Scanner sc = new Scanner(System.in);
        User user = new User();
        System.out.print("\nname: ");
        user.setName(sc.next());

        System.out.print("username: ");
        user.setUsername(sc.next());


        user.setLevel(1);

        if(service.addUser(user)){
            MessageService.ShowSuccessfulUserCreateMessage();
        } else{
            MessageService.ShowFailedCreateMessage();
        }

    }

    public static void ShowRemoveChef() {
        service = new UserService();

        System.out.print("\nremoving a chef from our restaurant\n");
        Scanner sc = new Scanner(System.in);
        System.out.print("\nusername : ");
        String username = sc.next();

        if(service.deleteUser(username)){
            MessageService.ShowSuccessfulUserDeletedMessage();
        } else{
            MessageService.ShowFailedDeleteMessage();
        }

    }

    public static void ShowUpdateChef() {
        service = new UserService();

        System.out.print("\nupdating info about a chef from our restaurant \n");
        Scanner sc = new Scanner(System.in);
        User user = new User();

        System.out.print("username : ");
        String username = sc.next();

        System.out.print("\nthe new name : ");
        user.setName(sc.next());

        System.out.print("the new username :  ");
        user.setUsername(sc.next());

  

        user.setLevel(1);

        if(service.updateUser(username, user)){
            MessageService.ShowSuccessfulUserUpdatedMessage();
        } else{
            MessageService.ShowFailedCreateMessage();
        }

    }


    public static void ShowListChef() {
        service = new UserService();
        ArrayList<User> users = service.listUsers();

        System.out.println("\nlisting the chefs in our restaurant \n");
        boolean leftJustifiedRows = true;

        int n = users.size();
        String [][] table = new String[n+1][3];
        table[0][0] = "Id";
        table[0][1] = "Name";
        table[0][2] = "Username";

        int index = 1;
        for (User user : users){
            table[index][0] = String.valueOf(index);
            table[index][1] = user.getName();
            table[index][2] = user.getUsername();
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
