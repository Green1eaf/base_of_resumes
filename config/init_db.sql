CREATE TABLE resume
(
    uuid      CHAR(36) PRIMARY KEY NOT NULL,
    full_name TEXT                 NOT NULL
);

CREATE TABLE contact
(
    id          SERIAL,
    resume_uuid CHAR(36) NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
    type        TEXT     NOT NULL,
    value       TEXT     NOT NULL
);
CREATE UNIQUE INDEX contact_uuid_type_index
    ON contact (resume_uuid, type);

create table section
(
    id          serial
        constraint section_pk
            primary key,
    type        text     not null,
    value       text     not null,
    resume_uuid char(36) not null
        constraint section_resume_uuid_fk
            references resume (uuid)
            on update restrict on delete cascade
);
CREATE UNIQUE INDEX section_uuid_type_index
    ON section (resume_uuid, type);