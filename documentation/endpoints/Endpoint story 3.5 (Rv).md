**Rank person up**
----
  Returns code 200.

* **URL**

  /person/rankUp/{id}

* **Method:**

  `PATCH`
  
*  **URL Params**

   **Required:**
 
   `id=[integer]`

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ "Person´s rank up succesful" }`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "Person doesn't exist" }`

  OR

  * **Code:** 403 FORBIDDEN <br />
    **Content:** `{ error : "Person already has the highest rank." }`

  OR
  
    * **Code:** 403 FORBIDDEN <br />
    **Content:** `{ error : "Person is deceased." }`

  OR
   
   * **Code:** 403 FORBIDDEN <br />
    **Content:** `{ error : "Person is imprisoned." }`

  OR

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{ error : "You are unauthorized to make this request." }`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/person/rankUp/8",
      dataType: "json",
      type : "PATCH",
      success : function(r) {
        console.log(r);
      }
    });
  ```
