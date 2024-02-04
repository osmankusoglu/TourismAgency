package view;

import business.HotelManager;
import business.PencionManager;
import business.RoomManager;
import business.SeasonManager;
import entity.Hotel;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class EmployeeView extends Layout {
    private JPanel container;
    private User user;
    private JTabbedPane tbp_room;
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
    private JPopupMenu hotelMenu;
    Object[] col_hotel;
    Object[] col_pencion;
    Object[] col_season;
    Object[] col_room;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tmdl_pencion = new DefaultTableModel();
    private DefaultTableModel tmdl_season = new DefaultTableModel();
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private PencionManager pencionManager;
    private SeasonManager seasonManager;
    private HotelManager hotelManager;
    private RoomManager roomManager;

    public EmployeeView(User loginUser) {
        this.hotelManager = new HotelManager();
        this.pencionManager = new PencionManager(null);
        this.seasonManager = new SeasonManager(null);
        this.roomManager = new RoomManager();
        this.add(container);
        this.guiInitilaze(1200, 600);
        this.user = loginUser;
        loadHotelAddView(null);
        loadPencionTable(null);
        loadRoomTable();
        loadRoomAddComponent();

        this.lbl_welcome.setText("Hoş geldiniz : " + this.user.getUsername());

        Object[] col_hotel = {"Otel ID", "Otel Adı", "Otel Şehri", "Otel Maili", "Otel Telefonu", "Otel Yıldızı", "Otel Otoparkı", "Otel Wifi", "Otel Havuzu", "Otel Spor Salonu", "Otel Kapı Hizmeti", "Otel Spa", "Otel Oda Servisi"};
        ArrayList<Hotel> hotelList = this.hotelManager.findAll();
        tmdl_hotel.setColumnIdentifiers(col_hotel);
        for (Hotel hotel : hotelList) {
            Object[] obj = {hotel.getId(), hotel.getName(), hotel.getAddress(), hotel.getMail(), hotel.getPhone(), hotel.getStar(), hotel.isCar_park(), hotel.isWifi(), hotel.isPool(), hotel.isFitness(), hotel.isConcierge(), hotel.isSpa(), hotel.isRoom_service()};
            tmdl_hotel.addRow(obj);
        }

        this.tbl_hotel.setModel(tmdl_hotel);
        this.tbl_hotel.getTableHeader().setReorderingAllowed(false);
        this.tbl_hotel.setEnabled(false);

        this.tbl_hotel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_hotel.rowAtPoint(e.getPoint());
                tbl_hotel.setRowSelectionInterval(selectedRow, selectedRow);
            }
        });
        this.hotelMenu = new JPopupMenu();
        this.hotelMenu.add("Pansiyon Tipi Ekle").addActionListener(e -> {
            int selectedId = getTableSelectedRow(tbl_hotel, 0);
            PencionView pensionView = new PencionView(hotelManager.getById(selectedId));
            pensionView.addWindowListener((new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    loadPencionTable(null);

                }

            }));
            loadPencionTable(null);
        });

        this.hotelMenu.add("Sezon Ekle").addActionListener(e -> {
            int selectedId = getTableSelectedRow(tbl_hotel, 0);
            SeasonView seasonView = new SeasonView(hotelManager.getById(selectedId));
            seasonView.addWindowListener((new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable(null);
                }
            }));
            loadSeasonTable(null);
        });
        this.tbl_hotel.setComponentPopupMenu(hotelMenu);
        loadPencionTable(null);
        loadSeasonTable(null);

    }

    public void loadHotelAddView(Object o) {
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
        col_hotel = new Object[]{"Otel ID", "Otel Adı", "Otel Şehri", "Otel Maili", "Otel Telefonu", "Otel Yıldızı", "Otel Otoparkı", "Otel Wifi", "Otel Havuzu", "Otel Spor Salonu", "Otel Kapı Hizmeti", "Otel Spa", "Otel Oda Servisi"};
        if (hotelList == null) {
            hotelList = this.hotelManager.getForTable(col_hotel.length, this.hotelManager.findAll());
        }
        this.createTable(this.tmdl_hotel, this.tbl_hotel, col_hotel, hotelList);
    }

    public void loadPencionTable(ArrayList<Object[]> pancionList) {
        col_pencion = new Object[]{"pencion_id", "hotel_id", "pencion_type"};
        if (pancionList == null) {
            pancionList = this.pencionManager.getForTable(col_pencion.length, this.pencionManager.findAll());
        }
        //System.out.println("loadhotel" + pancionList.size());
        createTable(this.tmdl_pencion, this.tbl_pencion, col_pencion, pancionList);

    }

    public void loadSeasonTable(ArrayList<Object[]> seasonList) {
        col_season = new Object[]{"season_id", "hotel_id", "season_strt_date", "season_fnsh_date"};
        if (seasonList == null) {
            seasonList = this.seasonManager.getForTable(col_season.length, this.seasonManager.findAll());
        }
        //System.out.println("loadhotel" + pancionList.size());
        createTable(this.tmdl_season, this.tbl_season, col_season, seasonList);
    }

    public void loadRoomTable() {
        col_room = new Object[]{"Id", "Otel Adı", "Pansiyon", "Oda Tipi", "Stok", "Yetişkin Fiyat", "Çocuk Fiyat", "Yatak Kapasitesi", "m2", "Tv", "Minibar", "Konsol", "Kasa", "Projeksiyon"};
        ArrayList<Object[]> roomListe = null;
        if (roomListe == null) {
            roomListe = this.roomManager.getForTable(col_room.length, this.roomManager.findAll());
        }
        createTable(this.tmdl_room, this.tbl_room_att, col_room, roomListe);
    }

    public void loadRoomAddComponent() {
        btn_room_add.addActionListener(e -> {
            RoomAddView roomAddView = new RoomAddView(null);
            roomAddView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null);
                    loadRoomTable();
                }
            });
        });

    }

}
