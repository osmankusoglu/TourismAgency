package business;

import Dao.RoomDao;
import entity.Hotel;
import entity.Room;

import java.util.ArrayList;

public class RoomManager {
    private final RoomDao roomDao;
    private Hotel hotel;
    private Room room;

    public RoomManager() {
        this.roomDao = new RoomDao();
        this.hotel = hotel;
        this.room = room;
        }


    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> rooms) {
        ArrayList<Object[]> roomObjList = new ArrayList<>();
        for (Room obj : rooms) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = obj.getRoom_id();
            rowObject[i++] = obj.getHotel().getName();
            rowObject[i++] = obj.getPencion().getPencionType();
            rowObject[i++] = obj.getRoom_type();
            rowObject[i++] = obj.getRoom_stock();
            rowObject[i++] = obj.getRoom_adult_price();
            rowObject[i++] = obj.getRoom_child_price();
            rowObject[i++] = obj.getRoom_bed_capacity();
            rowObject[i++] = obj.getRoom_square_meter();
            rowObject[i++] = obj.isRoom_television();
            rowObject[i++] = obj.isRoom_minibar();
            rowObject[i++] = obj.isRoom_game_console();
            rowObject[i++] = obj.isRoom_cash_box();
            rowObject[i++] = obj.isRoom_projection();
            roomObjList.add(rowObject);
            //rowObject[i++] = obj.getSeason().getSeason_strt_date();
            //rowObject[i++] = obj.getSeason().getSeason_fnsh_date();
            //rowObject[i++] = obj.getHotel().getAddress();
        }
        return roomObjList;
    }
    public ArrayList<Room> findAll(){
        return this.roomDao.findAll();
    }
}
