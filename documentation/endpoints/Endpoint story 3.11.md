**Post ResourceUrl**
----
  Returns json data about a created ResourceUrl.

* **URL**

  /resource-urls

* **Method:**

  `PUT`
  
*  **URL Params**

   **Required:**
    {
    "url": "String"
    }

* **Data Params**
    {
    "url": "String"
    }
  
* **Success Response:**

  * **Code:** 200 OK<br />
    **Content:** `{"id": "int","url": "String"}`
 
* **Error Response:**

  * **Code:** 400 BAD REQUEST <br />
    **Content:** `{ error : "Invalid specified syntax" }`




