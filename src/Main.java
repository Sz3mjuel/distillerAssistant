import controller.DBController;
import model.Customer;
import model.Distillation;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Distillation> distillations = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();

    public static void main(String[] args){

        DBController controller = new DBController();

        Customer c1 = new Customer("Pista", 11223344, "Csorna", "Mező utca 14.", "202480285");
        Customer c2 = new Customer("Jenő", 44332211, "Bezi", "Kossuth L. u . 20.", "301234567");

        Distillation d1 = new Distillation(11223344, 1000001, 4.5, 6.6, Date.valueOf(LocalDate.now()));
        Distillation d2 = new Distillation(44332211, 1000002, 6.7, 3.3, Date.valueOf(LocalDate.now()));

        //controller.insertDistilled(d1);
        controller.updateDistilled(d1, 0);

        distillations = controller.listOfDistilled();
        customers = controller.listOfCustomers();

        for(Customer c : customers){

            StringBuilder sb = new StringBuilder();

            sb.append(c.getVATNumber() + " : " + c.getName());
            sb.append(System.getProperty("line.separator"));

            for(Distillation d : distillations){
                if(d.getVATNumber() == c.getVATNumber()) {
                    sb.append(d.getDistillationNumber());
                    sb.append(System.getProperty("line.separator"));
                }
            }
            System.out.println(sb);
        }

    }
}
