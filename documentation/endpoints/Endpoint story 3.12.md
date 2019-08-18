**Delete ResourceUrl**
----
  Returns json data about a deleted ResourceUrl.

* **URL**

  /caseReport/{id}

* **Method:**

  `DELETE`
  
*  **URL Params**

   **Required:**
 
   `id=[integer]`

* **Data Params**
  None

* **Success Response:**

  * **Code:** 200 OK<br />
    **Content:** `{"Deleted url."}`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "ResourceURL doesn't exist" }`


