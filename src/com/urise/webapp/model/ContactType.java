package com.urise.webapp.model;

public enum ContactType {
    PHONE("Номер телефона"),
    EMAIL("Адрес электронной почты"),
    SKYPE("Скайп"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль Stackoverflow"),
    HOMEPAGE("Адрес домашней страницы");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
