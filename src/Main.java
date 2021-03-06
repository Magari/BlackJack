import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	private Card tempCard= null;
	
	public Main(){
		init();
		start();
	}
	
	public void init(){
		
	}
	
	public void start(){
		GameBoard board = new GameBoard();
		board.start();
	}
	
	public static void main(String[] args){
		new Main();
	}

}
