CREATE TABLE "USER"
(
    username      varchar(20)    NOT NULL,
    first_Name    varchar(50)    NOT NULL,
    last_Name     varchar(50)    NOT NULL,
    email         varchar(50)    NOT NULL    UNIQUE,
    password      varchar(50)    NOT NULL,
    CONSTRAINT    USERPK
       PRIMARY KEY (username)
);

CREATE TABLE "ROOM"
(
    room_ID           UUID           NOT NULL,
    room_Admin        varchar(20)    NOT NULL,
    room_Name         varchar(50)    NOT NULL,
    subject           varchar(50)    NOT NULL,
    "location"        varchar(50)    NOT NULL,
    description       varchar(250),
    CONSTRAINT    ROOMPK
       PRIMARY KEY (room_ID),
    CONSTRAINT ROOMADMINFK
       FOREIGN KEY (room_Admin) REFERENCES "USER"(username)
                   ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE "USERROOM"
(
    username    varchar(20)    NOT NULL,
    room_ID     UUID           NOT NULL,
    join_Date   varchar(30),
    CONSTRAINT USERROOMPK
       PRIMARY KEY (username, room_ID),
    CONSTRAINT USERROOMUSERFK
       FOREIGN KEY (username) REFERENCES "USER"(username)
                    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT USERROOMROOMFK
       FOREIGN KEY (room_ID) REFERENCES "ROOM"(room_ID)
                    ON DELETE CASCADE ON UPDATE CASCADE
);