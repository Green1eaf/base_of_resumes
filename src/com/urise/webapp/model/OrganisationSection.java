package com.urise.webapp.model;

import java.util.List;

public class OrganisationSection implements Section {
    private List<Organisation> storage;

    public OrganisationSection(List<Organisation> storage) {
        this.storage = storage;
    }

    public List<Organisation> getStorage() {
        return storage;
    }

    @Override
    public String toString() {
        return "OrganisationSection{" +
                "storage=" + storage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganisationSection that = (OrganisationSection) o;

        return storage.equals(that.storage);
    }

    @Override
    public int hashCode() {
        return storage.hashCode();
    }
}
