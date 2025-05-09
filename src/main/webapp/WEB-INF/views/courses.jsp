<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html><body>
<h2>Available Courses</h2>
<form method="post" action="">
<table border="1">
<tr><th>ID</th><th>Name</th><th>Instructor</th><th>Credits</th><th>Action</th></tr>
<c:forEach var="course" items="${courses}">
    <tr>
        <td>${course.course_id}</td>
        <td>${course.name}</td>
        <td>${course.instructor}</td>
        <td>${course.credits}</td>
        <td>
            <form action="register/${course.course_id}" method="post">
                <input type="submit" value="Register"/>
            </form>
        </td>
    </tr>
</c:forEach>
</table>
</body></html>
