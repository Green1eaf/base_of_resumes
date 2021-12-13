package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    protected final void updateResume(Resume r, Object searchKey) {
        listStorage.set((Integer) searchKey, r);
    }

    @Override
    protected final void insertResume(Resume r, Object searchKey) {
        listStorage.add(r);
    }

    @Override
    protected final Resume getResume(Object searchKey) {
        return listStorage.get((Integer) searchKey);
    }

    @Override
    protected final void eraseResume(Object searchKey) {
        listStorage.remove(((Integer) searchKey).intValue());
    }

    @Override
    public final void clear() {
        listStorage.clear();
    }

    @Override
    public final List<Resume> getAllSorted() {
        return listStorage.stream().sorted(AbstractStorage::compare).collect(Collectors.toList());
    }

    @Override
    public final int size() {
        return listStorage.size();
    }
}
