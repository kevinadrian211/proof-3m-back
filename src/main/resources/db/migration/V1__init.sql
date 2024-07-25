CREATE TABLE IF NOT EXISTS films (
    id SERIAL PRIMARY KEY,
    titlef VARCHAR(255) NOT NULL,
    duration INT NOT NULL,
    description TEXT
    );

CREATE TABLE IF NOT EXISTS scenes (
    id SERIAL PRIMARY KEY,
    titles VARCHAR(255) NOT NULL,
    duration INT NOT NULL,
    films_id INT REFERENCES films(id)
    );

CREATE TABLE IF NOT EXISTS characters (
    id SERIAL PRIMARY KEY,
    namec VARCHAR(255) NOT NULL,
    scenes_count INT NOT NULL,
    scenes_id INT REFERENCES scenes(id)
    );