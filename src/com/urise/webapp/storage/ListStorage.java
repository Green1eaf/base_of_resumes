package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> listStorage = new ArrayList<>();

    @Override
    protected final Integer getSearchKey(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (listStorage.get(i).getUuid().equals(uuid)) return i;
        }
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(listStorage);
    }

    @Override
    protected final void doUpdate(Resume r, Object searchKey) {
        listStorage.set((Integer) searchKey, r);
    }

    @Override
    protected final void doSave(Resume r, Object searchKey) {
        listStorage.add(r);
    }

    @Override
    protected final Resume doGet(Object searchKey) {
        return listStorage.get((Integer) searchKey);
    }

    @Override
    protected final void doDelete(Object searchKey) {
        listStorage.remove(((Integer) searchKey).intValue());
    }

    @Override
    public final void clear() {
        listStorage.clear();
    }

    @Override
    public final int size() {
        return listStorage.size();
    }
}
