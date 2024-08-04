const form = document.querySelector("form");
const inputName = document.querySelector(".name")
const inputEmail = document.querySelector(".email")
const inputCellphone = document.querySelector(".cellphone")
const inputPassword = document.querySelector(".password")

function register () {

    fetch("http://localhost:8080/register",
        {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({
                name: inputName.value,
                email: inputEmail.value,
                cellphone: inputCellphone.value,
                password: inputPassword.value
            })
        })
        .then(function (res) { console.log(res) })
        .catch(function (res) { console.log(res) })
};

function cleanDataJsonRegister() {
    inputName.value = "";
    inputEmail.value = "";
    inputCellphone.value = "";
    inputPassword.value = "";
};

form.addEventListener('submit', function (event){
    event.preventDefault();
    console.log(register)
    register();
    cleanDataJsonRegister();
}); 