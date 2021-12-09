package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.LinkedList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> listStorage = new LinkedList<>();

    @Override
    protected final int getIndex(String uuid) {
        int index = 0;
        for (Resume r : listStorage) {
            if (r.getUuid().equals(uuid)) return index;
            index++;
        }
        return -1;
    }

    @Override
    protected final void insertResume(Resume r, int index) {
        listStorage.add(r);
    }

    @Override
    protected final void eraseResume(String uuid) {
        listStorage.remove(getIndex(uuid));
    }

    @Override
    protected final Resume getResume(String uuid) {
        return listStorage.get(getIndex(uuid));
    }

    @Override
    protected final void updateResume(Resume r, int index) {
        listStorage.add(index, r);
    }

    @Override
    protected final boolean isOverflow() {
        return false;
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
