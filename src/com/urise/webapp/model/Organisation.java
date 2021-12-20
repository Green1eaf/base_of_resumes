package com.urise.webapp.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import com.urise.webapp.util.DateUtil;

public class Organisation {
    private final Link homepage;

    private final List<Position> positions;

    public Organisation(String name, String url, List<Position> list) {
        this.homepage = new Link(name, url);
        positions = list;
    }

    @Override
    public String toString() {
        return homepage + ": positions=" + positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organisation that = (Organisation) o;

        if (!homepage.equals(that.homepage)) return false;
        return positions.equals(that.positions);
    }

    @Override
    public int hashCode() {
        int result = homepage.hashCode();
        result = 31 * result + positions.hashCode();
        return result;
    }

    public static class Position {

        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String title;
        private final String description;

        public Position(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(DateUtil.of(startYear, startMonth), DateUtil.of(startYear, endMonth), title, description);
        }

        public Position(LocalDate startDate, LocalDate endDate, String title, String description) {
            Objects.requireNonNull(startDate,"beginDate must not be null");
            Objects.requireNonNull(endDate,"endDate must not be null");
            Objects.requireNonNull(title,"title must not be null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "startDate=" + startDate +
                    ", endDate=" + endDate +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Position position = (Position) o;

            if (!startDate.equals(position.startDate)) return false;
            if (!endDate.equals(position.endDate)) return false;
            if (!title.equals(position.title)) return false;
            return description.equals(position.description);
        }

        @Override
        public int hashCode() {
            int result = startDate.hashCode();
            result = 31 * result + endDate.hashCode();
            result = 31 * result + title.hashCode();
            result = 31 * result + description.hashCode();
            return result;
        }
    }
}
