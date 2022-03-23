package ru.forsh.sweater;

import org.junit.Assert;
import org.junit.Test;

public class SimpleTests {

    @Test
    public void test(){
        int x = 3;
        int y = 5;

        Assert.assertEquals("всё ерунда", x,y);
    }
}
