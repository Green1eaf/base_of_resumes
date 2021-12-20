package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private final List<Resume> listStorage = new ArrayList<>();

    @Override
    protected final Integer getSearchKey(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (listStorage.get(i).getUuid().equals(uuid)) return i;
        }
        return null;
    }

    @Override
    protected boolean isExist(Integer index) {
        return index != null;
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(listStorage);
    }

    @Override
    protected final void doUpdate(Resume r, Integer index) {
        listStorage.set(index, r);
    }

    @Override
    protected final void doSave(Resume r, Integer index) {
        listStorage.add(r);
    }

    @Override
    protected final Resume doGet(Integer index) {
        return listStorage.get(index);
    }

    @Override
    protected final void doDelete(Integer index) {
        listStorage.remove(index.intValue());
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
