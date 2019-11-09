create table if not exists artist
(
    id        varchar(36) primary key,
    created_on timestamp not null,
    spotify_id varchar(100) not null unique,
    name      varchar(100) not null
);

create table if not exists album
(
    id        varchar(36) primary key,
    created_on timestamp not null,
    spotify_id varchar(100) not null unique,
    name      varchar(100) not null
);

create table if not exists track
(
    id         varchar(36) primary key,
    created_on  timestamp not null,
    spotify_id  varchar(100) not null unique,
    name       varchar(100) not null,
    popularity integer,
    album_id    varchar(36)  not null,
    constraint albumIdFk foreign key (album_id) references album (id)
);

create table if not exists track_artist
(
    track_id  varchar(36) not null,
    artist_id varchar(36) not null,
    primary key (track_id, artist_id),
    constraint artistTrackIdFk foreign key (track_id) references track (id),
    constraint trackArtistIdFk foreign key (artist_id) references artist (id)
);

create table if not exists track_listen
(
    id varchar(36) primary key,
    created_on  timestamp not null,
    played_at  timestamp not null unique,
    track_id  varchar(36) not null,
    constraint trackListenTrackIdFk foreign key (track_id) references track (id)
);
