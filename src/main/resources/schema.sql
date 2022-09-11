drop table if exists request_info;
create table request_info
(
   id bigint not null unique,
   date timestamp not null,
   input_text varchar(255) not null,
   translated_text varchar(255) not null,
   translation_rule varchar(255) not null,
   ip varchar(255) not null
);

alter table request_info
    add constraint pk_id primary key(id);

drop table if exists translation_info;
create table translation_info
(
    request_id bigint not null,
    input_word varchar(255) not null,
    translated_word varchar(255) not null
);

alter table translation_info
    add constraint fk_id foreign key(request_id)
    references request_info(id);