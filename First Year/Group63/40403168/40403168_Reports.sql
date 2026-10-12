DROP TABLE IF EXISTS tblBag;
DROP TABLE IF EXISTS tblAttendance;
DROP TABLE IF EXISTS tblBeachCleanEvent;
DROP TABLE IF EXISTS tblVolunteers;

CREATE TABLE tblVolunteers(
    volunteerId INT AUTO_INCREMENT PRIMARY KEY,
    volunteerName VARCHAR(255) NOT NULL,
    volunteerEmail VARCHAR(255) UNIQUE NOT NULL,
    volunteerJoinDate DATE NOT NULL,
    volunteerStatus ENUM("ACTIVE", "INACTIVE") DEFAULT "ACTIVE"
);

CREATE TABLE tblBeachCleanEvent(
    eventId INT AUTO_INCREMENT PRIMARY KEY,
    eventDateStartTime DATETIME NOT NULL,
    eventStatus ENUM("UNCOMPLETED", "COMPLETED") DEFAULT "UNCOMPLETED"
);

CREATE TABLE tblAttendance(
    volunteerId INT,
    eventId INT,
    hoursSpent Decimal(5,2) NOT NULL DEFAULT 0,
    PRIMARY KEY (volunteerId, eventId),
    FOREIGN KEY (volunteerId) REFERENCES tblVolunteers(volunteerId),
    FOREIGN KEY (eventId) REFERENCES tblBeachCleanEvent(eventId)
);

CREATE TABLE tblBag(
    bagId INT AUTO_INCREMENT PRIMARY KEY,
    bagWeight DECIMAL(5,2),
    volunteerId INT,
    eventId INT,
    FOREIGN KEY (volunteerId) REFERENCES tblVolunteers(volunteerId),
    FOREIGN KEY (eventId) REFERENCES tblBeachCleanEvent(eventId)
);

INSERT INTO tblVolunteers VALUES
(1, 'Jane', 'Janedoe@gmail.com', '2026-03-12', 'ACTIVE'),
(2, 'Arthur', 'Morgan@gmail.com', '2026-02-09', 'INACTIVE'),
(3, 'Mark', 'invincible@gmail.com', '2026-02-28', 'ACTIVE');

INSERT INTO tblBeachCleanEvent VALUES
(1, '2026-03-01 10:00:00', 'COMPLETED'),
(2, '2026-03-21 09:30:00', 'COMPLETED'),
(3, '2026-04-11 11:00:00', 'UNCOMPLETED');

INSERT INTO tblAttendance VALUES
(1, 1, 4.7),
(2, 2, 6.8),
(3, 3, 10.59);

INSERT INTO tblBag VALUES
(1, 50.37, 1, 1),
(2, 6.2, 2, 2),
(3, 17.9, 3, 3);

-- Report Title: Volunteer attendance
-- Business Question: What number of events has each volunteer attended?
-- Why this report is useful: It helps to identify which volunteers are putting in the most time helping, allowing for some sort of reward scheme for the volunteers.
-- Tables used: Volunteer table, beach clean event table, and attendance table.

SELECT
vo.volunteerId,
vo.volunteerName,
COUNT(att.eventId) AS totalEventsAttended
FROM tblVolunteers AS vo
LEFT JOIN tblAttendance AS att
  ON vo.volunteerId = att.volunteerId
GROUP BY vo.volunteerId, vo.volunteerName
HAVING totalEventsAttended >= 0
ORDER BY totalEventsAttended DESC;

-- Report Title: Volunteer efficiency
-- Business Question: How efficiently does each volunteer collect rubbish at the events?
-- Why this report is useful: It would help to gain a number of the amount of rubbish collected, and could incentivise volunteers to compete with one another.
-- Tables used: volunteer table, beach clean event table, bag table, and attendance table.

SELECT
vo.volunteerId,
vo.volunteerName,
SUM(b.bagWeight) AS totalRubbishCollected,
SUM(att.hoursSpent) AS timeSpentCollecting,
SUM(b.bagWeight / att.hoursSpent) AS volunteerEfficiency
From tblVolunteers AS vo 
INNER JOIN tblAttendance AS att ON vo.volunteerId = att.volunteerId
LEFT JOIN tblBag AS b
  ON vo.volunteerId = b.volunteerId
GROUP BY vo.volunteerId, vo.volunteerName
HAVING timeSpentCollecting > 0
ORDER BY volunteerEfficiency DESC;


-- Report Title: Volunteer commitment
-- Business Question: How long is each volunteer spending at the events?
-- Why this report is useful: This could help identify what is keeping volunteers at events or what is causing them to spend less time at events.
-- Tables used: volunteer table, attendance table, and beach clean event table.

SELECT
vo.volunteerId,
vo.volunteerName,
COUNT(att.eventId) AS totalEventsAttended,
SUM(att.hoursSpent) AS totalTimeAtEvents,
AVG(att.hoursSpent) AS averageTimePerEvent
FROM tblVolunteers AS vo
INNER JOIN tblAttendance AS att ON vo.volunteerId = att.volunteerId
GROUP BY vo.volunteerId, vo.volunteerName
HAVING totalTimeAtEvents > 0
ORDER BY totalTimeAtEvents DESC;