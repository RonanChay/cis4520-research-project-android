package com.example.cis4520researchprojectphone.code;

import java.security.SecureRandom;

public class Utils {
    /**
     * Helper method - Converts a byte[] to a hex string
     * @param bytesHash Input byte array
     * @return Hex string representation of byte array
     */
    public static String convertBytesToHex(byte[] bytesHash) {
        // Convert to hex
        StringBuilder hexString = new StringBuilder();
        for (byte aByte : bytesHash) {
            hexString.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return hexString.toString();
    }

    /**
     * Helper method - Generates random 16B salt as byte[]
     * @return Random 16B salt as byte[]
     */
    public static byte[] generateSalt16Bytes() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);

        return salt;
    }
}
