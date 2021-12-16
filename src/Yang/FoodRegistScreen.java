package Yang;
import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FoodRegistScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField clink;
	private JTextField fprice;
	private JTextField fdeadline;
	private JTextField fname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FoodRegistScreen frame = new FoodRegistScreen();
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
	public FoodRegistScreen() {
		setVisible(true);
		setTitle("FoodRegistScreen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(20,5,845,506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(14, 12, 799, 435);
		contentPane.add(layeredPane);
		
		fdeadline = new JTextField();
		fdeadline.setBounds(277, 170, 347, 44);
		layeredPane.add(fdeadline);
		fdeadline.setColumns(10);
		
		fprice = new JTextField();
		fprice.setBounds(277, 238, 347, 44);
		layeredPane.add(fprice);
		fprice.setColumns(10);
		
		clink = new JTextField();
		clink.setBounds(277, 303, 347, 44);
		layeredPane.add(clink);
		clink.setColumns(10);
		
		fname = new JTextField();
		fname.setBounds(277, 108, 347, 44);
		layeredPane.add(fname);
		fname.setColumns(10);
		
		JLabel foodnamelabel = new JLabel("\uC2DD\uC7AC\uB8CC\uBA85");
		foodnamelabel.setBounds(184, 121, 62, 18);
		layeredPane.add(foodnamelabel);
		
		JLabel fooddeadlinelabel = new JLabel("\uC2DD\uC7AC\uB8CC \uC720\uD1B5\uAE30\uD55C\r\n");
		fooddeadlinelabel.setBounds(152, 183, 111, 18);
		layeredPane.add(fooddeadlinelabel);
		
		JLabel foodpricelabel = new JLabel("\uC2DD\uC7AC\uB8CC \uAE08\uC561");
		foodpricelabel.setBounds(171, 251, 75, 18);
		layeredPane.add(foodpricelabel);
		
		JLabel chatlinklabel = new JLabel("\uCC44\uD305 \uB9C1\uD06C\r\n");
		chatlinklabel.setBounds(184, 316, 62, 18);
		layeredPane.add(chatlinklabel);
		
		JButton registbtn = new JButton("\uB4F1\uB85D");
		registbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FoodDAO reg=new FoodDAO();		//FoodDAO 객체생성
	      		boolean b =reg.FoodReg(BeginingScreen.conId,fname.getText(),fdeadline.getText(),fprice.getText(),clink.getText(),BeginingScreen.conRegion);
	      		//boolean b 란 정상적인 입력을 마치고 성공적은 실행을했다면 true를 받음
	      		if(b==true) {	//위 실행을 마치고 정상적이라면 해당 창을 닫고 메인화면으로 복귀
	      		setVisible(false);
	      		MainScreen msc=new MainScreen();
	      		}
			}
		});
		registbtn.setBounds(519, 372, 105, 27);
		layeredPane.add(registbtn);
	}

}
