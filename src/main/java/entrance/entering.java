package entrance;

import features.User;
import the_menu.UserService;
import the_menu.XmlReaderService;

public class entering {
    public UserService userService;

    public Integer successfulEntering(User user){
        XmlReaderService xmlReaderService = new XmlReaderService();
        userService = new UserService();
        for(User x : userService.listUsers()){
            if(x.getUsername().equals(user.getUsername()) && x.getPassword().equals(user.getPassword())){
                return x.getLevel();
            }
        }
        return 1;
    }
}
