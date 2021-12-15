package com.urise.webapp.model;

import java.util.*;

import static com.urise.webapp.model.ContactType.*;
import static com.urise.webapp.model.SectionType.*;

public class ResumeTestData {

    public static void main(String[] args) {
        Map<ContactType, String> contacts = new HashMap<>();
        contacts.put(PHONE, "+7(921) 855-0482");
        contacts.put(EMAIL, "gkislin@yandex.ru");
        contacts.put(SKYPE, "grigory.kislin");
        contacts.put(LINKEDIN, "linkedin.com/in/gkislin");
        contacts.put(GITHUB, "github.com/gkislin");
        contacts.put(STACKOVERFLOW, "stackoverflow.com/users/548473/grigory-kislin");
        contacts.put(HOMEPAGE, "gkislin.ru/");

        Map<SectionType, Section> sections = new HashMap<>();

        String personal = "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.";
        String objective = "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям";
        sections.put(PERSONAL, new TextSection(personal));
        sections.put(OBJECTIVE, new TextSection(objective));

        List<String> achievment = new ArrayList<>(Arrays.asList("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
                        "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
                        "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников. ",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                        "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk. ",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. " +
                        "Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации " +
                        "различных ERP модулей, интеграция CIFS/SMB java сервера.",
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, " +
                        "GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). " +
                        "Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования " +
                        "и мониторинга системы по JMX (Jython/ Django).",
                "Реализация протоколов по приему платежей всех основных платежных системы России " +
                        "(Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."));
        sections.put(ACHIEVEMENT, new ListSection(achievment));

        List<String> qualification = new ArrayList<>(Arrays.asList("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB",
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy, XML/XSD/XSLT, SQL, C/C++, Unix shell scripts",
                "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), " +
                        "JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).",
                "Python: Django.",
                "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js",
                "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka",
                "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, " +
                        "Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.",
                "Инструменты: Maven + plugin development, Gradle, настройка Ngnix",
                "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer",
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования",
                "Родной русский, английский \"upper intermediate\""));
        sections.put(QUALIFICATIONS, new ListSection(qualification));

        List<Organisation> list = new ArrayList<>();
        String homepage = "javaops.ru";
        String beginDate = "10/2013";
        String endDate = "Сейчас";
        String post = "Автор проекта";
        String comment = "Создание, организация и проведение Java онлайн проектов и стажировок.";
        Organisation organisation = new Organisation(homepage, beginDate, endDate, post, comment);
        list.add(organisation);

        homepage = "wrike.com/vn/";
        beginDate = "10/2014";
        endDate = "01/2016";
        post = "Старший разработчик (backend)";
        comment = "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.";
        organisation = new Organisation(homepage, beginDate, endDate, post, comment);
        list.add(organisation);

        homepage = "RIT Center";
        beginDate = "04/2012";
        endDate = "10/2014";
        post = "Java архитектор";
        comment = "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, " +
                "ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. " +
                "Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов " +
                "общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера " +
                "документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, " +
                "OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python";
        organisation = new Organisation(homepage, beginDate, endDate, post, comment);
        list.add(organisation);

        sections.put(EXPERIENCE, new OrganisationSection(list));

        List<Organisation> listEducation = new ArrayList<>();
        homepage = "www.coursera.org/learn/scala-functional-programming";
        beginDate = "03/2013";
        endDate = "05/2013";
        post = "Student";
        comment = "Functional Programming Principles in Scala\" by Martin Odersky";
        organisation = new Organisation(homepage, beginDate, endDate, post, comment);
        listEducation.add(organisation);

        homepage = "www.luxoft-training.ru/kurs/obektno-orientirovannyy_analiz_i_proektirovanie_na_uml.html";
        beginDate = "03/2011";
        endDate = "04/2011";
        post = "Student";
        comment = "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"";
        organisation = new Organisation(homepage, beginDate, endDate, post, comment);
        listEducation.add(organisation);
        sections.put(EDUCATION, new OrganisationSection(listEducation));

        Resume r = new Resume("Кислин Григорий", contacts, sections);

        System.out.println("ID: " + r.getUuid());
        System.out.println("\nПолное имя: " + r.getFullName());

        System.out.println("\nКонтакты:\n" +
                PHONE.getTitle() + ": " + r.getContacts().get(PHONE) + "\n" +
                EMAIL.getTitle() + ": " + r.getContacts().get(EMAIL) + "\n" +
                SKYPE.getTitle() + ": " + r.getContacts().get(SKYPE) + "\n" +
                LINKEDIN.getTitle() + ": " + r.getContacts().get(LINKEDIN) + "\n" +
                GITHUB.getTitle() + ": " + r.getContacts().get(GITHUB) + "\n" +
                STACKOVERFLOW.getTitle() + ": " + r.getContacts().get(STACKOVERFLOW) + "\n" +
                HOMEPAGE.getTitle() + ": " + r.getContacts().get(HOMEPAGE));


        System.out.println("\n" + PERSONAL.getTitle() + ": \n" +
                ((TextSection) r.getSections().get(PERSONAL)).getText());

        System.out.println("\n" + OBJECTIVE.getTitle() + ": \n" +
                ((TextSection) r.getSections().get(OBJECTIVE)).getText());

        System.out.println("\n" + ACHIEVEMENT.getTitle() + ": ");
        ((ListSection) r.getSections().get(ACHIEVEMENT)).getStorage().forEach(System.out::println);

        System.out.println("\n" + QUALIFICATIONS.getTitle() + ": ");
        ((ListSection) r.getSections().get(QUALIFICATIONS)).getStorage().forEach(System.out::println);

        System.out.println("\n" + EXPERIENCE.getTitle() + ": ");
        ((OrganisationSection) r.getSections().get(EXPERIENCE)).getStorage().forEach(s -> {
            System.out.println("homepage: " + s.getHomepage() + "\n" +
                    "Date: " + s.getBeginDate() + " - " + s.getEndDate() + "\n" +
                    "Post: " + s.getPost() + "\n" +
                    "Comment: " + s.getComment());
        });

        System.out.println("\n" + EDUCATION.getTitle() + ": ");
        ((OrganisationSection) r.getSections().get(EDUCATION)).getStorage().forEach(s -> {
            System.out.println("homepage: " + s.getHomepage() + "\n" +
                    "Date: " + s.getBeginDate() + " - " + s.getEndDate() + "\n" +
                    "Post: " + s.getPost() + "\n" +
                    "Comment: " + s.getComment());
        });
    }
}
