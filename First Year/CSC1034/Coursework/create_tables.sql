CREATE TABLE tblBeach (
    beachId INT PRIMARY KEY AUTO_INCREMENT,
    beachName VARCHAR(100) NOT NULL UNIQUE,
    beachCoordinates VARCHAR(50) NOT NULL,
    beachPostcode VARCHAR(10) NOT NULL,
    beachTown VARCHAR(50) NOT NULL,
    beachCounty VARCHAR(50) NOT NULL,
    beachType ENUM('Sand', 'Shingle', 'Unknown') NOT NULL DEFAULT 'Unknown'
);

CREATE TABLE tblOrganiser (
    organiserId INT PRIMARY KEY AUTO_INCREMENT,
    organiserForename VARCHAR(255) NOT NULL,
    organiserSurname VARCHAR(255) NOT NULL,
    organiserEmail VARCHAR(255) NOT NULL,
    organiserDateJoined DATE NOT NULL
);

CREATE TABLE tblVolunteer (
    volunteerId INT PRIMARY KEY AUTO_INCREMENT,
    volunteerForename VARCHAR(255) NOT NULL,
    volunteerSurname VARCHAR(255) NOT NULL,
    volunteerEmail VARCHAR(255),
    volunteerDateJoined DATE NOT NULL,
    volunteerType ENUM('Active', 'Inactive') NOT NULL DEFAULT 'Active'
);


CREATE TABLE tblBeachCleanEvent (
    eventId INT PRIMARY KEY AUTO_INCREMENT,
    eventDateStartTime DATETIME NOT NULL,
    eventStatus ENUM('Upcoming', 'Completed') NOT NULL,
    beachId INT NOT NULL,
    organiserId INT NOT NULL,
    FOREIGN KEY (beachId) REFERENCES tblBeach(beachId),
    FOREIGN KEY (organiserId) REFERENCES tblOrganiser(organiserId)
);

CREATE TABLE tblBag (
    bagId INT PRIMARY KEY AUTO_INCREMENT,
    bagWeight DECIMAL(6,3),
    volunteerId INT NOT NULL,
    eventId INT NOT NULL,
    FOREIGN KEY (volunteerId) REFERENCES tblVolunteer(volunteerId),
    FOREIGN KEY (eventId) REFERENCES tblBeachCleanEvent(eventId)
);

CREATE TABLE tblAttendance (
    eventId INT NOT NULL,
    volunteerId INT NOT NULL,
    hoursSpent INT NOT NULL DEFAULT 0,
    PRIMARY KEY (eventId, volunteerId),
    FOREIGN KEY (volunteerId) REFERENCES tblVolunteer(volunteerId),
    FOREIGN KEY (eventId) REFERENCES tblBeachCleanEvent(eventId)
);