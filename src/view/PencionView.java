package view;

import business.HotelManager;
import business.PencionManager;
import entity.Hotel;

import javax.swing.*;

public class PencionView extends Layout {
    private JPanel container;
    private JComboBox cmb_pencion;
    private JButton btn_pencion_save;
    private JLabel lbl_hotel_id;
    private Hotel hotel;
    private HotelManager hotelManager;
    private PencionManager pencionManager;


    public PencionView(Hotel hotel) {
        this.pencionManager = new PencionManager(hotel);
        this.hotelManager = new HotelManager();
        this.hotel = hotel;
        this.add(container);
        this.guiInitilaze(400, 350);
        this.lbl_hotel_id.setText(String.valueOf(this.hotel.getId()));
        lbl_hotel_id.setText("OTEL ID : " + hotel.getId());

        btn_pencion_save.addActionListener(e -> {
            this.cmb_pencion.getSelectedItem().toString();
            System.out.println(this.cmb_pencion.getSelectedItem().toString());
            pencionManager.savePencion(hotel, String.valueOf(cmb_pencion.getSelectedItem()));
            dispose();
        });
    }


}