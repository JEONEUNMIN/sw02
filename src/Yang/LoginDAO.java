package Yang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
/**
 * ���� ������ �Ҷ� �����ͺ��̽��� �����Ͽ� �α����� ���ִ� Ŭ����
 * Login�� Signup���� �̷�����ִ�.
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
			System.out.println("�α��� ����");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("DB����̹� �ε� ����:" + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DB���� ����:" + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unknown error");
			e.printStackTrace();
		}
	}
	public void dbClose() { //DB�ڿ� ����ȭ�� ���� db free�۾�
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
	 * �����ͺ��̽� ���̺� Users�� ����ִ� �����͸� select���� ���� ����.
	 * �׼��� ����ɶ� id, passwd�� ���� �޾Ƽ� ����
	 * @param id �ش� ������� ID �����ͺ��̽��� ������ ���޹��� passwd�� �ش� id�� ���� ���ڵ�� �α��� ����
	 * @param passwd �ش� ID�� �н������ �ش� ID���ڵ忡 ���޹��� passwd�� ���ƾ��Ѵ�.
	 * �������� ���� ID�� �����޽��� ���
	 * �ش� id ���ڵ忡 passwd�� ���޹��� passwd�� �ٸ��� �����޽��� ���
	 * id�� passwd�� ��������� �����޽��� ���
	 * ������ return b�� �������� ó���� �Ϸ�Ǹ� �ش�â�� �ݱ����� �����ϴ� ������ false�Ͻ� �ش�â���� �������� ó���� �ɶ����� ���డ��
	 */
	public boolean Login(String id,String passwd) {
		boolean b=false;
		try {
			if(id.equals("developer")) {
				JOptionPane.showMessageDialog(null, "������ ������ �״� ������");
			}
			sql="select *from users where id='"+ id+"'";
			rs=stmt.executeQuery(sql);
			if(rs.next()!=true||id.isEmpty()==true) {		//id �ؽ�Ʈ�ʵ忡�� ���� ������ sql���� �־� idȮ�� �Ǵ� ����ִ��� Ȯ��
				JOptionPane.showMessageDialog(null, "ID�� Ȯ�����ּ���!");
			}
			else if((id.isEmpty()) == true || (passwd.isEmpty()) == true) {	// ����ִ� �� �ν��� ���� ���
				JOptionPane.showMessageDialog(null, "�Է°��� Ȯ�����ּ���!");
			}
			else {
			sql="select *from users where id='"+id+"'and passwd='"+passwd+"'";
			//sql="select * from (select * from users where id='" + id + "')";
			// ���̵�� ��й�ȣ�� �ΰ��� �´ٸ� �������� �α���
			System.out.println(sql);
			rs=stmt.executeQuery(sql);
				if(rs.next()==true) {
					JOptionPane.showMessageDialog(null, "�α��� ����");
					BeginingScreen.conId=id;
					sql="select region from users where id='"+id+"'";
					prs=stmt.executeQuery(sql);
					prs.next();
					BeginingScreen.conRegion=prs.getString(1);
					// �α��� ������ �α��λ��¸� Ȯ���ϱ����� ���̵�� ������ ���������� �����Ͽ� �� �ν�
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
							//String conType="������";
							System.out.println(BeginingScreen.conRegion);
							System.out.println(BeginingScreen.conId);
							b=true;
							MainScreen msc = new MainScreen();
						}
				}
				else {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� Ȯ�����ּ���!");
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
	 * �����ͺ��̽� ���̺� Users�� �����͸� �߰��Ѵ�.
	 * �׼��� ����ɶ� name, id, passwd, region�� ���� �޾Ƽ� ����
	 * @param id ����ڰ� ���ϴ� ID�� ���������
	 * @param passwd id�� ���� ������ �̷���� �ִ� passwd
	 * @param name ������� �̸�
	 * @param region ������� ����
	 * �̹� users���̺� �Է��� id�� �ִٸ� �ߺ��Ǿ� �����޽��� ���(id�� �ߺ��Ǿ� �߰��� �Ұ����ϴ�)
	 * ��� �ؽ�Ʈ�ʵ尡 ����ְų� region �ؽ�Ʈ�ʵ尡 ���÷� �Էµ� ex)������ �Ͻ� �����޽��� ���
	 * ���̰� �ߺ��Ǿ� ���� �ʰ� �Է°��� �����̶�� users���̺� �߰�����
	 * ������ return b�� �������� ó���� �Ϸ�Ǹ� �ش�â�� �ݱ����� �����ϴ� ������ false�Ͻ� �ش�â���� �������� ó���� �ɶ����� ���డ��
	 */
	public boolean SignUp(String name,String id,String passwd,String region) {
		boolean b=false;
		try {
  			sql="select *from users where id='"+ id +"'";
  			// �Է¹��� ���̵�� �ߺ��� �˻�
  			rs=stmt.executeQuery(sql);
  			if(rs.next()==true) {
  				JOptionPane.showMessageDialog(null, "ID�� �ߺ��Դϴ�!");
  			}
  			else if((id.isEmpty()) == true || (passwd.isEmpty()) == true
					|| (name.isEmpty()) || (region.equals("ex)������")) || (region.isEmpty())) {
  				JOptionPane.showMessageDialog(null, "�Է°��� Ȯ�����ּ���!");
  			}
  			else {
  			sql="insert into users values ('"+name+"','"+id+"','"+passwd+"','"+region+"')";
  			// �ߺ��� ���� ���� ����ִ� ���� ���ٸ� ���޹��� �̸� ���̵� ��й�ȣ ������ ����� users ���̺� �߰�
  			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "���Լ���!");
			b=true;
  			}
		} catch (Exception e2) {
			System.out.println("�����߻�");
			e2.printStackTrace();
		} finally {
			dbClose();
		}
		return b;
	}
}
