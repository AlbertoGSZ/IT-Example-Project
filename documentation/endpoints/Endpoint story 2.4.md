**Show case report**
----
  Returns json data about a person's case report.

* **URL**

  /case-reports/{idPerson}/{idCaseReport}

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
   `idPerson=[integer]`
   `idCaseReport=[integer]`

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 OK<br />
    **Content:** `{"iD": "ID", "status": "Enum","name": "String","idCode": "String","date": "Date","mugshot": "Image","personDetails": "String","eventDescription": "String","evidencePhotos": "List","felonyRecordPDF": "PDF"}`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "Case report doesn't exist" }`

