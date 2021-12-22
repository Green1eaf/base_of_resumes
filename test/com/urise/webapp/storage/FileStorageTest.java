package com.urise.webapp.storage;

import com.urise.webapp.storage.serializator.ObjectStreamSerializator;

public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializator()));
    }
}
