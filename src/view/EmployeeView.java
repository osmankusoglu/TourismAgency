package view;

import business.HotelManager;
import entity.Hotel;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class EmployeeView extends Layout {
    private JPanel container;
    private User user;
    private JTabbedPane tbp_hotel;
    private JButton btn_emp_logout;
    private JButton btn_add_hotel;
    private JLabel lbl_welcome;
    private JPanel pnl_hotel;
    private JPanel pnl_room;
    private JPanel pnl_reserv;
    private JPanel pnl_inside_room;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_city;
    private JTextField fld_hotel_strt_date;
    private JTextField fld_hotel_fnsh_date;
    private JTextField fld_hotel_adult;
    private JTextField fld_hotel_child;
    private JButton btn_room_search;
    private JButton btn_room_add;
    private JButton btn_room_reset;
    private JTable tbl_room_att;
    private JLabel lbl_hotel_name;
    private JLabel lbl_hotel_city;
    private JLabel lbl_hotel_strt_date;
    private JLabel lbl_hotel_fnsh_date;
    private JLabel lbl_hotel_adult;
    private JLabel lbl_hotel_child;
    private JTable tbl_reserv;
    private JTable tbl_hotel;
    private JTable tbl_season;
    private JTable tbl_pencion;
    private JScrollPane scrl_hotel;
    private JScrollPane scrl_pension;
    private JScrollPane scrl_season;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private HotelManager hotelManager;
    private Object[] col_hotel;


    public EmployeeView(User loginUser){
        this.hotelManager = new HotelManager();
        this.add(container);
        this.guiInitilaze(1200,600);
        this.user = loginUser;
        /*if (this.user == null){
            dispose();

        }*/
        lbl_welcome.setText("Hoş geldiniz : " + this.user.getUsername());

        Object[] col_hotel = {"Otel ID","Otel Adı","Otel Şehri","Otel Maili","Otel Telefonu","Otel Yıldızı","Otel Otoparkı","Otel Wifi","Otel Havuzu","Otel Spor Salonu","Otel Kapı Hizmeti","Otel Spa","Otel Oda Servisi"};
        ArrayList<Hotel> hotelList = hotelManager.findAll();
        tmdl_hotel.setColumnIdentifiers(col_hotel);
        for (Hotel hotel : hotelList) {
            Object[] obj = {hotel.getId(),hotel.getName(),hotel.getAddress(),hotel.getMail(),hotel.getPhone(),hotel.getStar()};
            tmdl_hotel.addRow(obj);
        }

        tbl_hotel.setModel(tmdl_hotel);
        tbl_hotel.getTableHeader().setReorderingAllowed(false);
        tbl_hotel.setEnabled(false);
        loadHotelTable(null);
        loadHotelAddView();

    }
    public void loadHotelAddView(){
        btn_add_hotel.addActionListener(e -> {
            HotelAddView hotelAddView = new HotelAddView(null);
            hotelAddView.addWindowListener(new WindowAdapter() { //yeni açılan pencereyi izler
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null); //kapandıktan sonra tabloyu günceller
                }
            });
        });

    }

    public void loadHotelTable(ArrayList<Object[]> hotelList) {
        col_hotel = new Object[]{"Otel ID","Otel Adı","Otel Şehri","Otel Maili","Otel Telefonu","Otel Yıldızı","Otel Otoparkı","Otel Wifi","Otel Havuzu","Otel Spor Salonu","Otel Kapı Hizmeti","Otel Spa","Otel Oda Servisi"};
        if (hotelList == null){
            hotelList = this.hotelManager.getForTable(col_hotel.length,this.hotelManager.findAll());
        }
        this.createTable(this.tmdl_hotel,this.tbl_hotel, col_hotel, hotelList);
    }
}
