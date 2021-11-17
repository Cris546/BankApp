import java.util.*;
import java.sql.*;

public class Account {
    private String name;
    private String DOB;
    private int pin;
    private double balance;

    public Account(){
        name = null;

    }

    public Account(String newName, String birth, int pass, double newBalance){
        name = newName;
        DOB = birth;
        pin = pass;
        balance = newBalance;

    }

    public void setName(String newName){
        name = newName;
    }

    public void setBirth(String birth){
        DOB = birth;
    }

    public void setPin(int pass){
        pin = pass;
    }

    public void setBalance(double newBalance){
        balance = newBalance;
    }

    public String getName(){
        return name;
    }

    public String getBirth(){
        return DOB;
    }

    public int getPass(){
        return pin;
    }

    public double getBalance(){
        return balance;
    }

    public String toString(){
        return "Name: " + name + "\nDOB: " + DOB + "\nPIN: " + pin + "\nBalance: $" + balance;
    }



}