package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {
    private final Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        return mapStorage.containsKey(uuid)? uuid : null;
    }

    @Override
    protected void doSave(Resume r, String uuid) {
        mapStorage.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(String uuid) {
        mapStorage.remove(uuid);
    }

    @Override
    protected Resume doGet(String uuid) {
        return mapStorage.get(uuid);
    }

    @Override
    protected void doUpdate(Resume r, String uuid) {
        mapStorage.put(uuid, r);
    }

    @Override
    protected boolean isExist(String uuid) {
        return uuid != null;
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