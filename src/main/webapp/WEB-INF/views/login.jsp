<html><body>
<h2>Login</h2>
<form action="login" method="post">
    Email: <input name="email" /><br/>
    Password: <input type="password" name="password" /><br/>
    <input type="submit" value="Login"/>
</form>
<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>
</body></html>
