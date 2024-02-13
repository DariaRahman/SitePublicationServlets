CREATE SCHEMA IF NOT EXISTS periodicals_db DEFAULT CHARACTER SET utf8;

use periodicals_db;

DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS subscriptions;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS periodicals;

CREATE TABLE periodicals
(
    periodical_id          BIGINT                                  NOT NULL AUTO_INCREMENT,
    publisher              VARCHAR(255)                            NOT NULL,
    frequency              ENUM ('Daily', 'Weekly', 'Monthly')     NOT NULL,
    periodical_type        ENUM ('Comic', 'Magazine', 'Newspaper') NOT NULL,
    periodical_name        VARCHAR(255)                            NOT NULL,
    periodical_price       DECIMAL(10, 2)                          NOT NULL,
    periodical_description VARCHAR(1000)                           NOT NULL,
    PRIMARY KEY (periodical_id)
);

CREATE TABLE roles
(
    role_id   INT          NOT NULL,
    role_name VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (role_id)
);

CREATE table users
(
    user_id     BIGINT                       NOT NULL AUTO_INCREMENT,
    role_id     INT                          NOT NULL,
    email       VARCHAR(255)                 NOT NULL UNIQUE,
    password    VARCHAR(255)                 NOT NULL,
    first_name  VARCHAR(255)                 NOT NULL,
    last_name   VARCHAR(255)                 NOT NULL,
    gender      ENUM ('MALE', 'FEMALE')      NOT NULL,
    user_status BOOLEAN        DEFAULT FALSE NOT NULL,
    balance     DECIMAL(10, 2) DEFAULT 0     NOT NULL,
    PRIMARY KEY (user_id),
    CONSTRAINT fk_user_role
        FOREIGN KEY (role_id) REFERENCES roles (role_id)
            ON UPDATE RESTRICT
            ON DELETE RESTRICT
);

CREATE TABLE subscriptions
(
    subscription_id     BIGINT                                                       NOT NULL AUTO_INCREMENT,
    subscription_status BOOLEAN   DEFAULT FALSE                                      NOT NULL,
    user_id             BIGINT                                                       NOT NULL,
    periodical_id       BIGINT,
    subscription_period ENUM ('ONE_MONTH', 'THREE_MONTHS', 'SIX_MONTHS', 'ONE_YEAR') NOT NULL,
    start_date          TIMESTAMP DEFAULT CURRENT_TIMESTAMP                          NOT NULL,
    end_date            TIMESTAMP DEFAULT CURRENT_TIMESTAMP                          NOT NULL,
    PRIMARY KEY (subscription_id),
    CONSTRAINT fk_subscription_periodical
        FOREIGN KEY (periodical_id) REFERENCES periodicals (periodical_id)
            ON DELETE CASCADE,
    CONSTRAINT fk_user_subscription
        FOREIGN KEY (user_id) REFERENCES users (user_id)
            ON UPDATE RESTRICT
            ON DELETE RESTRICT
);

CREATE TABLE payments
(
    payment_id      BIGINT                                                          NOT NULL AUTO_INCREMENT,
    user_id         BIGINT                                                          NOT NULL,
    subscription_id BIGINT,
    payment_date    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    total_price     DECIMAL(10, 2)                                                  NOT NULL,
    PRIMARY KEY (payment_id),
    CONSTRAINT fk_user_payment
        FOREIGN KEY (user_id) REFERENCES users (user_id)
            ON UPDATE RESTRICT
            ON DELETE RESTRICT,
    CONSTRAINT fk_payment
        FOREIGN KEY (subscription_id) REFERENCES subscriptions (subscription_id)
            ON DELETE SET NULL
);