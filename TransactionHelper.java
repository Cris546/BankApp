public class TransactionHelper {
    int userPIN;
    FrameHelper frame;
    BankDataManager db;


    public TransactionHelper(int newUser, FrameHelper newFrame){
        userPIN = newUser;
        frame = newFrame;
        db = new BankDataManager();
    }

    public void addMoney(double newMoney){
        db.establishConn();
        Account user = db.findAccount(userPIN);
        double balance = user.getBalance();
        db.updateBalance(userPIN, balance + newMoney);
        db.closeConn();
         

    }

    public void takeMoney(double newMoney){
        db.establishConn();
        Account user = db.findAccount(userPIN);
        double balance = user.getBalance();
        db.updateBalance(userPIN, Math.abs(balance - newMoney));
        db.closeConn();

    }


    
    
    
}
