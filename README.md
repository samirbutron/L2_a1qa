# REST API (Get/Post)

## Task Description
**API - [https://jsonplaceholder.typicode.com](https://jsonplaceholder.typicode.com/).**

|#|Step|Expected results|Example|
|---|---|---|---|
|1|Send **GET** Request to get all posts (**/posts**).|Status code is **200.**<br><br>The list in response body is json.<br><br>Posts are sorted ascending (by id).|![](https://wiki.a1qa.com/download/attachments/597825293/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202019-10-16%20%D0%B2%2012.43.59.png?version=26&modificationDate=1646322589000&api=v2)|
|2|Send **GET** request to get post with id=99 (**/posts/99**).|Status code is **200.**<br><br>Post information is correct: **userId** is **10**, **id** is **99, title** and **body** aren't empty.|![](https://wiki.a1qa.com/download/attachments/597825293/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202019-10-16%20%D0%B2%2012.53.49.png?version=9&modificationDate=1644489471000&api=v2)|
|3|Send **GET** request to get post with id=150 (**/posts/150**).|Status code is **404.**<br><br>Response body is empty.|{ }|
|4|Send **POST** request to create post with userId=1 and random body and random title (**/posts**).|Status code is **201**.<br><br>Post information is correct: title, body, userId match data from request, id is present in response.|![](https://wiki.a1qa.com/download/thumbnails/597825293/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202019-10-16%20%D0%B2%2012.37.53.png?version=2&modificationDate=1639131682000&api=v2)|
|5|Send **GET** request to get users (**/users**).|Status code is **200.**<br><br>The list in response body is json.<br><br>User (id=5) data equals to: <br><br>name: Chelsey Dietrich  <br>username: Kamren  <br>email: Lucio_Hettinger@annie.ca  <br>**address:**  <br>street: Skiles Walks  <br>suite: Suite 351  <br>city: Roscoeview  <br>zipcode: 33263  <br>**geo:**  <br>lat: -31.8129  <br>lng: 62.5342  <br>  <br>phone: (254)954-1289,  <br>website: demarco.info  <br>**company:**  <br>name: Keebler LLC,  <br>catchPhrase: User-centric fault-tolerant solution  <br>bs: revolutionize end-to-end systems|![](https://wiki.a1qa.com/download/attachments/597825293/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202019-10-16%20%D0%B2%2013.24.38.png?version=8&modificationDate=1645292464000&api=v2)|
|6|Send **GET** request to get user with id=5 (**/users/5**).|Status code is **200.**<br><br>User data matches with user data in the previous step.|![](https://wiki.a1qa.com/download/attachments/597825293/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202019-10-16%20%D0%B2%2013.30.44.png?version=2&modificationDate=1643029726000&api=v2)|

- API Utils classes/methods are universal.
- Data isn't hardcoded.
- Models are implemented

