import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.*;

import java.text.*;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.SwingUtilities;
import javax.swing.*;
import javax.swing.text.*;

public class SignInFrame extends JFrame{
    private JLabel greetingLabel;
    private JButton existingButton;
    private JButton newCusButton;

    private JLabel enterPINLabel;
    private JFormattedTextField pField;
    private JButton enterButton;
    private JButton cancelButton;

    private JLabel newCusGreetingLabel;
    private JLabel newCusLabel;
    private JTextField newCusTextField;
    private JLabel newCusBirthLabel;
    private JFormattedTextField bField;
    private JFormattedTextField pinField;
    private JLabel newCusPINLabel;
    private JButton newCusSaveButton;
    
    private JLabel customerLabel;
    private JButton depositButton;
    private JButton widthdrawButton;
    private JButton deleteButton;
    private JButton signOutButton;

    private JLabel depositLabel;
    private JFormattedTextField moneyField;
    private JButton insertButton;
    private JButton doneButton;

    private JLabel widthdrawLabel;
    private JButton takeButton;


    private JPanel signInPanel;
    private JPanel existingSignInPanel;
    private JPanel newCusPanel;
    private JPanel customerMainPanel;
    private JPanel depositPanel;
    private JPanel widthdrawPanel;
    private JPanel deletePanel;

    private BankDataManager db;
    



    public SignInFrame(){
        createComponents();
        this.setTitle("Cris' Bank App");
        this.setSize(500,500);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    

    private void createComponents(){
        enterPINLabel = new JLabel("Welcome back! Please enter your PIN:");
        try{
            MaskFormatter numberFormat = new MaskFormatter("####");
            pField = new JFormattedTextField(numberFormat);
            pField.setColumns(5);
                
    
        }
        catch (ParseException e){
            e.printStackTrace();
        }   

        enterButton = new JButton("Enter");
        enterButton.addActionListener(new enterButtonListener());

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new cancelButtonListener());

        existingSignInPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        existingSignInPanel.add(enterPINLabel);
        existingSignInPanel.add(pField);
        existingSignInPanel.add(enterButton);
        existingSignInPanel.add(cancelButton);

        



        newCusGreetingLabel = new JLabel("Welcome! Please fill out the information below.");
        newCusLabel = new JLabel("Name: ");
        newCusTextField = new JTextField(10);

        newCusBirthLabel = new JLabel("Date of Birth: ");
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
        
        

        newCusPINLabel = new JLabel("PIN: ");
        

        newCusSaveButton = new JButton("Save");
        newCusSaveButton.addActionListener(new newCusSaveButtonListener());
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new cancelButtonListener());

        newCusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        newCusPanel.add(newCusGreetingLabel);
        newCusPanel.add(newCusLabel);
        newCusPanel.add(newCusTextField);
        newCusPanel.add(newCusBirthLabel);
        newCusPanel.add(bField);
        newCusPanel.add(newCusPINLabel);
        newCusPanel.add(pinField);
        newCusPanel.add(newCusSaveButton);
        newCusPanel.add(cancelButton);

        




        greetingLabel = new JLabel("Welcome! Please choose an option!");
        existingButton = new JButton("Exisiting Customer");
        existingButton.setFocusable(false);
        existingButton.addActionListener(new existButtonLister());

        newCusButton = new JButton("New Customer");
        newCusButton.setFocusable(false);
        newCusButton.addActionListener(new newCusButtonListener());


        signInPanel = new JPanel( new FlowLayout(FlowLayout.CENTER));
        signInPanel.add(greetingLabel);
        signInPanel.add(existingButton);
        signInPanel.add(newCusButton);

        

