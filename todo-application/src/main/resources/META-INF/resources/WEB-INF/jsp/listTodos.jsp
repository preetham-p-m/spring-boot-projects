<%@ include file="common/header.jspf" %> <%@ include file="common/navbar.jspf"
%>

<div class="container">
  <h1>Welcome to Todos Application ${name}</h1>
  <hr />
  <h2>Your Todos are:</h2>
  <table class="table">
    <thead>
      <tr>
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

<%@ include file="common/footer.jspf"%>
