<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css" />
    
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
</head>
<body style="margin-top: 50px;">
    <div class="container container-fluid">
    	<h3 style="text-align: center;">SCHOOL PROJECT</h3>
    	<div id="div_login" style="width: 600px; margin: auto;"><hr>
    		<br>
	        <p>Log in here:</p><br>
	        <form class="form" method="POST" action="/SchoolProject/Authenticate">
	            <input placeholder="username" class="form-control" type="text" name="username" id="username" required /><br>
	            <input placeholder="password" class="form-control" type="password" name="password" id="password" required /><br>
	            <button type="submit" id="btn_login" class="btn btn-primary btn-block">Log in</button>
	        </form>
       </div>
    </div>
    
</body>
</html>