fetch("http://localhost:8080/api/user")
    .then(response => response.json())
    .then(u => {
        let temp = "";

        temp += "<tr>";
        temp += "<td>" + u.id + "</td>";
        temp += "<td>" + u.userName + "</td>";
        temp += "<td>" + u.firstName + "</td>";
        temp += "<td>" + u.lastName + "</td>";
        temp += "<td>" + u.age + "</td>";
        temp += "<td>" + u.password + "</td>";
        temp += "<td>" + u.rolesToString + "</td></tr>";

        document.getElementById("userInfo").innerHTML = temp;

    })