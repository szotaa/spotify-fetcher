alter table track drop constraint albumidfk;
alter table track_artist drop constraint artisttrackidfk;
alter table track_artist drop constraint trackartistidfk;
alter table track_listen drop constraint tracklistentrackidfk;

alter table album alter column id type uuid using id::uuid;
alter table artist alter column id type uuid using id::uuid;
alter table track alter column id type uuid using id::uuid;
alter table track alter column album_id type uuid using album_id::uuid;
alter table track_artist alter column track_id type uuid using track_id::uuid;
alter table track_artist alter column artist_id type uuid using artist_id::uuid;
alter table track_listen alter column id type uuid using id::uuid;
alter table track_listen alter column track_id type uuid using track_id::uuid;

alter table track add constraint album_id_fk foreign key (album_id) references album (id);
alter table track_artist add constraint artist_track_id_fk foreign key (track_id) references track (id);
alter table track_artist add constraint track_artist_id_fk foreign key (artist_id) references artist (id);
alter table track_listen add constraint track_listen_track_id_fk foreign key (track_id) references track (id);
