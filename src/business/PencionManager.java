package business;

import core.Helper;
import Dao.PencionDao;
import entity.Hotel;
import entity.Pencion;

import java.util.ArrayList;

public class PencionManager {
    private final PencionDao pencionDao;
    private Hotel hotel;
    public PencionManager(Hotel hotel) {
        this.hotel = hotel;
        this.pencionDao = new PencionDao();
    }
    public boolean savePencion(Hotel hotel, String val){
       if (hotel.getId() !=0 ){
            Helper.showMsg("done");

        }
        return this.pencionDao.savePencion(hotel,val);
    }


    public ArrayList<Object[]> getForTable(int size, ArrayList<Pencion> pencions){
        ArrayList<Object[]> pencionList = new ArrayList<>();
        for (Pencion obj : pencions){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getPencionId();
            rowObject[i++] = obj.getHotelId();
            rowObject[i++] = obj.getPencionType();

            pencionList.add(rowObject);
        }
        return pencionList;
    }
    public ArrayList<Pencion> findAll(){
        return this.pencionDao.findAll();
    }
}