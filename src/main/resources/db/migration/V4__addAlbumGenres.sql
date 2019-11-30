CREATE TABLE album_genre (
    album_id uuid references album (id),
    name varchar(200) not null,
    primary key (album_id, name)
)
