import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class HomePage extends JFrame implements ActionListener, MouseListener {
    protected static boolean dashboard_accessed=false,balance_updated=false,verify_details=false;
    private static JButton log_out,deposit,withdraw,mini,dash_b,statement,change_pass,send_money,verify_ben,ini_tb,ok_b;
    private static ArrayList<String>list,recent_list,send_money_list;
    private static JLayeredPane lp;
    private static JPanel dash_pan,deposite_pan;
    private static JTextField source_acct,beni_acct,beni_namet,amount_sendt;
    private static JLabel ver_ben_msg,cannot_transfer,transaction_details,transaction_details2,transfer_failed;
    private static Home_details details;
    private static int dr_cr=0;
    private static JPasswordField transfer_pint;
    HomePage(String account_no){
        details=new Home_details(account_no);
        list=get_object_of_list();
        recent_list=get_obj_of_recent_list();
        send_money_list=get_obj_of_send_money_list();

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
        withdraw=new JButton("Change Pin");
        withdraw.setBackground(Color.WHITE);
        withdraw.setFocusable(false);
        withdraw.setForeground(Color.BLACK);
        withdraw.setBounds(35,310,200,40);
        withdraw.setFont(new Font("long Island",Font.PLAIN,20));
        withdraw.addMouseListener(this);
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
        send_money=new JButton("Send Money");
        send_money.setBackground(Color.WHITE);
        send_money.setFocusable(false);
        send_money.setForeground(Color.BLACK);
        send_money.setBounds(35,710,200,40);
        send_money.setFont(new Font("long Island",Font.PLAIN,20));
        send_money.addMouseListener(this);

        deposit.setVisible(true);
        deposit.addActionListener(this);
        log_out.setVisible(true);
        log_out.addActionListener(this);
        dash_b.setVisible(true);
        dash_b.addActionListener(this);
        withdraw.setVisible(true);
        withdraw.addActionListener(this);
        mini.setVisible(true);
        mini.addActionListener(this);
        statement.setVisible(true);
        statement.addActionListener(this);
        change_pass.setVisible(true);
        change_pass.addActionListener(this);
        send_money.setVisible(true);
        send_money.addActionListener(this);

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
        panel2.add(withdraw);
        panel2.add(mini);
        panel2.add(statement);
        panel2.add(change_pass);
        panel2.add(send_money);
        this.add(panel2);
        this.add(panel);
        this.add(lp);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==log_out){list.clear();dashboard_accessed=false;this.dispose();new FirstPage();}
        if(e.getSource()==verify_ben){
            cannot_transfer.setVisible(false);
            if(transfer_details_verify()){
                verify_ben.setEnabled(false);
                ver_ben_msg.setEnabled(true);
            }
            else{
                ver_ben_msg.setText("X Invalid Account Details");
                ver_ben_msg.setForeground(Color.RED);
                ver_ben_msg.setVisible(true);
            }
        }
        else if(e.getSource()==deposit){
            deposit.setEnabled(false);
            withdraw.setEnabled(true);
            mini.setEnabled(true);
            dash_b.setEnabled(true);
            statement.setEnabled(true);
            change_pass.setEnabled(true);
            send_money.setEnabled(true);
            lp.removeAll();
            lp.add(deposit_pan());
        }
        else if(e.getSource()==withdraw){
            deposit.setEnabled(true);
            withdraw.setEnabled(false);
            mini.setEnabled(true);
            dash_b.setEnabled(true);
            statement.setEnabled(true);
            change_pass.setEnabled(true);
            send_money.setEnabled(true);
        }
        else if (e.getSource()==mini) {
            deposit.setEnabled(true);
            withdraw.setEnabled(true);
            mini.setEnabled(false);
            dash_b.setEnabled(true);
            statement.setEnabled(true);
            change_pass.setEnabled(true);
            send_money.setEnabled(true);
        }
        else if (e.getSource()==dash_b) {
            deposit.setEnabled(true);
            withdraw.setEnabled(true);
            mini.setEnabled(true);
            dash_b.setEnabled(false);
            statement.setEnabled(true);
            change_pass.setEnabled(true);
            send_money.setEnabled(true);
            if(!dashboard_accessed){details.fetch_dashboard_details(list);dashboard_accessed=true;}
            else if(!balance_updated){details.fetch_balance(list);balance_updated=true;}
            lp.removeAll();
            lp.add(dash_pan());
        }
        else if (e.getSource()==statement) {
            deposit.setEnabled(true);
            withdraw.setEnabled(true);
            mini.setEnabled(true);
            dash_b.setEnabled(true);
            statement.setEnabled(false);
            change_pass.setEnabled(true);
            send_money.setEnabled(true);
        }
        else if (e.getSource()==change_pass) {
            deposit.setEnabled(true);
            withdraw.setEnabled(true);
            mini.setEnabled(true);
            dash_b.setEnabled(true);
            statement.setEnabled(true);
            change_pass.setEnabled(false);
            send_money.setEnabled(true);
        }
        else if(e.getSource()==send_money){
            deposit.setEnabled(true);
            withdraw.setEnabled(true);
            mini.setEnabled(true);
            dash_b.setEnabled(true);
            statement.setEnabled(true);
            change_pass.setEnabled(true);
            send_money.setEnabled(false);
        }
        else if(e.getSource()==ini_tb){
            if(verify_details){
                cannot_transfer.setText("Enter Pin ↓");
                cannot_transfer.setForeground(Color.GREEN);
                transfer_pint.setEnabled(true);
                ok_b.setEnabled(true);
            }
            else{
                cannot_transfer.setText("Complete Verification!");
                cannot_transfer.setForeground(Color.RED);
                cannot_transfer.setVisible(true);
                transfer_pint.setEnabled(false);
                ok_b.setEnabled(false);
            }
        }
        else if(e.getSource()==ok_b){
            details.initiate_transfer();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==source_acct&&source_acct.getText().contains("        \b\benter account no\b\b")){
            source_acct.setText("");
        }
        else if(e.getSource()==beni_acct&&beni_acct.getText().contains("        \b\benter account no\b\b")){
            beni_acct.setText("");
        }
        else if (e.getSource()==beni_namet&&beni_namet.getText().contains("       \b\benter account name\b\b")) {
            beni_namet.setText("");
        }
        else if(e.getSource()==amount_sendt&&amount_sendt.getText().contains("             \b\benter amount\b\b")){
            amount_sendt.setText("");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource()==source_acct&&source_acct.getText().contains("        \b\benter account no\b\b")){
            source_acct.setText("");
            cannot_transfer.setVisible(false);
        }
        else if(e.getSource()==beni_acct&&beni_acct.getText().contains("        \b\benter account no\b\b")){
            beni_acct.setText("");
            cannot_transfer.setVisible(false);
        }
        else if (e.getSource()==beni_namet&&beni_namet.getText().contains("       \b\benter account name\b\b")) {
            beni_namet.setText("");
            cannot_transfer.setVisible(false);
        }
        else if(e.getSource()==amount_sendt&&amount_sendt.getText().contains("             \b\benter amount\b\b")){
            amount_sendt.setText("");
            cannot_transfer.setVisible(false);
        }
        else if(e.getSource()==amount_sendt){
            cannot_transfer.setVisible(false);
        }
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
        else if(e.getSource()==withdraw){
            withdraw.setBackground(Color.RED);
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
        else if(e.getSource()==send_money){
            send_money.setBackground(Color.RED);
        }
        else if (e.getSource()==verify_ben) {
            verify_ben.setBackground(Color.RED);
            verify_ben.setForeground(Color.WHITE);
        }
        else if(e.getSource()==ini_tb){
            ini_tb.setBackground(Color.RED);
            ini_tb.setForeground(Color.WHITE);
        }
        else if (e.getSource()==ok_b) {
            ok_b.setBackground(Color.RED);
            ok_b.setForeground(Color.WHITE);
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
        else if(e.getSource()==withdraw){
            withdraw.setBackground(Color.WHITE);
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
        else if(e.getSource()==send_money){
            send_money.setBackground(Color.WHITE);
        }
        else if (e.getSource()==verify_ben) {
            verify_ben.setBackground(Color.WHITE);
            verify_ben.setForeground(Color.BLACK);
        }
        else if(e.getSource()==ini_tb){
            ini_tb.setBackground(Color.WHITE);
            ini_tb.setForeground(Color.BLACK);
        }
        else if(e.getSource()==ok_b){
            ok_b.setBackground(Color.WHITE);
            ok_b.setForeground(Color.BLACK);
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


        transaction_details2=new JLabel(String.format("%s ₹%s   on %s","Lolu"," 20000"," 07-08-2024"));
        transaction_details2.setBounds(300,480,500,100);
        transaction_details2.setFont(new Font("long Island",Font.PLAIN,30));
        transaction_details2.setForeground(Color.WHITE);
        transaction_details2.setVisible(true);

        dr_cr=details.get_recent_transaction(recent_list,dr_cr);
        if(dr_cr==0){//dr==0,cr==1
            transaction_details.setText("⭧ Paid To:");
            transaction_details.setForeground(Color.RED);
            transaction_details2.setBounds(220,480,500,100);
        }
        else{
            transaction_details.setText("⭩ Received from:");
            transaction_details.setForeground(Color.GREEN);
            transaction_details2.setBounds(300,480,500,100);
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

        source_acct=new JTextField("        \b\benter account no\b\b");
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

        beni_acct=new JTextField("        \b\benter account no\b\b");
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

        beni_namet=new JTextField("       \b\benter account name\b\b");
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

        amount_sendt=new JTextField("             \b\benter amount\b\b");
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

        JLabel pin=new JLabel("Pin: ");
        pin.setBounds(930,560,1920,100);
        pin.setFont(new Font("long Island",Font.PLAIN,30));
        pin.setForeground(Color.WHITE);
        pin.setVisible(true);

        transfer_pint=new JPasswordField();
        transfer_pint.setBounds(995,600,300,40);
        transfer_pint.setForeground(Color.RED);
        transfer_pint.setBackground(Color.WHITE);
        transfer_pint.setFont(new Font("Long Island",Font.PLAIN,25));
        transfer_pint.addMouseListener(this);
        transfer_pint.setVisible(true);
        transfer_pint.setEnabled(false);

        ok_b=new JButton("OK");
        ok_b.setBackground(Color.WHITE);
        ok_b.setFocusable(false);
        ok_b.setBounds(995,660,100,40);
        ok_b.setFont(new Font("long Island",Font.PLAIN,20));
        ok_b.setVisible(true);
        ok_b.setEnabled(false);
        ok_b.addActionListener(this);
        ok_b.addMouseListener(this);

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
        depo_contents.add(transfer_pint);
        depo_contents.add(ok_b);
        depo_contents.add(pin);
        depo_contents.add(transfer_failed);
        deposite_pan.add(depo_contents);
        return deposite_pan;
    }
    private static ArrayList<String> get_object_of_list(){return new ArrayList<>();}
    private static ArrayList<String> get_obj_of_recent_list(){return new ArrayList<>();}
    private static ArrayList<String> get_obj_of_send_money_list(){return new ArrayList<>();}
    private static boolean transfer_details_verify(){
        for (char c:source_acct.getText().toCharArray()){
            switch (c){
                case '0','1','2','3','4','5','6','7','8','9'->{}
                default -> {
                    return false;
                }
            }
        }
        int s_acc=Integer.parseInt(source_acct.getText());

        for (char c:beni_acct.getText().toCharArray()){
            switch (c){
                case '0','1','2','3','4','5','6','7','8','9'->{}
                default -> {return false;}
            }
        }
        int b_acc=Integer.parseInt(beni_acct.getText());

        for(char c:beni_namet.getText().split("")[0].toCharArray()){
            switch (c){
                case '0','1','2','3','4','5','6','7','8','9'->{return false;}
                default -> {}
            }
        }
        String b_name=beni_namet.getText().split("")[0];

        for(char c:amount_sendt.getText().toCharArray()){
            switch (c){
                case '0','1','2','3','4','5','6','7','8','9'->{}
                default -> {return false;}
            }
        }
        double amt_send=Integer.parseInt(amount_sendt.getText());

        verify_details=details.verify_all_account_details(s_acc, b_acc, b_name, send_money_list);
        return true;
    }
}
