package Yang;

public class MainDB {
	public static void main(String[] args) {
		BeginingScreen start=new BeginingScreen();		//���ι� ���� (BeginingScreen �� ������� ȭ���� �����ش�)
	}
	public static int largest(int [] list) {
		int i, max=Integer.MIN_VALUE;
		for(i=0;i<list.length;i++) {
			if(list[i]>max) {
				max=list[i];
			}
		}
		return max;
	}
}