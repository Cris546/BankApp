import java.awt.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;

import java.util.Date;


public class NewCustomerFrame extends JFrame{

    private JLabel greetingLabel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel birthLabel;
    private JFormattedTextField bField;
    private JLabel pinLabel;
    private JFormattedTextField pinField;

    private JButton saveButton;
    private JButton cancelButton;
    
    public NewCustomerFrame(){
        createComponents();
        this.setTitle("New Customer Page");
        this.setSize(850, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createComponents(){
        greetingLabel = new JLabel("Welcome! Please fill out the information below.");
        nameLabel = new JLabel("Name: ");
        nameTextField = new JTextField(10);

        birthLabel = new JLabel("Date of Birth: ");
        try{
            MaskFormatter dateFormatter = new MaskFormatter("##/##/####");
            bField = new JFormattedTextField(dateFormatter);
            bField.setColumns(10);

            MaskFormatter numberFormat = new MaskFormatter("####");
            pinField = new JFormattedTextField(numberFormat);
            pinField.setColumns(5);

        }
        catch (ParseException e){
            e.printStackTrace();
        }
        
        

        pinLabel = new JLabel("PIN: ");
        

        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        JPanel newCusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        newCusPanel.add(greetingLabel);
        newCusPanel.add(nameLabel);
        newCusPanel.add(nameTextField);
        newCusPanel.add(birthLabel);
        newCusPanel.add(bField);
        newCusPanel.add(pinLabel);
        newCusPanel.add(pinField);
        newCusPanel.add(saveButton);
        newCusPanel.add(cancelButton);

        this.add(newCusPanel);



    }

    public static void main(String[] args) {
        NewCustomerFrame t = new NewCustomerFrame();
        t.setSize(500, 500);
        t.setDefaultCloseOperation(EXIT_ON_CLOSE);
        t.setLocationRelativeTo(null);
        t.setVisible(true);
    }
}
