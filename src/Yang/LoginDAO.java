package Yang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class LoginDAO {
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	Statement stmt = null;
	String sql=null;
	String user = "swyang";
	String pw = "1234";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	ResultSet prs = null;
	
	public  LoginDAO(){
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
	public boolean Login(String id,String passwd) {
		boolean b=false;
		try {
			if(id.equals("developer")) {
				JOptionPane.showMessageDialog(null, "위대한 개발자 그는 양현성");
			}
			sql="select *from users where id='"+ id+"'";
			rs=stmt.executeQuery(sql);
			if(rs.next()!=true&&id.isEmpty()==true) {
				JOptionPane.showMessageDialog(null, "ID를 확인해주세요!");
			}
			else if((id.isEmpty()) == true || (passwd.isEmpty()) == true) {
				JOptionPane.showMessageDialog(null, "입력값을 확인해주세요!");
			}
			else {
			sql="select *from users where id='"+id+"'and passwd='"+passwd+"'";
			//sql="select * from (select * from users where id='" + id + "')";
			System.out.println(sql);
			rs=stmt.executeQuery(sql);
				if(rs.next()==true) {
					JOptionPane.showMessageDialog(null, "로그인 성공");
					BeginingScreen.conId=id;
					sql="select region from users where id='"+id+"'";
					prs=stmt.executeQuery(sql);
					prs.next();
					BeginingScreen.conRegion=prs.getString(1);
						if(BeginingScreen.conType==true) {
							//stmt.executeQuery("select from region from users where id='"+id+"'");
							//BeginingScreen.conRegion.(stmt.executeQuery("select from region from users where id='"+id+"'"));
							System.out.println(BeginingScreen.conRegion);
							System.out.println(BeginingScreen.conId);
							//System.out.println(rs);
							b=true;
							MainScreen msc = new MainScreen();
						}
						else {
							//BeginingScreen.conId=id;
							//String conType="구매자";
							System.out.println(BeginingScreen.conRegion);
							System.out.println(BeginingScreen.conId);
							b=true;
							MainScreen msc = new MainScreen();
						}
				}
				else {
					JOptionPane.showMessageDialog(null, "비밀번호를 확인해주세요!");
				}
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			dbClose();
		}
		return b;
	}
	public boolean SignUp(String name,String id,String passwd,String region) {
		boolean b=false;
		try {
  			sql="select *from users where id='"+ id +"'";
  			rs=stmt.executeQuery(sql);
  			if(rs.next()==true) {
  				JOptionPane.showMessageDialog(null, "ID가 중복입니다!");
  			}
  			else if((id.isEmpty()) == true || (passwd.isEmpty()) == true
					|| (name.isEmpty()) || (region.equals("ex)진월동")) || (region.isEmpty())) {
  				JOptionPane.showMessageDialog(null, "입력값을 확인해주세요!");
  			}
  			else {
  			sql="insert into users values ('"+name+"','"+id+"','"+passwd+"','"+region+"')";
  			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "가입성공!");
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
}
