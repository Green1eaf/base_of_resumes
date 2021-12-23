package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonth().getValue());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    private Section readSection(DataInputStream dis, SectionType key) throws IOException {
        int size;
        switch (key) {
            case PERSONAL:
            case OBJECTIVE:
                return new TextSection(dis.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                size = dis.readInt();
                List<String> listSectionItems = new ArrayList<>(size);
                for (int j = 0; j < size; j++) {
                    listSectionItems.add(dis.readUTF());
                }
                return new ListSection(listSectionItems);
            case EXPERIENCE:
            case EDUCATION:
                size = dis.readInt();
                List<Organisation> listOrganisation = new ArrayList<>(size);
                for (int j = 0; j < size; j++) {
                    String name = dis.readUTF();
                    String url = dis.readUTF();
                    size = dis.readInt();
                    List<Organisation.Position> positionList = new ArrayList<>(size);
                    for (int k = 0; k < size; k++) {
                        positionList.add(new Organisation.Position(readLocalDate(dis),
                                readLocalDate(dis), dis.readUTF(), dis.readUTF()));
                    }
                    listOrganisation.add(new Organisation(name, url, positionList));
                }
                return new OrganisationSection(listOrganisation);
            default:
                throw new IllegalStateException();
        }
    }

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            //TODO implements sections
            Map<SectionType, Section> sections = r.getSections();
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType key = entry.getKey();
                dos.writeUTF(key.name());
                switch (key) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) entry.getValue()).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ListSection listSection = (ListSection) entry.getValue();
                        dos.writeInt(listSection.getItems().size());
                        for (String item : listSection.getItems()) {
                            dos.writeUTF(item);
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        OrganisationSection organisationSection = (OrganisationSection) entry.getValue();
                        List<Organisation> organisations = organisationSection.getOrganisations();
                        dos.writeInt(organisations.size());
                        for (Organisation o : organisations) {
                            dos.writeUTF(o.getHomePage().getName());
                            dos.writeUTF(o.getHomePage().getUrl());
                            List<Organisation.Position> positions = o.getPositions();
                            dos.writeInt(positions.size());
                            for (Organisation.Position pos : positions) {
                                writeLocalDate(dos, pos.getStartDate());
                                writeLocalDate(dos, pos.getEndDate());
                                dos.writeUTF(pos.getEndDate().toString());
                                dos.writeUTF(pos.getTitle());
                                dos.writeUTF(pos.getDescription());
                            }
                        }
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            //TODO implements sections
            for (int i = 0; i < SectionType.values().length; i++) {
                SectionType key = SectionType.valueOf(dis.readUTF());
                resume.addSection(key, readSection(dis, key));
            }
            return resume;
        }
    }
}
