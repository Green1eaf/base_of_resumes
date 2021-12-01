package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    private int indexResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        System.out.println("ERROR: resume " + uuid + " not presence");
        return -1;
    }

    public void update(Resume r) {
        int index = indexResume(r.getUuid());
        if (index != -1) {
            storage[index] = r;
        }
    }

    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("ERROR: storage is full");
            return;
        }
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                System.out.println("ERROR: resume " + r.getUuid() + " already exists");
                return;
            }
        }
        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        int index = indexResume(uuid);
        if (index != -1) return storage[index];
        return null;
    }

    public void delete(String uuid) {
        int index = indexResume(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
