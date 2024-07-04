import java.sql.*;
import java.util.ArrayList;

public class Home_details {
    private static ArrayList<String> t_id_gen,shuff_t_id,all_used_id;
    private static final String url="jdbc:mysql://127.0.0.1:3306/my_db",uname="root",pass="sreyan";
    private static PreparedStatement statement;
    private static Statement statement1;
    private static Connection connect;
    private final String acc_number;
    Home_details(String acc_number){
        this.acc_number=acc_number;
        try{connect= DriverManager.getConnection(url,uname,pass);}
        catch (Exception e){System.out.println(e.getMessage());}
    }
    private static void transaction_id_generate(){
    }
    protected void fetch_dashboard_details(ArrayList<String> list){
        String query="SELECT Holder_fname,Balance,Holder_ph,Account_no,Holder_mail FROM BANK_DETAILS WHERE Account_no=?";
        try{
            statement=connect.prepareStatement(query);
            statement.setInt(1,Integer.parseInt(acc_number));
            ResultSet set=statement.executeQuery();
            if(set.next()){
                list.add(set.getString("Holder_fname"));
                list.add(String.valueOf(set.getDouble("Balance")));
                list.add(set.getString("Holder_ph"));
                list.add(String.valueOf(set.getInt("Account_no")));
                list.add(set.getString("Holder_mail"));
            }
            System.out.println("Dashboard details accessed");
        }
        catch (Exception e){System.out.println(e.getMessage());}
    }
    protected void fetch_balance(ArrayList<String> list){
        String query="SELECT ";
        try{
            statement=connect.prepareStatement(query);
        }
        catch (Exception e){System.out.println(e.getMessage());}
    }
    protected boolean verify_all_account_details(int s_acc, int b_acc, String b_name, ArrayList<String> send_money_list){
        String query1=String.format("SELECT Account_no, Account_pin FROM BANK_DETAILS WHERE Account_no=%d",s_acc);
        String query2=String.format("SELECT Holder_fname, Account_no FROM BANK_DETAILS WHERE Account_no=%d",b_acc);
        try{
            statement1=connect.createStatement();
        }
        catch (Exception e){System.out.println(e.getMessage());}
        return false;
    }
    protected void initiate_transfer(){
        transaction_id_generate();
        String query="SELECT ";
        try{
            statement=connect.prepareStatement(query);
        }
        catch (Exception e){System.out.println(e.getMessage());}
    }
    protected int get_recent_transaction(ArrayList<String> recent_list,int dr_cr){
        String query="SELECT ";
        dr_cr=1;
        try{
            statement=connect.prepareStatement(query);
        }
        catch (Exception e){System.out.println(e.getMessage());}
        return dr_cr;
    }
}
