package Dao;

import core.Db;
import entity.Hotel;
import entity.Season;

import java.sql.Date;

import java.sql.*;
import java.util.ArrayList;

public class SeasonDao {
    private final Connection con;

    public SeasonDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<Season> findAll() {
        ArrayList<Season> seasonList = new ArrayList<>();
        String sql = "SELECT * FROM public.season";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()) {
                seasonList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasonList;
    }

    public Season match(ResultSet rs) throws SQLException {
        Season obj = new Season();
        obj.setSeason_id(rs.getInt("season_id"));
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setSeason_strt_date(rs.getString("season_strt_date"));
        obj.setSeason_fnsh_date(rs.getString("season_fnsh_date"));
        return obj;
    }

    public boolean saveSeason(Hotel hotel, String strDate, String endDate) {
        String query = "INSERT INTO public.season" +
                "(hotel_id, season_strt_date, season_fnsh_date)" +
                " VALUES ( ?, ?, ?)";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, hotel.getId());
            pr.setDate(2, Date.valueOf(strDate));
            pr.setDate(3, Date.valueOf(endDate));

            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public Season getById(int id) {
        Season obj = null;
        String query = "SELECT * FROM public.season WHERE season_id = ?";
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
