package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {

    private JTable table;
    private JButton btnNewCustomer;
    private JButton btnEdit;
    private JButton btnRemove;
    private JButton btnExport;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton btnSearch;
    private JComboBox cmbPeriod;

    public MainMenu() {

        btnNewCustomer.setText("Új főzető");

        btnNewCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
