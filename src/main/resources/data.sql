insert into role (role_id, name) values('100', 'ROLE_USER');
insert into role (role_id, name) values('101', 'MODERATOR');
insert into role (role_id, name) values('102', 'ROLE_ADMIN');

INSERT INTO `user_role`(user_id, role_id) VALUES(1, 100);
INSERT INTO `user_role`(user_id, role_id) VALUES(1, 101);
INSERT INTO `user_role`(user_id, role_id) VALUES(1, 102);

insert into question 
	(answer1,
	answer2,
	answer3,
	answer4,
	category,
	correct_answer,
	created_at,
	created_by,
	explanation,
	question,
	status,
	subcategory,
	type)
values 
	('answer 1',
	'answer 2',
	'answer3',
	'answer 4',
	'botanica',
	timestamp,
	'jeremy',
	'explanation 1',
	'question 1',
	'PUBLISHED',
	'hoja',
	'MULTIPLE_CHOISE');
	
insert into question 
	(answer1,
	answer2,
	answer3,
	answer4,
	category,
	correct_answer,
	created_at,
	created_by,
	explanation,
	question,
	status,
	subcategory,
	type)
values 
	('answer 1',
	'answer 2',
	'answer3',
	'answer 4',
	'botanica',
	timestamp,
	'jeremy',
	'explanation 2',
	'question 2',
	'PUBLISHED',
	'hoja',
	'MULTIPLE_CHOISE');
	
insert into question 
	(answer1,
	answer2,
	answer3,
	answer4,
	category,
	correct_answer,
	created_at,
	created_by,
	explanation,
	question,
	status,
	subcategory,
	type)
values 
	('answer 1',
	'answer 2',
	'answer3',
	'answer 4',
	'genetica',
	timestamp,
	'jeremy',
	'explanation 3',
	'question 3',
	'PUBLISHED',
	'adn',
	'CLASSIFICATION');
	
insert into question 
	(answer1,
	answer2,
	answer3,
	answer4,
	category,
	correct_answer,
	created_at,
	created_by,
	explanation,
	question,
	status,
	subcategory,
	type)
values 
	('answer 1',
	'answer 2',
	'answer3',
	'answer 4',
	'herpetologia',
	timestamp,
	'jeremy',
	'explanation 3',
	'question 3',
	'PUBLISHED',
	'anolis',
	'CLASSIFICATION');