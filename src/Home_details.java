import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Home_details {
    private static final String url="jdbc:mysql://127.0.0.1:3306/my_db",uname="root",pass="sreyan";
    private static PreparedStatement pstatement1,pstatement2;
    private static Statement statement1;
    private static Connection connect;
    private final String acc_number;
    private static double bal1,bal2;
    private static int acc_pin;
    private static double amt_send;
    private static String s_name,b_name,acc_pass;
    Home_details(String acc_number){
        this.acc_number=acc_number;
        try{connect=DriverManager.getConnection(url,uname,pass);statement1=connect.createStatement();connect.setAutoCommit(false);}
        catch (Exception e){System.out.println(e.getMessage());}
    }
    protected void fetch_dashboard_details(ArrayList<String> list){
        String query="SELECT Holder_fname,Balance,Holder_ph,Account_no,Holder_mail FROM BANK_DETAILS WHERE Account_no=?";
        try{
            list.clear();
            pstatement1=connect.prepareStatement(query);
            pstatement1.setInt(1,Integer.parseInt(acc_number));
            ResultSet set=pstatement1.executeQuery();
            if(set.next()){
                list.add(set.getString("Holder_fname"));
                list.add(String.valueOf(set.getDouble("Balance")));
                list.add(set.getString("Holder_ph"));
                list.add(String.valueOf(set.getInt("Account_no")));
                list.add(set.getString("Holder_mail"));
            }
            System.out.println("Dashboard details accessed");
            set.close();
        }
        catch (Exception e){System.out.println(e.getMessage());}
    }
    protected boolean verify_transfer_amount(String amount_sendt){
                for(char c:amount_sendt.toCharArray()){
            switch (c){
                case '0','1','2','3','4','5','6','7','8','9'->{}
                default -> {return false;}
            }
        }
        amt_send=Double.parseDouble(amount_sendt);
        return true;
    }
    protected boolean verify_all_account_details(int s_acc, int b_acc, String b_name){
        String query1=String.format("SELECT Holder_fname, Account_no, Account_pin, Balance FROM BANK_DETAILS WHERE Account_no=%d",s_acc);
        String query2=String.format("SELECT Holder_fname, Account_no, Balance FROM BANK_DETAILS WHERE Account_no=%d",b_acc);
        try(ResultSet result1=statement1.executeQuery(query1)){
            if(result1.next()){
                if(s_acc!=result1.getInt("Account_no")) return false;
                bal1=result1.getDouble("Balance");
                acc_pin=result1.getInt("Account_pin");
                s_name=result1.getString("Holder_fname");
                System.out.println(bal1);
            }else return false;
        }
        catch (Exception e){System.out.println(e.getMessage());}
        try (ResultSet result2=statement1.executeQuery(query2)){
            if(result2.next()) {
                if (b_acc != result2.getInt("Account_no") || !b_name.equals(result2.getString("Holder_fname"))) return false;
                bal2=result2.getDouble("Balance");
                this.b_name=b_name;
                System.out.println(bal2);
            }else return false;
        }
        catch (Exception e){System.out.println(e.getMessage());}
        return true;
    }
    protected boolean initiate_transfer(JLabel status, JPasswordField transfer_pin,int s_acc,int b_acc){
        if(bal1-amt_send<0){return false;}
        String query_bank_update="UPDATE BANK_DETAILS SET Balance=? WHERE Account_no=?";
        String query_transaction_update="INSERT INTO Transaction_? (Account_no,Holder_fname,Amount,Balance,Dr,Cr) VALUES (?,?,?,?,?,?)";
        try{
            pstatement1=connect.prepareStatement(query_bank_update);
            pstatement2=connect.prepareStatement(query_transaction_update);
            try {
                pstatement2.setInt(1,s_acc);
                pstatement2.setInt(2,b_acc);
                pstatement2.setString(3,b_name);
                pstatement2.setDouble(4,amt_send);
                pstatement2.setDouble(5,(bal1-amt_send));
                pstatement2.setInt(6,1);
                pstatement2.setInt(7,0);
                pstatement2.execute();
                System.out.println("Successes1");

                pstatement2.setInt(1,b_acc);
                pstatement2.setInt(2,s_acc);
                pstatement2.setString(3,s_name);
                pstatement2.setDouble(4,amt_send);
                pstatement2.setDouble(5,(bal2+amt_send));
                pstatement2.setInt(6,0);
                pstatement2.setInt(7,1);
                pstatement2.execute();
                System.out.println("Successes2");

                pstatement1.setDouble(1,(bal1-amt_send));
                pstatement1.setInt(2,s_acc);
                pstatement1.executeUpdate();
                pstatement1.setDouble(1,(bal2+amt_send));
                pstatement1.setInt(2,b_acc);
                pstatement1.executeUpdate();

                connect.commit();
                System.out.println("Transaction Successful!!");
            }
            catch (SQLException e){
                connect.rollback();
                System.out.println(e.getMessage());
                return false;
            }
        }
        catch (Exception e){System.out.println(e.getMessage());}
        return true;
    }
    protected int get_recent_transaction(ArrayList<String> recent_list,int dr_cr){
        String query="SELECT Holder_fname, Amount, Dr, Tdate_Ttime FROM Transaction_? order by Sl_no desc";
        try{
            recent_list.clear();
            pstatement1=connect.prepareStatement(query);
            pstatement1.setInt(1,Integer.parseInt(acc_number));
            ResultSet result=pstatement1.executeQuery();
            if(result.next()){
                recent_list.add(result.getString("Holder_fname"));
                recent_list.add(Double.toString(result.getDouble("Amount")));
                if(result.getInt("Dr")==0){dr_cr=1;}else dr_cr=0;
                Timestamp ts=result.getTimestamp("Tdate_Ttime");
                recent_list.add(ts.toString());
            }
            else System.out.println("No transactions found!!");
        }
        catch (Exception e){System.out.println(e.getMessage());}
        return dr_cr;
    }
    protected boolean valid_pin(String p){
        for(char i:p.toCharArray()){
            switch (i){
                case '1','2','3','4','5','6','7','8','9','0'->{}
                default -> {return false;}
            }
        }
        return Integer.parseInt(p) == acc_pin;
    }
    protected boolean valid_old_pin(String p) {
        if(!p.isEmpty()) {
            for (char i : p.toCharArray()) {
                switch (i) {
                    case '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' -> {
                    }
                    default -> {
                        return false;
                    }
                }
            }
            String query = String.format("SELECT Account_pin FROM BANK_DETAILS WHERE Account_no=%d", Integer.parseInt(acc_number));
            try {
                ResultSet res = statement1.executeQuery(query);
                if (res.next()) {
                    return res.getInt("Account_pin") == Integer.parseInt(p);
                } else return false;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }return false;
    }
    protected boolean verify_new_pin(String pin1,String pin2){
        String []p={pin1,pin2};
        if(!p[0].isEmpty()&&!p[1].isEmpty()) {
            if(p[0].length()!=6||p[1].length()!=6){return false;}
            for(int n=0;n<2;n++) {
                for (char i : p[n].toCharArray()) {
                    switch (i) {
                        case '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' -> {
                        }
                        default -> {
                            return false;
                        }
                    }
                }
            }
        }else{return false;}
        acc_pin=Integer.parseInt(p[0]);
        return p[0].equals(p[1]);
    }
    protected boolean update_new_pin(){
        try{statement1.execute(String.format("UPDATE BANK_DETAILS SET Account_pin=%d WHERE Account_no=%d",acc_pin,Integer.parseInt(acc_number)));connect.commit();
            System.out.println("Pin updated!!");}
        catch (Exception e) {
            try {
                connect.rollback();
                return false;
            } catch (SQLException ex) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return true;
    }
    protected boolean verify_old_pass(String p){
        if(p.isEmpty()){return false;}
        try{
            ResultSet res=statement1.executeQuery(String.format("SELECT Holder_password FROM BANK_DETAILS WHERE Account_no=%d",Integer.parseInt(acc_number)));
            if(res.next()){return p.equals(res.getString("Holder_password"));}
            else return false;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    protected boolean verify_new_pass(String pass1,String pass2){if(pass1.length()>30||pass2.length()>30){return false;}acc_pass=pass1;return pass1.equals(pass2);}
    protected boolean update_new_pass(){
        try{
            statement1.execute(String.format("UPDATE BANK_DETAILS SET Holder_password='%s' WHERE Account_no=%d",acc_pass,Integer.parseInt(acc_number)));connect.commit();
            System.out.println("Pass updated!");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            try{
                connect.rollback();
                return false;
            }
            catch (SQLException ex){
                System.out.println(e.getMessage());
                return false;
            }
        }
        return true;
    }
    protected void get_all_10_transactions(JLabel transaction){
        try {
            int last=0;
            ResultSet res1=statement1.executeQuery(String.format("SELECT Sl_no FROM Transaction_%d order by Sl_no DESC",Integer.parseInt(acc_number)));
            if(res1.next()){last=res1.getInt("Sl_no");}
            ResultSet res=statement1.executeQuery(String.format("SELECT Account_no, Holder_fname, Amount, Balance, Dr, Tdate_Ttime FROM Transaction_%d WHERE Sl_no BETWEEN %d and %d", Integer.parseInt(acc_number),(last-9),last));
            while(res.next()){
                transaction.removeAll();
                if(res.getInt("Dr")==1){
                    transaction.setText(transaction.getText()+"<html>"+"⭧ Paid To:"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"XXXX"+Integer.toString(res.getInt("Account_no")).substring(4)+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                            res.getString("Holder_fname")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                            res.getDouble("Amount")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                            res.getDouble("Balance")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                            res.getTimestamp("Tdate_Ttime")+
                            "<br><hr><html>");
                }
                else{
                    transaction.setText(transaction.getText()+"<html>"+"⭩ Received from:"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"XXXX"+Integer.toString(res.getInt("Account_no")).substring(4)+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                            res.getString("Holder_fname")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                            res.getDouble("Amount")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                            res.getDouble("Balance")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                            res.getTimestamp("Tdate_Ttime")+
                            "<br><hr><html>");
                }
            }
        }
        catch (SQLException e){System.out.println(e.getMessage());}
    }
}