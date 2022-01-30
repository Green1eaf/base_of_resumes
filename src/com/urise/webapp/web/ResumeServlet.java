package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.*;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.urise.webapp.model.SectionType.*;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init() {
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume r = storage.get(uuid);
        r.setFullName(fullName);
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                r.addContact(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }

        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            if (value == null || value.trim().length() == 0) {
                r.getSections().remove(type);
            } else {
                switch (type.name()) {
                    case "PERSONAL":
                    case "OBJECTIVE":
                        r.addSection(type, new TextSection(value));
                        break;
                    case "ACHIEVEMENT":
                    case "QUALIFICATIONS":
                        r.addSection(type, new ListSection(value.split("\n")));
                        break;
                    case "EXPERIENCE":
                    case "EDUCATION":
                        r.getSections().remove(type);
                }
            }
        }
        storage.update(r);
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
                r = storage.get(uuid);
                removeSpaces(r); //NPE here
                break;
            case "edit":
                r = storage.get(uuid);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "WEB-INF/jsp/view.jsp" : "WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }

    public static void removeSpaces(Resume r) {
        if (r.getSections() != null && r.getContacts()!=null) {
            for (SectionType type : values()) {
                if (type == ACHIEVEMENT || type == QUALIFICATIONS) {
                    Section ls = r.getSections().get(type);
                    List<String> list = ((ListSection) ls).getItems().stream().map(String::trim).filter(s -> s.length() > 1).collect(Collectors.toList());
                    r.addSection(type, new ListSection(list));
                }
            }
        }
    }
}
