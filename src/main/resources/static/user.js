const table = document.getElementById('tableBody')
const spann = document.getElementById('span')

fetch("http://localhost:8080/user/user_page")
    .then((response) => {
        return response.json();
    })
    .then((user) => {
        let tr = `<tr><td>${user.id}</td>
                  <td>${user.username}</td>
                  <td>${user.age}</td>
                  <td>${user.role[0].role}</td></tr>`
        table.innerHTML = tr

        let span = `<h5><b>${user.username}</b> with roles: ${user.role[0].role}</h5>`
        spann.innerHTML = span

        let  roles = `${user.role[0].role}`
        console.log(roles)
        if (roles.indexOf("ADMIN") === -1) {
            document.getElementById('admin').style.display = "none";
        }
    });