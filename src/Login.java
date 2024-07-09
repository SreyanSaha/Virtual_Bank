import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login {
    private static final String url="jdbc:mysql://127.0.0.1:3306/my_db",uname="root",pass="sreyan";
    private static Statement statement;
    private final String ogmail,op;
    private final int oacc_no;
    protected Login(String gmail,String p,int acc_no){
        ogmail=gmail;
        op=p;
        oacc_no=acc_no;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection(url, uname, pass);
            statement=connect.createStatement();
        }
        catch (Exception e){System.out.println(e.getMessage());}
    }
    protected int try_query(){
        try{
            String query=String.format("SELECT Account_no, Holder_mail, Holder_password FROM BANK_DETAILS WHERE Account_no=%d",oacc_no);
            ResultSet resultSet=statement.executeQuery(query);
            int acc=0;
            String gmail="";
            String pas="";
            if(resultSet.next()) {
                acc = resultSet.getInt("Account_no");
                gmail = resultSet.getString("Holder_mail");
                pas = resultSet.getString("Holder_password");
            }
            if(!gmail.equals(ogmail)&&!pas.equals(op)&&acc!=oacc_no){return 3;}
            else if(!gmail.equals(ogmail)){return 0;}
            else if(!pas.equals(op)){return 1;}
            else if(acc!=oacc_no){return 2;}
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return -1;
    }
}
