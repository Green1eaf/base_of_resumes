package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected final Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) return i;
        }
        return -1;
    }

    @Override
    protected final void paste(Resume r, Integer searchKey) {
        storage[size] = r;
    }

    @Override
    protected final void remove(Integer searchKey) {
        storage[searchKey] = storage[size - 1];
    }
}
