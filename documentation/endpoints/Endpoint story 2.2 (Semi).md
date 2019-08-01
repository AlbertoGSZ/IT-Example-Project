**Search Person**
----
  Returns json data about a single person.

* **URL**

  /person/{XXXXXX PERSON JSON XXXXXX}

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
   None

* **Data Params**

   XXXXXXXXXXXXXXXXXXXX
   XXXXXXXXXXXXXXXXXXXX
   XXXXXXXXXXXXXXXXXXXX
   XXXXXXXXXXXXXXXXXXXX


* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ XXXXXXXXXX PERSON JSON XXXXXXXXXX" }`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "No person matches with the specified info" }`

  OR

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{ error : "You are unauthorized to make this request." }`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/person/XXXXXX PERSON JSON XXXXXX",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```