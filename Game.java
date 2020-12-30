/* Mareike Carolin Keller
 * mk4323
 * Game.java - Class file that plays the actual poker game, evaluates hands
 * and provides a test constructor that can be used for evaluation of checkHand
*/

import java.util.Collections;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;


public class Game {
	
    //intrance variables
	private Player p;
	private Deck cards;
    private boolean playAgain;
    private double payout; 
    private Scanner input;
	
    
	//constructor when testHand is passed in through command line
	public Game(String[] testHand){ 
        p = new Player();
        cards = new Deck();
        input = new Scanner(System.in);
        playAgain = true;
        payout = 0.0;
        
        //transforming command line into something program understands
        for(int i = 0; i < testHand.length; i++){ //is an array 
            int s = 0;
            int r = 0;
            String suitString = testHand[i].substring(0,1); //first char 
            r = Integer.parseInt(testHand[i].substring
                                 (1, testHand[i].length())); //to int
            
            if(suitString.equals("c")){
                s = 1;
            }           
            else if(suitString.equals("d")){
                s = 2;
            }
            else if(suitString.equals("h")){
                s = 3;
            }
            else if(suitString.equals("s")){
                s = 4;
            }
             p.getHand().add(new Card(s,r)); //create new hand            
        }
        System.out.println(p.getHand());
   }
    
    
    //constructor when no arguments given through command line
	public Game(){
        p = new Player();
        cards = new Deck();
        input = new Scanner(System.in); 
        playAgain = true;
        payout = 0.0;       
    }
		
  
    
	//actual gameplay
	public void play(){ 
      
        if(p.getHand().size() == 0){ //if no hand was created through testHand
            
            while(playAgain){ //if user chooses playAgain (or it's first game)
            
                System.out.println("Hi! Welcome to Video Poker." +
                                   "you're starting with " + p.getBankroll() +
                                " tokens in your bankroll.");
                System.out.println("How many tokens would you like to bet?" +
                                   " Choose 1-5");
                
                Double inputBet = input.nextDouble(); //user bet input
                p.bets(inputBet); //passes bet input into bet method
            
                dealHand(); //method shuffles, deals and adds card to hand 
                switcharoo(); //ask user if they want to switch cards (redraw)
            
                Collections.sort(p.getHand()); //sort the hand
                System.out.println("Your hand: " + p.getHand()); //print after redraw
                checkHand(p.getHand()); //checks hand
            
                if(payout > 0.0){ //only when p wins they will get winnings tokens
                    p.winnings(payout); //updates winnings in payroll if p wins
                }
   
                System.out.println("You now have " + p.getBankroll() + " tokens.");
                playAgain = playAgain(); //asks user to play another game                
            }
        }
            
        else{ //when hand is populated, as in testHand
            
            Collections.sort(p.getHand()); //sorts hand
            checkHand(p.getHand()); //checks hand
                
            }            
        }
        
    
    
        //helper method to switch cards
        private void switcharoo(){ //asks user if they want to switch card
            
            int answer = 0;
            for(int i = 5; i > 0; i--){ //backwards so "numbering" is 1-5
                System.out.println("Would you like to keep card # " +
                                   i + " ? 0-No, 1-Yes");
                answer = input.nextInt(); //expecting user input for answer
                
                if(answer == 0){ //if answer == no
                    p.getHand().set(i-1, cards.deal()); //deals new card
                }
            }
        }
            
    
    
        //helper method to deal hand   
        public void dealHand(){ //shuffles cards, deals and adds to hand
            for(int i = 0; i < 10000; i++){ //shuffle deck 10000x
                cards.shuffle();
            }
            
            for(int i = 0; i < 5; i++){ //deals top card 5 times
                p.addCard(cards.deal()); //adds 5 cards to hand array //!! replace with addCard
            }

            System.out.println("Your hand: " + p.getHand()); //shows hand to player
        }
            

    
        //asking user if they want to play again
        public boolean playAgain(){ 
               
            System.out.println("Would you like to play again? 0-No  1-Yes");
            int againQuestion = input.nextInt();
               if(againQuestion == 1){
                   resetHand(p.getHand()); //resets hand for next game
                   
                   return true; //for while loop in play method
               }
                   return false;
        }
           
      
           
           
	public String checkHand(ArrayList<Card> hand){ //"scores" hands and prints

        String message = "";
        
        if(royalFlush(p.getHand()) == true){
            payout = 250.0;
            message = "You have a Royal Flush! ";
        }
        
        else if(straightFlush(p.getHand()) == true){
            payout = 50.0;
            message = "You have a Straight Flush! ";            
        }
        
        else if(four(p.getHand()) == true){
            payout = 25.0;
            message = "You have Four of a Kind! ";
        }
        
        else if(fullHouse(p.getHand()) == true){
            payout = 6.0;
            message = "Your have a Full House! ";
        }
        
        else if(flush(p.getHand()) == true){
            payout = 5.0;
            message = "You have a Flush! ";
        }
        
        else if(straight(p.getHand()) == true){
            payout = 4.0;
            message = "You have a Straight! ";
        }
        
        else if(three(p.getHand()) == true){
            payout = 3.0;
            message = "You have three of a kind! ";
        }
        
        else if(twoPair(p.getHand()) == true){
            payout = 2.0;
            message = "You have two Pairs! ";
        }
        
       else if(onePair(p.getHand()) == true){
            payout = 1.0;
            message = "You have one Pair! ";
        }
        
       else{ //if none of the above conditions are true           
            String highMessage = ""; //for return later
            payout = 0.0;
           
            if(highCard(p.getHand()) > 1 && highCard(p.getHand()) < 11){
                highMessage = ("a " + highCard(p.getHand()));
            }
           
            if(highCard(p.getHand()) == 1){
                highMessage = "an Ace";
            }
            
            else if(highCard(p.getHand()) == 11){
                highMessage = "a Jack";
            }
           
            else if(highCard(p.getHand()) == 12){
                highMessage = "a Queen";
            }
           
            else if(highCard(p.getHand()) == 13){
                highMessage = "a King";
            }
                       
            message = ("You have no pair. You have " + 
                       highMessage + " as your highest card.");          
        }
        
        
        System.out.println(message + " Your payout for this is: " + 
                           payout + ".");
        return message;
        //checks everything and returns message
    }
        

        
        //PRIVATE HELPER METHODS FOR EVALUATION OF HAND        
        private boolean royalFlush(ArrayList<Card> hand){ //royal flush
            if(straightFlush(p.getHand()) == true && //consecutive & same suit
               p.getHand().get(0).getRank() == 1 && 
               p.getHand().get(1).getRank() == 10 &&
               p.getHand().get(2).getRank() == 11 && 
               p.getHand().get(3).getRank() == 12 &&
               p.getHand().get(4).getRank() == 13){
                return true;
            }
            
            else{
                return false;
            }
        }  
                
