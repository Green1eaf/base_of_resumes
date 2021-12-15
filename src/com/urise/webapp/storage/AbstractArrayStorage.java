package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void paste(Resume r, Integer searchKey);

    protected abstract void remove(Integer searchKey);

    @Override
    protected final Resume doGet(Integer searchKey) {
        return storage[searchKey];
    }

    @Override
    protected final void doUpdate(Resume r, Integer searchKey) {
        storage[searchKey] = r;
    }

    @Override
    protected final void doSave(Resume r, Integer searchKey) {
        if (size == STORAGE_LIMIT) throw new StorageException("Storage overflow", r.getUuid());
        paste(r, searchKey);
        size++;
    }

    @Override
    protected final void doDelete(Integer searchKey) {
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
     * @return sorted list, contains only Resumes in storage (without null)
     */
    protected List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOf(storage, size()));
    }

    public final int size() {
        return size;
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }
}
