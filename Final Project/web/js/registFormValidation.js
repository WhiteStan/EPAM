/**
 * Created by Lenovo on 23.04.2016.
 */
var result;
function validatePassword(lang){
    var password = document.getElementById("password")
        , confirm_password = document.getElementById("password_confirm"),
        result;
    var message;
    if(lang == "en_US") {
        message = "Passwords Don't Match";
    }
    else{
        message = "Пароли не совпадают";
    }
    if(password.value != confirm_password.value) {
        confirm_password.setCustomValidity(message);
        result = false;
    } else {
        confirm_password.setCustomValidity('');
        result = true;
    }
}
