<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      href="../../webjars/bootstrap/css/bootstrap.min.css"
      rel="stylesheet"
      crossorigin="anonymous"
    />

    <title>Todos List</title>
  </head>
  <body>
    <h1>Welcome to Todos Application ${name}</h1>
    <hr />
    <h2>Your Todos are:</h2>
    <table>
      <thead>
        <tr>
          <th>Id</th>
          <th>Description</th>
          <th>Target Date</th>
          <th>Completed</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${todos}" var="todo">
          <tr>
            <td>${todo.id}</td>
            <td>${todo.description}</td>
            <td>${todo.targetDate}</td>
            <td>${todo.done}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

    <script href="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
    <script href="webjars/jquery/3.6.0/jquery.min.js"></script>
  </body>
</html>
