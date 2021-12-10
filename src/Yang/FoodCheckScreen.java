package Yang;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.JButton;

public class FoodCheckScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FoodCheckScreen frame = new FoodCheckScreen();
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
	public FoodCheckScreen() {
		setTitle("FoodCheckScreen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(20,5,845,506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(14, 12, 799, 435);
		contentPane.add(layeredPane);
		
		JButton foodcheckbtn1 = new JButton("\uAE40\uC7A5\uAE40\uCE58 \uD314\uC544\uC694");
		foodcheckbtn1.setBounds(239, 111, 355, 27);
		layeredPane.add(foodcheckbtn1);
		
		JButton foodcheckbtn2 = new JButton("\uC591\uD30C \uB3C4\uB9E4\uAD6C\uB9E4 \uD558\uC2E4\uBD84");
		foodcheckbtn2.setBounds(239, 163, 355, 27);
		layeredPane.add(foodcheckbtn2);
		
		JButton foodcheckbtn3 = new JButton("\uACE0\uD5A5\uC9D1 \uACE0\uAD6C\uB9C8 \uBC18\uAC12\uB098\uB214");
		foodcheckbtn3.setBounds(239, 215, 355, 27);
		layeredPane.add(foodcheckbtn3);
		
		JButton morebtn = new JButton("\uB354\uBCF4\uAE30");
		morebtn.setBounds(364, 284, 105, 27);
		layeredPane.add(morebtn);
		
	}

}
