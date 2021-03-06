
public class Card {
	
	int card_kind = -1;
	int card_index = -1;
	boolean isShapeOut = false;
	
	public Card(int card_kind, int card_index){
		this.card_kind = card_kind;
		this.card_index = card_index;
	}
	
	public int getIndex(){
		return card_index;
	}
	
	public int getShape(){
		return card_kind;
	}
	
	public boolean isShapeOut(){
		return isShapeOut;
	}
	
	public void setShapeOut(){
		isShapeOut = true;
	}

	@Override
	public String toString() {
		String kind="";
		  String number="";

		  switch (this.card_kind) {
		   case 3:
		    kind = "SPADE";
		   break;
		   case 1:
		    kind = "DIAMOND";
		   break;
		   case 0:
		    kind = "HEART";
		   break;
		   case 2:
		    kind = "CLOVER";
		   break;
		   default:
		  } //switch

		  switch(this.card_index) {
		   case 13 :
		    number = "K" ;
		   break;
		   case 12 :
		    number = "Q" ;
		   break;
		   case 11 :
		    number = "J" ;
		   break;
		   case 1 :
			number = "A";
		   default :
		    number = this.card_index + "";
		  } //switch
		  return "kind : " + kind + " number : " + number;
	}
	
	
}
