package com.example.passtools;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Key extends EncodeDecode
{
    private Base64.Encoder b64En;
    private Base64.Decoder b64De;

    /**
     * A less-specialized constructor for Key, meant as the
     * default constructor for this object, with the mode
     * again defaulting to encode mode. In fact, the body
     * of this function essentially funnels the parameters
     * into the overloaded constructor below, because Java
     * is stupid and doesn't understand default parameters.
     * <p>
     * @author  Alex Sanchez
     * @param   input           The string to be inputted as
     *                          plaintext.
     * */
    public Key(String input) { this(input, EncodeDecode.Mode.ENCODE); }

    /**
     * A more-specialized constructor for Key, really only
     * to be realistically used in the event of decoding
     * a Key object, but still available nonetheless.
     * <p>
     * @author  Alex Sanchez
     * @param   input           The string to be inputted, either
     *                          plaintext or ciphertext
     * @param   what            Enumerated type specifying what mode
     *                          to put the underlying EncodeDecode structure
     *                          into, as well as specifying what kind of
     *                          input is being received
     * */
    public Key(String input, Mode what)
    {
        super(input, what);

        this.b64En = Base64.getEncoder();
        this.b64De = Base64.getDecoder();
    }

    /**
     * An override of the EncodeDecode method encode()
     * with this specific implementation being used to encode
     * plaintext into a Base64 ciphertext, using Java 8's new
     * default Base64 encoder.
     * <p>
     * @author  Alex Sanchez
     * @return  a String object representing the newly made ciphertext
     * */
    @Override
    public String encode()
    {
        try
        {
            if (this.plainbytes == null)
                this.plainbytes = this.plaintext.getBytes(StandardCharsets.UTF_8);

            String buf = new String(this.b64En.encode(this.plainbytes));
            if (getCiphertext().isEmpty()) this.ciphertext = buf;

            return this.ciphertext;
        }
        catch(Exception e)
        {
            System.out.println("Error in Key:encode()");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * An override of the EncodeDecode method decode()
     * with this specific implementation being used to decode
     * Base64 text.
     * <p>
     * @author  Alex Sanchez
     * @return  a String object representing the newly made plaintext
     * */
    @Override
    public String decode()
    {
        try {
            if (this.cipherbytes == null)
                this.cipherbytes = this.ciphertext.getBytes(StandardCharsets.UTF_8);

            String buf = new String(this.b64De.decode(this.cipherbytes));
            if (getPlaintext().isEmpty()) this.plaintext = buf;

            return this.plaintext;
        }
        catch(Exception e)
        {
            System.out.println("Error in Key:decode()");
            e.printStackTrace();
            return null;
        }
    }
}
