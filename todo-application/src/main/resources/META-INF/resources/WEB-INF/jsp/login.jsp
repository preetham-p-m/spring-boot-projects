<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login</title>
  </head>
  <body>
    <!-- Welcome Line -->
    <div>Welcome to the login page!</div>

    <!-- Login Form -->
    <div>
      <form method="post">
        User Name: <input type="text" name="userName" /> Password:
        <input type="password" name="password" />
        <input type="submit" />
      </form>
    </div>

    <!-- Errors Message -->
    <div>${error}</div>
  </body>
</html>
