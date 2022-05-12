package finall;

import java.util.*;
import java.util.stream.Stream;

import features.User;
import the_menu.MessageService;
import the_menu.UserService;

public abstract class MenuUser {
    private static UserService service;
    public static void ShowAddUser() {
        service = new UserService();

        System.out.print("\nadd a user please:  \n");
        Scanner sc = new Scanner(System.in);
        User user = new User();
        System.out.print("\nname: ");
        user.setName(sc.next());

        System.out.print("username: ");
        user.setUsername(sc.next());


        Character choice = ' ';
        do {
            System.out.print("\nChoose:\n" +
                    "1) chef;\n" +
                    "2) Supplier;\n");
            System.out.print("\nOption: ");

            choice = sc.next().charAt(0);
        } while(choice!= '1' && choice != '2' && choice != '3');

        user.setLevel(choice - '0');

        if(service.addUser(user)){
            MessageService.ShowSuccessfulUserCreateMessage();
        } else{
            MessageService.ShowFailedCreateMessage();
        }

    }

    public static void ShowRemoveUser() {
        service = new UserService();

        System.out.print("\nremove user : \n");
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter the username: ");
        String username = sc.next();

        if(service.deleteUser(username)){
            MessageService.ShowSuccessfulUserDeletedMessage();
        } else{
            MessageService.ShowFailedDeleteMessage();
        }

    }

    public static void ShowUpdateUser() {
        service = new UserService();

        System.out.print("\nupdate user : \n");
        Scanner sc = new Scanner(System.in);
        User user = new User();

        System.out.print("username: ");
        String username = sc.next();

        System.out.print("\n name: ");
        user.setName(sc.next());

        System.out.print("username: ");
        user.setUsername(sc.next());


        Character choice = '0';
        do {
            System.out.print("\nChoose/Enter space:\n" +
                    "1) chef;\n" +
                    "2) Supplier;\n" +
                    "3) there is no change;\n");
            System.out.print("\nchoose please : ");

            choice = sc.next().charAt(0);
        } while(choice!= '1' && choice != '2' && choice != '3');

        user.setLevel(choice - '0');

        if(service.updateUser(username, user)){
            MessageService.ShowSuccessfulUserUpdatedMessage();
        } else{
            MessageService.ShowFailedCreateMessage();
        }

    }

    public static void ShowListUser() {
        service = new UserService();
        ArrayList<User> users = service.listUsers();

        System.out.println("\nlist user\n");
       
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
                    type = "original country of the meal";
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
    }
}
