package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.LinkedList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> listStorage = new LinkedList<>();

    @Override
    protected final Object getIndex(String uuid) {
        int index = 0;
        for (Resume r : listStorage) {
            if (r.getUuid().equals(uuid)) return index;
            index++;
        }
        return -1;
    }

    @Override
    protected final void insertResume(Resume r, Object index) {
        listStorage.add(r);
    }

    @Override
    protected final void eraseResume(Object index) {
        listStorage.remove(((Integer) index).intValue());
    }

    @Override
    protected final Resume getResume(Object uuid) {
        return listStorage.get((Integer) getIndex((String) uuid));
    }

    @Override
    protected final void updateResume(Resume r, Object index) {
        listStorage.add((Integer) index, r);
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
