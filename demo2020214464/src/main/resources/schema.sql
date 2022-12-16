

CREATE TABLE IF NOT EXISTS `user`
(
    id       INT NOT NULL PRIMARY KEY,
    name     VARCHAR(255),
    password VARCHAR(255),
    role     INT,
    sex      VARCHAR(255),
    address  VARCHAR(255),
    phone    VARCHAR(255),
    email    VARCHAR(255)
);