        private boolean straightFlush(ArrayList<Card> hand){ //straight flush
            if(straight(p.getHand()) == true && flush(p.getHand()) == true){
                return true;               
            }
            
            else{
                return false;
            }
        }
           
                
        private boolean four(ArrayList<Card> hand){ //four of same rank
            if(p.getHand().get(0).getRank() != p.getHand().get(3).getRank() &&
               p.getHand().get(2).getRank() != p.getHand().get(4).getRank()){
                return false;
            }
            else{
                return true;
            }             
        }
        
        //full house, looking at pair and three
        private boolean fullHouse(ArrayList<Card> hand){
            if(p.getHand().get(0).getRank() == p.getHand().get(1).getRank() && 
              p.getHand().get(2).getRank() == p.getHand().get(3).getRank() && 
              p.getHand().get(2).getRank() == p.getHand().get(4).getRank() ||
              p.getHand().get(0).getRank() == p.getHand().get(1).getRank() && 
              p.getHand().get(0).getRank() == p.getHand().get(2).getRank() && 
              p.getHand().get(3).getRank() == p.getHand().get(4).getRank()){ 
                return true;
            }
            else{
                return false;
            }
        }
        

                
        private boolean flush(ArrayList<Card> hand){ //flush
             
            if(p.getHand().get(0).getSuit() == p.getHand().get(1).getSuit() && 
               p.getHand().get(0).getSuit() == p.getHand().get(2).getSuit() &&
               p.getHand().get(0).getSuit() == p.getHand().get(3).getSuit() &&
               p.getHand().get(0).getSuit() == p.getHand().get(4).getSuit()){
                return true;
            } 
            
            else{
                return false;
            }
        } 
    
                
        private boolean straight(ArrayList<Card> hand){ //straight
            int count = 0;
            for(int i = 0; i < p.getHand().size()-1; i++){
                if(p.getHand().get(i).getRank() + 1 == 
                   p.getHand().get(i+1).getRank()){
                    count++;
                }
             }    
            
                if(count == 4 || p.getHand().get(0).getRank() == 1 && 
                   p.getHand().get(1).getRank() == 10 &&
                   p.getHand().get(2).getRank() == 11 && 
                   p.getHand().get(3).getRank() == 12 &&
                   p.getHand().get(4).getRank() == 13){ //covering ace
                    
                    return true;
                }                                    
            
            return false;
        }
            
        
        
        private boolean three(ArrayList<Card> hand){
            if(p.getHand().get(0).getRank() == p.getHand().get(1).getRank() &&
               p.getHand().get(0).getRank() == p.getHand().get(2).getRank() ||
               p.getHand().get(1).getRank() == p.getHand().get(2).getRank() &&
               p.getHand().get(1).getRank() == p.getHand().get(3).getRank() ||
               p.getHand().get(2).getRank() == p.getHand().get(3).getRank() &&
               p.getHand().get(2).getRank() == p.getHand().get(4).getRank()){
                return true;
            }
            
            else{
                return false;
            }
        }
            
            
        private boolean twoPair(ArrayList<Card> hand){
            int pairs = 0;
            for(int i = 1; i < 5; i++){
                if(p.getHand().get(i-1).getRank() == 
                   p.getHand().get(i).getRank()){
                    pairs++;
                }
            }
            if(pairs == 2){ //when 2 pairs found
                return true;
            }
            
            else{ //if only one pair or none
                return false;
            }
        }
            
    
        private boolean onePair(ArrayList<Card> hand){
            if(p.getHand().get(0).getRank() == p.getHand().get(1).getRank() ||
               p.getHand().get(1).getRank() == p.getHand().get(2).getRank() ||
               p.getHand().get(2).getRank() == p.getHand().get(3).getRank() ||
               p.getHand().get(3).getRank() == p.getHand().get(4).getRank()){
                return true;
            }
            else{
                return false;   
            }
        }
        
            
        private int highCard(ArrayList<Card> hand){ //can't be a boolean
            int highest = 0;
            if(p.getHand().get(0).getRank() == 1){ //case of ace as high card
                highest = p.getHand().get(0).getRank();
            }           
            highest = p.getHand().get(4).getRank(); //else it's always card 5
            
            return highest; 
        } 
       
		
    //mutator method to clear hand ArrayList
        private void resetHand(ArrayList<Card> hand){ 
            p.getHand().clear(); //resets hand
        }

    
} //end of class
