package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class AdminView extends Layout {
    private User user;
    private JPanel container;
    private JButton btn_logout;
    private JComboBox<User.Role> cmb_user_search;
    private JButton btn_save;
    private JButton btn_search;
    private JLabel lbl_welcome;
    private JTable tbl_user;
    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private UserManager userManager;
    private JPopupMenu userMenu;
    private Object[] col_user;

    public AdminView(User loggedInUser) {
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitilaze(700, 500);
        this.user = loggedInUser;
        if (loggedInUser == null) {
            dispose();
        }
        this.lbl_welcome.setText("Hoşgeldiniz " + this.user.getUsername());

        loadUserTable(null);
        loadUserComponent();
        loadUserFilter();

        this.tbl_user.setComponentPopupMenu(userMenu);
    }

    public void loadUserTable(ArrayList<Object[]> usersList) {
        this.col_user = new Object[]{"Kullanıcı ID", "Kullanıcı Adı", "Parola", "Kullanıcı Rolü"};
        if (usersList == null) {
            usersList = this.userManager.getForTable(col_user.length, userManager.findAll());
        }
        this.createTable(this.tmdl_user, tbl_user, col_user, usersList);
    }


    public void loadUserComponent() {
        //tıklanan satırın seçilmesi
        this.tbl_user.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_user.rowAtPoint(e.getPoint());
                tbl_user.setRowSelectionInterval(selected_row, selected_row);
            }
        });

        // ekleme,güncelle,sil butonları ekleme
        this.userMenu = new JPopupMenu();
        this.userMenu.add("Yeni").addActionListener(e -> {
            UserView userView = new UserView(null);
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });

        // seçili olan userid yi databaseden alıp buraya gönderme
        userMenu.add("Güncelle").addActionListener(e -> {
            int selectUserId = this.getTableSelectedRow(tbl_user, 0);
            UserView userView = new UserView(this.userManager.getById(selectUserId));
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });
        userMenu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectUserId = this.getTableSelectedRow(tbl_user, 0);
                if (this.userManager.delete(selectUserId)) {
                    Helper.showMsg("done");
                    loadUserTable(null);
                } else {
                    Helper.showMsg("error");
                }
            }
        });

        this.btn_search.addActionListener(e -> {
            ArrayList<User> userListBySearch = this.userManager.searchForTable((User.Role) cmb_user_search.getSelectedItem());
            ArrayList<Object[]> userRowListBySearch = this.userManager.getForTable(col_user.length, userListBySearch);
            loadUserTable(userRowListBySearch);
        });

    }

    public void loadUserFilter() {
        this.cmb_user_search.setModel(new DefaultComboBoxModel<>(User.Role.values()));
        this.cmb_user_search.setSelectedItem(null);
    }

    public void loadHotelTable() {


    }
}
