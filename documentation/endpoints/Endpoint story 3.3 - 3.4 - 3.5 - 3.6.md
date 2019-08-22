**Imprison/Release/RankUp/RankDown person**
----
  Returns json data about person's changed info.

* **URL**

  /people/{id}

* **Method:**

  `PATCH`
  
*  **URL Params**

   **Required:**
 
   `id=[integer]`

* **Data Params**

    This endpoint is intended to modify only one of the following atributes at a time from the ¨Person¨ entity
    
     {"status": "IMPRISONED"}
     
  OR
  
     {"status": "ALIVE"} <!--- Understood as released from prison --->
     
  OR
  
     {"status": "DEAD"} <!--- Understood as released from prison --->
     
  OR
  
     {"status": "UNKNOWN"} <!--- Understood as released from prison --->
     
  OR
  
     {"rank": "rank +1"}
     
  OR
  
     {"rank": "rank -1"}
     

* **Success Response:**

  * **Code:** 200 OK<br />
    **Content:** `{"iD": "int","status": "Enum","name": "String","surname": "String","birthDate": "Date","age": "int","adress": "String","nationality": "Enum","sex": "Enum","alias": "String","felonyRecordsDigital": "List","rank": "int","chief": "Person","subordinates": "List"}`
 
* **Error Response:**

  * **Code:** 400 BAD REQUEST <br />
    **Content:** `{ error : "Invalid specified syntax" }`

  OR

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "Person doesn't exist" }`

 
