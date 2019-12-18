create table training (
id integer not null auto_increment, 
trainingDescription varchar(255),
trainerName varchar(255),
avgRating integer,
feeCharged integer,
numOfTrainingsCompleted integer,
trainingUrl varchar(255),
primary key (id)
)