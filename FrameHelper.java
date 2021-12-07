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

public class FrameHelper extends JFrame{
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
    private JFormattedTextField wField;
    private JButton takeButton;

    private JLabel deleteLabel;
    private JButton removeButton;
    


    private JPanel signInPanel;
    private JPanel existingSignInPanel;
    private JPanel newCusPanel;
    private JPanel customerMainPanel;
    private JPanel depositPanel;
    private JPanel widthdrawPanel;
    private JPanel deletePanel;

    private BankDataManager db;
    



    

    public void createExisPanel(){

        /**Creating Welcome page */
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

        establishNewPanel(existingSignInPanel);



    }

    public void createNewCusPanel(){
         /**New Customer Page */
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

         establishNewPanel(newCusPanel);

    }

    

    public void createGreetingPanel(){


        /**Greeting Panel */
        greetingLabel = new JLabel("Welcome! Please choose an option!");
        existingButton = new JButton("Exisiting Customer");
        existingButton.setFocusable(false);
        existingButton.addActionListener(new existButtonLister());
        existingButton.setPreferredSize(new Dimension(150,90));

        newCusButton = new JButton("New Customer");
        newCusButton.setFocusable(false);
        newCusButton.addActionListener(new newCusButtonListener());
        newCusButton.setPreferredSize(new Dimension(150,90));


        signInPanel = new JPanel( new FlowLayout(FlowLayout.CENTER));
        signInPanel.add(greetingLabel);
        signInPanel.add(existingButton);
        signInPanel.add(newCusButton);

        establishNewPanel(signInPanel);

        

        
    }



    public void createCustomerPanel(int PIN, String Frame){
        db = new BankDataManager();
            
        Account user = db.findAccount(PIN);
        if(user.getName() != null){
                
            customerLabel = new JLabel("Welcome back " + user.getName() + "! Please select an option");
            depositButton = new JButton("Deposit");
            depositButton.addActionListener(new depositButtonListener(PIN));

            widthdrawButton = new JButton("Widthdraw");
            widthdrawButton.addActionListener(new widthdrawButtonListener(PIN));

            deleteButton = new JButton("Delete");
            deleteButton.addActionListener(new deleteButtonListener(PIN));
                
            signOutButton = new JButton("Sign Out");
            signOutButton.addActionListener(new signOutButtonListener());

            customerMainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            customerMainPanel.add(customerLabel);
            customerMainPanel.add(depositButton);
            customerMainPanel.add(widthdrawButton);
            customerMainPanel.add(deleteButton);
            customerMainPanel.add(signOutButton);

            establishNewPanel(customerMainPanel);

        }
        else{
            if(Frame.equals("existing")){
                existingSignInPanel.add(new JLabel("ERROR: ENTER VALID PIN"));
                revalidate();
            }
        
         }

        db.closeConn();

        establishNewPanel(existingSignInPanel);

    }

    public void createDepositPanel(int PIN){
        db = new BankDataManager();
        Account user = db.findAccount(PIN);
        db.closeConn();

        depositPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        depositLabel = new JLabel("Current Balance: $" + user.getBalance());

        moneyField = new JFormattedTextField();
        moneyField.setValue("##,###.##");

        insertButton = new JButton("Deposit");
        insertButton.addActionListener(new insertButtionListener(PIN));
        doneButton = new JButton("Done");
        doneButton.addActionListener(new doneButtonListener());

        depositPanel.add(depositLabel);
        depositPanel.add(moneyField);
        depositPanel.add(insertButton);
        depositPanel.add(doneButton);

        

        establishNewPanel(depositPanel);

        
    }

    public void createWidthdrawPanel(int PIN){
        db = new BankDataManager();
        Account user = db.findAccount(PIN);
        db.closeConn();

        widthdrawPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        widthdrawLabel = new JLabel("Current Balance: $" + user.getBalance());
        
        wField = new JFormattedTextField();
        wField.setValue("##,###.##");
        takeButton = new JButton("Widthdraw");
        takeButton.addActionListener(new takeButtonListener(PIN));
        doneButton = new JButton("Done");
        doneButton.addActionListener(new doneButtonListener());

        widthdrawPanel.add(widthdrawLabel);
        widthdrawPanel.add(wField);
        widthdrawPanel.add(takeButton);
        widthdrawPanel.add(doneButton);

        

        establishNewPanel(widthdrawPanel);

        

    }

