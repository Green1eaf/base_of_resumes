package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    protected Storage storage;

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final Resume RESUME_1 = new Resume(UUID_1, "Petrov Ivan");
    protected static final Resume RESUME_2 = new Resume(UUID_2, "Ivanova Elena");
    protected static final Resume RESUME_3 = new Resume(UUID_3, "Petrov Ivan");

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        assertEquals(3, storage.size());
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume r4 = new Resume(UUID_3, "Ivanov Ivan");
        Assert.assertNotSame(storage.get(UUID_3), r4);
        storage.update(r4);
        Assert.assertSame(storage.get(UUID_3), r4);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistException() {
        storage.update(new Resume("uuid4", "Ivanov Ivan"));
    }

    @Test
    public void save() {
        Resume r4 = new Resume("uuid4", "Ivanov Ivan");
        storage.save(r4);
        assertEquals(4, storage.size());
        assertEquals(r4, storage.get("uuid4"));
    }

    @Test(expected = StorageException.class)
    public void saveStorageException() {
        try {
            for (int i = 3; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("Ivanov Sergei"));
            }
        } catch (StorageException e) {
            Assert.fail("Overflow occurred ahead of time");
        }
        storage.save(new Resume("Ivanov Sergei"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistStorageException() {
        storage.save(new Resume(UUID_3, "Ivanov Ivan"));
    }

    @Test
    public void get() {
        Resume test = storage.get(UUID_2);
        assertEquals(new Resume(UUID_2, "Ivanov Ivan"), test);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistStorageException() {
        storage.get("uuid5");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        assertEquals(2, storage.size());
        storage.get(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistStorageException() {
        storage.delete("uuid4");
    }

    @Test
    public void getAllSorted() {
        List<Resume> test = storage.getAllSorted();
        assertEquals(3, test.size());
        assertEquals(RESUME_1, test.get(1));
        assertEquals(RESUME_2, test.get(0));
        assertEquals(RESUME_3, test.get(2));
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
        storage.clear();
        assertEquals(0, storage.size());
    }
}