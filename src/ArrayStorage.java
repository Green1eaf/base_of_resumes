import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < 10000; i++) {
            if (storage[i] != null) storage[i] = null;
            else break;
        }
        size = 0;
    }

    public void save(Resume r) {
        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume != null && resume.uuid.equals(uuid)) return resume;
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < 10000; i++) {
            if (storage[i] != null && storage[i].uuid.equals(uuid)) {
                for (int j = i + 1; j < 10000; j++) {
                    storage[j - 1] = storage[j];
                    if (storage[j] == null) break;
                }
                break;
            }
        }
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }
}
