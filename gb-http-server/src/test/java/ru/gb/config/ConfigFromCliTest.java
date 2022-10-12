package ru.gb.config;

import org.junit.Assert;
import org.junit.Test;

public class ConfigFromCliTest {

    @Test
    public void testConfigFromCli() {
        Config config = new ConfigFromCli(new String[] {"first", "123"});
        Assert.assertEquals("first", config.getWWW());
        Assert.assertEquals(123, config.getPort());
    }

}
