package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.LinkedList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> listStorage = new LinkedList<>();

    @Override
    protected final Object getSearchKey(String uuid) {
        int index = 0;
        for (Resume r : listStorage) {
            if (r.getUuid().equals(uuid)) return index;
            index++;
        }
        return -1;
    }

    @Override
    protected final void insertResume(Resume r, Integer searchKey) {
        listStorage.add(r);
    }

    @Override
    protected final void eraseResume(Integer searchKey) {
        listStorage.remove(searchKey.intValue());
    }

    @Override
    protected final Resume getResume(Object searchKey) {
        return listStorage.get((Integer) searchKey);
    }

    @Override
    protected final void updateResume(Resume r, Integer searchKey) {
        listStorage.add(searchKey, r);
    }

    @Override
    public final void clear() {
        listStorage.clear();
    }

    @Override
    public final Resume[] getAll() {
        return listStorage.toArray(new Resume[0]);
    }

    @Override
    public final int size() {
        return listStorage.size();
    }
}
