import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];

    public void clear() {
        for (int i = 0; i < 10000; i++) {
            if (storage[i] != null) {
                storage[i] = null;
            } else break;
        }
    }

    public void save(Resume r) {
        storage[size()] = r;
    }

    public Resume get(String uuid) {
        for (Resume resume : storage) {
            if (Objects.equals(resume, uuid)) return resume;
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < 10000; i++) {
            if (Objects.equals(storage[i], uuid)) {
                for (int j = i + 1; j < 10000; j++) {
                    storage[j - 1] = storage[j];
                    if (storage[j] == null) break;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size());
    }

    public int size() {
        for (int i = 0; i < 10000; i++) {
            if (storage[i] == null) {
                return i;
            }
        }
        return 10000;
    }
}
