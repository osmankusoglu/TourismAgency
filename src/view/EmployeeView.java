package view;

import business.HotelManager;
import business.PencionManager;
import entity.Hotel;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
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
    private JPopupMenu hotelMenu;
    Object[] col_pancion;
    private DefaultTableModel tmdl_pancion = new DefaultTableModel();
    private PencionManager pencionManager;

    public EmployeeView(User loginUser){
        this.hotelManager = new HotelManager();
        this.pencionManager = new PencionManager(null);

        this.add(container);
        this.guiInitilaze(1200,600);
        this.user = loginUser;
        loadHotelAddView();
        loadPancionTable(null);
        /*if (this.user == null){
            dispose();

        }*/
        this.lbl_welcome.setText("Hoş geldiniz : " + this.user.getUsername());

        Object[] col_hotel = {"Otel ID","Otel Adı","Otel Şehri","Otel Maili","Otel Telefonu","Otel Yıldızı","Otel Otoparkı","Otel Wifi","Otel Havuzu","Otel Spor Salonu","Otel Kapı Hizmeti","Otel Spa","Otel Oda Servisi"};
        ArrayList<Hotel> hotelList = this.hotelManager.findAll();
        tmdl_hotel.setColumnIdentifiers(col_hotel);
        for (Hotel hotel : hotelList) {
            Object[] obj = {hotel.getId(),hotel.getName(),hotel.getAddress(),hotel.getMail(),hotel.getPhone(),hotel.getStar(),hotel.isCar_park(),hotel.isWifi(),hotel.isPool(),hotel.isFitness(),hotel.isConcierge(),hotel.isSpa(),hotel.isRoom_service()};
            tmdl_hotel.addRow(obj);
        }

        this.tbl_hotel.setModel(tmdl_hotel);
        this.tbl_hotel.getTableHeader().setReorderingAllowed(false);
        this.tbl_hotel.setEnabled(false);

        this.tbl_hotel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_hotel.rowAtPoint(e.getPoint());
                tbl_hotel.setRowSelectionInterval(selectedRow,selectedRow);
            }
        });
        this.hotelMenu = new JPopupMenu();
        this.hotelMenu.add("Pansiyon Tipi Ekle").addActionListener(e -> {
            int selectedId = getTableSelectedRow(tbl_hotel, 0);
            PencionView pensionView = new PencionView(hotelManager.getById(selectedId));
            loadPancionTable(null);


        });

        this.hotelMenu.add("Sezon Ekle");

        this.tbl_hotel.setComponentPopupMenu(hotelMenu);
        loadPancionTable(null);

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
    public void loadPancionTable(ArrayList<Object[]> pancionList) {
        col_pancion = new Object[]{"pencion_id", "hotel_id", "pencion_type"};
        if (pancionList == null) {
            pancionList = this.pencionManager.getForTable(col_pancion.length, this.pencionManager.findAll());
        }
        //System.out.println("loadhotel" + pancionList.size());
        createTable(this.tmdl_pancion, this.tbl_pencion, col_pancion, pancionList);


    }
}
