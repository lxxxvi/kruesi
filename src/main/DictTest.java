package main;

public class DictTest {

  public static void main(String[] args) {

    Dict dict = new Dict();

    /* test sBox */
    System.out.println("dict.sBox(0) expected 14: "  + dict.sBox((short) 0));
    System.out.println("dict.sBox(11) expected 12: " + dict.sBox((short) 11));

    /* test sInv */
    System.out.println("dict.sInv(6) expected 10: "  + dict.sInv((short) 6));
    System.out.println("dict.sInv(13) expected 2: "  + dict.sInv((short) 13));


  }
}
