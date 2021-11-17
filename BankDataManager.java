import java.util.*;
import java.sql.*;

public class BankDataManager {
    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String DB_URL = "jdbc:derby://localhost:1527/BankDB";

    private static final String user = "cris";
    private static final String pass = "admin";

    private static Connection conn = null;
    private static Statement stmt = null;
    
    public BankDataManager(){
        establishConn();
    }

    public void establishConn(){
        try{
            //Register Driver
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, user, pass);

            

           
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            
            
        }

    }

    public void closeConn(){
        try{
            stmt.close();
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                if (stmt != null){
                    stmt.close();
                }
            }
            catch (SQLException se2) {}
            try{
                if (conn != null){
                    conn.close();
                }
            }
            catch (SQLException se){
                se.printStackTrace();
            }
        }
    }

    public void listAllAccounts(){
        

        try{
            //Register Driver
            

            stmt = conn.createStatement();
            String sql = "SELECT * FROM ACCOUNT";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\nResults:");

            while(rs.next()){
                String accountName = rs.getString("accountName");
                System.out.println("Account Name: " + accountName);        
            }

            System.out.println();

            rs.close();

           
        }
        catch (SQLException se){
            se.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public Account findAccount(int PIN){
        try{
            Account user = new Account();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM ACCOUNT WHERE PIN=" + PIN;
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\nResults:");

            while(rs.next()){
                user = new Account(rs.getString("accountName"), rs.getString("DOB"), Integer.parseInt(rs.getString("PIN")), Double.parseDouble(rs.getString("Balance")));
                     
            }

            

            rs.close();

            return user;

           
        }
        catch (SQLException se){
            se.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
           
        }
        return new Account();
    }

    public void addAccount(Account newUser){
        try{
            stmt = conn.createStatement();
            String sql = "INSERT INTO ACCOUNT (accountName, DOB, PIN, Balance) VALUES('" + newUser.getName() + "', '" + newUser.getBirth() + "', " + newUser.getPass() + ", " + newUser.getBalance() + ")"; 
            stmt.executeUpdate(sql);

        }
        catch (SQLException se){
            se.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeAccount(int userPIN){
        try{
            stmt = conn.createStatement();
            String sql = "DELETE FROM Account WHERE PIN="+ userPIN;
            stmt.executeUpdate(sql);
        }
        catch (SQLException se){
            se.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateBalance(int userPIN, double newBalance){
        try{
            stmt = conn.createStatement();
            String sql = "UPDATE Account SET Balance = "+ newBalance + "WHERE PIN = " + userPIN;
            stmt.executeUpdate(sql);
        }
        catch (SQLException se){
            se.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    

    public static void main(String[] args){
        BankDataManager data = new BankDataManager();
        // data.listAllAccounts();

        // Account newUser = new Account("Tito", "12/23/2004", 1234, 512.83);
        // data.addAccount(newUser);

        // data.removeAccount(1234);
        // System.out.print(data.findAccount(1234));

        // data.updateBalance(1234, 600.50);
        // System.out.println(data.findAccount(1234));
        data.closeConn();
    }
}
