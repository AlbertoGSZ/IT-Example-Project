**Delete case report**
----
  Returns code 200.

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

  * **Code:** 200 <br />
    **Content:** `{ "Case report deleted" }`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "Case report doesn't exist" }`

  OR

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{ error : "You are unauthorized to make this request." }`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/caseReport/75",
      dataType: "json",
      type : "DELETE",
      success : function(r) {
        console.log(r);
      }
    });
  ```
