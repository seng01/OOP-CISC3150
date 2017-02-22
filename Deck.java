import java.security.SecureRandom;
import java.util.ArrayList;

//This class is for creating the deck, player's hand, and dealer's hand in an Arraylist
public class Deck {
	
	private ArrayList<CardVal> cards;

	private static final SecureRandom random = new SecureRandom();
	
	//Constructor, creates and arraylist
	public Deck(){
		this.cards = new ArrayList<CardVal>();
	
	}
	//Creates the deck of cards
	public void CreateDeck(){
		
		for(int suit_of_cards = 0; suit_of_cards < 4; suit_of_cards++){
			for(int value_of_card = 1; value_of_card <= 13; value_of_card++){
			
				cards.add(new CardVal(Suits.values()[suit_of_cards], value_of_card));
			}
		}
	}
	
	//Shuffles the deck using random numbers
	public void shuffle(){
		ArrayList<CardVal> TempDeck = new ArrayList<CardVal>();
		int deck_size = cards.size();
		
		for(int first = 0; first < deck_size; first++){
			
			//Generates a random int to help shuffle cards ((max - 1) + 1)
			int ran_gen = random.nextInt((cards.size() - 1) + 1);
			
				//Stores the shuffle deck into a temp and removes the card from original deck
				TempDeck.add(cards.get(ran_gen));
				cards.remove(ran_gen);
				
		}
		//Set the original card deck to Tempdeck
		cards = TempDeck;
	}
		
	//Removes a card from the deck
	public void RemoveCard(int i){
		cards.remove(i);
	}
	
	//Adds cards to the deck
	public void AddCard(CardVal add){
		cards.add(add);
	}
	
	//Returns the card value
	public CardVal CardValue(int card_value){
		return cards.get(card_value);
	}
	
	
	//Draws a top card
	public void DrawCards(Deck outstanding_card){
		//Adds the card
		cards.add(outstanding_card.CardValue(0));
		
		outstanding_card.RemoveCard(0);
	}
	//Moves cards back into deck
	public void MoveCardsToDeck(Deck player_cards){
		int Deck_Size = cards.size();
		
		for(int i = 0; i < Deck_Size; i++){
			player_cards.AddCard(CardValue(i));	
		}
		//Removes cards from deck
		for(int j = 0; j < Deck_Size; j++){
			this.RemoveCard(0);
		}
	}
	
	
	//Calculate the Card Values
	public int Values(){
		int Total_Values = 0;
		int Ace_Values = 0;
		
		for(CardVal aCard : cards){
			switch(aCard.getValue()){
			case 2 :
				Total_Values += 2;
				break;
			case 3 :
				Total_Values += 3;
				break;
			case 4 :
				Total_Values += 4;
				break;
			case 5 :
				Total_Values += 5;
				break;
			case 6 :
				Total_Values += 6;
				break;
			case 7 :
				Total_Values += 7;
				break;
			case 8 :
				Total_Values += 8;
				break;
			case 9 :
				Total_Values += 9;
				break;
			case 10 :
				Total_Values += 10;
				break;
			case 11 :
				Total_Values += 10;
				break;
			case 12 :
				Total_Values += 10;
				break;
			case 13 :
				Total_Values += 10;
				break;
			case 1 :
				Ace_Values += 1;
				break;			
			}//End of for
		}//End of switch

	
	//This is to find if the Aces is 11 or 1
	for(int i = 0; i < Ace_Values; i++){
		//If we have at least 1 ace and the value is less than 21 the ace will be 10 + the amount of Ace_Values
		if((Total_Values + 10 < 21) && Ace_Values <= 1){
			Total_Values += (10 + Ace_Values);
		
		//Any Aces that is after will add 1
		}else {
			Total_Values += 1;
		}
	}
		
	return Total_Values;
	}
	
	//Returns the Deck size
	public int DeckSize(){
		return cards.size();
	}
	
	//Prints the hand
	public void printDeck(){
		if(DeckSize() == 0){
			System.out.println("No deck");
			return;
		}else {
			System.out.println("--------------------------------");
			for(int i = 0; i < DeckSize(); i++){	
				
				System.out.println(" _________");
				System.out.println("|"+ CardValue(i) + " |");
				System.out.println("|_________|");
			}
		System.out.println("\nHand Total: " + Values());
		System.out.println("--------------------------------");
		}
	}
}
