<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      href="webjars/bootstrap/5.1.3/css/bootstrap.min.css"
      rel="stylesheet"
    />

    <title>Todos List</title>
  </head>
  <body>
    <div class="container">
      <h1>Welcome to Todos Application ${name}</h1>
      <hr />
      <h2>Your Todos are:</h2>
      <table class="table">
        <thead>
          <tr>
            <th>Id</th>
            <th>Description</th>
            <th>Target Date</th>
            <th>Completed</th>
            <th>Delete</th>
            <th>Update</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${todos}" var="todo">
            <tr>
              <td>${todo.id}</td>
              <td>${todo.description}</td>
              <td>${todo.targetDate}</td>
              <td>${todo.done}</td>
              <td>
                <a href="delete-todo?id=${todo.id}" class="btn btn-danger"
                  >Delete</a
                >
              </td>
              <td>
                <a href="update-todo?id=${todo.id}" class="btn btn-warning"
                  >Update</a
                >
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>

      <a href="add-todo" class="btn btn-primary">Add Todo</a>
    </div>

    <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js" />
    <script src="webjars/jquery/3.6.0/jquery.min.js" />
  </body>
</html>
