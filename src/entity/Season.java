package entity;

import core.ComboItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Season {
    private int season_id;
    private int hotel_id;
    private String season_strt_date;
    private String season_fnsh_date;

    public Season() {
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getSeason_strt_date() {
        return season_strt_date;
    }

    public void setSeason_strt_date(String season_strt_date) {
        this.season_strt_date = season_strt_date;
    }

    public String getSeason_fnsh_date() {
        return season_fnsh_date;
    }

    public void setSeason_fnsh_date(String season_fnsh_date) {
        this.season_fnsh_date = season_fnsh_date;
    }

    @Override
    public String toString() {
        return "Season{" +
                "season_id=" + season_id +
                ", hotel_id=" + hotel_id +
                ", season_strt_date='" + season_strt_date + '\'' +
                ", season_fnsh_date='" + season_fnsh_date + '\'' +
                '}';
    }
    public ComboItem getComboItem() {
        return new ComboItem(this.getSeason_id(),this.getSeason_strt_date() + " - " + this.getSeason_fnsh_date());
    }
}
