package ru.job4j.io;

import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int age = 33;
        boolean man = true;
        byte b = 120;
        short s = 32000;
        long l = 3000000000L;
        float f = 5.5F;
        double d = 6.6;
        char c = 'X';
        LOG.debug("User info age : {}, man? : {}, b : {}, s : {}, l : {}, f : {}, d : {}, c : {}", age, man, b, s, l, f, d, c);
    }
}
