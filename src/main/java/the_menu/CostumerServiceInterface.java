package the_menu;


import java.util.ArrayList;

import features.*;

public interface CostumerServiceInterface {
    public User personalInformation(String username);
    public ArrayList<Reciept> listIRecipetReport(User user);
    public Boolean existsCostumer(String username);
}
