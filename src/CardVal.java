//Creates the Cards.  Assigns the Card it Suit and its value
public class CardVal {

	//The suit of the card
	private Suits suit;
	
	//The card value
	private int card_value;
	
	//Constructor 
	public CardVal(Suits asuit, int avalue){
		this.suit = asuit;
		//Checks to make sure the value is within range
		if(avalue >= 1 && avalue <= 13){
			this.card_value = avalue;
			
		}else{
			System.out.println("Not a valid card number between 1 and 13");
			System.exit(1);
		}
	}
	//Returns the Value of the card
	public int getValue(){
		return card_value;
	
	}

	@Override
	public String toString(){
		String numString = null;
	switch(card_value){
		case 2:
			numString = "2";
			break;
		case 3:			
			numString = "3";
			break;
		case 4:			
			numString = "4";
			break;
		case 5:
			numString = "5";
			break;
		case 6:
			numString = "6";
			break;
		case 7:
			numString = "7";
			break;
		case 8:
			numString = "8";
			break;
		case 9:
			numString = "9";
			break;
		case 10:
			numString = "10";
			break;
		case 11:
			numString = "Jack";
			break;
		case 12:
			numString = "Queen";
			break;
		case 13:
			numString = "King";
			break;
		case 1:
			numString = "Ace";
			break;
		
		}
		return numString + "\t  | \n| " + suit.toString() + "  |\n|      " + numString;
	}
}
