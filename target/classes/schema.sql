DROP TABLE IF EXISTS TBL_EMPLOYEES;
 
CREATE TABLE TBL_EMPLOYEES (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  full_name VARCHAR(250) NOT NULL,
  job VARCHAR(250) NOT NULL,
  birth_day VARCHAR(250) NOT NULL
);