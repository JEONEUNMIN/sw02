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
 * FoodDAO�� �����ͺ��̽� Food���̺� ���õ� ��ũ������ �׼��� ���������� �����ͺ��̽��� �����ϴ� Ŭ�����̴�
 * �� Ŭ������ ���� FoodReg,FoodUpd,RecentFood�� �̷�����ִ�.
 * @author 20174332*/

public class FoodDAO {
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	Statement stmt = null;
	String sql=null;
	String user = "swyang";		//db ���ӽ� �ʿ��� user
	String pw = "1234";			//db ���ӽ� �ʿ��� user�� ��й�ȣ
	String url = "jdbc:oracle:thin:@localhost:1521:xe";		//db���� url
	static String rfname;		
	static String rfdeadline;
	static String rfprice;
	static String rclink;
	static Vector data = null;
	/**
	 * �����ͺ��̽��� ����
	 */
	public  FoodDAO(){
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
	 * �����ͺ��̽� ���̺� Food�� �����͸� �߰��Ѵ�.
	 * �׼��� ����ɶ� id, fname, fdeadline, fprice, clink, region�� ���� �޾Ƽ� ����
	 * @param id �ش� ������� ID(�̸� ���� ���� ����ߴ��� �˼��ִ�)
	 * @param fname ����ڰ� ������ ����Ҷ� �����̸�
	 * @param fdeadline	������ �������
	 * @param fprice	������ ����
	 * @param clink	�ش� ����ڿ� �����Ҽ��ִ� īī���� ä�ø�ũ
	 * @param region	�ش� ����� ��ó�� ��������鸸 ���ٰ����ϵ��� ������� ������ ����
	 * �ؽ�Ʈ�ʵ尡 ��������� �����޽����� �����
	 * ������ return b�� �������� ó���� �Ϸ�Ǹ� �ش�â�� �ݱ����� �����ϴ� ������ false�Ͻ� �ش�â���� �������� ó���� �ɶ����� ���డ��
	 */
	public boolean FoodReg(String id,String fname,String fdeadline,String fprice,String clink,String region) {
		boolean b=false;
		System.out.println(BeginingScreen.conId);
//		String id=BeginingScreen.conId;
//		String region=BeginingScreen.conRegion;
		try {
  			if((fname.isEmpty()) == true || (fdeadline.isEmpty()) == true	// �ؽ�Ʈ�ʵ忡 ���������
					|| (fprice.isEmpty()) || (clink.isEmpty())) {			// �����޽��� ���
  				JOptionPane.showMessageDialog(null, "�Է°��� Ȯ�����ּ���!");
  			}
  			else {
  			sql="insert into Food (id,fname,fdeadline,fprice,clink,region) values ('"+id+"','"+fname+"','"+fdeadline+"','"+fprice+"','"+clink+"','"+region+"')";
  			// �������� �Է��� �޾����� Food ���̺� �Է¹��� ���� insert
  			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "��ϼ���!");
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
	/**
	 * �̸� ��ϵǾ��ִ� Food ���̺� �����͸� �ش� ����ڰ� �����Ҽ� ����
	 * �׼��� ����ɶ� id�� fname,fdeadline,fprice,clink�� ���� �޾Ƽ� ����
	 * @param id �ش� ������� ID(�̸� ���� ���� ����ߴ��� �˼��ִ�)
	 * @param fname ����ڰ� ������ ����Ҷ� �����̸�
	 * @param fdeadline	������ �������
	 * @param fprice	������ ����
	 * @param clink	�ش� ����ڿ� �����Ҽ��ִ� īī���� ä�ø�ũ
	 * @param region	�ش� ����� ��ó�� ��������鸸 ���ٰ����ϵ��� ������� ������ ����
	 * �ؽ�Ʈ�ʵ尡 ��������� �����޽��� ���
	 * ������ return b�� �������� ó���� �Ϸ�Ǹ� �ش�â�� �ݱ����� �����ϴ� ������ false�Ͻ� �ش�â���� �������� ó���� �ɶ����� ���డ��
	 */
	public boolean FoodUpd(String id,String fname,String fdeadline,String fprice,String clink) {
		boolean b=false;
		try {
			if((fname.isEmpty()) == true || (fdeadline.isEmpty()) == true	//�ؽ�Ʈ�ʵ尡 ��������� �����޽��� ���
					|| (fprice.isEmpty()) || (clink.isEmpty())) {
				JOptionPane.showMessageDialog(null, "�Է°��� Ȯ�����ּ���!");
			}
			else {
			sql="update food set fname='"+fname+"',fdeadline='"+fdeadline+"',fprice='"+fprice+"',clink='"+clink+"'where id='"+id+"'";
			// �������� �Է��� ������ food ���̺� �������� ������Ʈ
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "��������!");
			b=true;
			}
		}catch (Exception e2) {
			System.out.println("�����߻�");
			e2.printStackTrace();
		} finally {
			dbClose();
		}
		return b;
	}
	/**
	 * ��ϵǾ� �ִ� Food���̺� �ִ� ���� �ؽ�Ʈ�ʵ忡 �ҷ���
	 * �׼��� ����Ǹ� ���޹��� id�� Food ���̺� �����ͺ��̽��� ����
	 * @param id �ش� ����ڰ� �����̰� ���޹��� id�� Food�� �����Ͽ� select��
	 * id�� ���� food�� ��ϵ� �����͸� ���޹޾� ����
	 * name,deadline,price,clink�� �����Ͽ� �ؽ�Ʈ�ʵ忡 �ҷ���
	 * ������ return b�� �������� ó���� �Ϸ�Ǹ� �ش�â�� �ݱ����� �����ϴ� ������ false�Ͻ� �ش�â���� �������� ó���� �ɶ����� ���డ��
	 */
	public boolean RecentFood(String id) {
		boolean b=false;
//		
		try {

  			BeginingScreen.conId=id;
			sql="select *from food where id='"+id+"'";
			// �α��ε� ���̵� Ű�� food ���̺��� �˻�
			rs=stmt.executeQuery(sql);
			if(rs.next()==true) {
				rfname=rs.getString(2);			// �˻��� ���̺��� name�� ������
				rfdeadline=rs.getString(3);		//�˻��� ���̺��� deadline�� ������
				rfprice=rs.getString(4);		//�˻��� ���̺��� price�� ������
				rclink=rs.getString(5);			//�˻��� ���̺��� clink�� ������
				b=true;
			//rs.next();
  			//stmt.executeUpdate(sql);
			//JOptionPane.showMessageDialog(null, "��ϼ���!");
				
			}
			else {
				rfname="";				//��ϵ� ��ǰ�� ã���� ������ ���� �� �Է�
				rfdeadline="";
				rfprice="";
				rclink="";
				//JOptionPane.showMessageDialog(null, "��ϵ� ��ǰ�̾����ϴ�!");
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
