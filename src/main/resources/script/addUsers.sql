INSERT INTO roles(role_name) VALUES
                            ('ROLE_ADMIN'),
                            ('ROLE_USER');

INSERT INTO Users(username, password, first_name, last_name, age, email)
    VALUES
        ('admin', '$2a$12$GY8uEB9RZ.970pLnTmmIsemQVKNsgK.bZoSSNCIJds62/.xbMp62u', 'admin', 'admin', 25, 'admin@email.com'),
        ('user', '$2a$12$VKy2F6S7.JZK3/TLjLbpaeLcsAYw62W1035ypVJC1hdvAHVHwvsI6', 'user', 'user', 22, 'user@email.com'),
        ('admusr', '$2a$12$wpjCxSSqpcEIOG3DmFTkee24QT6.jg4zP0TUHDNn15sO/FlbiRS/q
', 'admusr', 'admusr', 32, 'admusr@email.com');

INSERT INTO users_roles(user_id, role_id)
    VALUES
        (1, 1),
        (2, 2),
        (3, 1),
        (3, 2);

-- login admin, password admin;
-- login user, password user;
-- login admusr, password admusr