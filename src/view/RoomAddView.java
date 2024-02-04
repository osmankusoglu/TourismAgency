package view;

import business.HotelManager;
import business.PencionManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Pencion;
import entity.Room;
import entity.Season;

import javax.swing.*;
import java.awt.event.*;

public class RoomAddView extends Layout {
    private JPanel container;
    private JButton btn_room_add;
    private JComboBox cmb_hotel_add;
    private JComboBox cmb_pencion_add;
    private JComboBox cmb_season_add;
    private JComboBox cmb_room_type_add;
    private JTextField fld_stock_add;
    private JTextField fld_square_meter_add;
    private JTextField fld_bed_capacity_add;
    private JRadioButton rdb_tv_add;
    private JRadioButton rdb_minibar_add;
    private JRadioButton rdb_game_console_add;
    private JRadioButton rdb_case_box_add;
    private JRadioButton rdb_projection_add;
    private JLabel fld_oda;
    private JTextField fld_adult_add;
    private JTextField fld_child_add;
    private JPanel pnl_room_add;
    private JPanel pnl_add_room_right;
    private JPanel pnl_add_room_left;
    private RoomAddView roomAddView;
    private RoomManager roomManager;
    private Room room;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private PencionManager pencionManager;

    public RoomAddView(Object o) {
        this.roomManager = new RoomManager();
        this.seasonManager = new SeasonManager(null);
        this.pencionManager = new PencionManager(null);
        this.hotelManager = new HotelManager();
        this.room = room;
        this.add(container);
        this.guiInitilaze(1000, 600);
       this.cmb_room_type_add.setModel(new DefaultComboBoxModel<>(Room.RoomType.values()));

       for (Hotel hotel : this.hotelManager.findAll()){
            cmb_hotel_add.addItem(hotel.getComboItem());
        }
        for (Season season : this.seasonManager.findAll()) {
            cmb_season_add.addItem(season.getComboItem());
        }
        for (Pencion pencion : this.pencionManager.findAll()) {
            cmb_pencion_add.addItem((pencion.getComboItem()));
        }

        btn_room_add.addActionListener(e -> {
            JTextField[] checkFieldList = {this.fld_stock_add, this.fld_adult_add, this.fld_child_add, this.fld_bed_capacity_add,this.fld_square_meter_add};
            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMsg("fill");
            } else {
                boolean result = true;
                Room roomNew = new Room();

                ComboItem selectedOtelInfo = (ComboItem) cmb_hotel_add.getSelectedItem();
                roomNew.setHotel_id(selectedOtelInfo.getKey());

                ComboItem selectedSeasonInfo = (ComboItem) cmb_season_add.getSelectedItem();
                roomNew.setSeason_id(selectedSeasonInfo.getKey());

                ComboItem selectedPensionInfo = (ComboItem) cmb_pencion_add.getSelectedItem();
                roomNew.setPencion_id(selectedPensionInfo.getKey());

                roomNew.setRoom_stock(Integer.parseInt(fld_stock_add.getText()));
                roomNew.setRoom_adult_price(Integer.parseInt(fld_adult_add.getText()));
                roomNew.setRoom_child_price(Integer.parseInt(fld_child_add.getText()));
                roomNew.setRoom_bed_capacity(Integer.parseInt(fld_bed_capacity_add.getText()));
                roomNew.setRoom_square_meter(Integer.parseInt(fld_square_meter_add.getText()));
                roomNew.setRoom_type(String.valueOf((Room.RoomType) cmb_room_type_add.getSelectedItem()));
                roomNew.setRoom_television(rdb_tv_add.isSelected());
                roomNew.setRoom_minibar(rdb_minibar_add.isSelected());
                roomNew.setRoom_game_console(rdb_game_console_add.isSelected());
                roomNew.setRoom_cash_box(rdb_case_box_add.isSelected());
                roomNew.setRoom_projection(rdb_projection_add.isSelected());

                if (roomNew.getRoom_id() == 0) {
                    result = this.roomManager.save(roomNew);
                    dispose();

                } else {

                }

                if (result) {
                    Helper.showMsg("done");


                } else {
                    Helper.showMsg("error");
                }

            }
        });

    }

}

