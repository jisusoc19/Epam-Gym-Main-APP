INSERT INTO usergym (first_Name, last_Name, username, password, is_Active) VALUES ('John', 'Doe', 'john_doe1', 'password123', TRUE);
INSERT INTO usergym (first_Name, last_Name, username, password, is_Active) VALUES ('Jane', 'Doe', 'jane_doe', 'password123', TRUE);

INSERT INTO Trainee (date_Birth, addres, id_usuario) VALUES ('1990-01-01', '1234 Elm Street', 1);
INSERT INTO Trainee (date_Birth, addres, id_usuario) VALUES ('1992-02-02', '5678 Oak Avenue', 2);

INSERT INTO training_type (training_Type_Name) VALUES ('Physical Training');
INSERT INTO training_type (training_Type_Name) VALUES ('Mental Training');

INSERT INTO Trainer (id_usuario, specialization) VALUES ( 1, 1);
INSERT INTO Trainer (id_usuario, specialization) VALUES ( 2, 2);

INSERT INTO Training (trainee_id, trainer_id, training_name, training_date, duration, training_type_id) VALUES ( 1, 1, 'Basic Fitness', '2023-01-01', 60, 1);
INSERT INTO Training (trainee_id, trainer_id, training_name, training_date, duration, training_type_id) VALUES ( 2, 2, 'Advanced Mindfulness', '2023-02-01', 90, 2)
