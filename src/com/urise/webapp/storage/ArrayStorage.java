package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected final int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) return i;
        }
        return -1;
    }

    @Override
    protected final void insertResume(Resume r, int index) {
        storage[size] = r;
        size++;
    }

    @Override
    protected final void eraseResume(String uuid) {
        storage[getIndex(uuid)] = storage[size - 1];
        size--;
    }
}
