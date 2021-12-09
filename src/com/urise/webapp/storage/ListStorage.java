package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.LinkedList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    List<Resume> listStorage = new LinkedList<>();

    @Override
    protected final int getIndex(String uuid) {
        int index = 0;
        for (Resume r : listStorage) {
            if (r.getUuid() == uuid) return index;
            index++;
        }
        return -1;
    }

    @Override
    protected final void insertResume(Resume r, int index) {
        listStorage.add(r);
    }

    @Override
    protected final void eraseResume(String uuid, int index) {
        listStorage.remove(index);
    }

    @Override
    protected final Resume getResume(String uuid, int index) {
        return listStorage.get(index);
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
        size = 0;
    }

    @Override
    public final Resume[] getAll() {
        Resume[] result = new Resume[size];
        int index = 0;
        for (Resume r : listStorage) {
            result[index++] = r;
        }
        return result;
    }
}
