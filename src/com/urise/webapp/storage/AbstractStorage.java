package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractStorage implements Storage {
    protected abstract Object getSearchKey(String uuid);

    protected abstract void insertResume(Resume r, Object searchKey);

    protected abstract void eraseResume(Object searchKey);

    protected abstract Resume getResume(Object searchKey);

    protected abstract void updateResume(Resume r, Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract List<Resume> getStorage();

    @Override
    public final void update(Resume r) {
        Object searchKey = getExistedSearchKey(r.getUuid());
        updateResume(r, searchKey);
    }

    @Override
    public void save(Resume r) {
        Object searchKey = getNotExistedSearchKey(r.getUuid());
        insertResume(r, searchKey);
    }

    @Override
    public final Resume get(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        return getResume(searchKey);
    }

    @Override
    public final void delete(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        eraseResume(searchKey);
    }

    public List<Resume> getAllSorted() {
        List<Resume> storage = getStorage();
        return storage.stream().
                sorted(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid)).
                collect(Collectors.toList());
    }

    private Object getExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) throw new NotExistStorageException(uuid);
        return searchKey;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) throw new ExistStorageException(uuid);
        return searchKey;
    }
}