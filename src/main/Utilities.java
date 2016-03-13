package main;

/**
 * Created by trish on 13.03.16.
 */
public class Utilities {

    Dict dict = new Dict();

    public Utilities() {}

    /**
     * Eingabe: Byte-Array (4x4 Bits)
     * Ausgabe: Byte-Array (4x4 Bits)
     */
    protected byte[] xOR ( byte[] in, byte[] toXOR, int round ) {
        byte[] out = new byte[4];

        // Iterate over byte-array with the corresponding subset from the key, which gets incremented with round.
        for (int i = 0; i < out.length ; i++ ) {
            out[i] = (byte) (in[i] ^ toXOR[ i + round ]);
        }

        return out;
    }



    protected byte[] sBoxInv ( byte[] in ) {
        byte[] out = new byte[4];

        for (int i = 0; i < out.length ; i++ ) {
            out[i] = dict.sInv(in[i]);
        }

        return out;
    }
}
