**Edit ResourceUrl**
----
  Returns json data about an edited ResourceUrl.

* **URL**

  /resource-urls/{id}

* **Method:**

  `PATCH`
  
*  **URL Params**

   **Required:**
 
   `id=[integer]`

* **Data Params**

    {
    "url": "string"
    }

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{"id": "ID", "url": "String"}`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "Refered url doesn't exist" }`

