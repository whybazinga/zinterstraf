DROP SCHEMA IF EXISTS u_site_vvopaa;

CREATE SCHEMA IF NOT EXISTS u_site_vvopaa
CHARACTER SET utf8;
SET GLOBAL time_zone = '+3:00';
USE u_site_vvopaa;

CREATE TABLE users (
	id INTEGER UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(48) NOT NULL UNIQUE,
    password VARCHAR(48) NOT NULL,
    block_stat BIT(1) NOT NULL DEFAULT 0,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO u_site_vvopaa.users (email, password, block_stat) VALUES ('test@test.test', '1234', 0);

CREATE TABLE roles (
	id INTEGER UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    role VARCHAR(16) NOT NULL
);

INSERT INTO u_site_vvopaa.roles (role) VALUES ('user');
INSERT INTO u_site_vvopaa.roles (role) VALUES ('moderator');
INSERT INTO u_site_vvopaa.roles (role) VALUES ('admin');

CREATE TABLE link_users_roles (
	id_user INTEGER UNSIGNED NOT NULL,
    id_role INTEGER UNSIGNED NOT NULL,
    PRIMARY KEY (id_user, id_role),
	FOREIGN KEY (id_user) REFERENCES users(id)
    ON DELETE cascade
    ON UPDATE cascade,
    FOREIGN KEY (id_role) REFERENCES roles(id)
    ON DELETE cascade
    ON UPDATE cascade
);

INSERT INTO u_site_vvopaa.link_users_roles (id_user, id_role) VALUES (1, 1);
INSERT INTO u_site_vvopaa.link_users_roles (id_user, id_role) VALUES (1, 2);
INSERT INTO u_site_vvopaa.link_users_roles (id_user, id_role) VALUES (1, 3);


