**Rank person down**
----
  Returns code 200.

* **URL**

  /person/rankDown/{id}

* **Method:**

  `PATCH`
  
*  **URL Params**

   **Required:**
 
   `id=[integer]`

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ "Person´s rank down succesful" }`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "Person doesn't exist" }`

  OR

  * **Code:** 403 FORBIDDEN <br />
    **Content:** `{ error : "Person already has the lowest rank." }`

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
      url: "/person/rankDown/8",
      dataType: "json",
      type : "PATCH",
      success : function(r) {
        console.log(r);
      }
    });
  ```
