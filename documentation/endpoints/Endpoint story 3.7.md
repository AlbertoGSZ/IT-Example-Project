**Create case report**
----
  Returns json data about a case report.

* **URL**

  /caseReport

* **Method:**

  `POST`
  
*  **URL Params**

   **Required:**
 
   None

* **Data Params**
    {
    "status": "Enum",
    "name": "String",
    "idCode": "String",
    "date": "Date",
    "mugshot": "Image",
    "personDetails": "String",
    "eventDescription": "String",
    "evidencePhotos": "List",
    "felonyRecordPDF": "PDF"
    }
  
* **Success Response:**

  * **Code:** 200 OK<br />
    **Content:** `{"status": "Enum","name": "String","idCode": "String","date": "Date","mugshot": "Image","personDetails": "String","eventDescription": "String","evidencePhotos": "List","felonyRecordPDF": "PDF"}`
 
* **Error Response:**

  * **Code:** 400 BAD REQUEST <br />
    **Content:** `{ error : "Invalid specified syntax" }`

  OR

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{ error : "You are unauthorized to make this request." }`


