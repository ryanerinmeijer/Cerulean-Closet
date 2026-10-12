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
        // Log errors so they can be seen in the browser console during debugging.
        console.log(error.message);
      }
    }

    // Check that a student object contains valid values before using it in SQL.
    const validateStudent = (student) => {
      if (!student || typeof student !== "object") {
        return "Student details are required.";
      }

      // Clean up and convert values first so the checks below are easier to write.
      const studentID = Number(student.studentID);
      const firstName = typeof student.firstName === "string" ? student.firstName.trim() : "";
      const lastName = typeof student.lastName === "string" ? student.lastName.trim() : "";
      const course = typeof student.course === "string" ? student.course.trim() : "";
      const mark = Number(student.mark);

      if (!Number.isInteger(studentID) || studentID < 1) {
        return "Student ID must be a whole number greater than 0.";
      }

      if (!firstName) {
        return "First name is required.";
      }

      if (firstName.length > 50) {
        return "First name must be 50 characters or fewer.";
      }

      if (!lastName) {
        return "Last name is required.";
      }

      if (lastName.length > 50) {
        return "Last name must be 50 characters or fewer.";
      }

      if (!course) {
        return "Course is required.";
      }

      if (course.length > 50) {
        return "Course must be 50 characters or fewer.";
      }

      if (!Number.isInteger(mark) || mark < 0 || mark > 100) {
        return "Mark must be a whole number between 0 and 100.";
      }

      return "";
    }

    // Replace single quotes with two single quotes so they are safer inside SQL strings.
    // /'/g means "find every single quote in the text" (g = global, so not just the first one).
    const escapeSql = (value) => {
       return value.replace(/'/g, "''");
    }
