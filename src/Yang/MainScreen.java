package Yang;

import java.awt.BorderLayout;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.TextField;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.UIManager;

public class MainScreen extends JFrame {

   private JPanel contentPane;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               MainScreen frame = new MainScreen();
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
   public MainScreen() {
	  setVisible(true);
      setTitle("MainScreen");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(20,5,845,506);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      contentPane.setLayout(new BorderLayout(0, 0));
      setContentPane(contentPane);
      
      JLayeredPane layeredPane = new JLayeredPane();
      layeredPane.setBounds(14, 12, 799, 435);
      contentPane.add(layeredPane);
      
      JButton foodregistbtn = new JButton("식재료 등록");
      foodregistbtn.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent arg0) {
      		setVisible(false);
      		FoodRegistScreen frg = new FoodRegistScreen();
      	}
      });
      foodregistbtn.setBounds(171, 101, 116, 122);
      layeredPane.add(foodregistbtn);
      
      JButton deliveryregistbtn = new JButton("배달음식\n등록");
      deliveryregistbtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         }
      });
      deliveryregistbtn.setForeground(Color.BLACK);
      deliveryregistbtn.setBounds(344, 101, 116, 122);
      layeredPane.add(deliveryregistbtn);
      
      JButton mypagebtn = new JButton("마이페이지");
      mypagebtn.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent arg0) {
      		setVisible(false);
      		MypageScreen mps=new MypageScreen();
      	}
      });
      mypagebtn.setBounds(517, 101, 116, 122);
      layeredPane.add(mypagebtn);
      
      JButton foodcheckbtn = new JButton("\uC2DD\uC7AC\uB8CC\r\n\uC870\uD68C");
      foodcheckbtn.setBounds(257, 257, 105, 122);
      layeredPane.add(foodcheckbtn);
      
      JButton deliverycheckbtn = new JButton("\uBC30\uB2EC\uC74C\uC2DD\r\n\uC870\uD68C");
      deliverycheckbtn.setFont(new Font("굴림", Font.PLAIN, 15));
      deliverycheckbtn.setBounds(436, 257, 105, 122);
      layeredPane.add(deliverycheckbtn);
      
      JTextPane txtpnHh = new JTextPane();
      txtpnHh.setEditable(false);
      txtpnHh.setText(BeginingScreen.conId);
      txtpnHh.setBackground(SystemColor.control);
      txtpnHh.setBounds(12, 10, 105, 27);
      layeredPane.add(txtpnHh);
      
      JTextPane textPane = new JTextPane();
      textPane.setBackground(SystemColor.control);
      if(BeginingScreen.conType==true) {
      textPane.setText("판매자");
      }
      else textPane.setText("구매자");
      textPane.setEditable(false);
      textPane.setBounds(12, 36, 105, 27);
      layeredPane.add(textPane);
   }
}