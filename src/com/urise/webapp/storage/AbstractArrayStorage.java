package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract void paste(Resume r, Integer searchKey);

    protected abstract void remove(Integer searchKey);

    @Override
    protected final Resume getResume(Object searchKey) {
        return storage[(Integer) searchKey];
    }

    @Override
    protected final void updateResume(Resume r, Integer searchKey) {
        storage[searchKey] = r;
    }

    @Override
    protected final void insertResume(Resume r, Integer searchKey) {
        if (size == STORAGE_LIMIT) throw new StorageException("Storage overflow", r.getUuid());
        paste(r, searchKey);
        size++;
    }

    @Override
    protected final void eraseResume(Integer searchKey) {
        remove(searchKey);
        storage[size] = null;
        size--;
    }

    @Override
    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public final Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public final int size() {
        return size;
    }
}
