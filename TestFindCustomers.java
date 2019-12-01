/*
  File: TestFindCustomers.java
  Author: Christina Leung
  Description: Tests the functionalities of FindCustomers.java
*/

public class TestFindCustomers {
  public static void testInRange() {
    if (FindCustomers.inRange(100.0, 100.0)) {
      System.out.println("inRange failed with vals (100, 100)");
      return;
    } 
    if (FindCustomers.inRange(0.0, 0.0)) {
      System.out.println("inRange failed with vals (0, 0)");
      return;
    }
    if (!FindCustomers.inRange(-122.5, 37.5)) {
      System.out.println("inRange failed with vals (37.5, -122.5)");
      return;
    }
    if (!FindCustomers.inRange(-122.0, 37.0)) {
      System.out.println("inRange failed with vals (37.0, -122.0)");
      return;
    }
    System.out.println("All inRange tests passed!");
  }
  public static void main(String[] args) {
    testInRange();
  }
}
