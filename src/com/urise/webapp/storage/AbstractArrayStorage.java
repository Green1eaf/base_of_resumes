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

    protected abstract void paste(Resume r, Object index);

    protected abstract void remove(Object uuid);

    @Override
    protected final Resume getResume(Object uuid) {
        return storage[(Integer) getIndex((String) uuid)];
    }

    @Override
    protected final void updateResume(Resume r, Object index) {
        storage[(Integer) index] = r;
    }

    @Override
    protected final void insertResume(Resume r, Object index) {
        if (size == STORAGE_LIMIT) throw new StorageException("Storage overflow", r.getUuid());
        paste(r, index);
        size++;
    }

    @Override
    protected final void eraseResume(Object index) {
        int numMoved = size - 1 - (Integer) index;
        if (numMoved > 0) {
            System.arraycopy(storage, (Integer) index + 1, storage, (Integer) index, numMoved);
        }
        remove(index);
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
