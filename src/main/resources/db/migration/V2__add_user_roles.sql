
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(20),
    password VARCHAR(200),
    email VARCHAR(50),
    locked BOOLEAN,
    disabled BOOLEAN,
    UNIQUE (username),
    UNIQUE (email)
    );

CREATE TABLE IF NOT EXISTS roles (
    id SERIAL PRIMARY KEY,
    roles VARCHAR(25),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
    );
