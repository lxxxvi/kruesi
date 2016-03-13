package main;

/**
 * Created by trish on 13.03.16.
 */
public class Decryptor {

    /**
     * Implementing the decryptor based on the encryptor by using sInv()-function from class Dict
     * and bitPerm() from class Encryptor which should also work for the decryptor without changing anything.
     */

    Utilities utilies = new Utilities();

    //Konstruktor
    public Decryptor () { }

    public byte[] decryptByteArray( byte[] text, byte[] key) {
        byte[] out = text;

        out = utilies.xOR(out, key, 0);
        for (int i = 1 ; i < 4; i++ ) {
            out = utilies.sBoxInv(out);
            //bitperm gedings
            out = utilies.xOR( out, key, i );
        }

        //letzter Aufruf ohne bitperm
        out = utilies.sBoxInv(out);
        out = utilies.xOR(out, key, 4);

        return out;
    }
}
