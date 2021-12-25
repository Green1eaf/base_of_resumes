package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = new File("./storage");

    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume R1 =ResumeTestData.createResume(UUID_1, "Petrov Ivan");
    private static final Resume R2 = ResumeTestData.createResume(UUID_2, "Ivanova Elena");
    private static final Resume R3 =ResumeTestData.createResume(UUID_3, "Petrov Ivan");
    private static final Resume R4 = new Resume(UUID_4, "name");

//    static {
//        R1 = new Resume(UUID_1, "Name1");
//        R2 = new Resume(UUID_2, "Name2");
//        R3 = new Resume(UUID_3, "Name3");
//        R4 = new Resume(UUID_4, "Name4");
//
//        R1.addContact(ContactType.MAIL, "mail1@ya.ru");
//        R1.addContact(ContactType.PHONE, "11111");
//        R1.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
//        R1.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
//        R1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
//        R1.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
//        R1.addSection(SectionType.EXPERIENCE,
//                new OrganisationSection(
//                        new Organisation("Organisation11", "http://Organisation11.ru",
//                        new Organisation.Position(2005, Month.JANUARY, "position1", "content1"),
//                        new Organisation.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
//        R1.addSection(SectionType.EDUCATION,
//                new OrganisationSection((
//                        new Organisation("institute", null,
//                                new Organisation.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
//                                new Organisation.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet"))),
//                        new Organisation("Organosation12", "http://Organisation12.ru")));
//        R2.addContact(ContactType.SKYPE, "skype2");
//        R2.addContact(ContactType.PHONE, "22222");
//        R2.addSection(SectionType.EXPERIENCE,
//                new OrganisationSection(
//                        new Organisation("Organisation2", "http://Organisation2.ru",
//                                new Organisation.Position(2015, Month.JANUARY, "position1", "content1"))));
//    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "New Name");
        storage.update(newResume);
        assertTrue(newResume.equals(storage.get(UUID_1)));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistException() {
        storage.get("dummy");
    }

    @Test
    public void save() {
        storage.save(R4);
        assertSize(4);
        assertGet(R4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistStorageException() {
        storage.save(R1);
    }

    @Test
    public void get() {
        assertGet(R1);
        assertGet(R2);
        assertGet(R3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistStorageException() {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistStorageException() {
        storage.delete("dummy");
    }

    @Test
    public void getAllSorted() {
        List<Resume> expectedStorage = new ArrayList<>(Arrays.asList(
                ResumeTestData.createResume(UUID_2, "Ivanova Elena"),
                ResumeTestData.createResume(UUID_1, "Petrov Ivan"),
                ResumeTestData.createResume(UUID_3, "Petrov Ivan")));
        Assert.assertEquals(3, storage.getAllSorted().size());
        Assert.assertEquals(expectedStorage, storage.getAllSorted());
    }

    @Test
    public void size() {
        assertSize(3);
    }


    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}