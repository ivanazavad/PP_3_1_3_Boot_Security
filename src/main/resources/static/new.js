const formNewUser = document.getElementById('addUser')
let newRole = document.querySelector('#role').selectedOptions
const tableAdmin = document.getElementById('tableAdmin')

formNewUser.addEventListener('submit', newUser => {
    newUser.preventDefault()
    let roles = []
    for (let i = 0; i < newRole.length; i++) {
        roles.push({
            id: newRole[i].value
        })
    }
    let method = {
        method: 'POST',
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            username:formNewUser.username.value,
            age:formNewUser.age.value,
            password:formNewUser.password.value,
            role:roles
        })
    }
    fetch("http://localhost:8080/admin/new",method).then(() => {
        formNewUser.reset();
        adminPage();
        tableAdmin.click();
    })
})