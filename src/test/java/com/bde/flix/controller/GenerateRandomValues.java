package com.bde.flix.controller;

import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("GrazieInspection")
public class GenerateRandomValues {
    /**
     * Returns positive long of the length specified.
     * For bigger numbers cast it to string and concatenate.
     * For example lets take length equal to 5:
     * It will generate long from 10000..99999
     * If specified length is out of supported values,
     * Exception is thrown.
     * Can be easily turned into String by String.valueOf()
     * @param length    Desired length(1..19)
     * @return long     Long of specified length
     */
    public static long NumberLengthOfN(long length) throws Exception{
        if(length > 19){
            throw new Exception("Parameter too big! It's over 19");
        } else if (length < 1){
            throw new Exception("Parameter too small! It's less than 1");
        }
        long min = (long)Math.pow(10, length-1);
        long max = ((long)Math.pow(10, length))-1;

        return ThreadLocalRandom.current().nextLong(min, max + 1);
    }

    /**
     * Returns String of specified length made up of numbers.
     * The check for length being over 19 is there because of long limits.
     * @param length Desired length
     * @return String String made up of numbers
     */
    public static String StringLengthOfN(long length) {
        String output = "";
        if (length > 19) {
            long numberOfPasses = length / 19;
            long remainder = length % 19;
            length = 19;
            for (int i = 0; i < numberOfPasses; i++) {
                long min = (long) Math.pow(10, length - 1);
                long max = ((long) Math.pow(10, length)) - 1;
                output += String.valueOf(ThreadLocalRandom.current().nextLong(min, max + 1));
            }
            long min = (long) Math.pow(10, remainder - 1);
            long max = ((long) Math.pow(10, remainder)) - 1;
            output += String.valueOf(ThreadLocalRandom.current().nextLong(min, max + 1));
            return output;

        } else if (length < 1) {
            return "";
        } else {

            long min = (long) Math.pow(10, length - 1);
            long max = ((long) Math.pow(10, length)) - 1;
            output += String.valueOf(ThreadLocalRandom.current().nextLong(min, max + 1));

            return output;
        }
    }
}
