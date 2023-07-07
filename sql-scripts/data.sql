USE multirole_website_db;

INSERT INTO users (username, password, enabled)
  values ('user', '{noop}user', 1),
		 ('admin', '{noop}admin', 1),
		 ('moderator', '{noop}moderator', 1);

INSERT INTO authorities (username, authority)
  values ('user', 'ROLE_USER'),
		 ('moderator', 'ROLE_MOD'),
         ('admin', 'ROLE_ADMIN');