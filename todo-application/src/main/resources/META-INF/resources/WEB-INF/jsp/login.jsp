<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      href="webjars/bootstrap/5.1.3/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <title>Login</title>
  </head>
  <body>
    <div class="container">
      <!-- Welcome Line -->
      <div>Welcome to the login page!</div>

      <!-- Errors Message -->
      <pre>${error}</pre>

      <!-- Login Form -->
      <div>
        <form method="post">
          User Name: <input type="text" name="userName" /> Password:
          <input type="password" name="password" />
          <input type="submit" />
        </form>
      </div>
    </div>
  </body>
</html>
