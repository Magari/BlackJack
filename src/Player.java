import java.util.ArrayList;

public class Player {
	
	public static int money = 10000;
	public static String name = "user";
	
	ArrayList<Card> list = null;
	
	public void init(){
		list = new ArrayList<Card>();
	}
	
	public void minus(int m){
		money -= m;
	}
	
	public void plus(int p){
		money += p;
	}
	
	@Override
	public String toString(){
		return "player Name : " + name + " -  Have Money : " + money;
	}

}
