import controller.DBController;
import model.Customer;
import model.Distillation;
import view.MainMenu;

import java.sql.Date;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args){

        DBController controller = new DBController();

        Customer c1 = new Customer("Pista", 11223344, "Csorna", "Mező utca 14.", "202480285");
        Distillation d1 = new Distillation(11223344, 000001, 4.5, 6.6, Date.valueOf(LocalDate.now()));

        //controller.insertCustomer(c1);
        //controller.insertDistilled(d1);
        controller.removeDistilled(d1);

        MainMenu menu = new MainMenu();
    }
}
