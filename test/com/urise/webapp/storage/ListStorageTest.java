package com.urise.webapp.storage;

import org.junit.Test;

public class ListStorageTest extends AbstractStorageTest {

    public ListStorageTest() {
        super(new ListStorage());
    }

    @Override
    @Test
    public void saveStorageException() {
    }
}