(function (document, $, Coral) {
    "use strict";
    $(document).on("foundation-contentloaded", function () {
        //console.log("Email validator: foundation-contentloaded event fired");
        var registry = $(window).adaptTo("foundation-registry");
        if (!registry) {
            //console.error("Email validator: Could not get foundation-registry");
            return;
        }
        //console.log("Email validator: Registering custom email validator");
        registry.register("foundation.validation.validator", {
            selector: "[data-validation='email-custom'],[data-validation=email-custom]",
            validate: function (element) {
                var value = element.value;
                //console.log("Email validator: Validating value:", value);
                if (!value) {
                    return;
                }
                var emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
                if (!emailRegex.test(value)) {
                    //console.log("Email validator: Invalid email");
                    return "Please enter a valid email address";
                }
                //console.log("Email validator: Valid email");
            }
        });
        //console.log("Email validator: Registration complete");
    });

})(document, Granite.$, Coral);
