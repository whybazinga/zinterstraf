
INSERT IGNORE INTO u_site_vvopaa.oauth_users (email, password, accStatus)
VALUES ('test@test.test', '$2a$04$sJjk4.1ecL7I1NF3hFUZueq6LDBj2hIRLdo2U5GmYdoeA9XYpfREu', 1);

INSERT IGNORE INTO u_site_vvopaa.user_roles (role) VALUES ('user'), ('moderator'), ('admin');

INSERT IGNORE INTO u_site_vvopaa.link_users_roles (id_user, id_role)(
  select oauth_users.id, user_roles.id from oauth_users, user_roles
  where oauth_users.id = 1
);

INSERT IGNORE INTO u_site_vvopaa.client_scopes (scope) VALUES ('read'), ('write'), ('trust');

#SPRING SECURITY TEST
create table oauth_client_token (
  token_id VARCHAR(256),
  token long varbinary,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

/*
#this one for AC and REF tokens JDBC
create table oauth_access_token (
  token_id VARCHAR(256),
  token long varbinary,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication long varbinary,
  refresh_token VARCHAR(256)
);

create table oauth_refresh_token (
  token_id VARCHAR(256),
  token LONG,
  authentication LONG
);

create table oauth_code (
  code VARCHAR(256),
  authentication long varbinary
);

create table oauth_approvals (
  userId VARCHAR(256),
  clientId VARCHAR(256),
  scope VARCHAR(256),
  status VARCHAR(10),
  expiresAt TIMESTAMP,
  lastModifiedAt TIMESTAMP
);
*/