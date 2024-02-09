package Dao;

import core.Db;
import entity.Hotel;
import entity.Reservation;
import entity.Room;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;

public class ReservationDao {

    // Veritabanı bağlantısını temsil eden nesne
    private final Connection con;

    // İlişkili DAO sınıfı
    private final RoomDao roomDao = new RoomDao();

    // Kurucu metod: Veritabanı bağlantısını kurma
    public ReservationDao() {
        this.con = Db.getInstance();
    }

    // Tüm rezervasyonları getiren metot
    public ArrayList<Reservation> findAll() {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        String sql = "SELECT * FROM public.reservation";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()) {
                reservationList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationList;
    }

    // ResultSet'ten alınan verileri bir Reservation nesnesine eşleyen metot
    public Reservation match(ResultSet rs) throws SQLException {
        Reservation obj = new Reservation();
        obj.setId(rs.getInt("id"));
        obj.setReservation_room_id(rs.getInt("reservation_room_id"));
        obj.setReservation_start_date(LocalDate.parse(rs.getString("reservation_start_date")));
        obj.setReservation_end_date(LocalDate.parse(rs.getString("reservation_end_date")));
        obj.setReservation_total_price(rs.getInt("reservation_total_price"));
        obj.setReservation_guest_number(rs.getInt("reservation_guest_number"));
        obj.setReservation_guest_name(rs.getString("reservation_guest_name"));
        obj.setReservation_guest_id(rs.getInt("reservation_guest_id"));
        obj.setReservation_mail(rs.getString("reservation_mail"));
        obj.setReservation_phone(rs.getInt("reservation_phone"));
        return obj;
    }

    // Belirli bir rezervasyon ID'si ile rezervasyonu getiren metot
    public Reservation getById(int id) {
        Reservation obj = null;
        String query = "SELECT * FROM public.reservation WHERE id = ?";
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

    // Yeni bir rezervasyon ekleyen metot
    public boolean save(Reservation reservation) {
        String query = "INSERT INTO public.reservation" +
                "(" +
                "reservation_room_id," +
                "reservation_start_date," +
                "reservation_end_date," +
                "reservation_total_price," +
                "reservation_guest_number," +
                "reservation_guest_name," +
                "reservation_guest_id," +
                "reservation_mail," +
                "reservation_phone" +
                ")" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, reservation.getReservation_room_id());
            pr.setDate(2, Date.valueOf(reservation.getReservation_start_date()));
            pr.setDate(3, Date.valueOf(reservation.getReservation_end_date()));
            pr.setInt(4, reservation.getReservation_total_price());
            pr.setInt(5, reservation.getReservation_guest_number());
            pr.setString(6, reservation.getReservation_guest_name());
            pr.setInt(7, reservation.getReservation_guest_id());
            pr.setString(8, reservation.getReservation_mail());
            pr.setInt(9, reservation.getReservation_phone());

            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    // Belirli bir rezervasyonu güncelleyen metot
    public boolean update(Reservation reservation) {
        String query = "UPDATE public.reservation SET " +
                "reservation_guest_number= ? , " +
                "reservation_guest_name= ? , " +
                "reservation_guest_id= ? , " +
                "reservation_mail= ? , "  +
                "reservation_phone= ? " +
                "WHERE id = ? ";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, reservation.getReservation_guest_number());
            pr.setString(2, reservation.getReservation_guest_name());
            pr.setInt(3, reservation.getReservation_guest_id());
            pr.setString(4, reservation.getReservation_mail());
            pr.setInt(5, reservation.getReservation_phone());
            pr.setInt(6,reservation.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    // Belirli bir rezervasyonu silen metot
    public boolean delete(int id) {
        String query = "DELETE FROM public.reservation WHERE id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
