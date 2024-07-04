import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class FirstPage extends JFrame implements ActionListener, MouseListener {
    private final JButton but1,but2;
    protected static JButton logbut,canbut,signbut,csbut;
    protected static JPanel login_pan,sign_pan,new_pan1,new_pan2;
    protected static JTextField gtext,atext,fnamet,lnamet,hphonet;
    protected static JLabel sign_lab,log_label,acc,ema,pas,wrong_msg1,wrong_msg2,wrong_msg3,fname,lname,hphone,hpin;
    protected static JPasswordField password,hpinpas;
    protected static JLabel wsm1,wsm2,wsm3,wsm4,wsm5,wsm6,wsm7;
    protected static JLayeredPane pan_lp,lp;
    protected FirstPage() {
        lp=new JLayeredPane();
        lp.setBounds(0,0,400,1080);

        ImageIcon img = new ImageIcon("E:\\JDBC_programming\\JDBC_project\\src\\vb_nobg.png");
        JLabel label=new JLabel("Virtual Bank");
        label.setForeground(Color.RED);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setFont(new Font("long Island",Font.PLAIN,30));
        label.setIcon(img);

        but1=new JButton("Login");
        but2=new JButton("Sign Up");
        but1.setBounds(120,550,160,40);
        but2.setBounds(120,620,160,40);
        but1.setFocusable(false);
        but2.setFocusable(false);
        but1.setFont(new Font("long Island",Font.PLAIN,20));
        but2.setFont(new Font("long Island",Font.PLAIN,20));
        but1.setBackground(Color.WHITE);
        but1.addActionListener(this);
        but2.setBackground(Color.WHITE);
        but2.addActionListener(this);
        but1.addMouseListener(this);
        but2.addMouseListener(this);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1920, 1080);
        this.setTitle("Virtual Bank");
        ImageIcon image = new ImageIcon("E:\\JDBC_programming\\JDBC_project\\src\\vb.jpg");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(null);
        setExtendedState(MAXIMIZED_BOTH);



        JPanel panel=new JPanel(new GridLayout());
        panel.setPreferredSize(new Dimension(100,100));
        JPanel panel2=new JPanel(new GridLayout());
        panel2.setBackground(Color.LIGHT_GRAY);
        panel.setBackground(new Color(10,40,80));
        panel.add(label);
        panel.setBounds(0,0,400,1080);

        lp.add(panel,Integer.valueOf(0));
        lp.add(but1,Integer.valueOf(2));
        lp.add(but2,Integer.valueOf(2));
        this.add(lp);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pan_lp=new JLayeredPane();
        pan_lp.setBounds(405,0,1920,1080);
        pan_lp.setVisible(true);
        this.add(pan_lp);
        if(e.getSource()==but1){
            but1.setEnabled(false);
            pan_lp.removeAll();
            if(sign_pan!=null){
                signbut.setVisible(false);
                csbut.setVisible(false);
                sign_lab.setVisible(false);
                fname.setVisible(false);
                lname.setVisible(false);
                hphone.setVisible(false);
                hpin.setVisible(false);
                hpinpas.setVisible(false);
                fnamet.setVisible(false);
                lnamet.setVisible(false);
                hphonet.setVisible(false);
                gtext.setVisible(false);
                atext.setVisible(false);
                ema.setVisible(false);
                acc.setVisible(false);
                password.setVisible(false);
                pas.setVisible(false);
                sign_pan.setVisible(false);
                wsm1.setVisible(false);
                wsm2.setVisible(false);
                wsm3.setVisible(false);
                wsm4.setVisible(false);
                wsm5.setVisible(false);
                wsm6.setVisible(false);
                wsm7.setVisible(false);
            }
            pan_lp.add(add_log());
            but2.setEnabled(true);
        }
        else if(e.getSource()==but2){
            but2.setEnabled(false);
            pan_lp.removeAll();
            if(login_pan!=null){
                log_label.setVisible(false);
                gtext.setVisible(false);
                atext.setVisible(false);
                acc.setVisible(false);
                ema.setVisible(false);
                pas.setVisible(false);
                password.setVisible(false);
                logbut.setVisible(false);
                canbut.setVisible(false);
                wrong_msg1.setVisible(false);
                wrong_msg2.setVisible(false);
                wrong_msg3.setVisible(false);
            }
            pan_lp.add(add_sign());
            but1.setEnabled(true);
        }
        else if(e.getSource()==logbut){
            logbut.setEnabled(false);
            canbut.setEnabled(true);
            String gmail=gtext.getText();
            String acc_no=atext.getText();
            String pass=String.valueOf(password.getPassword());
            is_valid_log(gmail,acc_no,pass);
        }
        else if(e.getSource()==canbut){
            canbut.setEnabled(false);
            logbut.setEnabled(true);
            gtext.setText("");
            atext.setText("");
            password.setText("");
            gtext.setBackground(Color.white);
            atext.setBackground(Color.white);
            password.setBackground(Color.white);
            wrong_msg1.setVisible(false);
            wrong_msg2.setVisible(false);
            wrong_msg3.setVisible(false);
        }
        else if (e.getSource()==signbut) {
            signbut.setEnabled(false);
            is_valid_sign();
            csbut.setEnabled(true);
        }
        else if(e.getSource()==csbut){
            csbut.setEnabled(false);
            hpinpas.setText("");
            fnamet.setText("");
            lnamet.setText("");
            hphonet.setText("");
            gtext.setText("");
            atext.setText("");
            password.setText("");
            wsm1.setVisible(false);
            wsm2.setVisible(false);
            wsm3.setVisible(false);
            wsm4.setVisible(false);
            wsm5.setVisible(false);
            wsm6.setVisible(false);
            wsm7.setVisible(false);
            hpinpas.setBackground(Color.WHITE);
            fnamet.setBackground(Color.WHITE);
            lnamet.setBackground(Color.WHITE);
            hphonet.setBackground(Color.WHITE);
            gtext.setBackground(Color.WHITE);
            atext.setBackground(Color.WHITE);
            password.setBackground(Color.WHITE);
            signbut.setEnabled(true);
        }
    }
    private JPanel add_log(){
        login_pan=new JPanel();
        login_pan.setLayout(null);
        login_pan.setBounds(0,0,1920,1080);

        gtext=new JFormattedTextField();
        atext=new JFormattedTextField();
        acc=new JLabel();
        ema=new JLabel();
        pas=new JLabel();
        password=new JPasswordField();
        logbut=new JButton();
        canbut=new JButton();
        wrong_msg1=new JLabel("Enter a valid Gmail");
        wrong_msg2=new JLabel("Enter a valid Account number");
        wrong_msg3=new JLabel("Enter a valid Password");
        log_label=new JLabel();
        ema.setText("Gmail");
        acc.setText("Account Number");
        pas.setText("Password");
        log_label.setText("WELCOME");
        log_label.setForeground(Color.black);
        gtext.setBounds(520,450,400,40);
        gtext.setBackground(Color.WHITE);
        atext.setBounds(520,590,400,40);
        atext.setBackground(Color.WHITE);
        password.setBounds(520,720,400,40);
        password.setBackground(Color.WHITE);
        gtext.setFont(new Font("Long Island",Font.PLAIN,25));
        atext.setFont(new Font("Long Island",Font.PLAIN,25));
        gtext.setForeground(Color.red);
        atext.setForeground(Color.red);
        password.setForeground(Color.red);
        log_label.setFont(new Font("Long Island",Font.BOLD,60));
        ema.setFont(new Font("Long Island",Font.PLAIN,20));
        acc.setFont(new Font("Long Island",Font.PLAIN,20));
        pas.setFont(new Font("Long Island",Font.PLAIN,20));
        password.setFont(new Font("Long Island",Font.PLAIN,20));
        ema.setBounds(520,200,400,400);
        acc.setBounds(520,345,400,400);
        pas.setBounds(520,480,400,400);
        log_label.setBounds(520,50,700,300);
        logbut.setBounds(520,795,160,40);
        canbut.setBounds(763,795,160,40);
        logbut.setText("Login");
        canbut.setText("Cancel");
        logbut.setFocusable(false);
        canbut.setFocusable(false);
        logbut.setBackground(Color.WHITE);
        canbut.setBackground(Color.WHITE);
        logbut.addActionListener(this);
        logbut.addMouseListener(this);
        canbut.addActionListener(this);
        canbut.addMouseListener(this);
        logbut.setFont(new Font("long Island",Font.PLAIN,20));
        canbut.setFont(new Font("long Island",Font.PLAIN,20));
        logbut.setForeground(Color.BLACK);
        canbut.setForeground(Color.BLACK);
        login_pan.setBackground(Color.GRAY);
        wrong_msg1.setForeground(Color.RED);
        wrong_msg2.setForeground(Color.RED);
        wrong_msg3.setForeground(Color.RED);
        wrong_msg1.setBounds(930,450,200,40);
        wrong_msg2.setBounds(930,590,400,40);
        wrong_msg3.setBounds(930,720,400,40);
        wrong_msg1.setFont(new Font("long Island",Font.PLAIN,20));
        wrong_msg2.setFont(new Font("long Island",Font.PLAIN,20));
        wrong_msg3.setFont(new Font("long Island",Font.PLAIN,20));
        wrong_msg1.setVisible(false);
        wrong_msg2.setVisible(false);
        wrong_msg3.setVisible(false);
        acc.setForeground(Color.BLACK);
        ema.setForeground(Color.BLACK);
        pas.setForeground(Color.BLACK);
        gtext.addMouseListener(this);
        atext.addMouseListener(this);
        password.addMouseListener(this);

        login_pan.add(log_label);
        login_pan.add(ema);
        login_pan.add(acc);
        login_pan.add(pas);
        login_pan.add(gtext);
        login_pan.add(atext);
        login_pan.add(password);
        login_pan.add(logbut);
        login_pan.add(canbut);
        login_pan.add(wrong_msg1);
        login_pan.add(wrong_msg2);
        login_pan.add(wrong_msg3);
        return login_pan;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(login_pan!=null) {
            if (e.getSource() == gtext) {
                gtext.setBackground(Color.white);
                wrong_msg1.setVisible(false);
            } else if (e.getSource() == atext) {
                atext.setBackground(Color.white);
                wrong_msg2.setVisible(false);
            } else if (e.getSource() == password) {
                password.setBackground(Color.white);
                wrong_msg3.setVisible(false);
            }
            if (e.getSource() == gtext || e.getSource() == atext || e.getSource() == password)
                logbut.setEnabled(true);
        }
        if(sign_pan!=null) {
            if (e.getSource() == fnamet) {
                fnamet.setBackground(Color.WHITE);
                wsm1.setVisible(false);
                signbut.setEnabled(true);
            } else if (e.getSource() == lnamet) {
                lnamet.setBackground(Color.WHITE);
                wsm2.setVisible(false);
                signbut.setEnabled(true);
            } else if (e.getSource() == hphonet) {
                hphonet.setBackground(Color.WHITE);
                wsm3.setVisible(false);
                signbut.setEnabled(true);
            } else if (e.getSource()==hpinpas) {
                hpinpas.setBackground(Color.WHITE);
                wsm4.setVisible(false);
                signbut.setEnabled(true);
            } else if (e.getSource()==gtext) {
                gtext.setBackground(Color.WHITE);
                wsm5.setVisible(false);
                signbut.setEnabled(true);
            } else if (e.getSource()==atext) {
                atext.setBackground(Color.WHITE);
                wsm6.setVisible(false);
                signbut.setEnabled(true);
            } else if (e.getSource()==password) {
                password.setBackground(Color.WHITE);
                wsm7.setVisible(false);
                signbut.setEnabled(true);
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==but1)
            but1.setBackground(Color.red);
        else if(e.getSource()==but2)
            but2.setBackground(Color.red);
        else if(e.getSource()==logbut)
            logbut.setBackground(Color.red);
        else if(e.getSource()==canbut)
            canbut.setBackground(Color.red);
        else if (e.getSource()==signbut)
            signbut.setBackground(Color.red);
        else if(e.getSource()==csbut)
            csbut.setBackground(Color.red);

    }
    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==but1)
            but1.setBackground(Color.white);
        else if(e.getSource()==but2)
            but2.setBackground(Color.white);
        else if(e.getSource()==logbut)
            logbut.setBackground(Color.white);
        else if(e.getSource()==canbut)
            canbut.setBackground(Color.white);
        else if (e.getSource()==signbut)
            signbut.setBackground(Color.white);
        else if(e.getSource()==csbut)
            csbut.setBackground(Color.white);
    }
    private void is_valid_log(String gmail,String acc_no,String pass){
        if(!gmail.contains("@gmail.com") || gmail.contains(" ")) {
            gtext.setBackground(Color.red);
            wrong_msg1.setVisible(true);
        }
        else if(acc_no.length()!=9||acc_no.contains(" ")||contains_char(acc_no)){
            atext.setBackground(Color.red);
            wrong_msg2.setVisible(true);
        }
        else if(pass.contains(" ")||pass.length()>30||pass.isEmpty()){
            password.setBackground(Color.red);
            wrong_msg3.setVisible(true);
        }
        else{
            int acc=Integer.parseInt(acc_no);
            Login l=new Login(gmail,pass,acc);
            int x=l.try_query();
            if(x==-1){l.create_transaction_table();System.out.println("Logged in!!");this.dispose();new HomePage(acc_no);}
            else if(x==3){
                gtext.setBackground(Color.red);
                wrong_msg1.setVisible(true);
                password.setBackground(Color.red);
                wrong_msg3.setVisible(true);
                atext.setBackground(Color.red);
                wrong_msg2.setVisible(true);
            }
            else if(x==0){
                gtext.setBackground(Color.red);
                wrong_msg1.setVisible(true);
            }
            else if (x==1) {
                password.setBackground(Color.red);
                wrong_msg3.setVisible(true);
            }
            else if(x==2){
                atext.setBackground(Color.red);
                wrong_msg2.setVisible(true);
            }
        }
    }
    private void is_valid_sign(){
        String fn=fnamet.getText();
        String ln=lnamet.getText();
        String ph=hphonet.getText();
        String hpin=String.valueOf(hpinpas.getPassword());
        String gmail=gtext.getText();
        String acc_no=atext.getText();
        String pass=String.valueOf(password.getPassword());
        if(contains_num(fn)||fn.length()<=1){wsm1.setVisible(true);fnamet.setBackground(Color.red);}
        else if(contains_num(ln)||ln.length()<=1){wsm2.setVisible(true);lnamet.setBackground(Color.red);}
        else if(contains_char(ph)||ph.length()!=10||ph.contains(" ")){wsm3.setVisible(true);hphonet.setBackground(Color.red);}
        else if(contains_char(hpin)||hpin.length()!=6||hpin.contains(" ")){wsm4.setVisible(true);hpinpas.setBackground(Color.red);}
        else if(!gmail.contains("@gmail.com") || gmail.contains(" ")||gmail.length()>100) {gtext.setBackground(Color.red);wsm5.setVisible(true);}
        else if(acc_no.length()!=9||acc_no.contains(" ")||contains_char(acc_no)){atext.setBackground(Color.red);wsm6.setVisible(true);}
        else if(pass.contains(" ")||pass.length()>30||pass.isEmpty()){password.setBackground(Color.red);wsm7.setVisible(true);}
        else{
            SignUp su=new SignUp();
            int acc=Integer.parseInt(acc_no);
            int pinn=Integer.parseInt(hpin);
            if(su.try_signup(acc)){
                su.signing_up(acc,fn,ln,ph,pass,gmail,pinn);
                if(JOptionPane.showConfirmDialog(null,"SignUp successful!!","SignUp",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE)==0){this.dispose();new FirstPage();}
            }
        }
    }
    private boolean contains_num(String s){
        for(int i=0;i<s.length();i++){
            switch (s.charAt(i)) {
                case '1', '2', '3', '4', '5', '6', '7', '8', '9', '0','!','@','#','$','%','^','&','*','~',';','.',',' ->{return true;}
                default -> {}
            }
        }
        return false;
    }
    private boolean contains_char(String acc_no){
        for(int i=0;i<acc_no.length();i++){
            switch (acc_no.charAt(i)) {
                case '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' ->{}
                default -> {
                    return true;
                }
            }
        }
        return false;
    }
    private JPanel add_sign(){
        wsm1=new JLabel("Invalid first name!!");
        wsm1.setForeground(Color.red);
        wsm1.setBounds(930,280,200,40);
        wsm1.setFont(new Font("long Island",Font.PLAIN,20));
        wsm2=new JLabel("Invalid last name!!");
        wsm2.setForeground(Color.red);
        wsm2.setBounds(930,380,200,40);
        wsm2.setFont(new Font("long Island",Font.PLAIN,20));
        wsm3=new JLabel("Invalid phone number!!");
        wsm3.setForeground(Color.red);
        wsm3.setBounds(930,480,200,40);
        wsm3.setFont(new Font("long Island",Font.PLAIN,20));
        wsm4=new JLabel("Invalid pin!!");
        wsm4.setForeground(Color.red);
        wsm4.setBounds(930,580,200,40);
        wsm4.setFont(new Font("long Island",Font.PLAIN,20));
        wsm5=new JLabel("Invalid gmail!!");
        wsm5.setForeground(Color.red);
        wsm5.setBounds(930,680,200,40);
        wsm5.setFont(new Font("long Island",Font.PLAIN,20));
        wsm6=new JLabel("Invalid Acc number!!");
        wsm6.setForeground(Color.red);
        wsm6.setBounds(930,780,200,40);
        wsm6.setFont(new Font("long Island",Font.PLAIN,20));
        wsm7=new JLabel("Invalid password!!");
        wsm7.setForeground(Color.red);
        wsm7.setBounds(930,880,200,40);
        wsm7.setFont(new Font("long Island",Font.PLAIN,20));

        wsm1.setVisible(false);
        wsm2.setVisible(false);
        wsm3.setVisible(false);
        wsm4.setVisible(false);
        wsm5.setVisible(false);
        wsm6.setVisible(false);
        wsm7.setVisible(false);

        signbut=new JButton("Signup");
        csbut=new JButton("Cancel");
        fname=new JLabel("First name:");
        lname=new JLabel("Last name:");
        hphone=new JLabel("Phone number:");
        hpin=new JLabel("Pin:");
        hpinpas=new JPasswordField();
        fnamet=new JTextField();
        lnamet=new JTextField();
        hphonet=new JTextField();
        gtext=new JFormattedTextField();
        atext=new JFormattedTextField();
        ema=new JLabel("Gmail:");
        acc=new JLabel("Account Number:");
        password=new JPasswordField();
        pas=new JLabel("Password:");

        sign_pan=new JPanel();
        sign_pan.setLayout(null);
        sign_pan.setBackground(Color.gray);
        sign_pan.setBounds(0,0,1920,1080);

        sign_lab=new JLabel("WELCOME");
        sign_lab.setFont(new Font("Long Island",Font.BOLD,60));
        sign_lab.setBounds(520,50,700,300);
        sign_lab.setForeground(Color.BLACK);

        fname.setBounds(415,100,400,400);
        fname.setFont(new Font("long Island",Font.PLAIN,20));
        fname.setForeground(Color.BLACK);
        lname.setFont(new Font("long Island",Font.PLAIN,20));
        lname.setBounds(415,200,400,400);
        lname.setForeground(Color.BLACK);
        hphone.setBounds(382,300,400,400);
        hphone.setFont(new Font("long Island",Font.PLAIN,20));
        hphone.setForeground(Color.BLACK);
        hpin.setBounds(482,400,400,400);
        hpin.setFont(new Font("long Island",Font.PLAIN,20));
        hpin.setForeground(Color.BLACK);
        ema.setBounds(460,500,400,400);
        ema.setFont(new Font("long Island",Font.PLAIN,20));
        ema.setForeground(Color.BLACK);
        acc.setBounds(365,600,400,400);
        acc.setFont(new Font("long Island",Font.PLAIN,20));
        acc.setForeground(Color.BLACK);
        pas.setBounds(420,700,400,400);
        pas.setFont(new Font("long Island",Font.PLAIN,20));
        pas.setForeground(Color.BLACK);

        fnamet.setBounds(520,280,400,40);
        fnamet.setBackground(Color.white);
        fnamet.setFont(new Font("Long Island",Font.PLAIN,25));
        fnamet.setForeground(Color.red);
        lnamet.setBounds(520,380,400,40);
        lnamet.setBackground(Color.white);
        lnamet.setFont(new Font("Long Island",Font.PLAIN,25));
        lnamet.setForeground(Color.red);
        hphonet.setBounds(520,480,400,40);
        hphonet.setBackground(Color.white);
        hphonet.setFont(new Font("Long Island",Font.PLAIN,25));
        hphonet.setForeground(Color.red);
        hpinpas.setBounds(520,580,400,40);
        hpinpas.setBackground(Color.white);
        hpinpas.setFont(new Font("Long Island",Font.PLAIN,25));
        hpinpas.setForeground(Color.red);
        gtext.setBounds(520,680,400,40);
        gtext.setBackground(Color.WHITE);
        gtext.setForeground(Color.red);
        gtext.setFont(new Font("Long Island",Font.PLAIN,25));
        atext.setBounds(520,780,400,40);
        atext.setBackground(Color.WHITE);
        atext.setForeground(Color.red);
        atext.setFont(new Font("Long Island",Font.PLAIN,25));
        password.setBounds(520,880,400,40);
        password.setBackground(Color.white);
        password.setFont(new Font("Long Island",Font.PLAIN,25));
        password.setForeground(Color.red);

        hpinpas.addMouseListener(this);
        fnamet.addMouseListener(this);
        lnamet.addMouseListener(this);
        hphonet.addMouseListener(this);
        gtext.addMouseListener(this);
        atext.addMouseListener(this);
        password.addMouseListener(this);

        signbut.setBounds(520,950,160,40);
        signbut.setFocusable(false);
        signbut.setBackground(Color.WHITE);
        csbut.setBounds(763,950,160,40);
        csbut.setFocusable(false);
        csbut.setBackground(Color.WHITE);
        signbut.setFont(new Font("long Island",Font.PLAIN,20));
        csbut.setFont(new Font("long Island",Font.PLAIN,20));
        signbut.addActionListener(this);
        signbut.addMouseListener(this);
        csbut.addActionListener(this);
        csbut.addMouseListener(this);

        sign_pan.add(sign_lab);
        sign_pan.add(fnamet);
        sign_pan.add(fname);
        sign_pan.add(lname);
        sign_pan.add(lnamet);
        sign_pan.add(hphone);
        sign_pan.add(hphonet);
        sign_pan.add(hpin);
        sign_pan.add(hpinpas);
        sign_pan.add(signbut);
        sign_pan.add(csbut);
        sign_pan.add(gtext);
        sign_pan.add(ema);
        sign_pan.add(atext);
        sign_pan.add(acc);
        sign_pan.add(password);
        sign_pan.add(pas);
        sign_pan.add(wsm1);
        sign_pan.add(wsm2);
        sign_pan.add(wsm3);
        sign_pan.add(wsm4);
        sign_pan.add(wsm5);
        sign_pan.add(wsm6);
        sign_pan.add(wsm7);
        return sign_pan;
    }
}
