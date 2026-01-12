function handleLogin(event, formElement) {
    "use strict";
    event.preventDefault();

    var $form = $(formElement);
    var username = $form.find("#username").val();
    var password = $form.find("#password").val();
    var $messageEl = $("#loginMessage");

    $messageEl.text("").css("color", "");

    if (!username || !password) {
        $messageEl
            .css("color", "red")
            .text("Username and Password are required");
        return false;
    }

    $.ajax({
        url: "/bin/loginForm.json",
        type: "POST",
        dataType: "json",
        data: {
            username: username,
            password: password
        },
        success: function (response) {
            $messageEl
                .css("color", "green")
                .text("Login successful");
        },
        error: function (serverResponse) {
            var message =
                (serverResponse.responseJSON && serverResponse.responseJSON.message)
                    ? serverResponse.responseJSON.message
                    : "Invalid credentials";
            $messageEl
                .css("color", "red")
                .text(message);
        }
    });
    return false;
}
