package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) return i;
        }
        return -1;
    }

    @Override
    public void save(Resume r) {
        if (getIndex(r.getUuid()) >= 0) {
            System.out.println("ERROR: resume " + r.getUuid() + " already exists");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("ERROR: storage overflow");
        } else {
            storage[size] = r;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: resume " + uuid + " not exists");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }
}
