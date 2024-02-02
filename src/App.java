import business.UserManager;
import core.Db;
import core.Helper;
import view.AdminView;
import view.EmployeeView;
import view.LoginView;
import view.UserView;

import java.sql.Connection;
import java.sql.DriverManager;

public class App {
    public static void main(String[] args) {
        Helper.setTheme();
        LoginView loginView = new LoginView();
        //EmployeeView employeeView = new EmployeeView();

    }
}
