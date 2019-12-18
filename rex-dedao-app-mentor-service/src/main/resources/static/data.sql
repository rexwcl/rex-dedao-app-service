create table mentor (
	id integer not null auto_increment, 
    username varchar(255) unique, 
    password varchar(255), 
    first_name varchar(255), 
    last_name varchar(255), 
    yearsOfExperience integer,
    trainingsDeliveredInTotal integer,
    specificTechnology varchar(255),
    feeCharged double,
    token varchar(255), 
    primary key (id));