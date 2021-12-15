package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {
    private final Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        if (mapStorage.containsKey(uuid)) return uuid;
        return null;
    }

    @Override
    protected void doSave(Resume r, String searchKey) {
        mapStorage.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(String searchKey) {
        mapStorage.remove(searchKey);
    }

    @Override
    protected Resume doGet(String searchKey) {
        return mapStorage.get(searchKey);
    }

    @Override
    protected void doUpdate(Resume r, String searchKey) {
        mapStorage.put(searchKey, r);
    }

    @Override
    protected boolean isExist(String searchKey) {
        return searchKey != null;
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(mapStorage.values());
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}