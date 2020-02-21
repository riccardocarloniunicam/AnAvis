CREATE TABLE utente (
	id int(100) PRIMARY KEY AUTO INCREMENT UNIQUE,
	nome varchar(256),
	cognome varchar(256),
	email varchar(255),
	password varchar(255),
	cf varchar(256),  ----fatto
	indrizzo varchar(256), --atto
	città varchar(256), -- fatto
	provincia varchar(256), --fatto
	cap varchar(256), ----fatto
	telefono varchar(256), ----fatto
	numerodoc varchar(256), --fatto
	grupposanguinio varchar(256), --fatto
	donatore tinyint(1),
	emergenza tinyint(1),
	modulo tinyint(1)


);
"$2a$10$MpCqSNNe11hi42oRo3v3ouGusAfOX7noSJ0pff0vB.vmZHZpmqc/q
$2a$10$mVcMz3zdKCJ1MM7M8ebC5.4MVD752w9Yi5zvw3jRkfhmz7.dNXJDK"
create table utente_sedi (
	id int(10) primary key auto_increment,
	email varchar(256),
	password varchar(256),
	sede_id int(10),
	active int(11)

);





drop table if exists sede;
drop table if exists prenotazioni;
create table sede(
	sede_id int(10) PRIMARY KEY AUTO_INCREMENT,
	regione varchar(256),
	città varchar(256),
	presidente varchar(256),
	indirizzo varchar(256),
	cap  varchar(256),
	provincia varchar(256),
	telefono varchar(256),
	cellulare varchar(256),
	fax varchar(256),
	email varchar(256),
	sito varchar(256),
	social varchar(256),
	regionale tinyint(1)
    
);

create table prenotazioni(
id int(10) primary key auto_increment,
nome varchar(255),
cognome varchar(255),
data varchar(255),
orario varchar(255),
sede_id int(10),
user_id int(10),
analisi tinyint(2)

);









///





CREATE TABLE analisi(
	id
	nome
	cognome
	numerodoc

);

sede
logga scelta regionale
Select * from prenotazioni where regione




CREATE TABLE prenotazioni(

id int(10) primary key AUTO_INCREMENT,
nome varchar(256),
cognome varchar(256),
data varchar(256),
orario varchar(256),
stato varchar(256),
sede_id int(10),
user_id int(10)

);

Select orario , sede from prenotazioni,sede where sede = id 
Se (!= null 

Return Orario preso





CREATE TABLE modulo (
	id int(100) AUTO_INCREMENT,
	nome varchar(256),
	luogo_nascita varchar(256),
	data_nascita  varchar(256),
	residenza varchar(256),
	indirizzo varchar(256),
	professione varchar(256),
	tellavoro varchar(256),
	telcasa varchar(256),
	cellulare varchar(256),
	email varchar(256),
	data varchar(256),
	user_id int(10),
	
);


CREATE TABLE dottori(
	id int(100) PRIMARY KEY AUTO INCREMENT UNIQUE,
	nome varchar(256),
	cognome varchar(256),
	cf varchar(256),
	cellulare varchar(256),
	sedelavoro varchar(256)

);


CREATE TABLE sedi(
	id int(100) AUTO_INCREMENT PRIMARY_KEY,
	regione varchar(256),
	città varchar(256),
	presidente varchar(256),
	indirizzo varchar(256),
	cap  varchar(256),
	provincia varchar(256),
	telefono varchar(256),
	cellulare varchar(256),
	fax varchar(256),
	email varchar(256),
	sito varchar(256),
	social varchar(256),
	regionale tinyint(1)

);


CREATE TABLE news(
	id int(10) primary key auto_increment,
	titolo varchar(50),
	messaggio varchar(255),
	sede_id int(10),
	email varchar(255)
);

create table messaggi(
	id int(10) primary key auto_increment,
	oggetto varchar(256),
	messaggio varchar(256),
	sede_email varchar(256),
	user_id int(10),
	data varchar(20)
);


create table files(
id varchar(255),
file_name varchar(255),
file_type varchar(255),
data  MEDIUMBLOB NOT NULL,
nota varchar(255)
);





