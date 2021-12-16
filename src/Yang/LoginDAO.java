package Yang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
/**
 * 최초 실행을 할때 데이터베이스에 접근하여 로그인을 해주는 클래스
 * Login과 Signup으로 이루어져있다.
 * @author 20174332*/
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
	/**
	 * 데이터베이스 테이블 Users에 들어있는 데이터를 select문을 통해 접근.
	 * 액션이 실행될때 id, passwd를 전달 받아서 실행
	 * @param id 해당 사용자의 ID 데이터베이스에 접근해 전달받은 passwd가 해당 id와 같은 레코드면 로그인 성공
	 * @param passwd 해당 ID의 패스워드로 해당 ID레코드에 전달받은 passwd가 같아야한다.
	 * 존재하지 않은 ID면 오류메시지 출력
	 * 해당 id 레코드에 passwd가 전달받은 passwd와 다르면 오류메시지 출력
	 * id와 passwd가 비어있으면 오류메시지 출력
	 * 마지막 return b는 정상적인 처리가 완료되면 해당창을 닫기위해 구분하는 변수로 false일시 해당창에서 정상적인 처리가 될때까지 실행가능
	 */
	public boolean Login(String id,String passwd) {
		boolean b=false;
		try {
			if(id.equals("developer")) {
				JOptionPane.showMessageDialog(null, "위대한 개발자 그는 양현성");
			}
			sql="select *from users where id='"+ id+"'";
			rs=stmt.executeQuery(sql);
			if(rs.next()!=true||id.isEmpty()==true) {		//id 텍스트필드에서 값을 추출후 sql문에 넣어 id확인 또는 비어있는지 확인
				JOptionPane.showMessageDialog(null, "ID를 확인해주세요!");
			}
			else if((id.isEmpty()) == true || (passwd.isEmpty()) == true) {	// 비어있는 값 인식후 에러 출력
				JOptionPane.showMessageDialog(null, "입력값을 확인해주세요!");
			}
			else {
			sql="select *from users where id='"+id+"'and passwd='"+passwd+"'";
			//sql="select * from (select * from users where id='" + id + "')";
			// 아이디와 비밀번호가 두개다 맞다면 성공적인 로그인
			System.out.println(sql);
			rs=stmt.executeQuery(sql);
				if(rs.next()==true) {
					JOptionPane.showMessageDialog(null, "로그인 성공");
					BeginingScreen.conId=id;
					sql="select region from users where id='"+id+"'";
					prs=stmt.executeQuery(sql);
					prs.next();
					BeginingScreen.conRegion=prs.getString(1);
					// 로그인 성공후 로그인상태를 확인하기위해 아이디와 지역을 지역변수로 저장하여 쭉 인식
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
	/**
	 * 데이터베이스 테이블 Users에 데이터를 추가한다.
	 * 액션이 실행될때 name, id, passwd, region를 전달 받아서 실행
	 * @param id 사용자가 원하는 ID로 만들수있음
	 * @param passwd id와 같은 쌍으로 이루어져 있는 passwd
	 * @param name 사용자의 이름
	 * @param region 사용자의 지역
	 * 이미 users테이블에 입력한 id가 있다면 중복되어 오류메시지 출력(id가 중복되어 추가가 불가능하다)
	 * 모든 텍스트필드가 비어있거나 region 텍스트필드가 예시로 입력된 ex)진월동 일시 오류메시지 출력
	 * 아이가 중복되어 있지 않고 입력값이 정상이라면 users테이블에 추가성공
	 * 마지막 return b는 정상적인 처리가 완료되면 해당창을 닫기위해 구분하는 변수로 false일시 해당창에서 정상적인 처리가 될때까지 실행가능
	 */
	public boolean SignUp(String name,String id,String passwd,String region) {
		boolean b=false;
		try {
  			sql="select *from users where id='"+ id +"'";
  			// 입력받은 아이디로 중복값 검사
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
  			// 중복된 값이 없고 비어있는 값이 없다면 전달받은 이름 아이디 비밀번호 지역을 사용해 users 테이블에 추가
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
