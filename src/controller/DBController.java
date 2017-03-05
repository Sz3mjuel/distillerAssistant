package controller;

import model.Customer;
import model.Distillation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBController {

    Connection connection = null;
    Statement stmt = null;

    public DBController() {

        connection = DBConnector.DBConnector();

    }

    public void insertCustomer(Customer c){
        String sql = "INSERT INTO Customers (VATNum, Name, City, Address, Phone, IDCard, EMailAd) " +
                "VALUES (?,?,?,?,?,?,?);";
        try {
            stmt = connection.createStatement();
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, c.getVATNumber());
            pst.setString(2, c.getName());
            pst.setString(3, c.getCity());
            pst.setString(4, c.getAddress());
            pst.setString(5, c.getPhone());
            pst.setString(6, c.getIDCard());
            pst.setString(7, c.getEmailAddress());
            pst.executeUpdate();

            pst.close();
            stmt.close();

        }catch (SQLException sqlerr){
            System.err.println(sqlerr.getClass().getName() + ": " + sqlerr.getMessage());
            System.exit(0);
        }
        System.out.println("Data added");
    }

    public void insertDistilled(Distillation d){
        String sql = "INSERT INTO Distillation (VATNum, DistNum, MeshNum, HLF, GYSZL, Bring, Took) " +
                "VALUES (?,?,?,?,?,?,?);";
        try {
            stmt = connection.createStatement();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, d.getVATNumber());
            pst.setInt(2, d.getDistillationNumber());
            pst.setInt(3, d.getMashPaperNumber());
            pst.setDouble(4, d.getHLF());
            pst.setDouble(5, d.getGYSZL());
            pst.setDate(6, d.getBring());
            pst.setDate(7, d.getTook());
            pst.executeUpdate();

            stmt.close();
            pst.close();

        }catch (SQLException sqlerr){
            System.err.println(sqlerr.getClass().getName() + ": " + sqlerr.getMessage());
            System.exit(0);
        }
        System.out.println("Data added");
    }

    public void updateDistilled(Distillation d, int isActive){
        String sql = "UPDATE Distillation set " +
                "VATNum = ?, " +
                "MeshNum = ?, " +
                "HLF = ?, " +
                "GYSZL = ?, " +
                "ACTIVE = ? " +
                "where DistNum = ?;";
        try{
            stmt = connection.createStatement();
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(6, d.getDistillationNumber());
            pst.setInt(1, d.getVATNumber());
            pst.setInt(2, d.getMashPaperNumber());
            pst.setDouble(3, d.getHLF());
            pst.setDouble(4, d.getGYSZL());
            pst.setInt(5, isActive);
            pst.executeUpdate();

            pst.close();
            stmt.close();

            System.out.println(d.getDistillationNumber() + " is updated!");
        }catch (SQLException ex){
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
    }

    public void updateCustomer(Customer c, int VAT){
        String sql = "UPDATE Customers set " +
                "Name = ?, " +
                "City = ?, " +
                "Address = ?, " +
                "Phone = ?, " +
                "IDCard = ?," +
                "EMailAd = ?, " +
                "VATNum = ? " +
                "where VATNum = ?;";

        try{
            stmt = connection.createStatement();
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, c.getName());
            pst.setString(2, c.getCity());
            pst.setString(3, c.getAddress());
            pst.setString(4, c.getPhone());
            pst.setString(5, c.getIDCard());
            pst.setString(6, c.getEmailAddress());
            pst.setInt(7, VAT);
            pst.setInt(8, c.getVATNumber());

            pst.executeUpdate();

            pst.close();
            stmt.close();

        }catch (SQLException ex){
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
    }

    public void removeDistilled(Distillation d){
        String sql = "UPDATE Distillation set ACTIVE = 0 where DistNum = ?;";
        try{
            stmt = connection.createStatement();
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, d.getDistillationNumber());
            pst.executeUpdate();

            pst.close();
            stmt.close();

        }catch (SQLException sqlerr){
            System.err.println(sqlerr.getClass().getName() + ": " + sqlerr.getMessage());
            System.exit(0);
        }
        System.out.println("Data is not active");
    }

    public List<Customer> listOfCustomers(){
        String sql = "SELECT * FROM Customers;";
        List<Customer> customers = new ArrayList<>();

        try{
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                Customer customer = new Customer(
                        rs.getString("Name"),
                        rs.getInt("VATNum"),
                        rs.getString("City"),
                        rs.getString("Address"),
                        rs.getString("Phone")
                        );
                customer.setAddress(rs.getString("Address"));
                customer.setEmailAddress(rs.getString("EMailAd"));
                customers.add(customer);
            }

            rs.close();
            stmt.close();

            return customers;

        }catch (SQLException ex){
            System.err.println(ex.getClass().getName() +": "+ ex.getMessage());
            return null;
        }
    }

    public List<Distillation> listOfDistilled(){
        String sql = "SELECT * FROM Distillation;";
        List<Distillation> tmpList = new ArrayList<>();

        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){

                if(rs.getInt("ACTIVE") == 1) {

                    Distillation distillation = getDistillation(rs);

                    tmpList.add(distillation);
                }
            }
            rs.close();
            stmt.close();

            return tmpList;

        }catch (SQLException ex){
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            return null;
        }
    }

    public List<Distillation> listOfDistilled(Date from, Date to){
        String sql = "SELECT * FROM Distillation;";
        List<Distillation> tmpList = new ArrayList<>();

        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){

                Date took = rs.getDate("Took");

                //&& (took.getTime() >= from.getTime() || took.getTime() <= to.getTime())

                if(rs.getInt("ACTIVE") == 1) {

                    Distillation distillation = getDistillation(rs);

                    tmpList.add(distillation);
                }
            }
            rs.close();
            stmt.close();

            return tmpList;

        }catch (SQLException ex){
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            return tmpList;
        }
    }

    private Distillation getDistillation(ResultSet rs) throws SQLException {
        int VATNum = rs.getInt("VATNum");
        int DistNum = rs.getInt("DistNum");
        int MeshNum = rs.getInt("MeshNum");
        double HLF = rs.getDouble("HLF");
        double GYSZL = rs.getDouble("GYSZL");
        Date Bring = rs.getDate("Bring");
        Date Took = rs.getDate("Took");

        Distillation distillation = new Distillation(VATNum, DistNum, HLF, GYSZL, Bring);

        distillation.setMashPaperNumber(MeshNum);
        distillation.setTook(Took);
        return distillation;
    }


}
