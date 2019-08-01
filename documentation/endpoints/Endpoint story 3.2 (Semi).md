**Edit person**
----
  Returns json data about an edited person.

* **URL**

  /person

* **Method:**

  `PUT`
  
*  **URL Params**

   **Required:**
 
  None

* **Data Params**

  XXXXXXXXXXXXXXXXXXXXXXXXX
  XXXXXXXXXXXXXXXXXXXXXXXXX
  XXXXXXXXXXXXXXXXXXXXXXXXX
  XXXXXXXXXXXXXXXXXXXXXXXXX
  XXXXXXXXXXXXXXXXXXXXXXXXX


* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ XXXXXXXXXXXX JSON EDITED PERSON XXXXXXXXXXXX }`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "User doesn't exist" }`

  OR

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{ error : "You are unauthorized to make this request." }`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/person",
      dataType: "json",
      type : "PUT",
      success : function(r) {
        console.log(r);
      }
    });
  ```