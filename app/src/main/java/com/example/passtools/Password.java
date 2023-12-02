package com.example.passtools;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Password extends EncodeDecode
{
    final private String defaultKey = "BLOWFISHENCRYPT";
    final private String method = "Blowfish";

    private Cipher cObj;
    private Key bfKey = new Key(this.defaultKey);
    private SecretKeySpec keyspec;

    /**
     * A default constructor for the Password object, which
     * really just funnels its parameters into a more specialized
     * overloaded constructor below.
     * <p>
     * @author  Alex Sanchez
     * @param   input           String object to be inputted representing
     *                          plaintext
     * */
    public Password(String input)
    {
        this(input, Mode.ENCODE);
    }

    /**
     * A more specialized constructor which specifies the kind
     * of mode to put the underlying EncodeDecode structure into,
     * which also specifies the kind of input it is receiving.
     * <p>
     * @author  Alex Sanchez
     * @param   input           String object to be inputted, either
     *                          being plaintext or ciphertext depending
     *                          on the mode given
     *
     * @param   what            Enumerated type representing the kind of
     *                          mode to set this instance of the object
     *                          into, as well as specifying what input
     *                          is to be expected
     * */
    public Password(String input, Mode what)
    {
        super(input, what);
        try
        {
            this.cObj = Cipher.getInstance(this.method);
            this.keyspec = new SecretKeySpec(this.bfKey.plainbytes, this.method);
        }
        catch(Exception e)
        {
            System.out.println("Error in Password:constructor()");
            e.printStackTrace();
        }
    }

    /**
     * Another default constructor for the Password object, which
     * really just funnels its parameters into a more specialized
     * overloaded constructor below.
     * <p>
     * @author  Alex Sanchez
     * @param   input           String object to be inputted representing
     *                          plaintext
     *
     * @param   key             String object to be inputted into this
     *                          Password's instance of a Key object
     * */
    public Password(String input, String key)
    {
        this(input, key, Mode.ENCODE);
    }

    /**
     * A more specialized constructor which specifies the kind
     * of mode to put the underlying EncodeDecode structure into,
     * which also specifies the kind of input it is receiving.
     * <p>
     * @author  Alex Sanchez
     * @param   input           String object to be inputted, either
     *                          being plaintext or ciphertext depending
     *                          on the mode given
     *
     * @param   key             String object to be inputted into this
     *                          Password's instance of a Key object
     *
     * @param   what            Enumerated type representing the kind of
     *                          mode to set this instance of the object
     *                          into, as well as specifying what input
     *                          is to be expected
     * */
    public Password(String input, String key, Mode what)
    {
        super(input, what);
        try
        {
            this.bfKey = new Key(key);
            this.cObj = Cipher.getInstance(this.method);
            this.keyspec = new SecretKeySpec(this.bfKey.plainbytes, this.method);
        }
        catch(Exception e)
        {
            System.out.println("Error in Password:constructor(Mode)");
            e.printStackTrace();
        }
    }

    /**
     * An override of the EncodeDecode method encode()
     * with this specific implementation being used to encode
     * plaintext into ciphertext using the Blowfish cipher created
     * by Bruce Schneier in 1993, which he has graciously released
     * into the public domain.
     * <p>
     * @author  Alex Sanchez
     * @return  a String object representing the newly made ciphertext
     * */
    @Override
    public String encode()
    {
        if(this.plainbytes == null) this.plainbytes = this.plaintext.getBytes(StandardCharsets.UTF_8);

        String buf;
        try
        {
            this.cObj.init(Cipher.ENCRYPT_MODE, keyspec);
            buf = new String(this.cObj.doFinal(this.plainbytes));
            if (getCiphertext().isEmpty()) this.ciphertext = buf;

            return buf;
        }
        catch(Exception e)
        {
            System.out.println("Error in Password:encode()");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * An override of the EncodeDecode method decode()
     * with this specific implementation being used to decode
     * Blowfish ciphertext.
     * <p>
     * @author  Alex Sanchez
     * @return  a String object representing the newly made plaintext
     * */
    @Override
    public String decode()
    {
        if(this.plainbytes == null) this.plainbytes = this.plaintext.getBytes(StandardCharsets.UTF_8);

        String buf;
        try
        {
            this.cObj.init(Cipher.DECRYPT_MODE, keyspec);
            buf = new String(this.cObj.doFinal(this.plainbytes));
            if (getCiphertext().isEmpty()) this.ciphertext = buf;

            return buf;
        }
        catch(Exception e)
        {
            System.out.println("Error in Password:decode()");
            e.printStackTrace();
        }

        return null;
    }
}
