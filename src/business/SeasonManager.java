package business;

import Dao.SeasonDao;
import core.Helper;
import entity.Hotel;
import entity.Season;

import java.util.ArrayList;

public class SeasonManager {
    private final SeasonDao seasonDao;
    private Hotel hotel;

    public SeasonManager(Hotel hotel) {
        this.hotel = hotel;
        this.seasonDao = new SeasonDao();
    }
    public boolean saveSeason(Hotel hotel, String strDate,String endDate){
        if (hotel.getId() !=0 ){
            Helper.showMsg("done");
        }
        return this.seasonDao.saveSeason(hotel,strDate,endDate);
    }


    public ArrayList<Object[]> getForTable(int size, ArrayList<Season> seasons){
        ArrayList<Object[]> seasonList = new ArrayList<>();
        for (Season obj : seasons){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getSeason_id();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getSeason_fnsh_date();
            rowObject[i++] = obj.getSeason_fnsh_date();

            seasonList.add(rowObject);
        }
        return seasonList;
    }
    public ArrayList<Season> findAll(){
        return this.seasonDao.findAll();
    }
}

