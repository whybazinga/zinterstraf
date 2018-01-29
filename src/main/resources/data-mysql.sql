
INSERT IGNORE INTO zinterdb.oauth_users (username, password, acc_status)
VALUES ('t@t.t', '$2a$04$sJjk4.1ecL7I1NF3hFUZueq6LDBj2hIRLdo2U5GmYdoeA9XYpfREu', 1);

INSERT IGNORE INTO zinterdb.user_roles (role) VALUES ('ROLE_USER'), ('ROLE_MODERATOR'), ('ROLE_ADMIN');

INSERT IGNORE INTO zinterdb.link_users_roles (id_user, id_role)(
  select oauth_users.id, user_roles.id from oauth_users, user_roles
  where oauth_users.id = 1
);

INSERT IGNORE INTO zinterdb.oauth_clients (client_id, client_secret) VALUES ('clientIdPassword', 'secret');


INSERT IGNORE INTO zinterdb.client_scopes (scope) VALUES ('read'), ('write'), ('trust');

INSERT IGNORE INTO zinterdb.link_client_scope (id_client, id_scope)(
  select oauth_clients.id, client_scopes.id from oauth_clients, client_scopes
  where oauth_clients.id = 1
);

INSERT IGNORE INTO zinterdb.client_grant_types (grant_type) VALUES ('password'), ('refresh_token'), ('authorization_code'), ('implicit');

INSERT IGNORE INTO zinterdb.link_client_grant_type (id_client, id_grant_type)(
  select oauth_clients.id, client_grant_types.id from oauth_clients, client_grant_types
  where oauth_clients.id = 1 and grant_type IN ('password', 'refresh_token')
);

INSERT IGNORE INTO zinterdb.client_resource_ids (resource_id) VALUES ('main');

INSERT IGNORE INTO zinterdb.link_client_resource (id_client, id_resource)(
  select oauth_clients.id, client_resource_ids.id from oauth_clients, client_resource_ids
  where oauth_clients.id = 1
);

create table if not exists oauth_client_token (
  token_id VARCHAR(256),
  token long varbinary,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);


create table if not exists oauth_access_token (
  token_id VARCHAR(256),
  token long varbinary,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication long varbinary,
  refresh_token VARCHAR(256)
);

create table if not exists oauth_refresh_token (
  token_id VARCHAR(256),
  token long varbinary,
  authentication long varbinary
);

create table if not exists oauth_code (
  code VARCHAR(256),
  authentication long varbinary
);

create table if not exists oauth_approvals (
  userId VARCHAR(256),
  clientId VARCHAR(256),
  scope VARCHAR(256),
  status VARCHAR(10),
  expiresAt TIMESTAMP,
  lastModifiedAt TIMESTAMP
);