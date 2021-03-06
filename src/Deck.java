public class Deck {
	
	private static Deck deck  = null;
	Card c[] = new Card[CardInfo.CARD_NUM];

	private Deck(){
		init();
	}
	
	public static Deck getInstance(){
		if(deck==null)
			setInstance();
		return deck;
	}
	
	private static synchronized void setInstance(){
		if(deck==null)
			deck = new Deck();
	}
	
	public void init(){
		
		int i = 0;
		for(int k=0; k < CardInfo.KIND_MAX; k++){
			for(int n = 1; n < CardInfo.NUM_MAX + 1; n++){
				c[i++] = new Card(k, n);
			}
		}
		shuffle();
	}
	
	public void update(){
		
	}
	
	public Card pick (int index) { //지정된 위치(index)에 있는 카드를 선택한다.
		  return c[index%CardInfo.CARD_NUM];
		 }

	public Card pick() { // Deck에서 카드 하나를 선택한다.
		while(true){
			int index = (int)(Math.random() * CardInfo.CARD_NUM);
			Card tempCard = pick(index);
			if(tempCard.isShapeOut())continue;
			tempCard.setShapeOut();
			return pick(index);
		}
	}
	
	public void shuffle() { //카드를 섞는다.
		for(int n=0; n < 1000; n++) { // 1000번을 섞는다.
			int i = (int)(Math.random() * CardInfo.CARD_NUM); // 카드장수중에 1개 랜덤 선택
			Card temp = c[0]; // temp에 c[0]번째의 값을 담고
			c[0] = c[i]; // 난수 i번째의 값을 c[0]에 담고
			c[i] = temp; // temp(원래 c[0]의 값)의 값을 c[i]번째에 담는다.
		}
	} // void shuffle
}
