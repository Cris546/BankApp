import java.util.*;
import java.sql.*;

public class Account {
    private String name;
    private String DOB;
    private int pin;

    public Account(){
        name = null;

    }

    public Account(String newName, String birth, int pass){
        name = newName;
        DOB = birth;
        pin = pass;
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

    public String getName(){
        return name;
    }

    public String getBirth(){
        return DOB;
    }

    public int getPass(){
        return pin;
    }


}