package com.urise.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OrganisationSection implements Section {
    private final List<Organisation> organisations;

    public OrganisationSection(Organisation... organisations) {
        this(Arrays.asList(organisations));
    }

    public OrganisationSection(List<Organisation> organisations) {
        Objects.requireNonNull(organisations, "organisations must not be null");
        this.organisations = organisations;
    }

    public List<Organisation> getOrganisations() {
        return organisations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganisationSection that = (OrganisationSection) o;

        return organisations.equals(that.organisations);
    }

    @Override
    public int hashCode() {
        return organisations.hashCode();
    }

    @Override
    public String toString() {
        return organisations.toString();
    }
}
