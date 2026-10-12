// Send an SQL query to dbConnector.php and return the JSON response.
const runQuery = async (sql) => {
  try {
    // This is the PHP endpoint that talks to the database for us.
    const url = "https://rmeijer01.webhosting1.eeecs.qub.ac.uk/dbConnector.php";

    // Send the SQL statement in the body of a POST request.
    const response = await fetch(url, {
      method: "POST",
      body: new URLSearchParams({ query: sql })
    });

    // Stop and report a problem if the web request itself failed.
    if (!response.ok) {
      throw new Error(`HTTP Error ${response.status}`);
    }

    // Convert the JSON text from PHP into a JavaScript object.
    const result = await response.json();
    return result;

  } catch (error) {
    // Writes errors to console
    console.log(error.message);
  }
}
const validateVolunteer = (volunteer) => {
  if (volunteer.forename.trim() === "" || volunteer.forename.length > 255) {
    return "Forename invalid. Cannot be null or longer than 255 characters"
  };
  if (volunteer.surname.trim() === "" || volunteer.surname.length > 255) {
    return "Surname invalid. Cannot be null or longer than 255 characters"
  };
  if (volunteer.email.length > 255) {
    return "Forename invalid. Cannot be longer than 255 characters"
  };
  if (volunteer.dateJoined === null) {
    return "Date Joined invalid. Cannot be null"
  };
  return;
}
const validateOrganiser = (organiser) => {
  if (organiser.forename.trim() === "" || organiser.forename.length > 255) {
    return "Forename invalid. Cannot be null or longer than 255 characters"
  };
  if (organiser.surname.trim() === "" || organiser.surname.length > 255) {
    return "Surname invalid. Cannot be null or longer than 255 characters"
  };
  if (organiser.email.length > 255) {
    return "Email invalid. Cannot be longer than 255 characters"
  };
  if (organiser.dateJoined === null) {
    return "Date Joined invalid. Cannot be null"
  };
  return;
}
const escapeSql = (string) => {
  return string;
}
// Check that a beach object contains valid values before using it in SQL.
const validateBeach = (beach) => {
  if (!beach || typeof beach !== "object") {
    return "Beach details are required.";
  }
  // Clean up and convert values first so the checks below are easier to write.
  const beachName = typeof beach.beachName === "string" ?
    beach.beachName.trim() : "";
  const beachCoordinates = typeof beach.beachCoordinates === "string" ?
    beach.beachCoordinates.trim() : "";
  const beachTown = typeof beach.beachTown === "string" ?
    beach.beachTown.trim() : "";
  const beachCounty = typeof beach.beachCounty === "string" ?
    beach.beachCounty.trim() : "";
  const beachPostcode = typeof beach.beachPostcode === "string" ?
    beach.beachPostcode.trim() : "";
  if (!beachName) {
    return "Beach name is required.";
  }
  if (beachName.length > 100) {
    return "Beach name must be 100 characters or fewer.";
  }
  if (!beachCoordinates) {
    return "Beach Coordinates are required.";
  }
  if (beachCoordinates.length > 50) {
    return "Beach Coordinates must be 50 characters or fewer.";
  }
  if (!beachTown) {
    return "Town is required.";
  }
  if (beachTown.length > 50) {
    return "Town must be 50 characters or fewer.";
  }
  if (!beachCounty) {
    return "County is required.";
  }
  if (beachCounty.length > 50) {
    return "County must be 50 characters or fewer.";
  }
  if (!beachPostcode) {
    return "Postcode is required.";
  }
  if (beachPostcode.length > 10) {
    return "Postcode must be 10 characters or fewer.";
  }
  return "";
}
// Check that a bag object contains valid values before using it in SQL.
const validateBag = (bag) => {
  if (!bag || typeof bag !== "object") {
    return "Bag details are required.";
  }
  // Clean up and convert values first so the checks below are easier to write.
  const bagWeight = Number(bag.bagWeight);
  const volunteerId = Number(bag.volunteerId);
  const eventId = Number(bag.eventId);
  if (!bagWeight) {
    return "Bag weight is required.";
  }
  if (isNaN(bagWeight)) {
    return "Bag weight must be a number.";
  }
  if (bagWeight < 0) {
    return "Bag weight must not be negative.";
  }
  if (!volunteerId) {
    return "Volunteer ID is required.";
  }
  if (isNaN(volunteerId)) {
    return "Volunteer ID must be a number.";
  }
  if (!eventId) {
    return "Event ID is required.";
  }
  if (isNaN(eventId)) {
    return "Event ID must be a number.";
  }
  return "";
}
const validateEvent = (event) => {
  if (!event || typeof event !== "object") {
    return "Event details needed";
  }
  // can't use enum so validate that status is either completed or uncompleted
  const validateStatus = ["completed", "uncompleted"];
  const eventID = Number(event.eventID);
  const eventDate = event.eventDate;
  const eventStatus = event.eventStatus;
  const volunteerID = Number(event.volunteerID);
  const hoursSpentCleaning = Number(event.hoursSpentCleaning);
  //validate all variables
  if (!Number.isInteger(eventID) || eventID < 1) {
    return "The Event ID has to be a whole number > 0"
  }
  if (!eventDate) {
    return "The date needs to be in the format: yyyy/mm/dd hh:mm:ss";
  }
  if (!validateStatus.includes(eventStatus)) {
    return "The event status can only be 'completed' or 'uncompleted'";
  }
  if (!Number.isInteger(volunteerID) || volunteerID < 1) {
    return "The Volunteer ID has to be a whole number > 0"
  }
  if (!Number.isInteger(hoursSpentCleaning) || hoursSpentCleaning < 0) {
    return "Hours spent cleaning must be a positive whole number"
  }
  return "";
}


