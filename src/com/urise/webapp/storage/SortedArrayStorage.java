package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("ERROR: resume " + r.getUuid() + " already exists");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("ERROR: storage overflow");
        } else {
            for (int i = size; i > -(index + 1); i--) {
                storage[i] = storage[i - 1];
            }
            storage[-(index + 1)] = r;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: resume " + uuid + " not exists");
        } else {
            for (int i = index; i < size - 1; i++) {
                storage[i] = storage[i + 1];
            }
            size--;
        }
    }
}
