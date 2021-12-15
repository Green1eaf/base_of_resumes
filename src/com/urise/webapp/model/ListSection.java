package com.urise.webapp.model;

import java.util.List;

public class ListSection implements Section {
    private List<String> storage;

    public ListSection(List<String> listSection) {
        this.storage = listSection;
    }

    public List<String> getStorage() {
        return storage;
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "listSection=" + storage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return storage.equals(that.storage);
    }

    @Override
    public int hashCode() {
        return storage.hashCode();
    }
}
