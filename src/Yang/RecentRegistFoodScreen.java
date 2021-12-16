package Yang;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecentRegistFoodScreen extends JFrame {

	private JPanel contentPane;
	private JTextField foodname;
	private JTextField fooddeadline;
	private JTextField foodprice;
	private JTextField chatlink;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecentRegistFoodScreen frame = new RecentRegistFoodScreen();
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
	public RecentRegistFoodScreen() {
		Vector data = null;
		setTitle("RecentRegistFoodScreen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(20,5,845,506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(14, 12, 799, 435);
		contentPane.add(layeredPane);
		
		JLabel lbfoodname = new JLabel("\uC2DD\uC7AC\uB8CC\uBA85");
		lbfoodname.setBounds(149, 78, 62, 18);
		layeredPane.add(lbfoodname);
		
		JLabel lbfooddeadline = new JLabel("\uC720\uD1B5\uAE30\uD55C");
		lbfooddeadline.setBounds(149, 146, 62, 18);
		layeredPane.add(lbfooddeadline);
		
		JLabel lbfoodprice = new JLabel("\uAE08\uC561");
		lbfoodprice.setBounds(149, 213, 62, 18);
		layeredPane.add(lbfoodprice);
		
		JLabel lbchatlink = new JLabel("\uCC44\uD305\uB9C1\uD06C");
		lbchatlink.setBounds(149, 281, 62, 18);
		layeredPane.add(lbchatlink);
		
		JButton editbtn = new JButton("\uC218\uC815");
		editbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FoodDAO fupd=new FoodDAO();
				boolean b=fupd.FoodUpd(BeginingScreen.conId,foodname.getText(), fooddeadline.getText(), foodprice.getText(), chatlink.getText());
				if(b==true) { // 정상적인 전달을 받고 실행을했다면 화면을 닫음
					setVisible(false);
				}
			}
		});
		editbtn.setBounds(518, 334, 105, 27);
		layeredPane.add(editbtn);
		
		FoodDAO frgtxt=new FoodDAO();
		frgtxt.RecentFood(BeginingScreen.conId);
		//data=new Vector<>();
		//data=frgtxt.RecentFood(BeginingScreen.conId);
		
		foodname = new JTextField();
		foodname.setText(FoodDAO.rfname);
		foodname.setBounds(255, 69, 361, 36);
		layeredPane.add(foodname);
		foodname.setColumns(10);
		
		fooddeadline = new JTextField();
		fooddeadline.setHorizontalAlignment(SwingConstants.LEFT);
		fooddeadline.setText(FoodDAO.rfdeadline);
		fooddeadline.setBounds(255, 137, 361, 36);
		layeredPane.add(fooddeadline);
		fooddeadline.setColumns(10);
		
		foodprice = new JTextField();
		foodprice.setText(FoodDAO.rfprice);
		foodprice.setBounds(255, 204, 361, 36);
		layeredPane.add(foodprice);
		foodprice.setColumns(10);
		
		chatlink = new JTextField();
		chatlink.setText(FoodDAO.rclink);
		chatlink.setBounds(255, 267, 361, 36);
		layeredPane.add(chatlink);
		chatlink.setColumns(10);
		setVisible(true);
	}

}
