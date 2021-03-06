import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameBoard {
	
	private Player m_player = null;
	private Dealer m_dealer = null;
	
	public GameBoard(){
		m_player = new Player();
		m_dealer = new Dealer();
		init();
	}
	
	public void init(){
		m_player.init();
		m_dealer.init();
	}
	
	public void update(){
		
	}
	
	public void start(){
		Scanner in = new Scanner(System.in);
		while(!checkBankrupt()){
			info();
			if(m_dealer.money < 0){
				System.out.println("플레이어가 승리하였습니다.");
				System.exit(0);
			}
			int betting = 0;
			System.out.print("베팅액을 정해주세요 :");
			try{
					int inInt = in.nextInt();
					betting = inInt;	
			}catch(Exception e){
				System.out.println("정수값만 입력이 가능합니다.");
				in.next();
			}
			System.out.println("게임을 진행합니다. 베팅액 : " + betting);
			firstPick();
			
			matchBust(betting);
			
			System.out.println("플레이더가 소유한 카드");
			System.out.println(m_player.list);
			
			boolean haveA = false;
			int haveAI = -1;
			for(int i = 0 ; i < m_dealer.list.size() ; i++){
				if(m_dealer.list.get(i).getIndex()==1){
					haveA = true;
				}
			}
			
			System.out.println("딜러에게서 보이는 카드.");
			if(haveA){
				System.out.println(m_dealer.list.get(haveAI));
				int tempint = 0;
					System.out.println("1. Insurance , 2.Even Money");
					try{
						tempint  = in.nextInt();
						if(tempint != 1 && tempint != 2)continue;
					}catch(Exception e){
						System.out.println("정수만 입력이 가능합니다.");
						in.next();
					}
				if(tempint == 1){
					betting = (int)Math.round(betting * 1.5);
					checkMatch(betting);
					continue;
				}else if(tempint == 2){
					System.out.println("even money");
					m_player.plus(betting);
					m_dealer.minus(betting);
					continue;
				}
								
			}else{
				System.out.println(m_dealer.list.get(0));
			}
			
			System.out.println("1. hit, 2. stay, 3.surrender, 4. double down");
			try{
				int sel = in.nextInt();
				switch(sel){
				case 1:
					pick();
					break;
				case 2:
					break;
				case 3:
					System.out.println("surrender");
					m_player.minus(betting/2);
					m_dealer.plus(betting/2);
					continue;
				case 4:
					betting += betting*2;
					break;
				default:
					System.out.println("잘못된입력입니다. 게임을 다시 진행합니다.");
				continue;
				}
			}catch(Exception e){
				System.out.println("정수값많 입력해주세요.");
				in.next();
			}
			matchBust(betting);
			checkMatch(betting);
		}
		System.out.println("게임이 끝났습니다.");
	}
	
	public void firstPick(){
		//카드 두장씩 분배
		for(int i = 0; i < 2 ; i++){
			pick();
		}
	}
	
	public void pick(){
		System.out.println("카드를 한장 분배합니다.");
		m_player.list.add(Deck.getInstance().pick());
		m_dealer.list.add(Deck.getInstance().pick());
	}
	
	public void matchBust(int betting){
		infoView(m_player);
		if(checkBust(m_player.list)){
			m_player.minus(betting);
			m_dealer.plus(betting);
			System.out.println("Player Bust.");
		}
		
		if(checkBust(m_dealer.list)){
			m_player.plus(betting);
			m_dealer.minus(betting);
			System.out.println("Dealer Bust.");
		}
	}
	
	public void checkMatch(int betting){
		
		if(getScore(m_player.list)==getScore(m_dealer.list)){
			System.out.println("Push.");
		}
		
		if(checkBlackJack(m_player.list)&&!checkBlackJack(m_dealer.list)){
			m_player.plus(betting);
			m_dealer.minus(betting);
			System.out.println("Player BlckJack.");
		}
		
		if(!checkBlackJack(m_player.list)&&checkBlackJack(m_dealer.list)){
			m_dealer.plus(betting);
			m_player.minus(betting);
			System.out.println("dealer BlckJack.");
		}
		
		if(getScore(m_player.list)>getScore(m_dealer.list)){
			m_player.plus(betting);
			m_dealer.minus(betting);
			System.out.println("Player Win.");
		}
		
		if(getScore(m_player.list)<getScore(m_dealer.list)){
			m_player.minus(betting);
			m_dealer.plus(betting);
			System.out.println("Dealer Win.");
		}
	}
	
	public int getScore(ArrayList<Card> list){
		int tempScore = 0;
		int countNumA = 0;
		for(int i = 0 ; i < list.size() ; i++){
			if(list.get(i).getIndex()==CardInfo.NUM_A){
				countNumA += 1;
			}else{
				tempScore += list.get(i).getIndex();
			}
		}
		if(countNumA > 0){
			if(countNumA == 1){
				if((tempScore + 11) > 21){
					tempScore += 1;
				}else{
					tempScore += 11;
				}
			}else{
				tempScore += 1;
			}
		}
		return tempScore;
	}
	
	public boolean checkBust(ArrayList<Card> list){
		int tempScore = 0;
		for(int i = 0 ; i < list.size() ; i++){
			tempScore +=  list.get(i).getIndex();
		}
		System.out.println("check bust " + tempScore);
		if(tempScore > 21){
			return true;
		}else{
			return false;
		}
		
	}
	
	public boolean checkBlackJack(ArrayList<Card> list){
		if(list.size()==2){
			int tempScore = 0;
			for(int i = 0 ; i < list.size() ; i++){
				if(list.get(i).getIndex()==1){
					tempScore += 11;
				}else{
					tempScore += list.get(i).getIndex();
				}
			}
			if(tempScore==21){
				return true;
			}else{
				return false;
			}
		}else{
			int tempScore = 0;
			int tempScore2 = 0;
			for(int i = 0 ; i < list.size() ; i ++){
				if(list.get(i).getIndex()==1){
					tempScore += 11;
					tempScore2 += 1;
				}else{
					tempScore += list.get(i).getIndex();
				}
			}
			if(tempScore==21||tempScore2==21){
				return true;
			}else{
				return false;
			}
		}
	}
	
	public boolean checkBankrupt(){
		if(m_player.money<0){
			return true;
		}else{
			return false;
		}
	}
	
	public void info(){
		System.out.println(m_dealer);
		System.out.println(m_player);
	}
	
	public void infoView(Object o){
		if(o instanceof Player){
			Player p = (Player) o;
			System.out.println(p.list);
		}else if (o instanceof Dealer){
			Dealer d = (Dealer) o;
			System.out.println(d.list);
		}
	}

}
