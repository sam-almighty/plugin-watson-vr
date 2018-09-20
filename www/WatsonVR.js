var exec = require('cordova/exec');

var WatsonVR = {
    classify: function(versionDate, apiKey, file, success, failure) {
        cordova.exec(success, failure, "WatsonVR", "classify", [versionDate, apiKey, file]);
    },
    classifyImageFromUrl: function(versionDate, apiKey, success, failure) {
        cordova.exec(success, failure, "WatsonVR", "classifyImageFromUrl", [versionDate, apiKey]);
    }
}

module.exports = WatsonVR;