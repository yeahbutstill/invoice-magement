create table payment_provider
(
    id VARCHAR(36),
    primary key (id)
);

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

