create table running_number
(
    id          varchar(36)  not null,
    last_number bigint       not null,
    prefix      varchar(100) not null,
    primary key (id)
);

create table customer
(
    id            varchar(36)  not null,
    created       datetime(6),
    created_by    varchar(255),
    status_record varchar(255) not null,
    updated       datetime(6),
    updated_by    varchar(255),
    code          varchar(100) not null,
    name          varchar(255) not null,
    email         varchar(100) not null,
    phone         varchar(30)  not null,
    primary key (id)
);

create table invoice_type
(
    id            varchar(36)  not null,
    created       datetime(6),
    created_by    varchar(255),
    status_record varchar(255) not null,
    updated       datetime(6),
    updated_by    varchar(255),
    code          varchar(100) not null,
    name          varchar(100) not null,
    payment_type  varchar(50)  not null,
    primary key (id)
);
alter table invoice_type
    add constraint UK_g00m3ha80tpr6yh7x1rl6s83m unique (code);

create table invoice
(
    id              varchar(36)    not null,
    created         datetime(6),
    created_by      varchar(255),
    status_record   varchar(255)   not null,
    updated         datetime(6),
    updated_by      varchar(255),
    amount          decimal(19, 2) not null,
    total_payment   decimal(19, 2) not null,
    description     varchar(255)   not null,
    due_date        date           not null,
    invoice_number  varchar(100)   not null,
    payment_status  varchar(50)    not null,
    id_invoice_type varchar(36)    not null,
    id_customer     varchar(36)    not null,
    paid            bit            not null,
    primary key (id)
);
alter table invoice
    add constraint UK_t6xkdjx1qtd5whp2iljdfn2yj unique (invoice_number);
alter table invoice
    add constraint FKco4sbxv9cj2oevm6cdpq76ffb
        foreign key (id_invoice_type)
            references invoice_type (id);
alter table invoice
    add constraint FKlyajla9dkjg11gtikvka08ls0
        foreign key (id_customer)
            references customer (id);

create table invoice_detail
(
    id               varchar(36)    not null,
    id_invoice       varchar(36)    not null,
    product_code     varchar(100)   not null,
    product_name     varchar(255)   not null,
    measurement_unit varchar(36)    not null,
    unit_price       decimal(19, 2) not null,
    quantity         decimal(19, 2) not null,
    primary key (id)
);
alter table invoice_detail
    add constraint FKgxflbeie3colltw85ukacjx8o
        foreign key (id_invoice)
            references invoice (id);

create table payment_provider
(
    id            varchar(36)  not null,
    code          varchar(100) not null,
    name          varchar(100) not null,
    created       datetime(6),
    created_by    varchar(255),
    status_record varchar(255) not null,
    updated       datetime(6),
    updated_by    varchar(255),
    logo          varchar(255),
    primary key (id)
);
alter table payment_provider
    add constraint UK_rpti88040su50vrjqdtu8ulbi unique (code);

create table bank
(
    id                varchar(36)  not null,
    created           datetime(6),
    created_by        varchar(255),
    status_record     varchar(255) not null,
    updated           datetime(6),
    updated_by        varchar(255),
    code              varchar(100) not null,
    name              varchar(100) not null,
    central_bank_code varchar(100) not null,
    primary key (id)
);
alter table bank
    add constraint UK_nc70mw7kj0k56c4pjpl6b0xwt unique (code);

create table bank_account
(
    id             varchar(36)  not null,
    created        datetime(6),
    created_by     varchar(255),
    status_record  varchar(255) not null,
    updated        datetime(6),
    updated_by     varchar(255),
    id_bank        varchar(36)  not null,
    account_number varchar(100) not null,
    account_name   varchar(100) not null,
    branch_name    varchar(100),
    primary key (id)
);
alter table bank_account
    add constraint UK_mb8kv2x9143o96jgxbv6mahcq unique (account_number);
alter table bank_account
    add constraint FK4eape8iri4w1j2edy9jyykqhh
        foreign key (id_bank)
            references bank (id);

create table virtual_account_configuration
(
    id                         varchar(36)    not null,
    created                    datetime(6),
    created_by                 varchar(255),
    status_record              varchar(255)   not null,
    updated                    datetime(6),
    updated_by                 varchar(255),
    code                       varchar(100)   not null,
    name                       varchar(100)   not null,
    id_bank_account            varchar(36)    not null,
    id_payment_provider        varchar(36)    not null,
    transaction_fee_flat       decimal(19, 2) not null,
    transaction_fee_percentage decimal(7, 4)  not null,
    company_prefix             varchar(100)   not null,
    account_number_length      integer        not null,
    primary key (id)
);
alter table virtual_account_configuration
    add constraint UK_t064vnhefdgch7kn7m4kdupho unique (code);
alter table virtual_account_configuration
    add constraint UK_61c3ana4jhjq7vx51y0u7rhql unique (id_payment_provider);
alter table virtual_account_configuration
    add constraint FKbfvwy58xo8wb2ne6uk1ci3mj2
        foreign key (id_bank_account)
            references bank_account (id);
alter table virtual_account_configuration
    add constraint FKp5o8jrcun29y9po8cjrpu6lim
        foreign key (id_payment_provider)
            references payment_provider (id);

create table invoice_type_configuration
(
    id_invoice_type                  varchar(36) not null,
    id_virtual_account_configuration varchar(36) not null,
    primary key (id_invoice_type, id_virtual_account_configuration)
);
alter table invoice_type_configuration
    add constraint FKextslpqopbximqfd69mbi46rq
        foreign key (id_invoice_type)
            references invoice_type (id);
alter table invoice_type_configuration
    add constraint FKpbx15u40wtbil9sbvu24eu2hp
        foreign key (id_virtual_account_configuration)
            references virtual_account_configuration (id);

create table virtual_account
(
    id                               varchar(36)  not null,
    created                          datetime(6),
    created_by                       varchar(255),
    status_record                    varchar(255) not null,
    updated                          datetime(6),
    updated_by                       varchar(255),
    account_number                   varchar(255) not null,
    company_id                       varchar(255) not null,
    id_invoice                       varchar(36)  not null,
    id_virtual_account_configuration varchar(36)  not null,
    id_payment_provider              varchar(36)  not null,
    primary key (id)
);
alter table virtual_account
    add constraint UK_rnvqfqualuu1p4pmrv4hjkg88 unique (id_virtual_account_configuration);
alter table virtual_account
    add constraint FKbbdwdxpgdisiikyyhf2xteblc
        foreign key (id_invoice)
            references invoice (id);
alter table virtual_account
    add constraint FK64furldtdqywk8gird6tit1pq
        foreign key (id_virtual_account_configuration)
            references virtual_account_configuration (id);
alter table virtual_account
    add constraint FKt3t7f64hvgk4xjblsovqqkpll
        foreign key (id_payment_provider)
            references payment_provider (id);

create table payment
(
    id                 varchar(36)    not null,
    created            datetime(6),
    created_by         varchar(255),
    status_record      varchar(255)   not null,
    updated            datetime(6),
    updated_by         varchar(255),
    amount             decimal(19, 2) not null,
    transaction_fee    decimal(19, 2) not null,
    provider_reference varchar(255)   not null,
    transaction_time   datetime(6)    not null,
    id_virtual_account varchar(36)    not null,
    primary key (id)
);
alter table payment
    add constraint FKptriq88d7e8io9mhri8p10cq0
        foreign key (id_virtual_account)
            references virtual_account (id);