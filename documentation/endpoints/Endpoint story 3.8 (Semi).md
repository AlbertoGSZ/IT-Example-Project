**Edit case report**
----
  Returns json data about an edited case report.

* **URL**

  /caseReport/{id}

* **Method:**

  `PUT`
  
*  **URL Params**

   **Required:**
 
   `id=[integer]`

* **Data Params**

  XXXXXXXXXXXXXXXXXXXXXX
  XXXXXXXXXXXXXXXXXXXXXX
  XXXXXXXXXXXXXXXXXXXXXX  

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ XXXXXXXXXXX  JSON CASE REPORT XXXXXXXXXXXX" }`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "Case report doesn't exist" }`

  OR

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{ error : "You are unauthorized to make this request." }`

