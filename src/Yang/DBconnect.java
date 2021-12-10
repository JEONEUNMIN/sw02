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

public class DBconnect { 
	
//	   //private Connection conn = null; 
//	   Statement stmt = null;
//		ResultSet rs = null;
//		String url = "jdbc:oracle:thin:@203.236.209.193:1521:xe"; // 오라클 포트번호1521/@이후에는 IP주소
//		String sql = null;
//		Properties info = null;
//		Connection cnn = null;
	   public static Connection getConnection(){ 
		   Statement stmt = null;
			ResultSet rs = null;
			String url = "jdbc:oracle:thin:@203.236.209.193:1521:xe"; // 오라클 포트번호1521/@이후에는 IP주소
			String sql = null;
			Properties info = null;
			Connection cnn = null;
	      try { 
	    	  //DBconnect dbcon=new DBconnect();
	    	  System.out.println("d");
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			  info = new Properties();
			  info.setProperty("user", "swyang");
			  info.setProperty("password", "1234");
			  cnn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "swyang", "1234");
			  stmt=cnn.createStatement();
			  System.out.println("db연동완료");
	          
	      } 
	      catch( ClassNotFoundException e ) {
	         System.out.println( "해당드라이버를찾을수없습니다.\n" + e); 
	      }
	      catch( SQLException e) { 
	            System.out.println( "해당드라이버를찾을수없습니다.\n" + e); 
	         }
	      return cnn;
	   }
	   public static void dbClose(ResultSet rs, PreparedStatement stmt, 
				Connection cnn) {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (cnn != null)
					cnn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	   public static void main( String[] args )
	   { 
	         getConnection();
	   }

	}
