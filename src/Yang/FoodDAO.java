package Yang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
/**
 * FoodDAO는 데이터베이스 Food테이블에 관련된 스크린에서 액션이 실행됬을때 데이터베이스에 접근하는 클래스이다
 * 이 클래스는 각각 FoodReg,FoodUpd,RecentFood로 이루어져있다.
 * @author 20174332*/

public class FoodDAO {
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	Statement stmt = null;
	String sql=null;
	String user = "swyang";		//db 접속시 필요한 user
	String pw = "1234";			//db 접속시 필요한 user의 비밀번호
	String url = "jdbc:oracle:thin:@localhost:1521:xe";		//db접속 url
	static String rfname;		
	static String rfdeadline;
	static String rfprice;
	static String rclink;
	static Vector data = null;
	/**
	 * 데이터베이스에 접근
	 */
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
	/**
	 * 데이터베이스 테이블 Food에 데이터를 추가한다.
	 * 액션이 실행될때 id, fname, fdeadline, fprice, clink, region를 전달 받아서 실행
	 * @param id 해당 사용자의 ID(이를 통해 누가 등록했는지 알수있다)
	 * @param fname 사용자가 음식을 등록할때 음식이름
	 * @param fdeadline	음식의 유통기한
	 * @param fprice	음식의 가격
	 * @param clink	해당 사용자와 소통할수있는 카카오톡 채팅링크
	 * @param region	해당 사용자 근처의 지역사람들만 접근가능하도록 사용자의 지역을 받음
	 * 텍스트필드가 비어있으면 오류메시지를 출력함
	 * 마지막 return b는 정상적인 처리가 완료되면 해당창을 닫기위해 구분하는 변수로 false일시 해당창에서 정상적인 처리가 될때까지 실행가능
	 */
	public boolean FoodReg(String id,String fname,String fdeadline,String fprice,String clink,String region) {
		boolean b=false;
		System.out.println(BeginingScreen.conId);
//		String id=BeginingScreen.conId;
//		String region=BeginingScreen.conRegion;
		try {
  			if((fname.isEmpty()) == true || (fdeadline.isEmpty()) == true	// 텍스트필드에 비어있으면
					|| (fprice.isEmpty()) || (clink.isEmpty())) {			// 오류메시지 출력
  				JOptionPane.showMessageDialog(null, "입력값을 확인해주세요!");
  			}
  			else {
  			sql="insert into Food (id,fname,fdeadline,fprice,clink,region) values ('"+id+"','"+fname+"','"+fdeadline+"','"+fprice+"','"+clink+"','"+region+"')";
  			// 정상적인 입력을 받았으면 Food 테이블에 입력받은 값을 insert
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
	/**
	 * 미리 등록되어있는 Food 테이블에 데이터를 해당 사용자가 수정할수 있음
	 * 액션이 실행될때 id와 fname,fdeadline,fprice,clink를 전달 받아서 실행
	 * @param id 해당 사용자의 ID(이를 통해 누가 등록했는지 알수있다)
	 * @param fname 사용자가 음식을 등록할때 음식이름
	 * @param fdeadline	음식의 유통기한
	 * @param fprice	음식의 가격
	 * @param clink	해당 사용자와 소통할수있는 카카오톡 채팅링크
	 * @param region	해당 사용자 근처의 지역사람들만 접근가능하도록 사용자의 지역을 받음
	 * 텍스트필드가 비어있으면 오류메시지 출력
	 * 마지막 return b는 정상적인 처리가 완료되면 해당창을 닫기위해 구분하는 변수로 false일시 해당창에서 정상적인 처리가 될때까지 실행가능
	 */
	public boolean FoodUpd(String id,String fname,String fdeadline,String fprice,String clink) {
		boolean b=false;
		try {
			if((fname.isEmpty()) == true || (fdeadline.isEmpty()) == true	//텍스트필드가 비어있으면 오류메시지 출력
					|| (fprice.isEmpty()) || (clink.isEmpty())) {
				JOptionPane.showMessageDialog(null, "입력값을 확인해주세요!");
			}
			else {
			sql="update food set fname='"+fname+"',fdeadline='"+fdeadline+"',fprice='"+fprice+"',clink='"+clink+"'where id='"+id+"'";
			// 정상적인 입력을 받으면 food 테이블에 수정값을 업데이트
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
	/**
	 * 등록되어 있는 Food테이블에 있는 값을 텍스트필드에 불러옴
	 * 액션이 실행되면 전달받은 id로 Food 테이블 데이터베이스에 접근
	 * @param id 해당 사용자가 누구이고 전달받은 id로 Food에 접근하여 select함
	 * id를 통해 food에 등록된 데이터를 전달받아 각각
	 * name,deadline,price,clink를 저장하여 텍스트필드에 불러옴
	 * 마지막 return b는 정상적인 처리가 완료되면 해당창을 닫기위해 구분하는 변수로 false일시 해당창에서 정상적인 처리가 될때까지 실행가능
	 */
	public boolean RecentFood(String id) {
		boolean b=false;
//		
		try {

  			BeginingScreen.conId=id;
			sql="select *from food where id='"+id+"'";
			// 로그인된 아이디를 키로 food 테이블에서 검색
			rs=stmt.executeQuery(sql);
			if(rs.next()==true) {
				rfname=rs.getString(2);			// 검색된 테이블에서 name을 가져옴
				rfdeadline=rs.getString(3);		//검색된 테이블에서 deadline을 가져옴
				rfprice=rs.getString(4);		//검색된 테이블에서 price을 가져옴
				rclink=rs.getString(5);			//검색된 테이블에서 clink을 가져옴
				b=true;
			//rs.next();
  			//stmt.executeUpdate(sql);
			//JOptionPane.showMessageDialog(null, "등록성공!");
				
			}
			else {
				rfname="";				//등록된 상품을 찾을수 없으면 전부 빈값 입력
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
