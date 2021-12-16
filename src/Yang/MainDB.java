package Yang;

public class MainDB {
	public static void main(String[] args) {
		BeginingScreen start=new BeginingScreen();		//메인문 실행 (BeginingScreen 을 실행시켜 화면을 보여준다)
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