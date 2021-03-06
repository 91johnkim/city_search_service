

CREATE TABLE COUNTRY(
    id VARCHAR(2) NOT NULL,
    eng_name VARCHAR NOT NULL,
    kor_name VARCHAR NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE CITY(
    id BIGINT NOT NULL AUTO_INCREMENT,
    country_id VARCHAR(2) NOT NULL,
    name VARCHAR NOT NULL,
    selected_at DATETIME,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    PRIMARY KEY(id)
);