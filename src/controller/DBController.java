package controller;

import model.Customer;
import model.Distillation;

import java.sql.*;

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

        }catch (SQLException sqlerr){
            System.err.println(sqlerr.getClass().getName() + ": " + sqlerr.getMessage());
            System.exit(0);
        }
        System.out.println("Data added");
    }

    public void removeDistilled(Distillation d){
        String sql = "UPDATE Distillation set ACTIVE = 0 where DistNum = ?;";
        try{
            stmt = connection.createStatement();
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, d.getDistillationNumber());
            pst.executeUpdate();

        }catch (SQLException sqlerr){
            System.err.println(sqlerr.getClass().getName() + ": " + sqlerr.getMessage());
            System.exit(0);
        }
        System.out.println("Data is not active");
    }
}
