package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.Month;
import java.util.*;

import static com.urise.webapp.model.ContactType.*;
import static com.urise.webapp.model.SectionType.*;

public class ResumeTestData {
    private static final Map<ContactType, String> contacts = new HashMap<>();
    static  {
        contacts.put(PHONE, "+7(921) 855-0482");
        contacts.put(MAIL, "gkislin@yandex.ru");
        contacts.put(SKYPE, "grigory.kislin");
        contacts.put(LINKEDIN, "linkedin.com/in/gkislin");
        contacts.put(GITHUB, "github.com/gkislin");
        contacts.put(STACKOVERFLOW, "stackoverflow.com/users/548473/grigory-kislin");
        contacts.put(HOMEPAGE, "gkislin.ru/");
    }

    private static final Map<SectionType, Section> sections = new HashMap<>();
    static {
        sections.put(PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        sections.put(OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));

        sections.put(ACHIEVEMENT, new ListSection(new ArrayList<>(List.of("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
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
                        "(Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."))));


        sections.put(QUALIFICATIONS, new ListSection(new ArrayList<>(List.of("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
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
                "Родной русский, английский \"upper intermediate\""))));


        sections.put(EXPERIENCE, new OrganisationSection(List.of(
                new Organisation("JavaOps", "javaops.ru", List.of(
                        new Organisation.Position(2013, Month.OCTOBER, "Автор проекта",
                                "Создание, организация и проведение Java онлайн проектов и стажировок."))),
                new Organisation("Wrike", "wrike.com/vn/", List.of(
                        new Organisation.Position(2014, Month.OCTOBER, 2016, Month.OCTOBER, "Старший разработчик (backend)",
                                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                                        "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."))),
                new Organisation("RIT Center", "RIT Center", List.of(
                        new Organisation.Position(2012, Month.APRIL, 2014, Month.OCTOBER, "Java архитектор",
                                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, " +
                                        "ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. " +
                                        "Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов " +
                                        "общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера " +
                                        "документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, " +
                                        "OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"))))));


        sections.put(EDUCATION, new OrganisationSection(List.of(
                new Organisation("Coursera", "www.coursera.org/learn/scala-functional-programming", List.of(
                        new Organisation.Position(2013, Month.MARCH, 2013, Month.MAY, "Student",
                                "Functional Programming Principles in Scala\" by Martin Odersky"))),
                new Organisation("Luxoft", "www.luxoft-training.ru/kurs/obektno-orientirovannyy_analiz_i_proektirovanie_na_uml.html", List.of(
                        new Organisation.Position(2011, Month.MARCH, 2013, Month.APRIL, "Student",
                                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\""))),
                new Organisation("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "", List.of(
                        new Organisation.Position(1993, Month.SEPTEMBER, 1996, Month.JULY, "Student",
                                "Аспирантура (программист С, С++)"),
                        new Organisation.Position(1987, Month.SEPTEMBER, 1993, Month.JULY, "Student",
                                "Инженер (программист Fortran, C)"))))));
    }

    public static Resume createResume(String uuid, String name) {
        return new Resume(uuid, name, contacts, sections);
    }

    public static void main(String[] args) {

        Resume r = createResume("uuid1","Кислин Григорий");

        System.out.println("ID: " + r);

        System.out.println("\nКонтакты:\n");
        for (ContactType ct : ContactType.values()) {
            System.out.println(ct.getTitle() + ": " + r.getContact(ct));
        }

        for (SectionType st : SectionType.values()) {
            System.out.println(st.getTitle() + ": " + r.getSection(st));
        }
    }
}
