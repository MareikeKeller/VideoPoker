/* Mareike Carolin Keller
 * mk4323
 * Deck.java - Class file that accounts for basic properties of a card deck.
 * Deals and shuffles cards in Game class.
*/

import java.util.Random;

public class Deck {
	
    //instance variables
	private Card[] cards;
	private int top; //the index of the top of the deck
    private int deckCounter; //for filling deck
	
    //constructor
	public Deck(){ 
        
        cards = new Card[52]; 
        top = 0;
        deckCounter = 0;
        
        for(int suit = 1; suit < 5; suit++){ //instantiate 52 cards, add to array
           for(int rank = 1; rank < 14; rank++){
            
               cards[deckCounter] = new Card(suit, rank);
               deckCounter++;    
           } 
        }     
	}
	
    
    //shuffles the deck
	public void shuffle(){
        
        Random random = new Random();
        int randCard;
        for(int i = 0; i < cards.length; i++){ //shuffle each card in deck 
            Card temp = cards[i];
            randCard = random.nextInt(cards.length); //rand. number between 0-52
            
            cards[i] = cards[randCard]; //switch i and random card
            cards[randCard] = temp;                 
        }  
        top = 0; //reset top to 0         
    } 

    
    public Card deal(){ //method that returns card obj.
	   //deal the top card in the deck
       if(top == cards.length){ //if 52 is top card
           shuffle();
           top = 0; //reset top to 0         
       } 
       top++; //we're moving in the array to search the "top card"
       return cards[top-1]; 
         //returns top card
    }
    
} //end of class