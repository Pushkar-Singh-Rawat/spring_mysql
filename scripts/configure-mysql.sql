CREATE DATABASE smysql_dev;
CREATE DATABASE smysql_prod;

#service accounts.% is wildcard that suggests any host. 
CREATE USER 'smysql_dev_user'@'localhost' IDENTIFIED BY 'spring_mysql';
CREATE USER 'smysql_prod_user'@'localhost' IDENTIFIED BY 'spring_mysql';
CREATE USER 'smysql_dev_user'@'%' IDENTIFIED BY 'spring_mysql';
CREATE USER 'smysql_prod_user'@'%' IDENTIFIED BY 'spring_mysql';

#Granting only below dml access.
GRANT SELECT ON 'smysql_dev.*' to 'smysql_dev_user'@'localhost';
GRANT UPDATE ON 'smysql_dev.*' to 'smysql_dev_user'@'localhost';
GRANT DELETE ON 'smysql_dev.*' to 'smysql_dev_user'@'localhost';
GRANT INSERT ON 'smysql_dev.*' to 'smysql_dev_user'@'localhost';
GRANT INSERT ON 'smysql_prod.*' to 'smysql_prod_user'@'localhost';
GRANT DELETE ON 'smysql_prod.*' to 'smysql_prod_user'@'localhost';
GRANT SELECT ON 'smysql_prod.*' to 'smysql_prod_user'@'localhost';
GRANT UPDATE ON 'smysql_prod.*' to 'smysql_prod_user'@'localhost';

GRANT SELECT ON 'smysql_dev.*' to 'smysql_dev_user'@'%'; 
GRANT UPDATE ON 'smysql_dev.*' to 'smysql_dev_user'@'%';
GRANT DELETE ON 'smysql_dev.*' to 'smysql_dev_user'@'%';
GRANT INSERT ON 'smysql_dev.*' to 'smysql_dev_user'@'%';
GRANT INSERT ON 'smysql_prod.*' to 'smysql_prod_user'@'%';
GRANT DELETE ON 'smysql_prod.*' to 'smysql_prod_user'@'%';
GRANT SELECT ON 'smysql_prod.*' to 'smysql_prod_user'@'%';
GRANT UPDATE ON 'smysql_prod.*' to 'smysql_prod_user'@'%';