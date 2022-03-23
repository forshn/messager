package ru.forsh.sweater;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DumbPasswordEncoderTest {

    @Test
    void encode() {
        DumbPasswordEncoder dumbPasswordEncoder = new DumbPasswordEncoder();
        assertEquals("secret: 'mypwd'", dumbPasswordEncoder.encode("mypwd"));
        Assert.assertThat(dumbPasswordEncoder.encode("mypwd"), Matchers.containsString("mypwd"));
    }
}