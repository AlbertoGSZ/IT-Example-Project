**Delete ResourceUrl**
----
  Returns json data about a deleted ResourceUrl.

* **URL**

  /case-reports/{id}

* **Method:**

  `DELETE`
  
*  **URL Params**

   **Required:**
 
   `id=[integer]`

* **Data Params**
  None

* **Success Response:**

  * **Code:** 200 OK<br />
    **Content:** `{"Deleted URL."}`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "ResourceURL doesn't exist" }`


