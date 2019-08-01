**Imprison person**
----
  Returns code 200.

* **URL**

  /person/{id}

* **Method:**

  `PATCH`
  
*  **URL Params**

   **Required:**
 
   `id=[integer]`

* **Data Params**

    This endpoint is intended to modify only one of the following atributes at a time from the ¨Person¨ entity
    
     {"Status": "IMPRISONED"}
     
  OR
  
     {"Status": "ALIVE"} <!--- Understood as released from prison --->
     
  OR
  
     {"Rank": "RankUp"}
     
  OR
  
     {"Rank": "RankDown"}
     

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ "Person imprisoned." }`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "Person doesn't exist" }`

  OR

    * **Code:** 403 FORBIDDEN <br />
    **Content:** `{ error : "Person is already imprisoned" }`

  OR
    
    * **Code:** 403 FORBIDDEN <br />
    **Content:** `{ error : "Person is deceased" }`

  OR

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{ error : "You are unauthorized to make this request." }`


