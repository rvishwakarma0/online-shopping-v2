create table customer_sos(
cust_id varchar(5) PRIMARY KEY,
cust_email varchar(30),
cust_first_name varchar(20),
cust_last_name varchar(20));

create table order_line_item(
id varchar(10) PRIMARY KEY,
item_name varchar(50),
item_quantity int,
order_id varchar(30));

create table sales_order(
id varchar(10) PRIMARY KEY,
order_date date,
order_desc varchar(700),
total_price double,
cust_id varchar(5));
