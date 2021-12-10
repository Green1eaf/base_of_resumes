package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract Object getSearchKey(String uuid);

    protected abstract void insertResume(Resume r, Integer searchKey);

    protected abstract void eraseResume(Integer searchKey);

    protected abstract Resume getResume(Object searchKey);

    protected abstract void updateResume(Resume r, Integer searchKey);

    @Override
    public final void update(Resume r) {
        int index = (Integer) getSearchKey(r.getUuid());
        if (index < 0) throw new NotExistStorageException(r.getUuid());
        updateResume(r, index);
    }

    @Override
    public void save(Resume r) {
        int index = (Integer) getSearchKey(r.getUuid());
        if (index >= 0) throw new ExistStorageException(r.getUuid());
        insertResume(r, index);
    }

    @Override
    public final Resume get(String uuid) {
        int index = (Integer) getSearchKey(uuid);
        if (index < 0) throw new NotExistStorageException(uuid);
        return getResume(index);
    }

    @Override
    public final void delete(String uuid) {
        int index = (Integer) getSearchKey(uuid);
        if (index < 0) throw new NotExistStorageException(uuid);
        eraseResume(index);
    }
}