**Create case report**
----
  Returns json data about a case report.

* **URL**

  /case-reports

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
    **Content:** `{"iD": "ID", "status": "Enum","name": "String","idCode": "String","date": "Date","mugshot": "Image","personDetails": "String","eventDescription": "String","evidencePhotos": "List","felonyRecordPDF": "PDF"}`
 
* **Error Response:**

  * **Code:** 400 BAD REQUEST <br />
    **Content:** `{ error : "Invalid specified syntax" }`




