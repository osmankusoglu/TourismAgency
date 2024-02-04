package view;

import business.HotelManager;
import business.SeasonManager;
import core.Helper;
import entity.Hotel;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SeasonView extends Layout {
    private JPanel container;
    private JLabel lbl_season_id;
    private JLabel lbl_season_strt;
    private JLabel lbl_season_fnsh;
    private JButton btn_season_save;
    private JFormattedTextField ftxt_season_strt;
    private JFormattedTextField ftxt_season_fnsh;
    private Hotel hotel;
    private HotelManager hotelManager;
    //private PencionManager pencionManager;
    private SeasonManager seasonManager;


    public SeasonView(Hotel hotel) {
        this.seasonManager = new SeasonManager(null);
        //this.pencionManager = new PencionManager(hotel);
        this.hotelManager = new HotelManager();
        this.hotel = hotel;
        this.add(container);
        this.guiInitilaze(400, 350);
        //this.lbl_season_id.setText(String.valueOf(this.hotel.getId()));
        lbl_season_id.setText("OTEL ID : " + hotel.getId());

        btn_season_save.addActionListener(e -> {
            String convertedSeaonStrtDate = LocalDate.parse(this.ftxt_season_strt.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();

            String convertedSeaonFnshDate = LocalDate.parse(this.ftxt_season_fnsh.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();

            seasonManager.saveSeason(hotel, convertedSeaonStrtDate, convertedSeaonFnshDate);
            dispose();
        });
    }

    private void createUIComponents() throws ParseException {
        this.ftxt_season_strt = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.ftxt_season_fnsh = new JFormattedTextField(new MaskFormatter("##/##/####"));

    }
}
