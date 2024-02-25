import java.util.Random;

/** EN.500.112 Gateway Computing: Java.
   * Deck class
   * 11/18/2022
   * @author Maverick Espinosa
   * Program creates a deck with 52 cards in order
   */
public class Deck {
   private Card[] deck;
   private final int size = 52;
     
 /** Constructor used to initialize fields.
   */
   public Deck() {
      deck = new Card[size];
      int count = 0;
      Card card;
      for (int i = 1; i <= 4; i++) {
         for (int j = 1; j <= 13; j++) {
            deck[count] = new Card(j, i);
            count++;
         }
      }
   }
   
 /** shuffles cards in deck.
   */
   public void shuffle() {
      int max = 51;
      Random randGen = new Random();
      Card[] copy = new Card[size];
      int count = 0;
      Card card;
      for (int i = 1; i <= 4; i++) {
         for (int j = 1; j <= 13; j++) {
            copy[count] = new Card(j, i);
            count++;
         }
      }

      for (int i = 0; i < size; i++) {
         int randomnCard = randGen.nextInt(max + 1);
         deck[i] = copy[randomnCard];
         copy[randomnCard] = copy[max];
         max--;
      }
   }
   
 /** sorts cards in deck.
   */
   public void sort() {
      
      int cardBeforeJ;
      for (int j = 0; j < size; j++) {
         Card smallest;
         smallest = deck[j];
         cardBeforeJ = j - 1;
         while (cardBeforeJ >= 0 && 
               deck[cardBeforeJ].compareTo(smallest) == 1) {
            deck[cardBeforeJ + 1] = deck[cardBeforeJ];
            cardBeforeJ--;
         }
         deck[cardBeforeJ + 1] = smallest;
      }  

   }

         
 /** prints cards in deck in string form with name of suit and rank.
   * @return string with name of each card based on suit and rank
   */
   @Override
   public String toString() {
      String result = "";
      for (int i = 0; i < deck.length - 1; i++) {
         result += deck[i].toString() + "\n";
      }
      result += deck[deck.length - 1].toString();
      return result;
   }
}   