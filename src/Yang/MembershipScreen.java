package Yang;
import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.io.*; 
import java.lang.*; 
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import Yang.DBconnect;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MembershipScreen extends JFrame {

   private JPanel membership;
   private JTextField textField;
   private JTextField region;
   private JTextField password;
   private JTextField id;
   private JTextField name;
   Statement stmt = null;
	ResultSet rs = null;
	String url = "jdbc:oracle:thin:@203.236.209.193:1521:xe"; // 오라클 포트번호1521/@이후에는 IP주소
	String sql = null;
	Properties info = null;
	Connection cnn = null;
	
	

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               MembershipScreen frame = new MembershipScreen();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }
   
   /**
    * Create the frame.
    */
   
   public MembershipScreen() {
	  JPanel p = new JPanel();
      setTitle("MembershipScreen");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(20,5,845,506);
      setVisible(true);
      membership = new JPanel();
      membership.setBorder(new EmptyBorder(5, 5, 5, 5));
      membership.setLayout(new BorderLayout(0, 0));
      setContentPane(membership);
      
      JLayeredPane layeredPane = new JLayeredPane();
      layeredPane.setBounds(14, 12, 799, 435);
      membership.add(layeredPane);
      
      region = new JTextField();
      region.addMouseListener(new MouseAdapter() {
      	@Override
      	public void mouseClicked(MouseEvent e) {
      		region.setText("");
      	}
      });
      region.setText("ex)\uC9C4\uC6D4\uB3D9");
      region.setBounds(319, 266, 305, 41);
      layeredPane.add(region);
      region.setColumns(10);
      
      id = new JTextField();
      id.setBounds(319, 160, 305, 41);
      layeredPane.add(id);
      id.setColumns(10);
      
      password = new JTextField();
      password.setBounds(319, 213, 305, 41);
      layeredPane.add(password);
      password.setColumns(10);
      
      name = new JTextField();
      name.setBounds(319, 107, 305, 41);
      layeredPane.add(name);
      name.setColumns(10);
      
      JLabel namelabel = new JLabel("NAME");
      namelabel.setBounds(243, 118, 62, 18);
      layeredPane.add(namelabel);
      
      JLabel idlabel = new JLabel("ID");
      idlabel.setBounds(243, 171, 62, 18);
      layeredPane.add(idlabel);
      
      JLabel passwordlabel = new JLabel("PW");
      passwordlabel.setBounds(243, 224, 62, 18);
      layeredPane.add(passwordlabel);
      
      JLabel regionlabel = new JLabel("REGION");
      regionlabel.setBounds(243, 277, 62, 18);
      layeredPane.add(regionlabel);
      
      JButton signbtn = new JButton("Sing up");
      signbtn.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		LoginDAO Log=new LoginDAO();
      		boolean b =Log.SignUp(name.getText(), id.getText(), password.getText(), region.getText());
      		if(b==true)
      		setVisible(false);
      	}
      });
      signbtn.setBounds(519, 333, 105, 27);
      layeredPane.add(signbtn);
      
      JButton closebtn = new JButton("닫기");
      closebtn.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		setVisible(false);
      	}
      });
      
      closebtn.setBounds(39, 391, 91, 23);
      layeredPane.add(closebtn);
   
      System.out.println("dd");
      
      
   }
}