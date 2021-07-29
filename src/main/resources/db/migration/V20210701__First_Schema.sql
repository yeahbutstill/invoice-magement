create table payment_provider
(
    id VARCHAR(36),
    code varchar (100) not null,
    name varchar (100) not null,
    primary key (id)
);

alter table payment_provider
add constraint payment_provider_uc_code
unique (code);

create table invoice_type
(
    id VARCHAR(36),
    primary key (id)
);

create table invoice
(
    id VARCHAR(36),
    primary key (id)
);

create table virtual_account
(
    id VARCHAR(36),
    primary key (id)
);

create table payment
(
    id VARCHAR(36),
    primary key (id)
);

