package main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
*
*     USAGE:
*
*     Get yourself a Dictionary "Dict"    :   Dict dict = new Dict();
*     Lookup sBox                         :   dict.sBox((short) value);
*     Lookup sInv                         :   dict.sInv((short) value);
*
* */

public class Dict {

  private final HashMap<Short, Short> sBoxMap;
  private final HashMap<Short, Short> sInvMap;

  public Dict() {
    /* setup */
    HashMap<String, String> template = new HashMap<String, String>();
    template.put("0", "E");
    template.put("1", "4");
    template.put("2", "D");
    template.put("3", "1");
    template.put("4", "2");
    template.put("5", "F");
    template.put("6", "B");
    template.put("7", "8");
    template.put("8", "3");
    template.put("9", "A");
    template.put("A", "6"); // 10
    template.put("B", "C"); // 11
    template.put("C", "5"); // 12
    template.put("D", "9"); // 13
    template.put("E", "0"); // 14
    template.put("F", "7"); // 15

    /* build lookup hashs */
    sBoxMap = new HashMap<Short, Short>();
    sInvMap = new HashMap<Short, Short>();

    Iterator it = template.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry)it.next();

      short from = (short) getIntFromHex(pair.getKey().toString());
      short to   = (short) getIntFromHex(pair.getValue().toString());

      sBoxMap.put(from , to);
      sInvMap.put(to , from);
    }

  }

  public int getIntFromHex(String hex) {
    return Integer.parseInt(hex, 16);         // http://stackoverflow.com/questions/5886619/hexadecimal-to-integer-in-java
  }

  public short sBox(short input) {
    return sBoxMap.get(input);
  }

  public short sInv(short input) {
    return sInvMap.get(input);
  }

}
