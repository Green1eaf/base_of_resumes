package com.urise.webapp.model;

import java.time.LocalDate;
import java.time.YearMonth;

public class Organisation {
    private String homepage;
    private YearMonth beginDate;
    private YearMonth endDate;
    private String post;
    private String comment;

    public Organisation(String homepage, YearMonth beginDate, YearMonth endDate, String post, String comment) {
        this.homepage = homepage;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.post = post;
        this.comment = comment;
    }

    public String getHomepage() {
        return homepage;
    }

    public YearMonth getBeginDate() {
        return beginDate;
    }

    public YearMonth getEndDate() {
        return endDate;
    }

    public String getPost() {
        return post;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "Organisation{" +
                "homepage='" + homepage + '\'' +
                ", beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", post='" + post + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organisation that = (Organisation) o;

        if (!homepage.equals(that.homepage)) return false;
        if (!beginDate.equals(that.beginDate)) return false;
        if (!endDate.equals(that.endDate)) return false;
        if (!post.equals(that.post)) return false;
        return comment.equals(that.comment);
    }

    @Override
    public int hashCode() {
        int result = homepage.hashCode();
        result = 31 * result + beginDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + post.hashCode();
        result = 31 * result + comment.hashCode();
        return result;
    }
}
