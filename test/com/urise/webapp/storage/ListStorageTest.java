package com.urise.webapp.storage;

import org.junit.Ignore;
import org.junit.Test;

public class ListStorageTest extends AbstractStorageTest {

    public ListStorageTest() {
        super(new ListStorage());
    }

    @Ignore("StorageException will never happen")
    @Override
    @Test
    public void saveStorageException() {
    }
}