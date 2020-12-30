/* Mareike Carolin Keller
 * mk4323
 * Card.java - Class file that provides all properties of a card, such as suit,
 * rank, ordering (compareTo) and a stringTo method for proper printing. Used 
 * for poker game in game class.
*/

public class Card implements Comparable<Card>{
	
    //instance variables
	private int suit; //use integers 1-4 to encode the suit
	private int rank; //use integers 1-13 to encode the rank
    
    //constructor
	public Card(int s, int r){       
		//make a card with suit s and value v
        suit = s; //suit
        rank = r; //rank
	}
	
    //compareTo method to sort later
	public int compareTo(Card c){

        if(this.rank == c.rank){ //is rank the same next to it
            if(this.suit < c.suit){ //if yes, compare suit to sort by it
                return -1;
            }
            if(this.suit == c.suit){
                return 0;
            }
            if(this.suit > c.suit){
                return 1;
            }
        } 
        else if(this.rank < c.rank){ //rank smaller than next to it
            return -1;
        }
        
        return 1; //rank bigger than next to it (if(this.rank > c.rank))             
	}
	
    
    //method to easily print a Card object
	public String toString(){

        String suitString = "";
        String cardName = "";
        if(suit == 1){ //names for suits
            suitString = "clubs ";            
        }
        else if(suit == 2){
            suitString = "diamonds ";
        }
        else if(suit == 3){
            suitString = "hearts ";
        }
        else if(suit == 4){
            suitString = "spades ";
        }
        
        if(rank > 1 && rank < 11){ //full card name with rank
            cardName = rank + " of " + suitString; //for 2-10
        }
        else if(rank == 1){ //Ace
            cardName = "Ace of " + suitString;
        }
        else if(rank == 11){ //Jack
            cardName = "Jack of " + suitString;
        }
        else if(rank == 12){ //Queen
            cardName = "Queen of " + suitString;
        }
        else if(rank == 13){ //King
            cardName = "King of " + suitString;
        }
        return cardName;
	}
    

    //accessor method for rank
    public int getRank(){
        return rank;
    }
    
    //accessor method for suit
    public int getSuit(){
        return suit;
    }
    
} //end of class
