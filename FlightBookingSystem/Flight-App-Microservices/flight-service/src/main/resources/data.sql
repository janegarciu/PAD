INSERT INTO city
    (id, name)
VALUES (1, 'Barcelona');

INSERT INTO city
    (id, name)
VALUES (2, 'Madrid');

INSERT INTO airports
    (id, name, id_city)
VALUES (1, 'Josep Tarradellas Barcelona-El Prat Airport', 1);

INSERT INTO airports
    (id, name, id_city)
VALUES (2, 'Madrid-Barajas Adolfo Suárez Airport', 2);

INSERT INTO flights
    (id, flight_number, id_departure, id_destination)
VALUES (1, 'Abc123', 2, 1);



