package the_menu;

import java.util.ArrayList;

import features.*;

public interface RecieptServiceInterface {
    public Boolean addReciept(Reciept reciept);
    public Boolean deleteReciept(Integer id);
    public ArrayList<Reciept> listReciept();
    public Boolean updateReciept(Integer id, Reciept reciept);
    public Boolean existsReciept(Integer id);
    public Double getTotalPrice(String meal);
    public ArrayList<Reciept> listRecieptCostumer(String name);
    public Integer getLastIdReciept();
}
