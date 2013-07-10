create table if not exists bankaccount (
  id int(11) NOT NULL,
  account_number varchar(255) DEFAULT NULL,
  description varchar(1024) DEFAULT NULL,
  balance double DEFAULT NULL,
  PRIMARY KEY (id)
)