create table SPRING_SESSION
(
	PRIMARY_ID char(36) not null
		primary key,
	SESSION_ID char(36) not null,
	CREATION_TIME bigint not null,
	LAST_ACCESS_TIME bigint not null,
	MAX_INACTIVE_INTERVAL int not null,
	EXPIRY_TIME bigint not null,
	PRINCIPAL_NAME varchar(100) null,
	constraint SPRING_SESSION_IX1
		unique (SESSION_ID)
)
engine=InnoDB
;

create index SPRING_SESSION_IX2
	on SPRING_SESSION (EXPIRY_TIME)
;

create index SPRING_SESSION_IX3
	on SPRING_SESSION (PRINCIPAL_NAME)
;

create table SPRING_SESSION_ATTRIBUTES
(
	SESSION_PRIMARY_ID char(36) not null,
	ATTRIBUTE_NAME varchar(200) not null,
	ATTRIBUTE_BYTES blob not null,
	primary key (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
	constraint SPRING_SESSION_ATTRIBUTES_FK
		foreign key (SESSION_PRIMARY_ID) references SPRING_SESSION (PRIMARY_ID)
			on delete cascade
)
engine=InnoDB
;

create index SPRING_SESSION_ATTRIBUTES_IX1
	on SPRING_SESSION_ATTRIBUTES (SESSION_PRIMARY_ID)
;

create table room
(
	id bigint auto_increment
		primary key,
	owner bigint null comment '所属用户',
	title varchar(64) null,
	gmt_create timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
	gmt_modified timestamp default CURRENT_TIMESTAMP not null,
	online_users int null,
	topic varchar(64) null,
	status int null comment '0：未在线 1：直播中',
	last_broadcast_start_time timestamp null
)
engine=InnoDB
;

create table user
(
	id bigint auto_increment
		primary key,
	username varchar(64) not null,
	password varchar(64) not null,
	phone varchar(20) not null,
	gmt_create timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
	gmt_modified timestamp default CURRENT_TIMESTAMP not null,
	constraint phone
		unique (phone)
)
engine=InnoDB
;

