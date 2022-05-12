package the_menu;

import java.util.ArrayList;

import features.User;

public interface UserServiceInterface {
    public Boolean addUser(User user);
    public Boolean deleteUser(String username);
    public Boolean updateUser(String username, User user);
    public ArrayList<User> listUsers();
    public Boolean existsUser(String username);
    public ArrayList<User> getUserInformation(User user);
    public User getUserInformation(String username);
}
