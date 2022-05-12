package the_menu;

import finall.Entering;

import java.util.ArrayList;

import features.User;


public class UserService implements UserServiceInterface{
    private ArrayList<User> users = null;

    public UserService() {
        this.users = new XmlReaderService().readUsers();
    }

    @Override
    public Boolean addUser(User user) {
        if(!existsUser(user.getUsername()))
            return new XmlWriterService().addUserXML(user);
        return false;
    }

    @Override
    public Boolean deleteUser(String username) {
        if(!username.equals(Entering.active_user.getUsername()))
            return new XmlWriterService().deleteUserXML(username);
        return false;
    }

    @Override
    public Boolean updateUser(String username, User user) {
        return new XmlWriterService().updateUserXML(username, user);
    }

    @Override
    public ArrayList<User> listUsers() {
        users = new XmlReaderService().readUsers();
        return this.users;
    }

    @Override
    public Boolean existsUser(String username) {
        for (User u : users){
            if(u.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<User> getUserInformation(User user) {
        ArrayList<User> list = new ArrayList<>();
        for (User u : users){
            if(u.getUsername().equals(user.getUsername())){
                list.add(u);
            }
        }
        return list;
    }

    @Override
    public User getUserInformation(String username) {
        for (User u : users){
            if(u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }
}
