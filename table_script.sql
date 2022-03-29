create table contact (	
	id VARCHAR (100) NOT NULL,
	full_name VARCHAR (30) not null,
	phone_number VARCHAR (15) not null,
	email VARCHAR (100) not null,
	birth_date DATE,
	profile_image bytea,
	active boolean not null,
	created_date timestamp,
	updated_date timestamp,
	constraint contact_pk primary key (id)
);	

