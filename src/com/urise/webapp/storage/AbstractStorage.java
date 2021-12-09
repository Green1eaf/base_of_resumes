package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(Resume r, int index);

    protected abstract void eraseResume(int index);

    protected abstract Resume getResume(int index);

    protected abstract void updateResume(Resume r, int index);

    protected abstract boolean isOverflow();

    @Override
    public final void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) throw new NotExistStorageException(r.getUuid());
        updateResume(r, index);
    }

    @Override
    public final void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) throw new ExistStorageException(r.getUuid());
        if (isOverflow()) throw new StorageException("Storage overflow", r.getUuid());
        insertResume(r, index);
    }

    @Override
    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) throw new NotExistStorageException(uuid);
        return getResume(index);
    }

    @Override
    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) throw new NotExistStorageException(uuid);
        eraseResume(index);
    }
}
