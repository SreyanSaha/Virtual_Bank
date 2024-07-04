import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class SignUp {
    private static final String url="jdbc:mysql://127.0.0.1:3306/my_db",uname="root",pass="sreyan";
    private static Statement statement;
    protected SignUp(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection(url, uname, pass);
            statement=connect.createStatement();
        }
        catch (Exception e){System.out.println(e.getMessage());}
    }
    protected boolean try_signup(int acc_no){
        try{
            String query=String.format("SELECT Account_no FROM BANK_DETAILS WHERE Account_no=%d",acc_no);
            ResultSet resultSet=statement.executeQuery(query);
            if(resultSet.next()){System.out.println("Already Exists!!");FirstPage.wsm6.setVisible(true);}
            else {return true;}
        }
        catch (Exception e){System.out.println(e.getMessage());}
        return false;
    }
    protected void signing_up(int acc_no,String fname,String lname,String phone_n,String password,String mail,int pin){
        try{
            String query=String.format("INSERT INTO BANK_DETAILS (Account_no, Holder_fname, Holder_lname, Holder_password, Holder_ph, Holder_mail, Account_pin) VALUES (%d,'%s','%s','%s','%s','%s',%d)",acc_no,fname,lname,password,phone_n,mail,pin);
            int rows=statement.executeUpdate(query);
            if(rows>0){System.out.println("Inserted!!");}
        }
        catch (Exception e){System.out.println(e.getMessage());}
    }
}
