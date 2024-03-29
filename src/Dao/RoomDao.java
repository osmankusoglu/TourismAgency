package Dao;

import core.Db;
import entity.Hotel;
import entity.Pencion;
import entity.Room;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDao {
    // Veritabanı bağlantısını temsil eden nesne
    private final Connection con;

    // İlişkili DAO sınıfları
    private PencionDao pencionDao;
    private SeasonDao seasonDao;
    private HotelDao hotelDao;


    public RoomDao() {
        // Kurucu metod: DAO sınıflarını oluştur ve veritabanı bağlantısını kur
        this.hotelDao = new HotelDao();
        this.pencionDao = new PencionDao();
        this.seasonDao = new SeasonDao();
        this.con = Db.getInstance();
    }


    // ResultSet'ten alınan verileri bir Room nesnesine eşleyen metot
    public Room match(ResultSet rs) throws SQLException {
        Room obj = new Room();
        obj.setRoom_id(rs.getInt("room_id"));
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setPencion_id(rs.getInt("pencion_id"));
        obj.setSeason_id(rs.getInt("season_id"));
        obj.setRoom_type(rs.getString("room_type"));
        obj.setRoom_stock(rs.getInt("room_stock"));
        obj.setRoom_adult_price(rs.getInt("room_adult_price"));
        obj.setRoom_child_price(rs.getInt("room_child_price"));
        obj.setRoom_bed_capacity(rs.getInt("room_bed_capacity"));
        obj.setRoom_square_meter(rs.getInt("room_square_meter"));
        obj.setRoom_television(rs.getBoolean("room_television"));
        obj.setRoom_minibar(rs.getBoolean("room_minibar"));
        obj.setRoom_game_console(rs.getBoolean("room_game_console"));
        obj.setRoom_cash_box(rs.getBoolean("room_cash_box"));
        obj.setRoom_projection(rs.getBoolean("room_projection"));

        // İlişkili DAO sınıflarını kullanarak ilgili nesneleri set et
        obj.setPencion(this.pencionDao.getById(rs.getInt("pencion_id")));
        obj.setSeason(this.seasonDao.getById(rs.getInt("season_id")));
        obj.setHotel(this.hotelDao.getById(rs.getInt("hotel_id")));
        return obj;
    }

    // Yeni bir oda ekleyen metot
    public boolean save(Room room) {
        String query = "INSERT INTO public.room" +
                "(" +
                "hotel_id, " +
                "pencion_id, " +
                "season_id, " +
                "room_type, " +
                "room_stock, " +
                "room_adult_price, " +
                "room_child_price, " +
                "room_bed_capacity, " +
                "room_square_meter, " +
                "room_television, " +
                "room_minibar, " +
                "room_game_console, " +
                "room_cash_box, " +
                "room_projection " +
                ")" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, room.getHotel_id());
            pr.setInt(2, room.getPencion_id());
            pr.setInt(3, room.getSeason_id());
            pr.setString(4, room.getRoom_type());
            pr.setInt(5, room.getRoom_stock());
            pr.setInt(6, room.getRoom_adult_price());
            pr.setInt(7, room.getRoom_child_price());
            pr.setInt(8, room.getRoom_bed_capacity());
            pr.setInt(9, room.getRoom_square_meter());
            pr.setBoolean(10, room.isRoom_television());
            pr.setBoolean(11, room.isRoom_minibar());
            pr.setBoolean(12, room.isRoom_game_console());
            pr.setBoolean(13, room.isRoom_cash_box());
            pr.setBoolean(14, room.isRoom_projection());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    // Tüm odaları getiren metot
    public ArrayList<Room> findAll() {
        ArrayList<Room> roomList = new ArrayList<>();
        String sql = "SELECT * FROM public.room";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()) {
                roomList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    // Belirli bir sorguya göre odaları getiren metot
    public ArrayList<Room> selectByQuery(String query) {
        ArrayList<Room> roomList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                roomList.add(this.match(rs));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return roomList;
    }

    // Belirli bir oda ID'si ile odayı getiren metot
    public Room getById(int id) {
        Room obj = null;
        String query = "SELECT * FROM public.room WHERE room_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    // Oda stok miktarını güncelleyen metot
    public boolean updateStock(Room room){
        String query = "UPDATE public.room SET room_stock = ? WHERE room_id = ?";
        try {PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,room.getRoom_stock());
            pr.setInt(2,room.getRoom_id());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}
