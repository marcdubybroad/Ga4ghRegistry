
-- drop any tables already created
drop table if exists registry_server_node;


-- create the server node table
create table registry_server_node (
    server_node_id bigint(20) not null primary key,
    web_name varchar(1000) not null,
    url varchar(1000) not null,
    type varchar(400) not null,
    date_created timestamp,
    last_updated datetime,
    version bigint(20)
);
