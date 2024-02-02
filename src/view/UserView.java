package view;

import business.HotelManager;
import business.UserManager;
import core.Helper;
import entity.Hotel;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class UserView extends Layout {
    private JPanel container;
    private JLabel lbl_user;
    private JLabel lbl_user_name;
    private JTextField fld_user_name;
    private JPasswordField fld_user_pass;
    private JComboBox<User.Role> cmb_user_role;
    private JButton btn_userSave;
    private User user;
    private UserManager userManager;
    private DefaultTableModel tmdl_user;


    public UserView(User user) {
        this.user = user;
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitilaze(300, 300);

        if (user == null) {
            this.user = new User();
        } else {
            this.user = user;
        }
        if (user != null) {
            this.fld_user_name.setText(user.getUsername());
            this.fld_user_pass.setText(user.getPassword());
            this.cmb_user_role.setSelectedItem(user.getRole());
        }
        this.cmb_user_role.setModel(new DefaultComboBoxModel<>(User.Role.values()));
        btn_userSave.addActionListener(e -> {
            if (Helper.isFieldEmpty(this.fld_user_name) || (Helper.isFieldEmpty(this.fld_user_name))) {
                Helper.showMsg("fill");
            } else {
                boolean result = true;
                this.user.setUsername(fld_user_name.getText());
                this.user.setPassword(fld_user_pass.getText());
                this.user.setRole(String.valueOf(cmb_user_role.getSelectedItem()));

                if (this.user.getId() != 0) {
                    result = this.userManager.update(this.user);
                } else {
                    result = this.userManager.save(this.user);
                }
                if (result) {

                    Helper.showMsg("done");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }
}