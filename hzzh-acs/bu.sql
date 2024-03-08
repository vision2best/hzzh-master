create table permissions
(
    permission_id   bigint                               not null comment '权限 ID'
        primary key,
    permission_code varchar(128)                         not null comment '权限编码',
    permission_name varchar(256)                         not null comment '权限名',
    create_by       bigint                               not null comment '创建人',
    modify_by       bigint                               not null comment '修改人',
    create_at       datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    modify_at       datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    is_deleted      tinyint(1) default 0                 not null comment '是否删除'
)
    comment '权限';

create table role_permissions
(
    role_id       bigint                               not null comment '角色 ID',
    permission_id bigint                               not null comment '权限 ID',
    create_by     bigint                               not null comment '创建人',
    modify_by     bigint                               not null comment '修改人',
    create_at     datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    modify_at     datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    is_deleted    tinyint(1) default 0                 not null comment '是否删除'
)
    comment '角色权限';

create index role_permissions_role_id_permission_id_index
    on role_permissions (role_id, permission_id);

create table roles
(
    role_id    bigint                               not null comment '角色 ID'
        primary key,
    role_code  varchar(32)                          not null comment '角色编码',
    role_name  varchar(64)                          not null comment '角色名',
    create_by  bigint                               not null comment '创建人',
    modify_by  bigint                               not null comment '修改人',
    create_at  datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    modify_at  datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    is_deleted tinyint(1) default 0                 not null comment '是否删除'
)
    comment '角色';

create table tenants
(
    tenant_id   bigint                               not null comment '租户 ID'
        primary key,
    tenant_name varchar(50)                          not null comment '租户名',
    create_by   bigint                               not null comment '创建人',
    modify_by   bigint                               not null comment '修改人',
    create_at   datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    modify_at   datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    is_deleted  tinyint(1) default 0                 not null comment '是否删除'
)
    comment '租户';

create table user_roles
(
    user_id    bigint                               null comment '用户 ID',
    role_id    bigint                               null comment '角色 ID',
    create_by  bigint                               not null comment '创建人',
    modify_by  bigint                               not null comment '修改人',
    create_at  datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    modify_at  datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    is_deleted tinyint(1) default 0                 not null comment '是否删除'
)
    comment '用户角色';

create index user_roles_user_id_role_id_index
    on user_roles (user_id, role_id);

create table user_tenants
(
    tenant_id  bigint                               not null comment '租户 ID',
    user_id    bigint                               not null comment '用户 ID',
    create_by  bigint                               not null comment '创建人',
    modify_by  bigint                               not null comment '修改人',
    create_at  datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    modify_at  datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    is_deleted tinyint(1) default 0                 not null comment '是否删除'
)
    comment '用户租户';

create index user_tenants_user_id_tenant_id_index
    on user_tenants (user_id, tenant_id);

create table users
(
    user_id    bigint                                 not null comment '用户 ID'
        primary key,
    username   varchar(64)                            not null comment '用户名',
    password   varchar(256)                           null comment '密码',
    mobile     varchar(24)  default ''                null comment '手机号',
    email      varchar(128) default ''                not null comment '邮箱',
    create_by  bigint                                 not null comment '创建人',
    modify_by  bigint                                 not null comment '修改人',
    create_at  datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    modify_at  datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    is_deleted tinyint(1)   default 0                 not null comment '是否删除'
)
    comment '用户';

