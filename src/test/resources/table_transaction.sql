create table if not exists transaction (
  accountNumber varchar(100) NOT NULL,
  timeStamp float NOT NULL,
  amount float DEFAULT NULL,
  description varchar(10) DEFAULT NULL
)