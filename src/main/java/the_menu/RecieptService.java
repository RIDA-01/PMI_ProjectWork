package the_menu;


import java.util.ArrayList;

import features.*;

public class RecieptService implements RecieptServiceInterface{
    private ArrayList<Reciept>  reciepts = null;

    public RecieptService() {
        this.reciepts = new XmlReaderService().readReciepts();
    }

    @Override
    public Boolean addReciept(Reciept reciept) {
        CostumerService costumerService = new CostumerService();
        if(!costumerService.existsCostumer(reciept.getCostumerName()))
            return false;

        if(reciept.getPrice()<=0.0)
            return false;

        reciept.setId(getLastIdReciept() + 1);
        return new XmlWriterService().addRecieptXML(reciept);
    }

    @Override
    public Boolean deleteReciept(Integer id) {
        if(existsReciept(id)){
            return new XmlWriterService().deleterecieptXML(id);
        }
        return false;
    }

    @Override
    public Boolean updateReciept(Integer id, Reciept reciept) {
        reciept.setPrice(getTotalPrice(reciept.getmeal()));
        if(existsReciept(id))
            return new XmlWriterService().updaterecieptXML(id, reciept);
        return false;
    }


    @Override
    public ArrayList<Reciept> listReciept() {
        reciepts = new XmlReaderService().readReciepts();
        return reciepts;
    }

    @Override
    public Boolean existsReciept(Integer id) {
        for (Reciept i : reciepts){
            if(i.getId() == id){
                return true;
            }
        }
        return false;
    }


 /*   @Override
    public String MealReciept(Integer id) {
        for (Reciept i : reciepts){
            if(i.getId() == id){
                return i.getmeal();
            }
        }
        return "default";
    }
*/
    @Override
    public ArrayList<Reciept> listRecieptCostumer(String name) {
        reciepts = new XmlReaderService().readReciepts();
        ArrayList<Reciept> list = new ArrayList<>();
        for (Reciept m : reciepts){
            if(m.getCostumerName().equals(name)){
                list.add(m);
            }
        }
        return list;
    }

    @Override
    public Integer getLastIdReciept() {
        reciepts = new XmlReaderService().readReciepts();
        int len = reciepts.size();
        if(len == 0)
            return 0;

        Integer last_id = reciepts.get(len-1).getId();
        return last_id;
    }

    @Override
    public Double getTotalPrice(String meal) {
        Double mealPrice = new MealsService().priceMeale(meal);
        return (mealPrice);
    }


}