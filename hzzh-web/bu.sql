create table tenants
(
    id        varchar(20)                        not null comment '租户id'
        primary key,
    name      varchar(50)                        not null,
    create_by varchar(32)                        not null,
    modify_by varchar(32)                        not null,
    create_at datetime default CURRENT_TIMESTAMP not null,
    modify_at datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
)
    comment '租户';

create table users
(
    id        varchar(32)                            not null
        primary key,
    username  varchar(64)                            not null comment '用户名',
    mobile    varchar(24)  default ''                null comment '手机号',
    email     varchar(128) default ''                not null comment '邮箱',
    create_by varchar(32)                            not null,
    modify_by varchar(32)                            not null,
    create_at datetime     default CURRENT_TIMESTAMP not null,
    modify_at datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
)
    comment '用户';

