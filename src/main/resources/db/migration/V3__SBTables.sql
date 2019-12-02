CREATE TABLE "USER"
(
    username      varchar(20)    NOT NULL,
    first_name    varchar(50)    NOT NULL,
    last_name     varchar(50)    NOT NULL,
    email         varchar(50)    NOT NULL    UNIQUE,
    password      varchar(50)    NOT NULL,
    CONSTRAINT    USERPK
       PRIMARY KEY (username)
);

CREATE TABLE "ROOM"
(
    room_id           UUID           NOT NULL,
    room_admin        varchar(20)    NOT NULL,
    room_name         varchar(50)    NOT NULL,
    subject           varchar(50)    NOT NULL,
    "location"        varchar(50)    NOT NULL,
    description       varchar(250),
    CONSTRAINT    ROOMPK
       PRIMARY KEY (room_id),
    CONSTRAINT ROOMADMINFK
       FOREIGN KEY (room_admin) REFERENCES "USER"(username)
                   ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE "USERROOM"
(
    username    varchar(20)    NOT NULL,
    room_id     UUID           NOT NULL,
    join_date   varchar(30),
    CONSTRAINT USERROOMPK
       PRIMARY KEY (username, room_id),
    CONSTRAINT USERROOMUSERFK
       FOREIGN KEY (username) REFERENCES "USER"(username)
                    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT USERROOMROOMFK
       FOREIGN KEY (room_id) REFERENCES "ROOM"(room_id)
                    ON DELETE CASCADE ON UPDATE CASCADE
);