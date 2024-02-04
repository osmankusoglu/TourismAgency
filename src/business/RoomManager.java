package business;

import Dao.RoomDao;
import core.Helper;
import entity.Hotel;
import entity.Room;

import java.util.ArrayList;

public class RoomManager {
    private final RoomDao roomDao;
    private Hotel hotel;
    private HotelManager hotelManager;

    private Room room;

    public RoomManager() {
        this.roomDao = new RoomDao();
        this.hotel = new Hotel();
        this.room = room;
        this.hotelManager = new HotelManager();
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
        }
        return roomObjList;
    }

    public ArrayList<Room> findAll() {
        return this.roomDao.findAll();
    }

    public boolean save(Room room) {
        if (room.getRoom_id() != 0) {
            Helper.showMsg("error");

        }
        return this.roomDao.save(room);
    }
}
