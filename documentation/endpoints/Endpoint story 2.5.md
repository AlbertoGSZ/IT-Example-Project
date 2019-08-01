**Show all case reports of a person**
----
  Returns json data about a list of case reports.

* **URL**

  /person/{id}

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
   `id=[integer]`

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 OK<br />
    **Content:** `{["iD": "ID", "status": "Enum","name": "String","idCode": "String","date": "Date","mugshot": "Image","personDetails": "String","eventDescription": "String","evidencePhotos": "List","felonyRecordPDF": "PDF","iD": "ID", "status": "Enum","name": "String","idCode": "String","date": "Date","mugshot": "Image","personDetails": "String","eventDescription": "String","evidencePhotos": "List","felonyRecordPDF": "PDF",...]}`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "Person not found" }`

  OR

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{ error : "You are unauthorized to make this request." }`

