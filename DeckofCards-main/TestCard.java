/** EN.500.112 Gateway Computing: Java.
   * TestCard class
   * 11/18/2022
   * @author Maverick Espinosa
   * Program tests Card Class
   */
public class TestCard {

 /**
   * A helper method to assert that two integers are equal.
   * @param exp expected integer value.
   * @param act actual integer value.
   * @param dsc description of the test.
   */
   public static void assertEquals(int exp, int act, String dsc) {
      if (exp == act) {
         System.out.println("PASS: " + dsc);
      } else {
         System.out.println("FAIL: " + dsc);
      }
   }
   
 /**
   * A helper method to assert that two cards are equal.
   * @param exp expected integer value.
   * @param act actual integer value.
   * @param dsc description of the test.
   */
   public static void assertCardEquals(Card exp, Card act, String dsc) {
      if (exp.equals(act)) {
         System.out.println("PASS: " + dsc);
      } else {
         System.out.println("FAIL: " + dsc);
      }
   }
   
 /**
   * Execution starts here.
   * @param args command-line arguments.
   */
   public static void main(String[] args) {
      Card kingOfClubs = new Card(13, 1);
      Card aceOfHearts = new Card(1, 3);
      Card kingOfClubs2 = new Card(13, 1);
      Card equal = new Card(1, 3);
      Card jackOfClubs = new Card(11, 1);
      
      System.out.println(kingOfClubs); // must print “King of Clubs”
      assertEquals(13, kingOfClubs.getRank(), "Rank is correctly assigned.");
      assertCardEquals(equal, aceOfHearts, "Cards are equal.");
      assertEquals(1, aceOfHearts.compareTo(kingOfClubs), 
                             "ace of hearts is larger.");
      assertEquals(0, kingOfClubs.compareTo(kingOfClubs2), "they are equal");
      assertEquals(-1, kingOfClubs.compareTo(aceOfHearts),
                            "ace of hearts is larger.");
      assertEquals(1, kingOfClubs.compareTo(jackOfClubs), "king is larger.");
      
   }  
}