CREATE TABLE IF NOT EXISTS epik.type (
    id INTEGER NOT NULL AUTO_INCREMENT,
    activity_type VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS epik.history (
    id INTEGER NOT NULL AUTO_INCREMENT,
    endpoint VARCHAR(255),
    event_date DATETIME(3),
    user_login VARCHAR(255),
    type_id INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (type_id) REFERENCES epik.type(id)
);