        this.add(signInPanel);

        

        




    }

    private void createCustomerPanel(int PIN, String Frame){
        db = new BankDataManager();
            
            Account user = db.findAccount(PIN);
            if(user.getName() != null){
                
                customerLabel = new JLabel("Welcome back " + user.getName() + "! Please select an option");
                depositButton = new JButton("Deposit");
                widthdrawButton = new JButton("Widthdraw");
                deleteButton = new JButton("Delete");
                signOutButton = new JButton("Sign Out");
                signOutButton.addActionListener(new signOutButtonListener());

                customerMainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                customerMainPanel.add(customerLabel);
                customerMainPanel.add(depositButton);
                customerMainPanel.add(widthdrawButton);
                customerMainPanel.add(deleteButton);
                customerMainPanel.add(signOutButton);

                getContentPane().removeAll();
                repaint();
                add(customerMainPanel);
                revalidate();

            }
            else{
                if(Frame.equals("existing")){
                    existingSignInPanel.add(new JLabel("ERROR: ENTER VALID PIN"));
                    revalidate();
                }
                
                
            }

            db.closeConn();

    }

    private void createDepositPanel(int PIN){
        db = new BankDataManager();
        Account user = db.findAccount(PIN);
        db.closeConn();

        depositPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        depositLabel = new JLabel("Current Balance: $" + user.getBalance());
        NumberFormat nFormat = new DecimalFormat("$#0,000.00");
        NumberFormatter numberFormatter = new NumberFormatter(nFormat);
        moneyField = new JFormattedTextField(numberFormatter);

        insertButton = new JButton("Deposit");
        doneButton = new JButton("Done");

        depositPanel.add(depositLabel);
        depositPanel.add(moneyField);
        depositPanel.add(insertButton);
        depositPanel.add(doneButton);

        getContentPane().removeAll();
        repaint();
        add(depositPanel);
        revalidate();

        
    }

    private void createWidthdrawPanel(int PIN){
        db = new BankDataManager();
        Account user = db.findAccount(PIN);
        db.closeConn();

        widthdrawPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        widthdrawLabel = new JLabel("Current Balance: $" + user.getBalance());
        NumberFormat nFormat = new DecimalFormat("$#0,000.00");
        NumberFormatter numberFormatter = new NumberFormatter(nFormat);
        moneyField = new JFormattedTextField(numberFormatter);
        takeButton = new JButton("Widthdraw");
        doneButton = new JButton("Done");

        widthdrawPanel.add(widthdrawLabel);
        widthdrawLabel.add(moneyField);
        depositPanel.add(takeButton);
        depositPanel.add(doneButton);
        

    }

    private void clearData(String type){
        if(type.equals("exist")){
            pField.setValue(null);
        }
        else if(type.equals("new")){
            newCusTextField.setText(null);
            bField.setValue(null);
            pinField.setValue(null);
        }
        

    }

    private void createMainMenu(){
            getContentPane().removeAll();
            repaint();
            add(signInPanel);
            revalidate();

    }

    class existButtonLister implements ActionListener{
        public void actionPerformed(ActionEvent click){
            getContentPane().removeAll();
            repaint();
            add(existingSignInPanel);
            revalidate();



       
            
            
        }
    }

    class enterButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent click){
            
            createCustomerPanel(Integer.parseInt(pField.getText()), "existing");
            pField.setText(null);


        }
    }

    class cancelButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent click){
            createMainMenu();



        }
    }

    class newCusButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent click){
            getContentPane().removeAll();
            repaint();
            add(newCusPanel);
            revalidate();

            
        }
    }

    class newCusSaveButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent click){
            String newName = newCusTextField.getText();
            String newBirth = bField.getText();
            int newPIN = Integer.parseInt(pinField.getText());
            db = new BankDataManager();
            if( db.isPinUni(newPIN) ){
                db.addAccount(new Account(newName, newBirth, newPIN, 0));
                createCustomerPanel(newPIN, "new");
            }
            else{
                newCusPanel.add(new JLabel("ERROR: Enter unique PIN:"));
                revalidate();
            }
        }
    }

    class depositButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent click){
            //createDepositPanel();

        }
    }

    class widthdrawButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent click){

        }
    }

    class deleteButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent click){

        }
    }

    class signOutButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent click){
            createMainMenu();
        }
    }

    class insertButtionListener implements ActionListener{
        public void actionPerformed(ActionEvent click){

        }
    }

    class takeButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent click){

        }
    }

    class doneButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent click){
            getContentPane().removeAll();
            repaint();
            add(customerMainPanel);
            revalidate();

        }
    }

    public static void main(String[] args){
        SignInFrame t = new SignInFrame();
        t.setSize(500, 500);
        t.setDefaultCloseOperation(EXIT_ON_CLOSE);
        t.setLocationRelativeTo(null);
        t.setVisible(true);
    }
}
