import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class HomePage extends JFrame implements ActionListener, MouseListener {
    protected static boolean verify_details=false,verify_opin=false,verify_opass=false;
    private static JButton log_out,deposit,change_pin,mini,dash_b,statement,change_pass,verify_ben,ini_tb,cancel_but,proceed_but,verify_opin_but,c_pin,ver_opass_but,c_pass,show_but;
    private static ArrayList<String>list,recent_list;
    private static JLayeredPane lp;
    private static JPanel dash_pan,deposite_pan,pin_pan,change_pin_pan,change_pass_pan;
    private static JTextField source_acct,beni_acct,beni_namet,amount_sendt;
    private static JLabel ver_ben_msg,cannot_transfer,transaction_details,transaction_details2,transfer_failed,chances,status,ver_old_pin,pin_update_status,ver_old_pass,pass_update_status;
    private static JPasswordField transfer_pin,old_pin,new_pin,con_new_pin,old_pass,new_pass,con_new_pass;
    private static Home_details details;
    private static int dr_cr=0,s_acc,b_acc;
    private String account_no;
    protected HomePage(String account_no){
        this.account_no=account_no;
        details=new Home_details(account_no);
        list=get_object_of_list();
        recent_list=get_obj_of_recent_list();

        lp=new JLayeredPane();
        lp.setBounds(1,1,1920,1080);
        lp.setLayout(null);
        lp.setBackground(Color.RED);
        lp.setVisible(true);

        JPanel panel=new JPanel();
        panel.setBounds(0,0,1920,130);
        panel.setBackground(Color.GRAY);
        panel.setLayout(null);
        panel.setVisible(true);

        JPanel panel2=new JPanel();
        panel2.setBounds(0,133,280,1080);
        panel2.setBackground(Color.GRAY);
        panel2.setLayout(null);
        panel2.setVisible(true);

        JLabel label=new JLabel();
        label.setText("Virtual");
        label.setFont(new Font("long Island",Font.BOLD,40));
        label.setBounds(20,25,200,100);
        label.setForeground(Color.RED);
        label.setVisible(true);

        JLabel label2=new JLabel();
        label2.setText("Bank");
        label2.setFont(new Font("long Island",Font.BOLD,40));
        label2.setBounds(150,25,200,100);
        label2.setForeground(new Color(0,0,100));
        label2.setVisible(true);

        ImageIcon image2 = new ImageIcon("E:\\JDBC_programming\\JDBC_project\\src\\vb_nobg.png");
        JLabel label3=new JLabel();
        label3.setIcon(image2);
        label3.setBounds(730,20,450,100);

        log_out=new JButton("Logout");
        log_out.setBackground(Color.RED);
        log_out.setFocusable(false);
        log_out.setForeground(Color.WHITE);
        log_out.setBounds(1780,40,100,40);
        log_out.setFont(new Font("long Island",Font.PLAIN,20));
        log_out.addMouseListener(this);
        dash_b=new JButton("Dashboard");
        dash_b.setBackground(Color.WHITE);
        dash_b.setFocusable(false);
        dash_b.setForeground(Color.BLACK);
        dash_b.setBounds(35,110,200,40);
        dash_b.setFont(new Font("long Island",Font.PLAIN,20));
        dash_b.addMouseListener(this);
        deposit=new JButton("Fund Transfer");
        deposit.setBackground(Color.WHITE);
        deposit.setFocusable(false);
        deposit.setForeground(Color.BLACK);
        deposit.setBounds(35,210,200,40);
        deposit.setFont(new Font("long Island",Font.PLAIN,20));
        deposit.addMouseListener(this);
        change_pin=new JButton("Change Pin");
        change_pin.setBackground(Color.WHITE);
        change_pin.setFocusable(false);
        change_pin.setForeground(Color.BLACK);
        change_pin.setBounds(35,310,200,40);
        change_pin.setFont(new Font("long Island",Font.PLAIN,20));
        change_pin.addMouseListener(this);
        mini=new JButton("Mini Statement");
        mini.setBackground(Color.WHITE);
        mini.setFocusable(false);
        mini.setForeground(Color.BLACK);
        mini.setBounds(35,410,200,40);
        mini.setFont(new Font("long Island",Font.PLAIN,20));
        mini.addMouseListener(this);
        statement=new JButton("Statement");
        statement.setBackground(Color.WHITE);
        statement.setFocusable(false);
        statement.setForeground(Color.BLACK);
        statement.setBounds(35,510,200,40);
        statement.setFont(new Font("long Island",Font.PLAIN,20));
        statement.addMouseListener(this);
        change_pass=new JButton("Change password");
        change_pass.setBackground(Color.WHITE);
        change_pass.setFocusable(false);
        change_pass.setForeground(Color.BLACK);
        change_pass.setBounds(35,610,200,40);
        change_pass.setFont(new Font("long Island",Font.PLAIN,20));
        change_pass.addMouseListener(this);

        deposit.setVisible(true);
        deposit.addActionListener(this);
        log_out.setVisible(true);
        log_out.addActionListener(this);
        dash_b.setVisible(true);
        dash_b.addActionListener(this);
        change_pin.setVisible(true);
        change_pin.addActionListener(this);
        mini.setVisible(true);
        mini.addActionListener(this);
        statement.setVisible(true);
        statement.addActionListener(this);
        change_pass.setVisible(true);
        change_pass.addActionListener(this);

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1920, 1080);
        this.setTitle("Virtual Bank");
        ImageIcon image = new ImageIcon("E:\\JDBC_programming\\JDBC_project\\src\\vb.jpg");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(null);
        setExtendedState(MAXIMIZED_BOTH);
        panel.add(log_out);
        panel.add(label);
        panel.add(label2);
        panel.add(label3);
        panel2.add(deposit);
        panel2.add(dash_b);
        panel2.add(change_pin);
        panel2.add(mini);
        panel2.add(statement);
        panel2.add(change_pass);
        this.add(panel2);
        this.add(panel);
        this.add(lp);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==log_out){this.dispose();new FirstPage();}
        if(e.getSource()==verify_ben){
            cannot_transfer.setVisible(false);
            if(transfer_details_verify()){
                verify_ben.setEnabled(false);
                ver_ben_msg.setVisible(true);
            }
            else{
                ver_ben_msg.setText("X Invalid Account Details");
                ver_ben_msg.setForeground(Color.RED);
                ver_ben_msg.setVisible(true);
                verify_ben.setEnabled(true);
            }
        }
        else if(e.getSource()==deposit){
            deposit.setEnabled(false);
            change_pin.setEnabled(true);
            mini.setEnabled(true);
            dash_b.setEnabled(true);
            statement.setEnabled(true);
            change_pass.setEnabled(true);
            lp.removeAll();
            lp.add(deposit_pan());
        }
        else if(e.getSource()==change_pin){
            deposit.setEnabled(true);
            change_pin.setEnabled(false);
            mini.setEnabled(true);
            dash_b.setEnabled(true);
            statement.setEnabled(true);
            change_pass.setEnabled(true);
            lp.removeAll();
            lp.add(getChange_pin_pan());
        }
        else if (e.getSource()==mini) {
            deposit.setEnabled(true);
            change_pin.setEnabled(true);
            mini.setEnabled(false);
            dash_b.setEnabled(true);
            statement.setEnabled(true);
            change_pass.setEnabled(true);
        }
        else if (e.getSource()==dash_b) {
            deposit.setEnabled(true);
            change_pin.setEnabled(true);
            mini.setEnabled(true);
            dash_b.setEnabled(false);
            statement.setEnabled(true);
            change_pass.setEnabled(true);
            details.fetch_dashboard_details(list);
            lp.removeAll();
            lp.add(dash_pan());
        }
        else if (e.getSource()==statement) {
            deposit.setEnabled(true);
            change_pin.setEnabled(true);
            mini.setEnabled(true);
            dash_b.setEnabled(true);
            statement.setEnabled(false);
            change_pass.setEnabled(true);
        }
        else if (e.getSource()==change_pass) {
            deposit.setEnabled(true);
            change_pin.setEnabled(true);
            mini.setEnabled(true);
            dash_b.setEnabled(true);
            statement.setEnabled(true);
            change_pass.setEnabled(false);
            lp.removeAll();
            lp.add(getChange_pass_pan());
        }
        else if(e.getSource()==ini_tb){
            if(verify_details&&details.verify_transfer_amount(amount_sendt.getText())){
                deposit.setEnabled(false);
                change_pin.setEnabled(false);
                mini.setEnabled(false);
                dash_b.setEnabled(false);
                statement.setEnabled(false);
                change_pass.setEnabled(false);
                lp.removeAll();
                lp.add(pin_pan());
            }
            else{
                ver_ben_msg.setText("X Invalid Account Details");
                ver_ben_msg.setForeground(Color.RED);
                ver_ben_msg.setVisible(true);
                verify_ben.setEnabled(true);
                cannot_transfer.setText("Complete Verification!");
                cannot_transfer.setForeground(Color.RED);
                cannot_transfer.setVisible(true);
            }
        }
        else if (e.getSource()==cancel_but) {
            deposit.setEnabled(true);
            change_pin.setEnabled(true);
            mini.setEnabled(true);
            dash_b.setEnabled(false);
            statement.setEnabled(true);
            change_pass.setEnabled(true);
            details.fetch_dashboard_details(list);
            lp.removeAll();
            lp.add(dash_pan());
        }
        else if(e.getSource()==proceed_but){
            if(details.valid_pin(String.valueOf(transfer_pin.getPassword()))){
                chances.setVisible(false);
                status.setText("Processing...");
                status.setForeground(Color.GREEN);
                transfer_pin.setEnabled(false);
                if(details.initiate_transfer(status,transfer_pin,s_acc,b_acc)){
                   status.setText("Transaction Successful...");
                    JOptionPane.showConfirmDialog(this,"Transaction Successful","Transaction Status",JOptionPane.PLAIN_MESSAGE,JOptionPane.CLOSED_OPTION);
                    deposit.setEnabled(false);
                    change_pin.setEnabled(true);
                    mini.setEnabled(true);
                    dash_b.setEnabled(true);
                    statement.setEnabled(true);
                    change_pass.setEnabled(true);
                    lp.removeAll();
                    lp.add(deposit_pan());
                }
                else{
                    status.setForeground(Color.RED);
                    status.setText("Transaction Failed...");
                    transfer_pin.setEnabled(false);
                    proceed_but.setEnabled(false);
                }
            }
            else{chances.setVisible(true);}
        }
        else if(e.getSource()==verify_opin_but){
            verify_opin=details.valid_old_pin(String.valueOf(old_pin.getPassword()));
            if(verify_opin){
                verify_opin_but.setEnabled(false);
                ver_old_pin.setText("✓ Pin verified");
                ver_old_pin.setForeground(Color.GREEN);
                ver_old_pin.setVisible(true);
            }
            else{
                verify_opin_but.setEnabled(true);
                ver_old_pin.setForeground(Color.RED);
                ver_old_pin.setText("X Invalid pin");
                ver_old_pin.setVisible(true);
            }
        }
        else if(e.getSource()==c_pin){
            if(!verify_opin){
                pin_update_status.setText("X Complete Verification");
                pin_update_status.setForeground(Color.RED);
                pin_update_status.setVisible(true);
            }
            else if(!details.verify_new_pin(String.valueOf(new_pin.getPassword()),String.valueOf(con_new_pin.getPassword()))){
                pin_update_status.setText("X Pin Not Equal");
                pin_update_status.setForeground(Color.RED);
                pin_update_status.setVisible(true);
            }
            else if(!details.update_new_pin()){
                pin_update_status.setText("X Pin cannot be updated");
                pin_update_status.setForeground(Color.RED);
                pin_update_status.setVisible(true);
            }
            else{
                pin_update_status.setText("✓ Pin Updated");
                pin_update_status.setForeground(Color.GREEN);
                pin_update_status.setVisible(true);
                c_pin.setEnabled(false);
            }
        }
        else if(e.getSource()==ver_opass_but){
            verify_opass=details.verify_old_pass(String.valueOf(old_pass.getPassword()));
            if(verify_opass){
                ver_opass_but.setEnabled(false);
                ver_old_pass.setText("✓ Password Verified");
                ver_old_pass.setForeground(Color.GREEN);
                ver_old_pass.setVisible(true);
            }
            else{
                ver_opass_but.setEnabled(true);
                ver_old_pass.setForeground(Color.RED);
                ver_old_pass.setText("X Invalid Password");
                ver_old_pass.setVisible(true);
            }
        }
        else if(e.getSource()==c_pass){
            if(!verify_opass){
                pass_update_status.setText("X Complete Verification");
                pass_update_status.setForeground(Color.RED);
                pass_update_status.setVisible(true);
            }
            else if(!details.verify_new_pass(String.valueOf(new_pass.getPassword()),String.valueOf(con_new_pass.getPassword()))){
                pass_update_status.setText("X Password Not Equal");
                pass_update_status.setForeground(Color.RED);
                pass_update_status.setVisible(true);
            }
            else if(!details.update_new_pass()){
                pass_update_status.setText("X Password cannot be updated");
                pass_update_status.setForeground(Color.RED);
                pass_update_status.setVisible(true);
            }
            else{
                pass_update_status.setText("✓ Password Updated");
                pass_update_status.setForeground(Color.GREEN);
                pass_update_status.setVisible(true);
                c_pass.setEnabled(false);
            }
        }
        else if(e.getSource()==show_but){
            if(show_but.getText().equals("Show")){
                show_but.setText("Hide");
                new_pass.setEchoChar((char)0);
                con_new_pass.setEchoChar((char)0);
            }
            else{
                show_but.setText("Show");
                new_pass.setEchoChar('●');
                con_new_pass.setEchoChar('●');
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(e.getSource()==source_acct || e.getSource()==beni_acct || e.getSource()==beni_namet ||e.getSource()==amount_sendt){verify_ben.setEnabled(true);cannot_transfer.setVisible(false);}
        else if(e.getSource()==transfer_pin){chances.setVisible(false);}
        else if(e.getSource()==old_pin){verify_opin_but.setEnabled(true);}
        else if(e.getSource()==new_pin||e.getSource()==con_new_pin){c_pin.setEnabled(true);}
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource()==source_acct || e.getSource()==beni_acct || e.getSource()==beni_namet ||e.getSource()==amount_sendt){verify_ben.setEnabled(true);cannot_transfer.setVisible(false);}
        else if(e.getSource()==source_acct || e.getSource()==beni_acct || e.getSource()==beni_namet || e.getSource()==amount_sendt){verify_ben.setEnabled(true);}
        else if(e.getSource()==transfer_pin){chances.setVisible(false);}
        else if(e.getSource()==old_pin){verify_opin_but.setEnabled(true);}
        else if(e.getSource()==new_pin||e.getSource()==con_new_pin){c_pin.setEnabled(true);}
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource()==source_acct){
            cannot_transfer.setVisible(false);
        }
        else if(e.getSource()==beni_acct){
            ver_ben_msg.setVisible(false);
            cannot_transfer.setVisible(false);
        }
        else if (e.getSource()==beni_namet){
            ver_ben_msg.setVisible(false);
            cannot_transfer.setVisible(false);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==log_out){
            log_out.setBackground(Color.BLUE);
        }
        else if (e.getSource()==dash_b) {
            dash_b.setBackground(Color.RED);
        }
        else if (e.getSource()==deposit) {
            deposit.setBackground(Color.RED);
        }
        else if(e.getSource()==change_pin){
            change_pin.setBackground(Color.RED);
        }
        else if(e.getSource()==mini){
            mini.setBackground(Color.RED);
        }
        else if(e.getSource()==statement){
            statement.setBackground(Color.RED);
        }
        else if (e.getSource()==change_pass) {
            change_pass.setBackground(Color.RED);
        }
        else if (e.getSource()==verify_ben) {
            verify_ben.setBackground(Color.RED);
            verify_ben.setForeground(Color.WHITE);
        }
        else if(e.getSource()==ini_tb){
            ini_tb.setBackground(Color.RED);
            ini_tb.setForeground(Color.WHITE);
        }
        else if (e.getSource()==verify_opin_but) {
            verify_opin_but.setForeground(Color.WHITE);
            verify_opin_but.setBackground(Color.RED);
        }
        else if(e.getSource()==c_pin){
            c_pin.setForeground(Color.WHITE);
            c_pin.setBackground(Color.RED);
        }
        else if(e.getSource()==ver_opass_but){
            ver_opass_but.setForeground(Color.WHITE);
            ver_opass_but.setBackground(Color.RED);
        }
        else if(e.getSource()==c_pass){
            c_pass.setForeground(Color.WHITE);
            c_pass.setBackground(Color.RED);
        }
        else if(e.getSource()==show_but){
            show_but.setForeground(Color.WHITE);
            show_but.setBackground(Color.RED);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==log_out){
            log_out.setBackground(Color.RED);
        }
        else if (e.getSource()==dash_b) {
            dash_b.setBackground(Color.WHITE);
        }
        else if (e.getSource()==deposit) {
            deposit.setBackground(Color.WHITE);
        }
        else if(e.getSource()==change_pin){
            change_pin.setBackground(Color.WHITE);
        }
        else if(e.getSource()==mini){
            mini.setBackground(Color.WHITE);
        }
        else if(e.getSource()==statement){
            statement.setBackground(Color.WHITE);
        }
        else if (e.getSource()==change_pass) {
            change_pass.setBackground(Color.WHITE);
        }
        else if (e.getSource()==verify_ben) {
            verify_ben.setBackground(Color.WHITE);
            verify_ben.setForeground(Color.BLACK);
        }
        else if(e.getSource()==ini_tb){
            ini_tb.setBackground(Color.WHITE);
            ini_tb.setForeground(Color.BLACK);
        }
        else if (e.getSource()==verify_opin_but) {
            verify_opin_but.setForeground(Color.BLACK);
            verify_opin_but.setBackground(Color.WHITE);
        }
        else if(e.getSource()==c_pin){
            c_pin.setForeground(Color.BLACK);
            c_pin.setBackground(Color.WHITE);
        }
        else if(e.getSource()==ver_opass_but){
            ver_opass_but.setForeground(Color.BLACK);
            ver_opass_but.setBackground(Color.WHITE);
        }
        else if(e.getSource()==c_pass){
            c_pass.setForeground(Color.BLACK);
            c_pass.setBackground(Color.WHITE);
        }
        else if(e.getSource()==show_but){
            show_but.setForeground(Color.BLACK);
            show_but.setBackground(Color.WHITE);
        }
    }

    private JPanel dash_pan(){
        dash_pan=new JPanel();
        dash_pan.setBounds(282,132,1920,1080);
        dash_pan.setBackground(Color.GRAY);
        dash_pan.setLayout(null);
        dash_pan.setVisible(true);

        JPanel dash_contents=new JPanel();
        dash_contents.setBounds(30,30,1580,820);
        dash_contents.setBackground(new Color(0,35,100));
        dash_contents.setLayout(null);
        dash_contents.setVisible(true);


        JLabel name_lab=new JLabel(String.format("Hi, %s",list.get(0)));
        name_lab.setBounds(50,10,1920,100);
        name_lab.setForeground(Color.WHITE);
        name_lab.setFont(new Font("long Island",Font.PLAIN,65));
        name_lab.setVisible(true);

        String s="XXXX";
        s += list.get(3).substring(4);

        JLabel ac_num=new JLabel(String.format("Account number: %s",s));
        ac_num.setBounds(50,160,500,50);
        ac_num.setFont(new Font("long Island",Font.PLAIN,30));
        ac_num.setForeground(Color.WHITE);
        ac_num.setVisible(true);

        JLabel email=new JLabel(String.format("Email Id: %s",list.get(4)));
        email.setBounds(50,300,1920,50);
        email.setFont(new Font("long Island",Font.PLAIN,30));
        email.setForeground(Color.WHITE);
        email.setVisible(true);

        JLabel phone=new JLabel(String.format("Phone No: %s",list.get(2)));
        phone.setBounds(1060,160,1920,50);
        phone.setFont(new Font("long Island",Font.PLAIN,30));
        phone.setForeground(Color.WHITE);
        phone.setVisible(true);

        JLabel bal=new JLabel(String.format("₹%s",list.get(1)));
        bal.setBounds(1060,10,1920,100);
        bal.setFont(new Font("long Island",Font.PLAIN,60));
        bal.setForeground(Color.WHITE);
        bal.setVisible(true);

        JLabel recent_transaction=new JLabel("Recent Transaction. ");
        recent_transaction.setBounds(50,380,1920,100);
        recent_transaction.setFont(new Font("long Island",Font.BOLD,50));
        recent_transaction.setForeground(Color.RED);
        recent_transaction.setVisible(true);

        transaction_details=new JLabel();
        transaction_details.setBounds(50,480,500,100);
        transaction_details.setFont(new Font("long Island",Font.PLAIN,30));
        transaction_details.setForeground(Color.GREEN);
        transaction_details.setVisible(true);

        dr_cr=details.get_recent_transaction(recent_list,dr_cr);

        transaction_details2=new JLabel(String.format("%s  ₹%s  on  %s",recent_list.get(0),recent_list.get(1),recent_list.get(2)));
        transaction_details2.setBounds(300,480,800,100);
        transaction_details2.setFont(new Font("long Island",Font.PLAIN,30));
        transaction_details2.setForeground(Color.WHITE);
        transaction_details2.setVisible(true);

        if(dr_cr==0){//dr==0,cr==1
            transaction_details.setText("⭧ Paid To:");
            transaction_details.setForeground(Color.RED);
            transaction_details2.setBounds(220,480,800,100);
        }
        else{
            transaction_details.setText("⭩ Received from:");
            transaction_details.setForeground(Color.GREEN);
            transaction_details2.setBounds(300,480,800,100);
        }

        dash_contents.add(name_lab);
        dash_contents.add(ac_num);
        dash_contents.add(email);
        dash_contents.add(phone);
        dash_contents.add(bal);
        dash_contents.add(recent_transaction);
        dash_contents.add(transaction_details);
        dash_contents.add(transaction_details2);
        dash_pan.add(dash_contents);
        return dash_pan;
    }
    private JPanel deposit_pan(){
        deposite_pan=new JPanel();
        deposite_pan.setBounds(282,132,1920,1080);
        deposite_pan.setBackground(Color.GRAY);
        deposite_pan.setLayout(null);
        deposite_pan.setVisible(true);

        JPanel depo_contents=new JPanel();
        depo_contents.setBounds(30,30,1580,820);
        depo_contents.setBackground(new Color(0,35,100));
        depo_contents.setLayout(null);
        depo_contents.setVisible(true);

        JLabel source_details=new JLabel("Source Account Details.");
        source_details.setBounds(50,10,1920,100);
        source_details.setFont(new Font("long Island",Font.BOLD,50));
        source_details.setForeground(Color.RED);
        source_details.setVisible(true);

        JLabel beni_details=new JLabel("Beneficiary Account Details.");
        beni_details.setBounds(50,208,1920,100);
        beni_details.setFont(new Font("long Island",Font.BOLD,50));
        beni_details.setForeground(Color.RED);
        beni_details.setVisible(true);

        source_acct=new JTextField();
        source_acct.setBounds(275,132,400,40);
        source_acct.setForeground(Color.RED);
        source_acct.setBackground(Color.WHITE);
        source_acct.setFont(new Font("Long Island",Font.PLAIN,25));
        source_acct.addMouseListener(this);
        source_acct.setVisible(true);



        JLabel source=new JLabel("Source Account: ");
        source.setBounds(50,100,1920,100);
        source.setFont(new Font("long Island",Font.PLAIN,30));
        source.setForeground(Color.WHITE);
        source.setVisible(true);

        JLabel beni_accn=new JLabel("Beneficiary Account Number: ");
        beni_accn.setBounds(50,320,1920,100);
        beni_accn.setFont(new Font("long Island",Font.PLAIN,30));
        beni_accn.setForeground(Color.WHITE);
        beni_accn.setVisible(true);

        beni_acct=new JTextField();
        beni_acct.setBounds(450,352,400,40);
        beni_acct.setForeground(Color.RED);
        beni_acct.setBackground(Color.WHITE);
        beni_acct.setFont(new Font("Long Island",Font.PLAIN,25));
        beni_acct.addMouseListener(this);
        beni_acct.setVisible(true);

        JLabel beni_name=new JLabel("Beneficiary Account Name: ");
        beni_name.setBounds(50,420,1920,100);
        beni_name.setFont(new Font("long Island",Font.PLAIN,30));
        beni_name.setForeground(Color.WHITE);
        beni_name.setVisible(true);

        beni_namet=new JTextField();
        beni_namet.setBounds(450,452,400,40);
        beni_namet.setForeground(Color.RED);
        beni_namet.setBackground(Color.WHITE);
        beni_namet.setFont(new Font("Long Island",Font.PLAIN,25));
        beni_namet.addMouseListener(this);
        beni_namet.setVisible(true);

        verify_ben=new JButton("Verify");
        verify_ben.setBackground(Color.WHITE);
        verify_ben.setFocusable(false);
        verify_ben.setBounds(450,520,100,40);
        verify_ben.setFont(new Font("long Island",Font.PLAIN,20));
        verify_ben.setVisible(true);
        verify_ben.addActionListener(this);
        verify_ben.addMouseListener(this);

        ver_ben_msg=new JLabel("✓ Beneficiary account verified");
        ver_ben_msg.setBounds(565,520,500,40);
        ver_ben_msg.setFont(new Font("long Island",Font.BOLD,20));
        ver_ben_msg.setForeground(Color.GREEN);
        ver_ben_msg.setVisible(false);

        JLabel send_amt=new JLabel("Amount: ");
        send_amt.setBounds(875,420,1920,100);
        send_amt.setFont(new Font("long Island",Font.PLAIN,30));
        send_amt.setForeground(Color.WHITE);
        send_amt.setVisible(true);

        amount_sendt=new JTextField();
        amount_sendt.setBounds(995,452,400,40);
        amount_sendt.setForeground(Color.RED);
        amount_sendt.setBackground(Color.WHITE);
        amount_sendt.setFont(new Font("Long Island",Font.PLAIN,25));
        amount_sendt.addMouseListener(this);
        amount_sendt.setVisible(true);

        ini_tb=new JButton("Transfer");
        ini_tb.setBackground(Color.WHITE);
        ini_tb.setFocusable(false);
        ini_tb.setBounds(995,520,110,40);
        ini_tb.setFont(new Font("long Island",Font.PLAIN,20));
        ini_tb.setVisible(true);
        ini_tb.addActionListener(this);
        ini_tb.addMouseListener(this);

        cannot_transfer=new JLabel("Complete Verification!");
        cannot_transfer.setBounds(1115,490,1920,100);
        cannot_transfer.setFont(new Font("long Island",Font.BOLD,20));
        cannot_transfer.setForeground(Color.RED);
        cannot_transfer.setVisible(false);

        transfer_failed=new JLabel("Transfer Failed!");
        transfer_failed.setBounds(1105,630,1920,100);
        transfer_failed.setFont(new Font("long Island",Font.BOLD,20));
        transfer_failed.setForeground(Color.RED);
        transfer_failed.setVisible(false);

        depo_contents.add(source_details);
        depo_contents.add(beni_details);
        depo_contents.add(source);
        depo_contents.add(source_acct);
        depo_contents.add(beni_accn);
        depo_contents.add(beni_acct);
        depo_contents.add(beni_name);
        depo_contents.add(beni_namet);
        depo_contents.add(verify_ben);
        depo_contents.add(ver_ben_msg);
        depo_contents.add(send_amt);
        depo_contents.add(amount_sendt);
        depo_contents.add(ini_tb);
        depo_contents.add(cannot_transfer);
        depo_contents.add(transfer_failed);
        deposite_pan.add(depo_contents);
        return deposite_pan;
    }
    private JPanel pin_pan(){
        pin_pan=new JPanel();
        pin_pan.setBounds(282,132,1920,1080);
        pin_pan.setBackground(Color.GRAY);
        pin_pan.setLayout(null);
        pin_pan.setVisible(true);

        JPanel pin_pan_contents=new JPanel();
        pin_pan_contents.setBounds(30,30,1580,820);
        pin_pan_contents.setBackground(new Color(0,35,100));
        pin_pan_contents.setLayout(null);
        pin_pan_contents.setVisible(true);

        JLabel amt=new JLabel(String.format("Sending:    ₹%f",Double.parseDouble(amount_sendt.getText())));
        amt.setBounds(990,130,500,100);
        amt.setFont(new Font("long Island",Font.PLAIN,30));
        amt.setForeground(Color.WHITE);
        amt.setVisible(true);

        JLabel to=new JLabel(String.format("To:              %s",beni_namet.getText()));
        to.setBounds(990,80,500,100);
        to.setFont(new Font("long Island",Font.PLAIN,30));
        to.setForeground(Color.WHITE);
        to.setVisible(true);

        String an="XXXX";
        an+=account_no.substring(4);

        JLabel actno=new JLabel(String.format("From:    %s",an));
        actno.setBounds(110,80,500,100);
        actno.setFont(new Font("long Island",Font.PLAIN,30));
        actno.setForeground(Color.WHITE);
        actno.setVisible(true);

        status=new JLabel("ENTER YOUR 6-DIGIT PIN:");
        status.setBounds(450,250,500,100);
        status.setFont(new Font("long Island",Font.PLAIN,35));
        status.setForeground(Color.RED);
        status.setVisible(true);

        chances=new JLabel("Invalid Pin!");
        chances.setBounds(820,325,500,100);
        chances.setFont(new Font("long Island",Font.PLAIN,25));
        chances.setForeground(Color.RED);
        chances.setVisible(false);

        JLabel pin_t=new JLabel("Pin:");
        pin_t.setBounds(450,320,500,100);
        pin_t.setFont(new Font("long Island",Font.PLAIN,30));
        pin_t.setForeground(Color.WHITE);
        pin_t.setVisible(true);

        transfer_pin=new JPasswordField();
        transfer_pin.setBounds(515,355,300,40);
        transfer_pin.setForeground(Color.RED);
        transfer_pin.setBackground(Color.WHITE);
        transfer_pin.setFont(new Font("Long Island",Font.PLAIN,25));
        transfer_pin.addMouseListener(this);
        transfer_pin.setVisible(true);

        proceed_but=new JButton("Proceed");
        proceed_but.setBackground(Color.GREEN);
        proceed_but.setForeground(Color.BLACK);
        proceed_but.setFocusable(false);
        proceed_but.setBounds(695,420,120,40);
        proceed_but.setFont(new Font("long Island",Font.PLAIN,20));
        proceed_but.setVisible(true);
        proceed_but.addActionListener(this);
        proceed_but.addMouseListener(this);

        cancel_but=new JButton("Cancel");
        cancel_but.setBackground(Color.RED);
        cancel_but.setForeground(Color.BLACK);
        cancel_but.setFocusable(false);
        cancel_but.setBounds(450,420,120,40);
        cancel_but.setFont(new Font("long Island",Font.PLAIN,20));
        cancel_but.setVisible(true);
        cancel_but.addActionListener(this);
        cancel_but.addMouseListener(this);

        pin_pan_contents.add(chances);
        pin_pan_contents.add(transfer_pin);
        pin_pan_contents.add(pin_t);
        pin_pan_contents.add(amt);
        pin_pan_contents.add(to);
        pin_pan_contents.add(actno);
        pin_pan_contents.add(status);
        pin_pan_contents.add(proceed_but);
        pin_pan_contents.add(cancel_but);
        pin_pan.add(pin_pan_contents);
        return pin_pan;
    }
    private JPanel getChange_pin_pan(){
        change_pin_pan=new JPanel();
        change_pin_pan.setBounds(282,132,1920,1080);
        change_pin_pan.setBackground(Color.GRAY);
        change_pin_pan.setLayout(null);
        change_pin_pan.setVisible(true);

        JPanel cpin_contents=new JPanel();
        cpin_contents.setBounds(30,30,1580,820);
        cpin_contents.setBackground(new Color(0,35,100));
        cpin_contents.setLayout(null);
        cpin_contents.setVisible(true);

        JLabel opin=new JLabel("Enter Your Old Pin.");
        opin.setBounds(50,10,1920,100);
        opin.setFont(new Font("long Island",Font.BOLD,50));
        opin.setForeground(Color.RED);
        opin.setVisible(true);

        JLabel eopin=new JLabel("Old Pin:");
        eopin.setBounds(50,100,200,100);
        eopin.setFont(new Font("long Island",Font.PLAIN,30));
        eopin.setForeground(Color.WHITE);
        eopin.setVisible(true);

        old_pin=new JPasswordField();
        old_pin.setBounds(160,132,300,40);
        old_pin.setForeground(Color.RED);
        old_pin.setBackground(Color.WHITE);
        old_pin.setFont(new Font("Long Island",Font.PLAIN,40));
        old_pin.addMouseListener(this);
        old_pin.setVisible(true);

        ver_old_pin=new JLabel("✓ Pin verified");
        ver_old_pin.setForeground(Color.GREEN);
        ver_old_pin.setBounds(260,195,300,30);
        ver_old_pin.setFont(new Font("Long Island",Font.PLAIN,20));
        ver_old_pin.setVisible(false);

        verify_opin_but=new JButton("Verify");
        verify_opin_but.setBackground(Color.WHITE);
        verify_opin_but.setFocusable(false);
        verify_opin_but.setBounds(160,192,90,40);
        verify_opin_but.setFont(new Font("long Island",Font.PLAIN,20));
        verify_opin_but.setVisible(true);
        verify_opin_but.addActionListener(this);
        verify_opin_but.addMouseListener(this);

        JLabel newpin=new JLabel("Enter New Pin.");
        newpin.setBounds(50,250,1920,100);
        newpin.setFont(new Font("long Island",Font.BOLD,50));
        newpin.setForeground(Color.RED);
        newpin.setVisible(true);

        JLabel enewpin=new JLabel("New Pin:");
        enewpin.setBounds(50,330,200,100);
        enewpin.setFont(new Font("long Island",Font.PLAIN,30));
        enewpin.setForeground(Color.WHITE);
        enewpin.setVisible(true);

        new_pin=new JPasswordField();
        new_pin.setBounds(310,362,300,40);
        new_pin.setForeground(Color.RED);
        new_pin.setBackground(Color.WHITE);
        new_pin.setFont(new Font("Long Island",Font.PLAIN,40));
        new_pin.addMouseListener(this);
        new_pin.setVisible(true);

        JLabel conpin=new JLabel("Confirm New Pin:");
        conpin.setBounds(50,420,300,100);
        conpin.setFont(new Font("long Island",Font.PLAIN,30));
        conpin.setForeground(Color.WHITE);
        conpin.setVisible(true);

        con_new_pin=new JPasswordField();
        con_new_pin.setBounds(310,450,300,40);
        con_new_pin.setForeground(Color.RED);
        con_new_pin.setBackground(Color.WHITE);
        con_new_pin.setFont(new Font("Long Island",Font.PLAIN,40));
        con_new_pin.addMouseListener(this);
        con_new_pin.setVisible(true);

        c_pin=new JButton("Update");
        c_pin.setBackground(Color.WHITE);
        c_pin.setFocusable(false);
        c_pin.setBounds(310,510,120,40);
        c_pin.setFont(new Font("long Island",Font.PLAIN,20));
        c_pin.setVisible(true);
        c_pin.addActionListener(this);
        c_pin.addMouseListener(this);

        pin_update_status=new JLabel("✓ Pin Changed");
        pin_update_status.setForeground(Color.GREEN);
        pin_update_status.setBounds(440,515,300,30);
        pin_update_status.setFont(new Font("Long Island",Font.PLAIN,20));
        pin_update_status.setVisible(false);

        cpin_contents.add(opin);
        cpin_contents.add(eopin);
        cpin_contents.add(old_pin);
        cpin_contents.add(ver_old_pin);
        cpin_contents.add(verify_opin_but);
        cpin_contents.add(newpin);
        cpin_contents.add(enewpin);
        cpin_contents.add(new_pin);
        cpin_contents.add(conpin);
        cpin_contents.add(con_new_pin);
        cpin_contents.add(c_pin);
        cpin_contents.add(pin_update_status);
        change_pin_pan.add(cpin_contents);
        return change_pin_pan;
    }
    private JPanel getChange_pass_pan(){
        change_pass_pan=new JPanel();
        change_pass_pan.setBounds(282,132,1920,1080);
        change_pass_pan.setBackground(Color.GRAY);
        change_pass_pan.setLayout(null);
        change_pass_pan.setVisible(true);

        JPanel cpass_contents=new JPanel();
        cpass_contents.setBounds(30,30,1580,820);
        cpass_contents.setBackground(new Color(0,35,100));
        cpass_contents.setLayout(null);
        cpass_contents.setVisible(true);

        JLabel opas=new JLabel("Enter Your Old Password.");
        opas.setBounds(50,10,1920,100);
        opas.setFont(new Font("long Island",Font.BOLD,50));
        opas.setForeground(Color.RED);
        opas.setVisible(true);

        JLabel eopas=new JLabel("Old Password:");
        eopas.setBounds(50,100,200,100);
        eopas.setFont(new Font("long Island",Font.PLAIN,30));
        eopas.setForeground(Color.WHITE);
        eopas.setVisible(true);

        old_pass=new JPasswordField();
        old_pass.setBounds(245,132,400,40);
        old_pass.setForeground(Color.RED);
        old_pass.setBackground(Color.WHITE);
        old_pass.setFont(new Font("Long Island",Font.PLAIN,30));
        old_pass.addMouseListener(this);
        old_pass.setVisible(true);

        ver_old_pass=new JLabel("✓ Pin verified");
        ver_old_pass.setForeground(Color.GREEN);
        ver_old_pass.setBounds(345,195,300,30);
        ver_old_pass.setFont(new Font("Long Island",Font.PLAIN,20));
        ver_old_pass.setVisible(false);

        ver_opass_but=new JButton("Verify");
        ver_opass_but.setBackground(Color.WHITE);
        ver_opass_but.setFocusable(false);
        ver_opass_but.setBounds(245,192,90,40);
        ver_opass_but.setFont(new Font("long Island",Font.PLAIN,20));
        ver_opass_but.setVisible(true);
        ver_opass_but.addActionListener(this);
        ver_opass_but.addMouseListener(this);

        JLabel newpas=new JLabel("Enter New Password.");
        newpas.setBounds(50,250,1920,100);
        newpas.setFont(new Font("long Island",Font.BOLD,50));
        newpas.setForeground(Color.RED);
        newpas.setVisible(true);

        JLabel enewpas=new JLabel("New Password:");
        enewpas .setBounds(50,330,300,100);
        enewpas.setFont(new Font("long Island",Font.PLAIN,30));
        enewpas.setForeground(Color.WHITE);
        enewpas.setVisible(true);

        new_pass=new JPasswordField();
        new_pass.setBounds(370,362,400,40);
        new_pass.setForeground(Color.RED);
        new_pass.setBackground(Color.WHITE);
        new_pass.setFont(new Font("Long Island",Font.PLAIN,30));
        new_pass.addMouseListener(this);
        new_pass.setVisible(true);

        JLabel conpas=new JLabel("Confirm New Password:");
        conpas.setBounds(50,420,400,100);
        conpas.setFont(new Font("long Island",Font.PLAIN,30));
        conpas.setForeground(Color.WHITE);
        conpas.setVisible(true);

        con_new_pass=new JPasswordField();
        con_new_pass.setBounds(370,450,400,40);
        con_new_pass.setForeground(Color.RED);
        con_new_pass.setBackground(Color.WHITE);
        con_new_pass.setFont(new Font("Long Island",Font.PLAIN,30));
        con_new_pass.addMouseListener(this);
        con_new_pass.setVisible(true);

        show_but=new JButton("Show");
        show_but.setBackground(Color.WHITE);
        show_but.setFocusable(false);
        show_but.setBounds(800,455,90,30);
        show_but.setFont(new Font("long Island",Font.PLAIN,20));
        show_but.setVisible(true);
        show_but.addActionListener(this);
        show_but.addMouseListener(this);

        c_pass=new JButton("Update");
        c_pass.setBackground(Color.WHITE);
        c_pass.setFocusable(false);
        c_pass.setBounds(370,510,120,40);
        c_pass.setFont(new Font("long Island",Font.PLAIN,20));
        c_pass.setVisible(true);
        c_pass.addActionListener(this);
        c_pass.addMouseListener(this);

        pass_update_status=new JLabel("✓ Pin Changed");
        pass_update_status.setForeground(Color.GREEN);
        pass_update_status.setBounds(500,515,300,30);
        pass_update_status.setFont(new Font("Long Island",Font.PLAIN,20));
        pass_update_status.setVisible(false);

        cpass_contents.add(opas);
        cpass_contents.add(eopas);
        cpass_contents.add(newpas);
        cpass_contents.add(enewpas);
        cpass_contents.add(conpas);
        cpass_contents.add(old_pass);
        cpass_contents.add(new_pass);
        cpass_contents.add(con_new_pass);
        cpass_contents.add(ver_old_pass);
        cpass_contents.add(ver_opass_but);
        cpass_contents.add(c_pass);
        cpass_contents.add(show_but);
        cpass_contents.add(pass_update_status);
        change_pass_pan.add(cpass_contents);
        return change_pass_pan;
    }
    private static ArrayList<String> get_object_of_list(){return new ArrayList<>();}
    private static ArrayList<String> get_obj_of_recent_list(){return new ArrayList<>();}
    private boolean transfer_details_verify(){
        for (char c:source_acct.getText().toCharArray()){
            switch (c){
                case '0','1','2','3','4','5','6','7','8','9'->{}
                default -> {
                    return false;
                }
            }
        }

        if(!this.account_no.equals(source_acct.getText())){return false;}

        s_acc=Integer.parseInt(source_acct.getText());

        for (char c:beni_acct.getText().toCharArray()){
            switch (c){
                case '0','1','2','3','4','5','6','7','8','9'->{}
                default -> {return false;}
            }
        }

        if(this.account_no.equals(beni_acct.getText())){return false;}

        b_acc=Integer.parseInt(beni_acct.getText());

        for(char c:beni_namet.getText().toCharArray()){
            switch (c){
                case '1', '2', '3', '4', '5', '6', '7', '8', '9', '0','!','@','#','$','%','^','&','*','~',';','.',','->{return false;}
                default -> {}
            }
        }

        String b_name=beni_namet.getText().trim();

        verify_details=details.verify_all_account_details(s_acc, b_acc, b_name);
        ver_ben_msg.setText("✓ Beneficiary account verified");
        ver_ben_msg.setForeground(Color.GREEN);
        return true;
    }
}
