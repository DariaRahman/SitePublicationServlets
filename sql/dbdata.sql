INSERT INTO roles VALUES (0, 'admin');
INSERT INTO roles VALUES (1, 'client');

INSERT INTO users VALUES (DEFAULT, 0, 'admin@gmail.com', 'admin', 'Daria', 'Rahman', 'FEMALE', DEFAULT, DEFAULT);
INSERT INTO users VALUES (DEFAULT, 1, 'testuser@gmail.com', 'testpass', 'Ivan', 'Ivanov', 'MALE', DEFAULT, DEFAULT);

INSERT INTO periodicals_db.periodicals
VALUES (DEFAULT, 'Fred Ryan', 'Daily', 'Newspaper', 'The Washington Post', 4,
        'The Washington Post is an American daily newspaper published in Washington'),
       (DEFAULT, 'A. G. Sulzberger', 'Daily', 'Newspaper', 'The New York Times', 5,
        'The New York Times is an American daily newspaper based in New York City with a worldwide readership'),
       (DEFAULT, 'Marvel', 'Daily', 'Comic', 'X-Men', 7,
        'The X-Men are a team of fictional mutant superheroes published by Marvel Comics'),
       (DEFAULT, 'DC Comics', 'Weekly', 'Comic', 'Sandman', 7,
        'The Sandman is an American comic book written by Neil Gaiman and published by DC Comics'),
       (DEFAULT, 'DC Comics', 'Weekly', 'Comic', 'Justice League', 8,
        'The Justice League is a team of fictional superheroes appearing in American comic books published by DC Comics'),
       (DEFAULT, 'Time Inc.', 'Weekly', 'Magazine', 'People', 19,
        'People is an American weekly magazine that specializes in celebrity news, human-interest stories, and gossip'),
       (DEFAULT, 'The Forbes', 'Monthly', 'Magazine', 'Forbes', 12,
        'Forbes is an American business magazine owned by Integrated Whale Media Investments and the Forbes family'),
       (DEFAULT, 'Time Inc.', 'Weekly', 'Magazine', 'Time', 15,
        'Time is an American news magazine published and based in New York City'),
       (DEFAULT, 'Grupo Planeta', 'Daily', 'Magazine', 'National Geographic', 10,
        'National Geographic is the long-lived official monthly magazine of the National Geographic Society. It is one of the most widely read magazines of all time');
