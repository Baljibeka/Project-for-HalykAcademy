create sequence if not exists author_sequence start 1 increment 1;
create sequence if not exists book_sequence start 1 increment 1;
create sequence if not exists genre_sequence start 1 increment 1;
create sequence if not exists publisher_sequence start 1 increment 1;
create sequence if not exists user_sequence start 1 increment 1;

create table if not exists author (
                        author_id int8 not null,
                        date_of_birth date,
                        name varchar(255) not null,
                        patronymic varchar(255),
                        surname varchar(255),
                        primary key (author_id)
);

create table if not exists author_genre (
                              author_id int8 not null,
                              genre_id int8 not null
);

create table if not exists authors_book (
                              book_id int8 not null,
                              author_id int8 not null
);

create table if not exists book (
                      book_id int8 not null,
                      name varchar(255) not null,
                      number_of_pages int4 not null,
                      price int4 not null,
                      year_of_issue date not null,
                      publisher_id int8,
                      primary key (book_id)
);
CREATE TABLE if not exists users
(
    user_id  int8 NOT NULL,
    login    VARCHAR(255),
    password VARCHAR(255),
    role     INTEGER,
    status   BOOLEAN,
    CONSTRAINT pk_user PRIMARY KEY (user_id)
);

ALTER TABLE "users"
    ADD CONSTRAINT uc_user_login UNIQUE (login);

create table if not exists book_genre (
                            book_id int8 not null,
                            genre_id int8 not null
);

create table if not exists genre (
                       genre_id int8 not null,
                       name varchar(255) not null,
                       primary key (genre_id)
);

create table if not exists publisher (
                           publisher_id int8 not null,
                           name varchar(255) not null,
                           primary key (publisher_id)
);

alter table author_genre
    add constraint author_genre_fk unique (genre_id);


alter table book_genre
    add constraint book_genre_fk unique (genre_id);

alter table author_genre
    add constraint author_genre_fk
        foreign key (genre_id)
            references genre;

alter table author_genre
    add constraint genre_author_fk
        foreign key (author_id)
            references author;

alter table authors_book
    add constraint author_book_fk
        foreign key (author_id)
            references author;

alter table authors_book
    add constraint book_author_fk
        foreign key (book_id)
            references book;

alter table book
    add constraint book_publisher_fk
        foreign key (publisher_id)
            references publisher;

alter table book_genre
    add constraint book_genre_fk
        foreign key (genre_id)
            references genre;

alter table book_genre
    add constraint genre_book_fk
        foreign key (book_id)
            references book;