**Show all people**
----
  Returns json data about a every person in the DDBB.

* **URL**

  /people
    
* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
   
    `status:[Enum]`
    `name:[String]`
    `surname:[String]`
    `birthDate:[Date]`
    `age:[int]`
    `adress:[String]`
    `nationality:[Enum]`
    `sex:[Enum]`
    `alias:[String]`
    `felonyRecordsDigital:[List]`
    `rank:[int]`
    `chief:[Person]`
    `subordinates:[List]`
    
* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 OK <br />
    **Content:** ``{["ID": "int","status": "Enum","name": "String","surname": "String","birthDate": "Date","age": "int","adress": "String","nationality": "Enum","sex": "Enum","alias": "String","felonyRecordsDigital": "List","rank": "int","chief": "Person","subordinates": "List", "ID": "int","status": "Enum","name": "String","surname": "String","birthDate": "Date","age": "int","adress": "String","nationality": "Enum","sex": "Enum","alias": "String","felonyRecordsDigital": "List","rank": "int","chief": "Person","subordinates": "List"...]}`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "No people registered in database" }`


