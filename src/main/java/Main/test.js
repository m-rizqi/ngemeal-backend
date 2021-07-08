var gojek = require('gojek');
gojek.loginWithPhone('+6281341295423', function (error, response, body) {
    console.log(body);
});