package com.bde.flix.controller;

import java.util.concurrent.ThreadLocalRandom;

public class GenerateRandomValues {
    /**
     * Returns positive long of the length specified.
     * For bigger numbers cast it to string and concatenate.
     * For example lets take length equal to 5:
     * It will generate long from 10000..99999
     * If specified length is out of supported values,
     * max/min is applied instead.
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
}
