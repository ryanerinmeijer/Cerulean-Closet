-- ================================================
-- tblBeach
-- ================================================
INSERT INTO tblBeach (
        beachName,
        beachCoordinates,
        beachPostcode,
        beachTown,
        beachCounty,
        beachType
    )
VALUES (
        'Ballyholme Beach',
        '54.6537,-5.6694',
        'BT20 5NE',
        'Bangor',
        'Down',
        'Sand'
    ),
    (
        'Helens Bay Beach',
        '54.6833,-5.7167',
        'BT19 1LA',
        'Helens Bay',
        'Down',
        'Sand'
    ),
    (
        'Donaghadee Beach',
        '54.6404,-5.5322',
        'BT21 0HE',
        'Donaghadee',
        'Down',
        'Shingle'
    ),
    (
        'Millisle Beach',
        '54.6039,-5.5314',
        'BT22 2LY',
        'Millisle',
        'Down',
        'Sand'
    ),
    (
        'Tyrella Beach',
        '54.2208,-5.7889',
        'BT30 8SU',
        'Downpatrick',
        'Down',
        'Sand'
    ),
    (
        'Crawfordsburn Beach',
        '54.6742,-5.7328',
        'BT19 1JT',
        'Crawfordsburn',
        'Down',
        'Shingle'
    ),
    (
        'Portavoe Beach',
        '54.6333,-5.5667',
        'BT22 2AH',
        'Donaghadee',
        'Down',
        'Shingle'
    ),
    (
        'Ballymacormick Beach',
        '54.6667,-5.6833',
        'BT19 6RT',
        'Bangor',
        'Down',
        'Sand'
    ),
    (
        'Groomsport Beach',
        '54.6667,-5.6500',
        'BT19 6JR',
        'Groomsport',
        'Down',
        'Sand'
    ),
    (
        'Kinnegar Beach',
        '54.6500,-5.8000',
        'BT18 9JQ',
        'Holywood',
        'Down',
        'Sand'
    );
-- ================================================
-- tblOrganiser
-- ================================================
INSERT INTO tblOrganiser (
        organiserForename,
        organiserSurname,
        organiserEmail,
        organiserDateJoined
    )
VALUES (
        'James',
        'Murphy',
        'james.murphy@beachclean.com',
        '2022-01-15'
    ),
    (
        'Sarah',
        'Jones',
        'sarah.jones@beachclean.com',
        '2022-03-22'
    ),
    (
        'Mark',
        'Wilson',
        'mark.wilson@beachclean.com',
        '2022-06-10'
    ),
    (
        'Emma',
        'Thompson',
        'emma.thompson@beachclean.com',
        '2022-09-05'
    ),
    (
        'David',
        'Brown',
        'david.brown@beachclean.com',
        '2023-01-20'
    ),
    (
        'Laura',
        'Kelly',
        'laura.kelly@beachclean.com',
        '2023-04-14'
    ),
    (
        'Michael',
        'ONeill',
        'michael.oneill@beachclean.com',
        '2023-07-01'
    ),
    (
        'Claire',
        'Hughes',
        'claire.hughes@beachclean.com',
        '2023-10-18'
    ),
    (
        'Patrick',
        'Doherty',
        'patrick.doherty@beachclean.com',
        '2024-02-09'
    ),
    (
        'Rachel',
        'Stewart',
        'rachel.stewart@beachclean.com',
        '2024-05-30'
    );
-- ================================================
-- tblVolunteer
-- ================================================
INSERT INTO tblVolunteer (
        volunteerForename,
        volunteerSurname,
        volunteerEmail,
        volunteerDateJoined,
        volunteerType
    )
VALUES (
        'John',
        'Smith',
        'john.smith@email.com',
        '2022-02-10',
        'Active'
    ),
    (
        'Emily',
        'White',
        'emily.white@email.com',
        '2022-04-15',
        'Active'
    ),
    (
        'Daniel',
        'Martin',
        'daniel.martin@email.com',
        '2022-08-20',
        'Active'
    ),
    (
        'Sophie',
        'Taylor',
        'sophie.taylor@email.com',
        '2022-11-03',
        'Active'
    ),
    (
        'Ryan',
        'Anderson',
        'ryan.anderson@email.com',
        '2023-02-28',
        'Active'
    ),
    (
        'Megan',
        'Clarke',
        'megan.clarke@email.com',
        '2023-05-17',
        'Inactive'
    ),
    (
        'Thomas',
        'Robinson',
        'thomas.robinson@email.com',
        '2023-08-11',
        'Active'
    ),
    (
        'Hannah',
        'Walker',
        'hannah.walker@email.com',
        '2023-11-25',
        'Active'
    ),
    (
        'Luke',
        'Harris',
        'luke.harris@email.com',
        '2024-03-07',
        'Active'
    ),
    (
        'Amy',
        'Lewis',
        'amy.lewis@email.com',
        '2024-06-19',
        'Active'
    );
-- ================================================
-- tblBeachCleanEvent
-- ================================================
INSERT INTO tblBeachCleanEvent (
        eventDateStartTime,
        eventStatus,
        beachId,
        organiserId
    )
VALUES ('2024-01-20 09:00:00', 'Completed', 1, 1),
    ('2024-02-15 10:00:00', 'Completed', 2, 2),
    ('2024-03-10 09:30:00', 'Completed', 1, 3),
    ('2024-04-05 10:00:00', 'Completed', 3, 1),
    ('2024-05-18 09:00:00', 'Completed', 4, 4),
    ('2024-06-22 10:00:00', 'Completed', 2, 5),
    ('2024-07-14 09:00:00', 'Completed', 1, 2),
    ('2024-08-30 10:30:00', 'Completed', 5, 6),
    ('2024-09-21 09:00:00', 'Completed', 3, 3),
    ('2025-01-11 10:00:00', 'Upcoming', 6, 7);
-- ================================================
-- tblBag
-- ================================================
INSERT INTO tblBag (bagWeight, volunteerId, eventId)
VALUES (5.250, 1, 1),
    (3.750, 2, 1),
    (4.500, 3, 2),
    (6.000, 4, 2),
    (2.750, 1, 3),
    (5.500, 5, 3),
    (3.250, 6, 4),
    (4.750, 7, 5),
    (6.500, 8, 6),
    (3.000, 9, 7);
-- ================================================
-- tblAttendance
-- ================================================
INSERT INTO tblAttendance (volunteerId, eventId, hoursSpent)
VALUES (1, 1, 3),
    (2, 1, 2),
    (3, 2, 4),
    (4, 2, 3),
    (1, 3, 2),
    (5, 3, 3),
    (6, 4, 2),
    (7, 5, 4),
    (8, 6, 3),
    (9, 7, 2);