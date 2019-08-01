**Show case report**
----
  Returns json data about a person's case report.

* **URL**

  /caseReport/{idPerson}/{idCaseReport}

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
   `idPerson=[integer]`
   `idCaseReport=[integer]`

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ XXXXXXXXXXX JSON CaseReport XXXXXXXXXXX}`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "Case report doesn't exist" }`

  OR

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{ error : "You are unauthorized to make this request." }`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/caseReport/7/3",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```
