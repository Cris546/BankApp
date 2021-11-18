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
    
    private JPanel signInPanel;
    



    public SignInFrame(){
        createComponents();
        this.setTitle("Cris' Bank App");
        this.setSize(500,500);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private void createComponents(){
        greetingLabel = new JLabel("Welcome! Please choose an option!");
        existingButton = new JButton("Exisiting Customer");
        existingButton.setFocusable(false);
        existingButton.addActionListener(new existButtonLister());

        newCusButton = new JButton("New Customer");
        newCusButton.addActionListener(new newCusButtonListener());


        signInPanel = new JPanel( new FlowLayout(FlowLayout.CENTER));
        signInPanel.add(greetingLabel);
        signInPanel.add(existingButton);
        signInPanel.add(newCusButton);

        

        this.add(signInPanel);

        




    }

    class existButtonLister implements ActionListener{
        public void actionPerformed(ActionEvent click){
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

            signInPanel.removeAll();
            signInPanel.repaint();

            signInPanel.add(enterPINLabel);
            signInPanel.add(pField);
            signInPanel.add(enterButton);
            signInPanel.add(cancelButton);

            signInPanel.revalidate();



       
            
            
        }
    }

    class enterButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent click){

        }
    }

    class cancelButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent click){
            signInPanel.removeAll();
            signInPanel.repaint();

            signInPanel.add(greetingLabel);
            signInPanel.add(existingButton);
            signInPanel.add(newCusButton);

            signInPanel.revalidate();



        }
    }

    class newCusButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent click){
            NewCustomerFrame newCus = new NewCustomerFrame();
            newCus.setVisible(true);
            newCus.setLocationRelativeTo(null);
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
