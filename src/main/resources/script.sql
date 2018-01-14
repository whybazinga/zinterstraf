DROP SCHEMA IF EXISTS u_site_vvopaa;

CREATE SCHEMA IF NOT EXISTS u_site_vvopaa
CHARACTER SET utf8;
SET GLOBAL time_zone = '+3:00';
USE u_site_vvopaa;

CREATE TABLE users (
  id INTEGER UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(48) NOT NULL UNIQUE,
  password VARCHAR(64) NOT NULL,
  accStatus INT(1) NOT NULL DEFAULT 0,
  created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO u_site_vvopaa.users (email, password, accStatus) VALUES ('test@test.test', '$2b$10$OYfHj.JjcH/o4oO3so.NweA/5Pleyuu0vnFlowo1O4KLO8mWuXbma', 1);

CREATE TABLE roles (
	id INTEGER UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  role VARCHAR(16) NOT NULL
);

INSERT INTO u_site_vvopaa.roles (role) VALUES ('user'), ('moderator'), ('admin');

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

INSERT INTO u_site_vvopaa.link_users_roles (id_user, id_role)(
  select users.id, roles.id from users, roles
  where users.id = 1
);

#SPRING SECURITY TEST ???
create table oauth_client_token (
  token_id VARCHAR(256),
  token LONGVARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

create table oauth_access_token (
  token_id VARCHAR(256),
  token LONGVARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication LONGVARBINARY,
  refresh_token VARCHAR(256)
);

create table oauth_refresh_token (
  token_id VARCHAR(256),
  token LONGVARBINARY,
  authentication LONGVARBINARY
);

create table oauth_code (
  code VARCHAR(256), authentication LONGVARBINARY
);

create table oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);
