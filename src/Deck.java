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
	
	public Card pick (int index) { //������ ��ġ(index)�� �ִ� ī�带 �����Ѵ�.
		  return c[index%CardInfo.CARD_NUM];
		 }

	public Card pick() { // Deck���� ī�� �ϳ��� �����Ѵ�.
		while(true){
			int index = (int)(Math.random() * CardInfo.CARD_NUM);
			Card tempCard = pick(index);
			if(tempCard.isShapeOut())continue;
			tempCard.setShapeOut();
			return pick(index);
		}
	}
	
	public void shuffle() { //ī�带 ���´�.
		for(int n=0; n < 1000; n++) { // 1000���� ���´�.
			int i = (int)(Math.random() * CardInfo.CARD_NUM); // ī������߿� 1�� ���� ����
			Card temp = c[0]; // temp�� c[0]��°�� ���� ���
			c[0] = c[i]; // ���� i��°�� ���� c[0]�� ���
			c[i] = temp; // temp(���� c[0]�� ��)�� ���� c[i]��°�� ��´�.
		}
	} // void shuffle
}
