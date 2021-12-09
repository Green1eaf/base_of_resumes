package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected final Integer getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) return i;
        }
        return -1;
    }

    @Override
    protected final void paste(Resume r, Object index) {
        storage[size] = r;
    }

    @Override
    protected final void remove(Object index) {
        storage[(Integer) index] = storage[size - 1];
    }
}
