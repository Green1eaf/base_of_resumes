package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract Object getIndex(String uuid);

    protected abstract void insertResume(Resume r, Object index);

    protected abstract void eraseResume(Object uuid);

    protected abstract Resume getResume(Object uuid);

    protected abstract void updateResume(Resume r, Object index);

    @Override
    public final void update(Resume r) {
        int index = (Integer) getIndex(r.getUuid());
        if (index < 0) throw new NotExistStorageException(r.getUuid());
        updateResume(r, index);
    }

    @Override
    public void save(Resume r) {
        int index = (Integer) getIndex(r.getUuid());
        if (index >= 0) throw new ExistStorageException(r.getUuid());
        insertResume(r, index);
    }

    @Override
    public final Resume get(String uuid) {
        int index = (Integer) getIndex(uuid);
        if (index < 0) throw new NotExistStorageException(uuid);
        return getResume(uuid);
    }

    @Override
    public final void delete(String uuid) {
        int index = (Integer) getIndex(uuid);
        if (index < 0) throw new NotExistStorageException(uuid);
        eraseResume(index);
    }
}