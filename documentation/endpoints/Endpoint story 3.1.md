**Create person**
----
  Returns json data about a just created person.

* **URL**

  /person

* **Method:**

  `POST`
  
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

  * **Code:** 200 OK<br />
    **Content:** `{"ID": "int","status": "Enum","name": "String","surname": "String","birthDate": "Date","age": "int","adress": "String","nationality": "Enum","sex": "Enum","alias": "String","felonyRecordsDigital": "List","rank": "int","chief": "Person","subordinates": "List"}`
 
* **Error Response:**

  * **Code:** 403 FORBIDDEN <br />
    **Content:** `{ error : "Person already exists" }`

  OR

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{ error : "You are unauthorized to make this request." }`

