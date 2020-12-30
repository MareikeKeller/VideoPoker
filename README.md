# VideoPoker
A simple, interactive video poker game.

===============================================================================
Card.java -- Class file that provides all properties of a card
===============================================================================

The card class provides the properties for a Card object. I instantiated a card
object to take parameters rank and suit. A compareTo method is provided, using
conditionals since the regular compareTo static method doesn't work with ints. 
I modeled it after the original compareTo method and have it return -1,0,1 
depending on whether one int is smaller, bigger or equal to the preceding one.
I use this method to both compare rank and suit to one another. This will help
me to sort the hand later on in the game method. ToString transforms the card
object into readable strings. I also included accessor methods getRank and 
getSuit to easily access those properties about the card. 

===============================================================================
Deck.java -- Class file for card deck, shuffling and dealing cards
===============================================================================

I used the deck class to initiate a 52 card deck and included a for loop
that adds each rank-value combination to the array by creating a new card
object for each of these combinations. This simulates a real poker card-deck.

I incorporated a shuffle method that generates a random index at a number
between 0-52 and then switches the card in the deck at that index with the
card index that is i in the for loop. It will then reset the top card to 0 so
that we later on will always deal from the top of the shuffled deck. This method 
is later on called 10000 times in the game method, ensuring a thoroughly 
shuffled deck. In the deal method, before a card it dealt, the deck is also 
shuffled to ensure that the same cards aren't dealt twice. The deal method deals
the deck's top card and can be called however many times a card should be dealt
(in the Game class it's called 5 times and then added to the hand).

===============================================================================
Player.java -- Class file that creates hand, manages bets & updates bankroll
===============================================================================

In the player class, I created an arrayList that represents the player's hand
so that cards can be added to it. This class has a simply method that adds
a card to said hand and another method that allows us to get the contents of
a hand by passing in the name of the arrayList (hand). Methods bets subtracts
the bet from the users initial bankroll as a form of "payment of tokens" to
play a game. The winnings method is designed to only be called when the user
actually won something (in the Game method this will be if payout is > 0), and
it adds the tokens times payout to update the bankroll. It also provides a get 
method to access the current bankroll.

I commented out remove card since I built a reset method and use set() to 
swap cards in the game method.

===============================================================================
Game.java -- Class file that contains basic functionality of the game, 
a overloaded constructor that takes a testHand, and evaluates the hand
===============================================================================

The game class accounts for the functionality of the poker game. Firstly, I 
chose to include payout as an instance variable, because I will be using it 
across several different methods. Since the code will be tested by providing
test hands in the command line, that are passed into the program as an array
of strings, the class has two constructors. There is one for a normal, inter-
active play that automatically starts. It instantiates player, deck, a scanner
for user input, payout and playAgain, which will be important for our 
interactive play in the play method. The testHand constructor has looks
similar, but it also has a for loop that allows for "translation" between
command-line inputs and what the program understands when handling
cards (which are integers). It then creates a new hand based on these
inputs.

The play method is where the gameplay happens. I wanted to design my methods
in a way that would make them useable for interactive and test play. This is
why I chose to incorporate two branches: one for the case that the hand is
already populated (such as in commandline input in the test case), and one 
if the hand is empty when the play begins (before they are dealt out), which
would indicate an interactive game in which cards have to be dealt and redrawn.

For the interactive part, I chose to include a boolean so that the aspects of
the game are only executed the first time, or if the user chose to play a second
game. Like most aspects in my play method, I encapsulated the playAgain 
functionality in a separate, private helper method. The play method now
takes user input for bets and passes it into the bet method. I chose to 
encapsulate the next steps of dealing the hand and asking the user if they
would like a redraw for each card into separate methods, to keep the
play method clean and short. The redraw method "switcharoo" uses set() to
change a card in the arrayList hand that the user decided to switch. It
replaces it with a new card from the deck. I decided to use this rather
than calling a remove method and the add method from the player class, 
since it is more elegant.

For sorting the hand, I avoided creating another sort method since
calling collections.sort is such an easy and fast way of sorting the hand,
and it's also immediately clear what collections.sort does. The play
method then calls the checkhand method and updates winnings it payout in
that method is larger than 0. If the hand is not populated, we skip
most of these steps and only sort the already giving hand and check them. 
There is no need to deal, replace or account for any user interaction in
the testHand case.

The checkHand method uses an else if structure starting from the highest
payout, so that we can ensure that if several winning hands exist, the 
user will receive the payout for the highest hand that they have. The checkHand
method relies on several helper method that return a boolean value as true
or false after checking the hand for a certain scoring. If the checkHand
method has called all of these methods and none of them are true, this means
that it has to look for the highCard, which is done in the last else
statement corresponding with the first if. We look at all cards and see which
one's the highest. It also directly provides an output message that is
convenient for printing and in the end will also include the respective payout
for printing (payout itself is passed into the winnings method if there is one).

For the helper methods itself: they rely on a sorted hand, which is why I
hardcoded some numbers if I felt like a for-loop wouldn't be beneficial. Some
helper methods, such as straight or highCard cover the special case of an 
ace. In the end, a reset method allows me to reset the hand if the user decides
to play a second game. The bankroll is intentially not reset, as the same player
might want to play multiple games to increase their overall bankroll.

I overall took an approach of including a method where it made most sense to me
conceptually. All methods that are relevant to gameplay and not only properties
of cards or decks, are in the game method.

Some of these methods may be longer than 15 lines, but that is only because of
their basic structure of consecutive else-if statements. Since I believe that
each method is still easy to understand and not convoluted, I believe this is an
acceptable design-decision.
