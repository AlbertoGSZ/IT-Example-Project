**Matching Person**
----
  Returns json data about a single person.

* **URL**

  /person.php?status=ALIVE&name=Alberto&surname=García&birthDate=15051990&age=29&adress=CalleBonito&nationality=ESPAÑA&sex=HOMBRE&alias=Tardón& felonyRecordsDigital=List&rank=2&chief=JoseJulian&subordinates=List

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

  * **Code:** 200 OK<br />
    **Content:** `{["iD": "int","status": "Enum","name": "String","surname": "String","birthDate": "Date","age": "int","adress": "String","nationality": "Enum","sex": "Enum","alias": "String","felonyRecordsDigital": "List","rank": "int","chief": "Person","subordinates": "List", "iD": "int","status": "Enum","name": "String","surname": "String","birthDate": "Date","age": "int","adress": "String","nationality": "Enum","sex": "Enum","alias": "String","felonyRecordsDigital": "List","rank": "int","chief": "Person","subordinates": "List"...]}`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "No person matches with the specified info" }`

