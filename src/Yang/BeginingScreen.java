package Yang;

//import java.awt.BorderLayout;
//
//
//
//
//
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Properties;
import java.awt.event.ActionEvent;
//import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;
//import javax.swing.JOptionPane;
//import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
//import java.awt.TextField;
import java.awt.Color;
//import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.UIManager;


public class BeginingScreen extends JFrame {

   private JPanel contentPane;
   private JTextField id;
   private JTextField passwd;
   private final ButtonGroup buttonGroup = new ButtonGroup();
   static String conId;
   static String conRegion;
   static boolean conType = true;
   
   
 
//   public static void main(String[] args) {
//      EventQueue.invokeLater(new Runnable() {
//         public void run() {
//            try {
//            	BeginingScreen frame = new BeginingScreen();
//               frame.setVisible(true);
//            } catch (Exception e) {
//               e.printStackTrace();
//            }
//         }
//      });
//   }

   public BeginingScreen() {
	  setVisible(true);
      setTitle("����� �߰�ŷ�");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(20,5,845,506);
      contentPane = new JPanel();
      contentPane.setBackground(Color.PINK);
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      contentPane.setLayout(null);
      setContentPane(contentPane);
      
      JLayeredPane layeredPane = new JLayeredPane();
      layeredPane.setBounds(14, 12, 799, 435);
      contentPane.add(layeredPane);
       
      id = new JTextField();
      id.setBounds(260, 162, 292, 37);
      layeredPane.add(id);
      id.setColumns(10);
      
      passwd = new JPasswordField(10);
      passwd.setToolTipText("");
      passwd.setBounds(260, 223, 292, 37);
      layeredPane.add(passwd);     
      passwd.setColumns(10);
      
      JButton login = new JButton("�α���");
      login.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		LoginDAO Log=new LoginDAO();	//��ü����
      		boolean b=Log.Login(id.getText(), passwd.getText()); // �α���ó���� ���� LoginDAO�� login()�� id�� passwd�� �� �ؽ�Ʈ�� �����ش�
      		if(b==true) {	//�α��� ������ �Ǹ� ȭ���� �ݴ´�
      		setVisible(false); 	
      		}
      	}
      });
      login.setBackground(UIManager.getColor("CheckBox.background"));
      login.setBounds(600, 198, 105, 27);
      layeredPane.add(login);
      
      JButton sign_up = new JButton("ȸ������");
      sign_up.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		MembershipScreen ms = new MembershipScreen();	//ȸ������ ��ư�� ������ MembershipScreen ȭ���� �����ش�
      	}
      });
      sign_up.setBackground(UIManager.getColor("CheckBox.background"));
      sign_up.setBounds(260, 280, 105, 27);
      layeredPane.add(sign_up);
      
      JLabel lblNewLabel = new JLabel("ID");
      lblNewLabel.setBounds(196, 171, 62, 18);
      layeredPane.add(lblNewLabel);
      
      JLabel lblNewLabel_1 = new JLabel("PW");
      lblNewLabel_1.setBounds(196, 232, 62, 18);
    
      layeredPane.add(lblNewLabel_1);
      
      JRadioButton sellbtn = new JRadioButton("\uD310\uB9E4\uC790",true);
      sellbtn.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent arg0) {		//�Ǹ��� ������ ��ư �ν����� ���� �Ѱ���
      		conType=true;
      	}
      });
      sellbtn.setBackground(Color.PINK);
      buttonGroup.add(sellbtn);
      sellbtn.setBounds(464, 268, 139, 27);
      layeredPane.add(sellbtn);
      
      JRadioButton consbtn = new JRadioButton("\uAD6C\uB9E4\uC790",false);
      consbtn.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent arg0) {	//�Ǹ��� ������ ��ư �ν����� ���� �Ѱ���
      		conType=false;
      	}
      });
      consbtn.setBackground(Color.PINK);
      buttonGroup.add(consbtn);
      consbtn.setBounds(464, 299, 139, 27);
      layeredPane.add(consbtn);
      
      JTextPane textPane = new JTextPane();
      textPane.setEditable(false);
      textPane.setFont(new Font("������� ExtraBold", Font.BOLD, 37));
      textPane.setText("\uC2DD\uC7AC\uB8CC \uC911\uACE0\uAC70\uB798");
      textPane.setBackground(Color.PINK);
      textPane.setBounds(260, 88, 292, 52);
      layeredPane.add(textPane);
      
   }
}