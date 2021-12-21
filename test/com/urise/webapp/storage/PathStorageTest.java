package com.urise.webapp.storage;

import com.urise.webapp.storage.serializator.ObjectStream;

public class PathStorageTest extends AbstractStorageTest{
    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStream()));
    }
}