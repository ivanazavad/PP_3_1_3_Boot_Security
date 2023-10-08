const formDeleteUser = document.getElementById('deleteForm')
const deleteModalClose = document.getElementById('deleteModalClose')


function deleteModal(id) {
    fetch("http://localhost:8080/admin/" + id).then(response => response.json())
        .then(deleteUser => {
            formDeleteUser.idDelete.value = deleteUser.id
            formDeleteUser.usernameDelete.value = deleteUser.username
            formDeleteUser.ageDelete.value = deleteUser.age
            formDeleteUser.passwordDelete.value = deleteUser.password

        })
}



formDeleteUser.addEventListener('submit', deleteUser => {
    deleteUser.preventDefault()
    let method = {
        method: 'DELETE'
    }

    fetch("http://localhost:8080/admin/"+ formDeleteUser.idDelete.value,method).then(() => {
        adminPage();
        deleteModalClose.click();
    })
})