/* Mareike Carolin Keller
 * mk4323
 * Player.java - Class file that provides properties about the player used in 
 * game.java. Updates winnings and helps build a hand in game class.
*/

import java.util.ArrayList;

public class Player {
	
	//instance variables	
	private ArrayList<Card> hand; //the player's cards
	private double bankroll;
    private double bet;

    //constructor
	public Player(){		
        hand = new ArrayList<Card>();
        bankroll = 100.0;
        bet = 0.0;
	}

    //add the card c to the player's hand
	public void addCard(Card c){
        hand.add(c);
	}

    //remove the card c from the player's hand
    //commented out because provided by template, but not used in current design
	//public void removeCard(Card c){
    //    hand.remove(c);
   // }
	
    
    public void bets(double amt){ //set bets
        bet = amt;//player makes a bet
        bankroll -= bet;        
    }

    //adjust bankroll if player wins
    public void winnings(double odds){
        bankroll += (odds*bet); //update bankroll based on bets
    }

    //return current balance of bankroll
    public double getBankroll(){
        return bankroll;
    }


    public ArrayList<Card> getHand(){ //gethand method
        return hand;
    }
    
    

}


