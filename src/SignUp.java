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
            if(rows>0){create_transaction_table(acc_no);System.out.println("Inserted!!");}
        }
        catch (Exception e){System.out.println(e.getMessage());}
    }
    protected void create_transaction_table(int acc_no){
        String transaction_table_query=String.format("CREATE TABLE Transaction_%s (Sl_no int primary key auto_increment, Account_no int not null, Holder_fname varchar(50) not null, Amount double not null, Balance double not null, Dr int default 0, Cr int default 0, Tdate_Ttime timestamp default current_timestamp, foreign key(Account_no) references BANK_DETAILS(Account_no) on delete cascade)",Integer.toString(acc_no));
        try {System.out.println(!statement.execute(transaction_table_query));}
        catch (Exception e){System.out.println(e.getMessage());}
    }
}
