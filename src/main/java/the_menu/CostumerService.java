package the_menu;

import java.util.ArrayList;

import features.Reciept;
import features.User;

public class CostumerService implements CostumerServiceInterface {
    @Override
    public User personalInformation(String username) {
        UserService userService = new UserService();
        for (User user : userService.listUsers()){
            if(user.getUsername().equals(username))
                return user;
        }
        return null;
    }



    @Override
    public Boolean existsCostumer(String name) {
        for (User u : new UserService().listUsers()){
            if(u.getName().equals(name) && u.getLevel() == 2){
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Reciept> listIRecipetReport(User user) {
        ArrayList<Reciept> list = new ArrayList<>();
        for (Reciept reciept : new RecieptService().listReciept()){
            if(reciept.getCostumerName().equals(user.getName())){
                list.add(reciept);
            }
        }
        return list;
    }
}
