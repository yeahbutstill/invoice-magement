create table invoice
(
    id              varchar(255)   not null,
    created         datetime(6),
    created_by      varchar(255),
    status_record   varchar(255)   not null,
    updated         datetime(6),
    updated_by      varchar(255),
    amount          decimal(19, 2) not null,
    description     varchar(255)   not null,
    due_date        date           not null,
    invoice_number  varchar(100)   not null,
    paid            bit            not null,
    id_invoice_type varchar(255)   not null,
    primary key (id)
);

create table invoice_type_provider
(
    id_invoice_type     varchar(255) not null,
    id_payment_provider varchar(255) not null,
    primary key (id_invoice_type, id_payment_provider)
);

create table invoice_type
(
    id            varchar(255) not null,
    created       datetime(6),
    created_by    varchar(255),
    status_record varchar(255) not null,
    updated       datetime(6),
    updated_by    varchar(255),
    code          varchar(100) not null,
    name          varchar(100) not null,
    primary key (id)
);

create table payment
(
    id                 varchar(255)   not null,
    created            datetime(6),
    created_by         varchar(255),
    status_record      varchar(255)   not null,
    updated            datetime(6),
    updated_by         varchar(255),
    amount             decimal(19, 2) not null,
    provider_reference varchar(255)   not null,
    transaction_time   datetime(6) not null,
    id_virtual_account varchar(255)   not null,
    primary key (id)
);

create table payment_provider
(
    id            varchar(255) not null,
    created       datetime(6),
    created_by    varchar(255),
    status_record varchar(255) not null,
    updated       datetime(6),
    updated_by    varchar(255),
    code          varchar(100) not null,
    logo          varchar(255),
    name          varchar(100) not null,
    primary key (id)
);

create table virtual_account
(
    id                   varchar(255) not null,
    created              datetime(6),
    created_by           varchar(255),
    status_record        varchar(255) not null,
    updated              datetime(6),
    updated_by           varchar(255),
    account_number       varchar(255) not null,
    company_id           varchar(255) not null,
    virtual_account_type varchar(255) not null,
    id_invoice           varchar(255) not null,
    id_payment_provider  varchar(255) not null,
    primary key (id)
);

alter table invoice
    add constraint FKco4sbxv9cj2oevm6cdpq76ffb
        foreign key (id_invoice_type)
            references invoice_type (id);

alter table invoice
    add constraint invoice_unique_invoice_number
        unique (invoice_number);

alter table invoice_type_provider
    add constraint FKdue5pwwyn091emo8u1e5fkh7k
        foreign key (id_payment_provider)
            references payment_provider (id);

alter table invoice_type_provider
    add constraint FKfsym0hpqp4d8llimqvtn1iih1
        foreign key (id_invoice_type)
            references invoice_type (id);

alter table payment
    add constraint FKptriq88d7e8io9mhri8p10cq0
        foreign key (id_virtual_account)
            references virtual_account (id);

alter table payment_provider
    add constraint payment_provider_unique_code
        unique (code);

alter table virtual_account
    add constraint FKbbdwdxpgdisiikyyhf2xteblc
        foreign key (id_invoice)
            references invoice (id);

alter table virtual_account
    add constraint FKt3t7f64hvgk4xjblsovqqkpll
        foreign key (id_payment_provider)
            references payment_provider (id);