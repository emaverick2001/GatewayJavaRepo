/**  EN.500.112 Gateway Computing: Java.
   * Card class
   * 11/18/2022
   * @author Maverick Espinosa
   * Program creates a card with a rank and suit
   */
public class Card {
   //each card instance has a rank and a suit
   private int rank;
   private int suit;
   
 /** Constructor used to initialize fields.
   * @param rank is the rank of the card (Ace, 2 -10, Jack, Queen, King)
   * @param suit is the suit (Clubs, Diamonds, Hearts, Spades)
   */
   public Card(int rank, int suit) {
      //suit should be 1<=x<=4
      //rank should be 1<=x<=13
      if (suit >= 1 && suit <= 4 && rank >= 1 && rank <= 13) {
         this.rank = rank;
         this.suit = suit;
      }
     
      else {
         this.rank = 0;
         this.suit = 0;
      }
   }
   
 /** gets rank of card.
   * @return the value of the rank
   */
   public int getRank() {
      return this.rank;
   }
   
 /** gets suit of card.
   * @return the value of the suit
   */
   public int getSuit() {
      return this.suit;
   }
   
 /** prints suit and and rank in string form with names.
   * @return the string form of the suit and rank of the card
   */   
   @Override
   public String toString() {
      String result = "";
      switch (rank) {
         case 1:
            result += "Ace";
            break;
         case 11:
            result += "Jack";
            break;
         case 12:
            result += "Queen";
            break;
         case 13:
            result += "King";
            break;
         default:
            result += rank;
      }
      result += " of ";
      switch (suit) {
         case 1:
            result += "Clubs";
            break;
         case 2:
            result += "Diamonds";
            break;
         case 3:
            result += "Hearts";
            break;
         case 4:
            result += "Spades";
            break;
         default:
            
      }
      return result;
   }
     
 /** checks to see if an instance card is equal another instance card.
   * @param other is the card being compared with the instance Card)
   * @return the value of the rank
   */
   @Override
   public boolean equals(Object other) {
      return this.rank == ((Card) other).getRank() &&
             this.suit == ((Card) other).getSuit();
   }
   
 /** Compare this Card with the specified otherCard for order.
   * @param otherCard the other Card object to be compared.
   * @return a negative integer, zero, or a positive integer as
   * this object is less than, equal to, or greater than the otherCard.
   */
   public int compareTo(Card otherCard) {
    // TODO implement me!
      if (this.suit == otherCard.getSuit() && 
               this.rank == otherCard.getRank()) {
         return 0;
      }
      else if (this.suit > otherCard.getSuit()) { 
         return 1;
      }
      else if (this.suit == otherCard.getSuit() &&  
            this.rank > otherCard.getRank()) {
         return 1;
      }
      else {
         return -1;
      }
   }
}