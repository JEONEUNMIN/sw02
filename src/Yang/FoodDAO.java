package Yang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;

public class FoodDAO {
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	Statement stmt = null;
	String sql=null;
	String user = "swyang";
	String pw = "1234";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	static String rfname;
	static String rfdeadline;
	static String rfprice;
	static String rclink;
	static Vector data = null;
	public  FoodDAO(){
		try {			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pw);
			stmt = conn.createStatement();
			System.out.println("로그인 성공");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("DB드라이버 로딩 실패:" + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DB접속 실패:" + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unknown error");
			e.printStackTrace();
		}
	}
	public void dbClose() { //DB자원 최적화를 위해 db free작업
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			System.out.println(e + "=>dbClose fail");
		}
	}
	public boolean FoodReg(String fname,String fdeadline,String fprice,String clink) {
		boolean b=false;
		System.out.println(BeginingScreen.conId);
		String id=BeginingScreen.conId;
		String region=BeginingScreen.conRegion;
		try {
//  			sql="select *from users where id='"+ id +"'";
//  			rs=stmt.executeQuery(sql);
//  			if(rs.next()==true) {
//  				JOptionPane.showMessageDialog(null, "ID가 중복입니다!");
//  			}
  			if((fname.isEmpty()) == true || (fdeadline.isEmpty()) == true
					|| (fprice.isEmpty()) || (clink.isEmpty())) {
  				JOptionPane.showMessageDialog(null, "입력값을 확인해주세요!");
  			}
  			else {
  			sql="insert into Food (id,fname,fdeadline,fprice,clink,region) values ('"+id+"','"+fname+"','"+fdeadline+"','"+fprice+"','"+clink+"','"+region+"')";
  			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "등록성공!");
			b=true;
  			}
		} catch (Exception e2) {
			System.out.println("문제발생");
			e2.printStackTrace();
		} finally {
			dbClose();
		}
		return b;
	}
	public boolean FoodUpd(String fname,String fdeadline,String fprice,String clink) {
		boolean b=false;
		try {
			if((fname.isEmpty()) == true || (fdeadline.isEmpty()) == true
					|| (fprice.isEmpty()) || (clink.isEmpty())) {
				JOptionPane.showMessageDialog(null, "입력값을 확인해주세요!");
			}
			else {
			sql="update food set fname='"+fname+"',fdeadline='"+fdeadline+"',fprice='"+fprice+"',clink='"+clink+"'where id='"+BeginingScreen.conId+"'and fname='"+rfname+"'";
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "수정성공!");
			b=true;
			}
		}catch (Exception e2) {
			System.out.println("문제발생");
			e2.printStackTrace();
		} finally {
			dbClose();
		}
		return b;
	}
	public boolean RecentFood(String id) {
		boolean b=false;
//		
		try {
//  			sql="select *from users where id='"+ id +"'";
//  			rs=stmt.executeQuery(sql);
//  			if(rs.next()==true) {
//  				JOptionPane.showMessageDialog(null, "ID가 중복입니다!");
//  			}
//  			if((fname.isEmpty()) == true || (fdeadline.isEmpty()) == true
//					|| (fprice.isEmpty()) || (clink.isEmpty())) {
//  				JOptionPane.showMessageDialog(null, "입력값을 확인해주세요!");
//  			}
  			BeginingScreen.conId=id;
			sql="select *from food where id='"+id+"'";
			rs=stmt.executeQuery(sql);
			if(rs.next()==true) {
				rfname=rs.getString(2);
				rfdeadline=rs.getString(3);
				rfprice=rs.getString(4);
				rclink=rs.getString(5);
				b=true;
			//rs.next();
  			//stmt.executeUpdate(sql);
			//JOptionPane.showMessageDialog(null, "등록성공!");
				
			}
			else {
				rfname="";
				rfdeadline="";
				rfprice="";
				rclink="";
				//JOptionPane.showMessageDialog(null, "등록된 상품이없습니다!");
			}
  			
		} catch (Exception e2) {
			System.out.println("문제발생");
			e2.printStackTrace();
		} finally {
			
			dbClose();
		}
		return b;
	}
}
