package com.mage.gerrit.client;

import java.io.Closeable;
import java.io.IOException;

public interface GerritHttpConnection extends Closeable {

    <T extends Object> T get(String path, Class<T> cls) throws IOException;
}
