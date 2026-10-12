-- Report Title: Total Rubbish Collected at Each Beach
-- Business Question: How much total rubbish in kg has been collected at each beach?
-- Why this report is useful: Identifies the amount of rubbish collected at each beach and highlights beaches in which no rubbish has been collected, this allows the organisation to track progress over time and help them plan for future events
-- Tables used: tblBeach, tblBeachCleanEvent, tblBag
SELECT tblBeach.beachName,
    tblBeach.beachTown,
    tblBeach.beachCounty,
    COALESCE(SUM(tblBag.bagWeight), 0) AS totalRubbishKg
FROM tblBeach
    LEFT JOIN tblBeachCleanEvent ON tblBeach.beachId = tblBeachCleanEvent.beachId
    LEFT JOIN tblBag ON tblBeachCleanEvent.eventId = tblBag.eventId
GROUP BY tblBeach.beachId,
    tblBeach.beachName,
    tblBeach.beachTown,
    tblBeach.beachCounty
ORDER BY totalRubbishKg DESC;
-- Report Title: Number of Events Organised Per Beach
-- Business Question: How many events have been organised per beach?
-- Why this report is useful: Allows the organisation to easily see how many events have been organised per beach and identify which beaches may have never been cleaned or aren't cleaned enough. Also shows which beaches that maybe getting cleaned too often and allows fo a more even spread
-- Tables used: tblBeach, tblBeachCleanEvent
SELECT tblBeach.beachName,
    tblBeach.beachTown,
    tblBeach.beachCounty,
    tblBeach.beachType,
    COUNT(tblBeachCleanEvent.eventId) AS totalEvents
FROM tblBeach
    LEFT JOIN tblBeachCleanEvent ON tblBeach.beachId = tblBeachCleanEvent.beachId
GROUP BY tblBeach.beachId,
    tblBeach.beachName,
    tblBeach.beachTown,
    tblBeach.beachCounty,
    tblBeach.beachType
ORDER BY totalEvents DESC;
-- Report Title: Most Recently Cleaned Beaches
-- Business Question: Which beaches have been cleaned most recently?
-- Why this report is useful: Allows the organisation to see which beaches have been cleaned most recently and identify which beaches that maybe have not been cleaned in a while and are due one, helping them to plan for future events
-- Tables used: tblBeach, tblBeachCleanEvent, tblOrganiser
SELECT tblBeach.beachName,
    tblBeach.beachTown,
    tblOrganiser.organiserForename,
    tblOrganiser.organiserSurname,
    MAX(tblBeachCleanEvent.eventDateStartTime) AS lastCleaned,
    COUNT(tblBeachCleanEvent.eventId) AS totalEvents
FROM tblBeachCleanEvent
    INNER JOIN tblBeach ON tblBeachCleanEvent.beachId = tblBeach.beachId
    INNER JOIN tblOrganiser ON tblBeachCleanEvent.organiserId = tblOrganiser.organiserId
WHERE tblBeachCleanEvent.eventStatus = 'Completed'
GROUP BY tblBeach.beachId,
    tblBeach.beachName,
    tblBeach.beachTown,
    tblOrganiser.organiserId,
    tblOrganiser.organiserForename,
    tblOrganiser.organiserSurname
HAVING COUNT(tblBeachCleanEvent.eventId) >= 1
ORDER BY lastCleaned DESC
LIMIT 10;