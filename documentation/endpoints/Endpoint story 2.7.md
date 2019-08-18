**Show ResourceUrl**
----
  Returns a json with the info of a ResourceUrl.

* **URL**

  /resource-urls/{id}

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
   `id=[integer]`

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 OK<br />
    **Content:** `{"id": "int","url": "String"}`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "Refered url doesn't exist" }`

