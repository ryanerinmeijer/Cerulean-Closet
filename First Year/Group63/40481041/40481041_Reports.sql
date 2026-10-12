-- Report Title: Total Rubbish Collected and Average per Volunteer by Event
-- Business Question: How much rubbish is collected at each completed beach clean event, and what is the average amount collected per volunteer?
-- Why this report is useful: This helps measure the effectiveness of each event and shows how productive volunteers are. It can highlight which events or beaches generate the most waste and where volunteers are working the hardest.
-- Tables used: tblBeachCleanEvent, tblBag, tblAttendance, tblBeach
SELECT SUM(b.bagWeight) As totalWeight,
       SUM(b.bagWeight) / COUNT(DISTINCT a.volunteerId) AS averageWeightPerVolunteer,
       CONCAT(be.beachName, ' ',e.eventDateStartTime) AS eventName
FROM tblBeachCleanEvent e 
JOIN tblBag b ON e.eventId = b.eventId 
LEFT JOIN tblAttendance a ON e.eventId = a.eventId 
JOIN tblBeach be ON e.beachId = be.beachId 
WHERE e.eventStatus = 'Completed'
AND e.eventDateStartTime >= '${startDate}' AND e.eventDateStartTime <= '${endDate}'               
GROUP BY be.beachId, be.beachName, e.eventDateStartTime ORDER BY be.beachName, e.eventDateStartTime;

-- Report Title: Volunteer Attendance by Organiser and Event
-- Business Question: How many volunteers attend events organised by each organiser?
-- Why this report is useful: Can evaluate organiser performance and engagement. Organisers with higher attendance may be better at promotion or coordination, which can inform future planning and training.
-- Tables used: tblBeachCleanEvent, tblOrganiser, tblAttendance, tblBeach
SELECT CONCAT(o.organiserForename, ' ',o.organiserSurname) AS organiserName,
       CONCAT(be.beachName, ' ',e.eventDateStartTime) AS eventName,
       COUNT(DISTINCT a.volunteerID) AS volunteersPresent
FROM tblBeachCleanEvent e
JOIN tblOrganiser o ON e.organiserId = o.organiserId 
LEFT JOIN tblAttendance a ON e.eventId = a.eventId 
JOIN tblBeach be ON be.beachId = e.beachId 
WHERE eventStatus = 'Completed' 
GROUP BY o.organiserId, be.beachId 
ORDER BY volunteersPresent DESC;

-- Report Title: Volunteer Efficiency (Rubbish Collected per Hour)
-- Business Question: How long are volunteers spending at events and how efficiently are they collecting rubbish (kg per hour)?
-- Why this report is useful: This report measures volunteer productivity by comparing time spent to rubbish collected. It identifies the most efficient events and can be used to improve planning, resource allocation, and volunteer management.
-- Tables used: tblBeachCleanEvent, tblBeach, tblAttendance, tblBag
SELECT CONCAT(be.beachName, ' ', e.eventDateStartTime) AS eventName, 
a.totalHours, 
ROUND(b.totalWeight / a.totalHours, 2) AS kgPerHour 
FROM tblBeachCleanEvent e 
JOIN tblBeach be ON be.beachId = e.beachId 
JOIN (SELECT eventId,
             SUM(hoursSpent) AS totalHours
      FROM tblAttendance 
      GROUP BY eventId
      HAVING SUM(hoursSpent) >= '${hoursSpent}' ) a ON a.eventId = e.eventId 
JOIN (SELECT eventId, 
             SUM(bagWeight) AS totalWeight 
      FROM tblBag 
      GROUP BY eventId) b ON b.eventId = e.eventId 
WHERE e.eventStatus = 'Completed' 
ORDER BY a.totalHours DESC;