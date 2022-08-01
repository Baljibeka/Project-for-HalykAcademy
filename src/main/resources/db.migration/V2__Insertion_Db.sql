INSERT INTO author (author_id, date_of_birth, name, patronymic, surname) VALUES
    (3, '1971-07-13', 'Fyodor', 'Dostoevsky', null);
INSERT INTO author (author_id, date_of_birth, name, patronymic, surname) VALUES
    (author_sequence.increment_by, '1971-07-13', 'Teodor', 'Drayzer', null);
INSERT INTO book (book_id, name, number_of_pages, price, year_of_issue, publisher_id)VALUES
    ( 5, 'Financial', 1000, 5000, '2020-07-13', null);
