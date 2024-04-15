package com.example.cis4520researchprojectphone.code.algorithms;

/**
 * Interface to be implemented by password hashing algorithms
 */
public interface Algorithm {
    /**
     * Hash the password using the set parameter values
     * @return Hash of the plaintext password
     */
    byte[] hashPassword();
}
