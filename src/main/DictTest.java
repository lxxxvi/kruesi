package main;

public class DictTest {

  public static void main(String[] args) {

    Dict dict = new Dict();

    /* test sBox */
    System.out.println("dict.sBox(0) expected 14: "  + dict.sBox((byte) 0));
    System.out.println("dict.sBox(11) expected 12: " + dict.sBox((byte) 11));

    /* test sInv */
    System.out.println("dict.sInv(6) expected 10: "  + dict.sInv((byte) 6));
    System.out.println("dict.sInv(13) expected 2: "  + dict.sInv((byte) 13));

    //                    Bit                         Dec
    // biperm test input  0000 1000 1000 1000        2184
    // expected output    0111 0000 0000 0000       28672
    System.out.println("dict.biperm(2184) expected 28672: " + dict.biperm((short) 2184));

    //                    Bit                         Dec
    // biperm test input  1111 0111 0111 0111       63351
    // expected output    1000 1111 1111 1111       36863
    System.out.println("dict.biperm(63351) expected 36863: " + dict.biperm((short) 63351));



  }
}
