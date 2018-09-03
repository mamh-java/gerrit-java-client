package com.mage.gerrit.server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class NetRCTest {
    private NetRC netRC;

    @Before
    public void before() {
        netRC = new NetRC();
    }

    @Test
    public void getEntry() {
        NetRC.NetRCEntry entry = netRC.getEntry("github.com");
        Assert.assertEquals("bright.ma", entry.login);
    }

    @Test
    public void getEntries() {
        Collection<NetRC.NetRCEntry> entries = netRC.getEntries();
        Assert.assertEquals(2, entries.size());
    }
}