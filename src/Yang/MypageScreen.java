package Yang;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MypageScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MypageScreen frame = new MypageScreen();
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
	public MypageScreen() {
		setTitle("MypageScreen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(20,5,845,506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setVisible(true);
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(14, 12, 799, 435);
		contentPane.add(layeredPane);
		
		JButton recentregistfoodbtn = new JButton("\uCD5C\uADFC \uB4F1\uB85D\uD55C \uC2DD\uC7AC\uB8CC");
		recentregistfoodbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//RecentRegistFoodScreen rrfc=new RecentRegistFoodScreen();
				FoodDAO cfdao=new FoodDAO();
				cfdao.RecentFood(BeginingScreen.conId);
				if(FoodDAO.rfname.isEmpty()) {	// 전달받은 아이디를 sql문을 통해 검색후 값이 없다면 에러메시지 출력
					JOptionPane.showMessageDialog(null, "등록된 상품이없습니다!");
				}
				else {
					RecentRegistFoodScreen rrfc=new RecentRegistFoodScreen();
				}
			}
		});
		recentregistfoodbtn.setBounds(277, 73, 295, 67);
		layeredPane.add(recentregistfoodbtn);
		
		JButton recentregistdeliverybtn = new JButton("\uCD5C\uADFC \uB4F1\uB85D\uD55C \uBC30\uB2EC\uC74C\uC2DD");
		recentregistdeliverybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		recentregistdeliverybtn.setBounds(277, 177, 295, 67);
		layeredPane.add(recentregistdeliverybtn);
		
		JButton userdatachangebtn = new JButton("\uAC1C\uC778\uC815\uBCF4 \uC218\uC815");
		userdatachangebtn.setBounds(277, 281, 295, 67);
		layeredPane.add(userdatachangebtn);
		
		JButton mainscreenbtn = new JButton("\uBA54\uC778\uD654\uBA74");
		mainscreenbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				MainScreen msc=new MainScreen();
			}
		});
		mainscreenbtn.setBounds(625, 379, 105, 27);
		layeredPane.add(mainscreenbtn);
	}

}
