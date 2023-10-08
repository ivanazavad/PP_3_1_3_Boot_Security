const formEditUser = document.getElementById('formEdit')
let editRole = document.querySelector('#roleEdit').selectedOptions
const editModalClose = document.getElementById('editModalClose')

function editModal(id) {
    fetch("http://localhost:8080/admin/" + id).then(response => response.json())
        .then(editUser => {
            formEditUser.idEdit.value = editUser.id
            formEditUser.usernameEdit.value = editUser.username
            formEditUser.ageEdit.value = editUser.age
            formEditUser.passwordEdit.value = editUser.password

        })
}

formEditUser.addEventListener('submit', editUser => {
    editUser.preventDefault()
    let roles = []
    for (let i = 0; i < editRole.length; i++) {
        roles.push({
            id: editRole[i].value
        })
    }
    let method = {
        method: 'PATCH',
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            id: formEditUser.idEdit.value,
            username:formEditUser.usernameEdit.value,
            age:formEditUser.ageEdit.value,
            password:formEditUser.passwordEdit.value,
            role:roles
        })
    }
    fetch("http://localhost:8080/admin/"+ formEditUser.idEdit.value,method).then(() => {
        adminPage();
        editModalClose.click();
    })
})
