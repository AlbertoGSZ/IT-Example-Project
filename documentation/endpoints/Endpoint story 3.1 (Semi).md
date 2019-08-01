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

  XXXXXXXXXXXXXXXXXX
  XXXXXXXXXXXXXXXXXX
  XXXXXXXXXXXXXXXXXX
  XXXXXXXXXXXXXXXXXX
  XXXXXXXXXXXXXXXXXX


* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{   XXXXXXXX JSON PERSON XXXXXXXX" }`
 
* **Error Response:**

  * **Code:** 403 FORBIDDEN <br />
    **Content:** `{ error : "Person already exists" }`

  OR

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{ error : "You are unauthorized to make this request." }`

