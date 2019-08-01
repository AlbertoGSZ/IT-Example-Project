**Edit person**
----
  Returns json data about an edited person.

* **URL**

  /person

* **Method:**

  `PUT`
  
*  **URL Params**

   **Required:**
 
   None

* **Data Params**

  {
  "status": "Enum",
  "name": "String",
  "surname": "String",
  "birthDate": "Date",
  "age": "int",
  "adress": "String",
  "nationality": "Enum",
  "sex": "Enum",
  "alias": "String",
  "felonyRecordsDigital": "List",
  "rank": "int",
  "chief": "Person",
  "subordinates": "List"
  }

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{"ID": "int","status": "Enum","name": "String","surname": "String","birthDate": "Date","age": "int","adress": "String","nationality": "Enum","sex": "Enum","alias": "String","felonyRecordsDigital": "List","rank": "int","chief": "Person","subordinates": "List"}`

 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "No people registered in database" }`

  OR

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{ error : "You are unauthorized to make this request." }`

