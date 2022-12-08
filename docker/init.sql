CREATE TABLE categories
(
    category_id BIGSERIAL PRIMARY KEY NOT NULL,
    name text NOT NULL UNIQUE
);

CREATE TABLE articles
(
    article_id BIGSERIAL PRIMARY KEY NOT NULL,
    title text UNIQUE NOT NULL,
    body text NOT NULL,
    category_id BIGINT NOT NULL,
    CONSTRAINT article_category_id_fkey FOREIGN KEY (category_id)
    REFERENCES categories(category_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
