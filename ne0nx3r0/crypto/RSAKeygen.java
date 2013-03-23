package ne0nx3r0.crypto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.RSAKeyGenParameterSpec;

public class RSAKeygen
{
    
    public static KeyPair generate(int bits) throws Exception
    {
        KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");

        RSAKeyGenParameterSpec spec = new RSAKeyGenParameterSpec(bits, RSAKeyGenParameterSpec.F4);

        keygen.initialize(spec);

        return keygen.generateKeyPair();
    }
}