    public void createDeletePanel(int PIN){
        deletePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        deleteLabel = new JLabel("Are you sure you want to remove this account?");
        
        removeButton = new JButton("Remove");
        removeButton.addActionListener(new removeButtonListener(PIN));

        doneButton = new JButton("Cancel");
        doneButton.addActionListener(new doneButtonListener());

        deletePanel.add(deleteLabel);
        deletePanel.add(removeButton);
        deletePanel.add(doneButton);

        

        establishNewPanel(deletePanel);




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

    private void calculateBalance(int PIN, String type){
        if(type.equals("add")){
            double amount = Double.parseDouble(moneyField.getText());
            db = new BankDataManager();
            Account user = db.findAccount(PIN);
            amount += user.getBalance();
            db.updateBalance(PIN, amount);
            db.closeConn();

            

            establishNewPanel(customerMainPanel);

        }
        else if(type.equals("remove")){
            double amount = Double.parseDouble(wField.getText());
            db = new BankDataManager();
            Account user = db.findAccount(PIN);
            amount -= user.getBalance();
            amount = Math.abs(amount);
            db.updateBalance(PIN, amount);
            db.closeConn();

            

            establishNewPanel(customerMainPanel);

        }
    }

    private void establishNewPanel(JPanel newPanel){
        getContentPane().removeAll();
        repaint();
        add(newPanel);
        revalidate();
    }

    

    class existButtonLister implements ActionListener{
        public void actionPerformed(ActionEvent click){
            
            establishNewPanel(existingSignInPanel);
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
            
            establishNewPanel(signInPanel);
        }
    }

    class newCusButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent click){
            

            establishNewPanel(newCusPanel);

            
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
        private int PIN;

        public depositButtonListener(int userPIN){
            PIN = userPIN;
        }
        public void actionPerformed(ActionEvent click){
            createDepositPanel(PIN);

        }
    }

    class widthdrawButtonListener implements ActionListener{
        private int PIN;

        public widthdrawButtonListener(int userPIN){
            PIN = userPIN;
        }
        public void actionPerformed(ActionEvent click){
            createWidthdrawPanel(PIN);
        }
    }

    class deleteButtonListener implements ActionListener{
        private int PIN;

        public deleteButtonListener(int userPIN){
            PIN = userPIN;
        }
        public void actionPerformed(ActionEvent click){
            createDeletePanel(PIN);

        }
    }

    class signOutButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent click){
            

            establishNewPanel(signInPanel);
        }
    }

    class insertButtionListener implements ActionListener{
        private int PIN;

        public insertButtionListener(int userPIN){
            PIN = userPIN;
        }
        public void actionPerformed(ActionEvent click){
            calculateBalance(PIN, "add");


        }
    }

    class takeButtonListener implements ActionListener{
        private int PIN;

        public takeButtonListener(int userPIN){
            PIN = userPIN;
        }

        public void actionPerformed(ActionEvent click){
            calculateBalance(PIN, "remove");

        }
    }

    class removeButtonListener implements ActionListener{
        private int PIN;

        public removeButtonListener(int userPIN){
            PIN = userPIN;
        }
        public void actionPerformed(ActionEvent click){
            db = new BankDataManager();
            db.removeAccount(PIN);
            db.closeConn();

            
            establishNewPanel(signInPanel);
        }
    }

    class doneButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent click){

            establishNewPanel(customerMainPanel);

        }
    }

    public static void main(String[] args){
        FrameHelper t = new FrameHelper();
        t.setSize(500, 500);
        t.setDefaultCloseOperation(EXIT_ON_CLOSE);
        t.setLocationRelativeTo(null);
        t.setVisible(true);
    }
}
