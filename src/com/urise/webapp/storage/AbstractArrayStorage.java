package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void paste(Resume r, Integer searchKey);

    protected abstract void remove(Integer searchKey);

    @Override
    protected final Resume getResume(Object searchKey) {
        return storage[(Integer) searchKey];
    }

    @Override
    protected final void updateResume(Resume r, Object searchKey) {
        storage[(Integer) searchKey] = r;
    }

    @Override
    protected final void insertResume(Resume r, Object searchKey) {
        if (size == STORAGE_LIMIT) throw new StorageException("Storage overflow", r.getUuid());
        paste(r, (Integer) searchKey);
        size++;
    }

    @Override
    protected final void eraseResume(Object searchKey) {
        remove((Integer) searchKey);
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
    public final List<Resume> getAllSorted() {
        return Arrays.stream(Arrays.copyOf(storage, size)).sorted(AbstractStorage::compare).collect(Collectors.toList());

    }

    public final int size() {
        return size;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }
}
