package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class AbstractArrayStorageTest {
    private Storage storage = new ArrayStorage();

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
        storage.clear();
        Assert.assertEquals(0, storage.size());
        storage.save(new Resume(UUID_1));
        Assert.assertEquals(1, storage.size());
        storage.delete(UUID_1);
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void clear() {
        Assert.assertEquals(3, storage.size());
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void update() {
        Resume r4 = new Resume(UUID_3);
        Assert.assertEquals(false, storage.get(UUID_3) == r4);
        storage.update(r4);
        Assert.assertEquals(true, storage.get(UUID_3) == r4);
        storage.update(new Resume("uuid4"));
    }

    @Test
    public void getAll() {
        Resume[] testStorage = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        Assert.assertEquals(true, Arrays.equals(storage.getAll(), testStorage));
        storage.delete(UUID_3);
        Assert.assertEquals(false, Arrays.equals(storage.getAll(), testStorage));
        storage.save(new Resume(UUID_3));
        storage.save(new Resume("uuid4"));
        Assert.assertEquals(false, Arrays.equals(storage.getAll(), testStorage));
    }

    @Test(expected = StorageException.class)
    public void save() {
        Assert.assertEquals(3, storage.size());
        storage.save(new Resume("uuid4"));
        Assert.assertEquals(4, storage.size());
        try {
            storage.save(new Resume(UUID_3));
        } catch (ExistStorageException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
        storage.clear();
        int count = 0;
        while (true) {
            storage.save(new Resume(Integer.toString(count++)));
        }
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        Assert.assertEquals(3, storage.size());
        storage.delete(UUID_2);
        Assert.assertEquals(2, storage.size());
        storage.delete(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void get() {
        Assert.assertEquals(new Resume(UUID_2), storage.get(UUID_2));
        storage.get("uuid5");
    }
}