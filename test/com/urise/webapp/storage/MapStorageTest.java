package com.urise.webapp.storage;

import org.junit.Ignore;
import org.junit.Test;


public class MapStorageTest extends AbstractStorageTest {

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Ignore("StorageException will never happen")
    @Override
    @Test
    public void saveStorageException() {
    }
}