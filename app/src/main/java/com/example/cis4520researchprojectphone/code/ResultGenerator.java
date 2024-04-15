package com.example.cis4520researchprojectphone.code;

import com.example.cis4520researchprojectphone.code.algorithms.Argon2idAlgo;
import com.example.cis4520researchprojectphone.code.algorithms.BcryptAlgo;
import com.example.cis4520researchprojectphone.code.algorithms.SHA512Algo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.cis4520researchprojectphone.code.Utils.convertBytesToHex;

public class ResultGenerator {
    private final String plaintext = "password123";
    long startTime, endTime, duration;
    final int MAX_DURATION = 180000;
    byte[] hashedPassword;
    List<String[]> dataLines = new ArrayList<>();
    final String SHA512_CSV_FILE_NAME = "/sha512-results.csv";
    final String BCRYPT_CSV_FILE_NAME = "/bcrypt-results.csv";
    final String ARGON2_CSV_FILE_NAME = "/argon2id-results.csv";

    /**
     * Converts String array to csv String
     * @param data String array input to create into csv
     * @return Csv string
     */
    private String convertToCSV(String[] data) {
        return String.join(",", data);
    }

    /**
     * Write data lines from algorithm tests to csv file
     * @param filename Filename of the csv file output
     * @throws IOException
     */
    private void convertDataLinesToCSVFile(String filename) throws IOException {
        File csvResultsFile = new File(filename);
        try (PrintWriter pw = new PrintWriter(csvResultsFile)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
    }

    /**
     * Generate cost data for SHA-512 algorithm
     * @throws IOException
     */
    public void generateSHA512Results(File filepath) throws IOException {
        String[] headers = {"WORK_FACTOR", "HASH", "TIME"};   // Csv file headers
        dataLines.add(headers);

        SHA512Algo sha512 = new SHA512Algo();
        sha512.setPlaintextPassword(plaintext);
        System.out.println("Plaintext: " + sha512.getPlaintextPassword());
        // Try work factor parameter for values 0 to 31
        for (int workfactor = 0; workfactor < 32; workfactor++) {
            sha512.setWorkFactor(workfactor);
            System.out.println("Work factor: " + sha512.getWorkFactor());

            startTime = System.currentTimeMillis();
            hashedPassword = sha512.hashPassword();
            endTime = System.currentTimeMillis();
            duration = endTime - startTime;

            String[] resultData = {
                    String.valueOf(workfactor),         // WORK_FACTOR
                    convertBytesToHex(hashedPassword),  // HASH
                    String.valueOf(duration)            // TIME
            };
            System.out.println(Arrays.toString(resultData));
            dataLines.add(resultData);

            if (duration > MAX_DURATION) {
                System.out.println("Taking too long, stopping...");
                break;
            }
        }
        convertDataLinesToCSVFile(filepath + SHA512_CSV_FILE_NAME);
    }

    /**
     * Generate cost data for BCrypt algorithm
     * @throws IOException
     */
    public void generateBCryptResults(File filepath) throws IOException {
        String[] headers = {"WORK_FACTOR", "SALT", "HASH", "TIME"};   // Csv file headers
        dataLines.add(headers);

        BcryptAlgo bcrypt = new BcryptAlgo();
        bcrypt.setPlaintextPassword(plaintext);
        System.out.println("Plaintext: " + bcrypt.getPlaintextPassword());
        // Try work factor parameter for values 4 to 31 - BouncyCastle specification limits for BCrypt algorithm
        for (int workfactor = 4; workfactor < 32; workfactor++) {
            bcrypt.setWorkFactor(workfactor);
            System.out.println("Work factor: " + bcrypt.getWorkFactor());

            startTime = System.currentTimeMillis();
            hashedPassword = bcrypt.hashPassword();
            endTime = System.currentTimeMillis();
            duration = endTime - startTime;

            String[] resultData = {
                    String.valueOf(workfactor),         // WORK_FACTOR
                    bcrypt.getSaltAsHex(),              // SALT
                    convertBytesToHex(hashedPassword),  // HASH
                    String.valueOf(duration)            // TIME
            };
            System.out.println(Arrays.toString(resultData));
            dataLines.add(resultData);

            if (duration > MAX_DURATION) {
                System.out.println("Taking too long, stopping...");
                break;
            }
        }
        convertDataLinesToCSVFile(filepath + BCRYPT_CSV_FILE_NAME);
    }

    /**
     * Generate cost data for Argon2id algorithm. Uses default parameters below for all tests:
     *      Hash Length: 32 Bytes,
     *      Number of Threads: 1
     * @throws IOException
     */
    public void generateArgon2idResults(File filepath) throws IOException {
        String[] headers = {"NUM_ITERATIONS", "MEM_LIMIT", "HASH_LENGTH", "NUM_THREADS", "SALT", "HASH", "TIME"};   // Csv file headers
        dataLines.add(headers);

        Argon2idAlgo argon2id = new Argon2idAlgo();
        argon2id.setPlaintextPassword(plaintext);
        argon2id.setHashLength(32);
        argon2id.setNumThreads(1);
        System.out.println("Plaintext: " + argon2id.getPlaintextPassword());
        // Try memory limit parameter for values 10 MiB to 50 MiB, increments of 10MiB
        for (int memLimit = 10240; memLimit <= 51200; memLimit += 10240) {
            argon2id.setMemLimit(memLimit);
            // Try number of iterations parameter for values 4 to 31
            for (int numIterations = 4; numIterations < 32; numIterations++) {
                argon2id.setNumIterations(numIterations);
                System.out.println("Memory Limit: " + argon2id.getMemLimit() + "     Num Iterations: " + argon2id.getNumIterations());

                startTime = System.currentTimeMillis();
                hashedPassword = argon2id.hashPassword();
                endTime = System.currentTimeMillis();
                duration = endTime - startTime;

                String[] resultData = {
                        String.valueOf(numIterations),              // NUM_ITERATIONS
                        String.valueOf(memLimit),                   // MEM_LIMIT
                        String.valueOf(argon2id.getHashLength()),   // HASH_LENGTH
                        String.valueOf(argon2id.getNumThreads()),   // NUM_THREADS
                        argon2id.getSaltAsHex(),                    // SALT
                        convertBytesToHex(hashedPassword),          // HASH
                        String.valueOf(duration)                    // TIME
                };
                System.out.println(Arrays.toString(resultData));
                dataLines.add(resultData);

                if (duration > MAX_DURATION) {
                    System.out.println("Taking too long, stopping...");
                    break;
                }
            }
        }

        convertDataLinesToCSVFile(filepath + ARGON2_CSV_FILE_NAME);
    }
}
