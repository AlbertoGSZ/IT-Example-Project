**Edit case report**
----
  Returns json data about an edited case report.

* **URL**

  /case-reports/{id}

* **Method:**

  `PUT`
  
*  **URL Params**

   **Required:**
 
   `id=[integer]`

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

  * **Code:** 200 <br />
    **Content:** `{"iD": "ID", "status": "Enum","name": "String","idCode": "String","date": "Date","mugshot": "Image","personDetails": "String","eventDescription": "String","evidencePhotos": "List","felonyRecordPDF": "PDF"}`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "Case report doesn't exist" }`

