// Show Password
$(document).ready(function () {
    if (localStorage.getItem('username') != null) {
        $('#username').val(localStorage.getItem('username'))
    }
    if (localStorage.getItem('password') != null) {
        $('#password').val(localStorage.getItem('password'))
    }
    document.getElementById("rememberMe").checked = localStorage.getItem('isRememberMe') === 'true';
    localStorage.getItem('isRememberMe') ? console.log('1') : console.log('0');

    function showPassword(button) {
        let inputPassword = $(button).parent().find('input');
        if (inputPassword.attr('type') === "password") {
            inputPassword.attr('type', 'text');
            $(".show-password i").removeClass("far fa-eye");
            $(".show-password i").addClass("far fa-eye-slash");
        } else {
            inputPassword.attr('type', 'password');
            $(".show-password i").removeClass("far fa-eye-slash");
            $(".show-password i").addClass("far fa-eye");
        }
    }

    function rememberAccount() {
        let btnRemember = document.getElementById("rememberMe");
        let username = document.getElementById("username").value;
        let password = document.getElementById("password").value;

        if (btnRemember.checked) {
            localStorage.setItem('username', username);
            localStorage.setItem('password', password);
            localStorage.setItem("isRememberMe", 'true')
        } else {
            localStorage.setItem('username', '');
            localStorage.setItem('password', '');
            localStorage.setItem("isRememberMe", 'false')
        }
    }

    $('#rememberMe, #signin').on('click', function () {
        rememberAccount();
    })
    $('.show-password').on('click', function () {
        showPassword(this);
    })
});