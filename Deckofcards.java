import java.util.InputMismatchException;
import java.util.Scanner;

public class Deckofcards {
	public static void main(String[] args){
					
		System.out.println("BlackJack 21");

		//Create a New Deck
		Deck card = new Deck();
		
		card.CreateDeck();
		
		//Shuffle
		card.shuffle();
		
		//Player's Hand
		Deck player_cards = new Deck();
	
		//Player's money
		double player_money = 100.00;
		//Player can place bet
		double bet = 0;
		
		//Dealer's hand
		Deck dealer_cards = new Deck();
		
		//Reads in the user input
		Scanner scan = new Scanner(System.in);
		Scanner user_response = new Scanner(System.in);
		
		//if player wants to play again
		boolean play_again = true;
		
	//Continues if the player has the available amount and if the player wants to play again
	while(player_money > 0 && play_again){
		//Used to see if the round ended
		boolean End_Round = false;
		//Check the if the user entered a correct bet
		boolean valid = true;
		
		System.out.println("===================================");
		System.out.println("New Hand");
	
		
		//Used to loop if the user enters a string, characters, or invalid amount for the bet request
		while(valid){
			try{
				System.out.println("Cash Available: $" + player_money);
				
				System.out.println("How much do you want to Bet?");
				//user enters the amount
				bet = scan.nextDouble();
				
				//if user enters a number lower than 0 or more than the Available amount
				while(bet <= 0 || bet > player_money){
					System.out.println("Invalid Entry, Please enter Correct Amount ");
					System.out.println("Available Cash: $" + player_money);
					//reads user response
					bet = scan.nextDouble();
					//end loop if valid amount is in range
					valid = false;
	
				}
				//if a string was entered first then a valid amount it will end the loop
				valid = false;
			//if the user enters characters or a string in the bet request goes into the catch block
			}catch(InputMismatchException ex){
				//if the user enters characters or string it will print error and await for next entry
				while(!scan.hasNextDouble()){
					System.out.println("Invalid Entry, Please enter Correct Amount");
					scan.next();
				}
				
				bet = scan.nextDouble();
		
				if(bet <= 0 || bet > player_money){
					//restart loop if bet is not in range
					System.out.println("Invalid Amount " );
					valid = true;
				}else
					valid = false;

			}//catch
		}//while
		
		//Player's Hand
		player_cards.DrawCards(card);
		player_cards.DrawCards(card);
		
		//Dealer's Hand
		dealer_cards.DrawCards(card);
		dealer_cards.DrawCards(card);
		
		while(true){
			//Prints Player's hand
			System.out.println("Player's Hand:");

			player_cards.printDeck();
		
			//Prints Dealer's hand
			System.out.println("--------------------------------");
			System.out.println("\nDealer's Hand:");
	    	System.out.println(" _________");
	    	System.out.println("|"+ dealer_cards.CardValue(0) + " |");
	    	System.out.println("|_________|");
		
	    	System.out.println(" ________");
	    	System.out.println("|?       |");
	    	System.out.println("|    ?   |");
	    	System.out.println("|  ?  ?  |");
	    	System.out.println("|_______?|");
	    	
	    	System.out.println("--------------------------------");

		    //If the player has blackjack and the dealers hand is less than blackjack
		    if((player_cards.Values() == 21 && player_cards.DeckSize() == 2) && dealer_cards.Values() < player_cards.Values() && (End_Round == false)){
		    	System.out.println("Player has BLACKJACK!!");
		    	//if the dealer has an ACE or a card with a value of 10
		    	if(dealer_cards.CardValue(0).equals(1) || dealer_cards.CardValue(0).equals(10)){
		    		System.out.println("Dealer Reveals Hand:");
		    		dealer_cards.printDeck();
		    		//If the dealer also has BlackJack it is consider a push
		    		if((dealer_cards.Values() == 21 && dealer_cards.DeckSize() == 2) && (End_Round == false)){
		    			System.out.println("Dealer has BLACKJACK!!...PUSH!!!!");
		    			End_Round = true;
		    		}
		    	}else{
		    		player_money += (bet * 1.5);
	    			End_Round = true;
	    			break;
		    	}
		    }
		    //Dealer wins if hand has blackjack
		    if((dealer_cards.Values() == 21 && dealer_cards.DeckSize() == 2) && (End_Round == false)){
				System.out.println("Dealer has BLACKJACK!!");
				player_money -= bet;
				End_Round = true;
				break;
				
			}
	    	
		    //Print Stand or Hit
	    	System.out.println("\nWould you like to Stand or Hit? Enter (s) or (h)");
	    	
	    	//User input if hit or stand
	    	String input = new String(user_response.nextLine()).toLowerCase();
		    
	    	//Checks to see if the input is correct
	    	if(input.length() > 1 && (!input.toLowerCase().equals("s") || (!input.equals("h")))){
	    		System.out.print("\nInvaild response!!\n");
	    	}
	    	
	    	//If the value is read in is correct
	    	if(input.equals("s") || input.equals("h")){
	    		if(input.equals("h")){
	    			System.out.println("Player's Hit: ");
	    		
	    			player_cards.DrawCards(card);
	    			
	    			//Checks if the player's total value is over 21 automatically loses
	    			if(player_cards.Values() > 21){
	    				System.out.println("Busted!! Total Value: " + player_cards.Values());
	    				player_cards.printDeck();
	    				player_money -= bet;
	    				End_Round = true;
	    				break;
	    			}
	    		//if the player decides to stand
	    		}else{
		    		break;
		    	}
	    	
	    	}
		}//End of while
		System.out.println("--------------------------------");
		System.out.println("Dealer Reveals Cards: ");
	    dealer_cards.printDeck();
	    System.out.println("--------------------------------");
	    

	    //Dealer must hit if the value is under 17
	    while(dealer_cards.Values() < 17 && (End_Round == false)){
	    	System.out.println("Dealer's Hit: ");
	    	dealer_cards.DrawCards(card);
	    	dealer_cards.printDeck();
	    }
	    	//Dealer has over 21, dealer loses
    		if(dealer_cards.Values() > 21 && (End_Round == false)){
    			System.out.println("Dealer Busted !!! YOU WIN!!!");
    			player_money += bet;
    			End_Round = true;
    			
    		//Dealer Total_Value is greater than the Player's Total Value
    		}else if(dealer_cards.Values() > player_cards.Values() && (End_Round == false)){
	    		System.out.println("Dealer Wins");
	    		player_money -= bet;
	    		End_Round = true;
	    	
	    	//Dealer and Player's Hand are equal
	    	}else if(dealer_cards.Values() == player_cards.Values() && (End_Round == false)){
    			System.out.println("PUSH!!!!");
    			End_Round = true;
    			
    		//Player's Hand is greater than the Dealer's Hand
    		}else if(player_cards.Values() > dealer_cards.Values() && (End_Round == false)){
    			System.out.println("Player WINS!!!!");
    			player_money += bet;
    			End_Round = true;
    		}
    		//if the player loses all the money
    		if(player_money == 0){
    			System.out.println("Lost all your money, Thank you for playing");
    			System.exit(1);
    		}
    		//Prints the hand values
    		System.out.println("Player has: " + player_cards.Values());
    		System.out.println("Dealer has: " + dealer_cards.Values());
    		
    		//Putting cards back into deck
    		player_cards.MoveCardsToDeck(card);
    		dealer_cards.MoveCardsToDeck(card);
    		
    		System.out.println("Hand Ended!");
    		System.out.println("Player's Winning: $" + player_money);
    		
    		
    		System.out.println("\nPlay Again? y or n");
	    	String play = new String(user_response.nextLine()).toLowerCase();
	    	
	    	
	    	if(play.equals("y")){
    			play_again = true;
    		//Game Ends
	    	}else if(play.equals("n")){
	    		System.out.println("Thank You for Playing BlackJack");
	    		System.out.println("Total Winnings: $" + player_money);
	    		play_again = false;
	    	}else{
	    		while (!play.equals("y") && (!play.equals("n"))){
	    			System.out.print("Invaild response!!.\nWould you like to play again y or n?");
	    			play = (user_response.nextLine()).toLowerCase();
	    	
	    		}
	    		
	    	}
		}//end of while
		scan.close();
		user_response.close();
	}

}
