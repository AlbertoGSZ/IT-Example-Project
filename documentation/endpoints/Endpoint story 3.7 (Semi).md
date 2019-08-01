**Create case report**
----
  Returns json data about a case report.

* **URL**

  /caseReport

* **Method:**

  `POST`
  
*  **URL Params**

   **Required:**
 
   `id=[integer]`

* **Data Params**

  XXXXXXXXXXXXXXXXXXXXXX 
  XXXXXXXXXXXXXXXXXXXXXX 
  XXXXXXXXXXXXXXXXXXXXXX 
  XXXXXXXXXXXXXXXXXXXXXX 
  
* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ XXXXXXXXX JSON CASE REPORT XXXXXXXX" }`
 
* **Error Response:**

  * **Code:** 400 BAD REQUEST <br />
    **Content:** `{ error : "Invalid specified syntax" }`

  OR

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{ error : "You are unauthorized to make this request." }`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/caseReport",
      dataType: "json",
      type : "POST",
      success : function(r) {
        console.log(r);
      }
    });
  ```
