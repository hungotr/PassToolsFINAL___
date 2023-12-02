package com.example.passtools;

import androidx.annotation.NonNull;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public abstract class EncodeDecode
{
    enum Mode { DECODE, ENCODE }

    protected String plaintext;
    protected String ciphertext;
    protected byte[] plainbytes;
    protected byte[] cipherbytes;

    /**
     * The less-specialized constructor for EncodeDecode,
     * overloading the more-specialized constructor that
     * has parameters for a mode, that being an encrypt mode
     * and decrypt mode, mainly for specifying the purpose of
     * the input string, as the constructor has no other way
     * of knowing.
     * <p>
     * @author  Alex Sanchez
     * @param   input           The string to be encoded.
     * */
    protected EncodeDecode(String input) { this(input, Mode.ENCODE); }

    /**
     * The more-specialized constructor for EncodeDecode,
     * overloaded by the less-specialized version above.
     * Default for creating an EncodeDecode object is meant
     * for the constructor above, and this constructor below
     * will likely be called in the event of decoding a string.
     * <p>
     * @author  Alex Sanchez
     * @param   input           The string to either be encoded or decoded.
     * @param   what            An enumerated type specifying what mode to
     *                          set the EncodeDecode object to, and what kind
     *                          of input to expect.
     * */
    protected EncodeDecode(String input, Mode what)
    {
        if(what == Mode.DECODE)
        {
            this.ciphertext = input;
            this.cipherbytes = input.getBytes(StandardCharsets.UTF_8);
        }
        else if(what == Mode.ENCODE || what == null)
        {
            this.plaintext = input;
            this.plainbytes = input.getBytes(StandardCharsets.UTF_8);
        }
    }

    /**
     * Standard-issue getter for protected member 'plaintext'.
     * <p>
     * @author  Alex Sanchez
     * @return  a String object representing the plaintext.
     * */
    public String getPlaintext() { return plaintext; }

    /**
     * Standard-issue setter for protected member 'plaintext'.
     * <p>
     * @author  Alex Sanchez
     * @param   plaintext       New String object to set protected
     *                          member 'plaintext' to
     * */
    public void setPlaintext(@NonNull String plaintext)
    {
        this.plaintext = plaintext;
        this.plainbytes = plaintext.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Standard-issue getter for protected member 'ciphertext'.
     * <p>
     * @author  Alex Sanchez
     * @return  a String object representing the ciphertext.
     * */
    public String getCiphertext() { return ciphertext; }

    /**
     * Standard-issue setter for protected member 'ciphertext'.
     * <p>
     * @author  Alex Sanchez
     * @param   ciphertext      New String object to set protected
     *                          member 'ciphertext' to
     * */
    public void setCiphertext(@NonNull String ciphertext)
    {
        this.ciphertext = ciphertext;
        this.cipherbytes = ciphertext.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * An abstract method to encode the plaintext using a user-defined
     * implementation.
     * <p>
     * @author  Alex Sanchez
     * @return  a String object representing the newly created ciphertext
     * */
    public abstract String encode() throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
    /**
     * An abstract method to encode the ciphertext using a user-defined
     * implementation.
     * <p>
     * @author  Alex Sanchez
     * @return  a String object representing the newly created plaintext
     * */
    public abstract String decode() throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException;

    /**
     * An overridden toString method, displaying the plaintext
     * next to the ciphertext, for debugging purposes.
     * @author  Alex Sanchez
     * @return  a String representation of the EncodeDecode object
     * */
    @NonNull
    @Override
    public String toString()
    {
        return "[" + this.plaintext + ", " + this.ciphertext + "]";
    }
}
