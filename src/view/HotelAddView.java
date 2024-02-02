package view;

import business.HotelManager;
import core.Helper;
import entity.Hotel;

import javax.swing.*;

public class HotelAddView extends Layout {
    private JPanel container;
    private JTextField fld_hotel_name;
    private JTextField fld_mail;
    private JTextField fld_phone;
    private JComboBox cmb_star;
    private JRadioButton rb_pool;
    private JRadioButton rp_fitness;
    private JRadioButton rb_car_park;
    private JRadioButton rb_concierge;
    private JRadioButton rb_spa;
    private JRadioButton rb_room_service;
    private JButton btn_hotel_save;
    private JRadioButton rb_wifi;
    private JLabel lbl_star;
    private JLabel lbl_phone;
    private JLabel lbl_mail;
    private JLabel lbl_hotel_name;
    private JLabel lbl_hote_add;
    private JLabel lbl_address;
    private JTextField fld_address;
    private HotelManager hotelManager;
    private Hotel hotel;

    public HotelAddView(Object o) {
        this.hotel = hotel;
        this.hotelManager = new HotelManager();
        this.add(container);
        this.guiInitilaze(300, 500);
        //loadHotelAddComponent();

    }

    public void loadHotelAddComponent() {
        btn_hotel_save.addActionListener(e -> {
            JTextField[] checkFieldList = {this.fld_hotel_name, this.fld_mail, this.fld_phone, this.fld_address};
            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMsg("fill");
            } else {
                boolean result = true;
                Hotel hotelNew = new Hotel();
                hotelNew.setName(fld_hotel_name.getText());
                hotelNew.setMail(fld_mail.getText());
                hotelNew.setPhone(fld_phone.getText());
                hotelNew.setAddress(fld_address.getText());
                hotelNew.setStar(String.valueOf(cmb_star.getSelectedItem()));
                hotelNew.setCar_park(rb_car_park.isSelected());
                hotelNew.setPool(rb_pool.isSelected());
                hotelNew.setFitness(rp_fitness.isSelected());
                hotelNew.setConcierge(rb_concierge.isSelected());
                hotelNew.setSpa(rb_spa.isSelected());
                hotelNew.setRoom_service(rb_room_service.isSelected());
                if (hotelNew.getId() == 0) {
                    result = this.hotelManager.save(hotelNew);
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