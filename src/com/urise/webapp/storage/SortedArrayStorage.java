package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertResume(Resume r, int index) {
        int insertIndex = -index - 1;
        if (size - insertIndex > 0)
            System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        storage[insertIndex] = r;
    }

    @Override
    protected void eraseResume(int index) {
        if (size - 1 - index > 0) {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        }
    }
}
