**Show all people**
----
  Returns json data about a every person in the DDBB.

* **URL**

  /person

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
  None

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ XXXXXXXXXXXXXX PERSON [PERSON, PERSON ...] XXXXXXXXXXXXXX }`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "No people registered in database" }`

  OR

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{ error : "You are unauthorized to make this request." }`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/person",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```