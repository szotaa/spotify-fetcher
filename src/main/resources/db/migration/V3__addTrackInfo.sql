alter table track
    add column duration         integer,
    add column key              integer,
    add column mode             varchar(5),
    add column time_signature   integer,
    add column acousticness     double precision,
    add column danceability     double precision,
    add column energy           double precision,
    add column instrumentalness double precision,
    add column liveness         double precision,
    add column loudness         double precision,
    add column speechiness      double precision,
    add column valence          double precision,
    add column tempo            double precision;
