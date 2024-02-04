package Dao;

import core.Db;
import entity.Hotel;
import entity.Pencion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PencionDao {
    private final Connection con;

    public PencionDao() {
        this.con = Db.getInstance();
    }

    public boolean savePencion(Hotel hotel, String val) {
        String query = "INSERT INTO public.pencion" +
                " (hotel_id, pencion_type)" +
                " VALUES ( ?, ? )";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, hotel.getId());
            pr.setString(2, val);

            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public ArrayList<Pencion> findAll() {
        ArrayList<Pencion> pencionList = new ArrayList<>();
        String sql = "SELECT * FROM public.pencion";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()) {
                pencionList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pencionList;
    }

    public ArrayList<Pencion> findByHotelId(int hotelId) {
        ArrayList<Pencion> pencionList = new ArrayList<>();
        String sql = "SELECT * FROM public.pencion WHERE hotel_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1, hotelId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                pencionList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pencionList;
    }


    public Pencion match(ResultSet rs) throws SQLException {
        Pencion obj = new Pencion();
        obj.setPencionId(rs.getInt("pencion_id"));
        obj.setHotelId(rs.getInt("hotel_id"));
        obj.setPencionType(rs.getString("pencion_type"));
        return obj;
    }

    public Pencion getById(int id) {
        Pencion obj = null;
        String query = "SELECT * FROM public.pencion WHERE pencion_id = ?";
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
}