require('bootstrap/dist/js/bootstrap.min');
require('bootstrap/dist/css/bootstrap.min.css');
//require('commonProject');

//require('bootstrap/dist/js/bootstrap.min');
//require('../template/commonJquery');

// need smth
const REGISTER = {
    header: 'Register in the system',
    button: 'Register',
    buttonSubmit: 'regSubmit'
};

const LOGIN = {
    header: 'Login to the system',
    button: 'Login',
    buttonSubmit: 'loginSubmit'
};

const AJAX = {
    postMethod: 'POST',
    getMethod: 'GET',
    contentJson: 'application/json; charset=UTF-8'
};

const ENTRY_ELEMENTS = {
    inputEmail: '[name="email"]',
    inputPass: '[name="pass"]',
    inputPassConf: '[name="passconfirm"]',
    resultModalBody: '.resultModalBody',
    mainModalLabel: '#exampleModalLabel',
    mainModalBtn: '.btn-submit-action',
    headerLoginBtn: '.btn-login-open',
    headerRegistBtn: '.btn-register-open',
    headerScrollBtn: '.scrolltop',
    divPassConf: '.appendedRegister',
    resultModalCloseBtn: '.responseModalClose'
};


function buttonManager(e) {
    let elementVal = commonProject.getElementValue(e.target);

    switch(elementVal) {
        case LOGIN.button.toLowerCase():
            clickButtonModal(LOGIN);
            break;
        case REGISTER.button.toLowerCase():
            clickButtonModal(REGISTER);
            break;
        case REGISTER.buttonSubmit:
            ajaxRegister(e);
            break;
        case LOGIN.buttonSubmit:
            ajaxLogin(e);
            break;
        case 'close':
            commonProject.setTagText(ENTRY_ELEMENTS.resultModalBody, "Wait a little bit...");
            break;
        default:
            console.log("No such button");
            break;
    }
}

function ajaxLogin(e) {
    let emailVal = commonProject.getElementValue(ENTRY_ELEMENTS.inputEmail);
    let passVal = commonProject.getElementValue(ENTRY_ELEMENTS.inputPass);
    passVal = '$2b$10$OYfHj.JjcH/o4oO3so.NweA/5Pleyuu0vnFlowo1O4KLO8mWuXbma'
    let clientId = 'clientIdPassword';
    let clientSecret = 'secret';

    if(emailVal && passVal) {
        let url = basicUrl + "/oauth/token";
        let ajaxdata = {
            grant_type: "password",
            username: emailVal,
            password: passVal,
            client_id: clientId,
            client_secret: clientSecret
        };
        $.ajax({
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
                "Authorization": "Basic " + btoa(clientId + ":" + clientSecret)
            },
            method: "POST",
            url: url,
            data: ajaxdata

        }).done(function(data) {
            alert(data);
        }).fail(function(data) {
            alert(data);
        });
        //commonProject.ajax(url, AJAX.postMethod, AJAX.contentJson, ajaxdata, ajaxResponse);
    }
    commonProject.setElementValue(ENTRY_ELEMENTS.inputEmail, '');
    commonProject.setElementValue(ENTRY_ELEMENTS.inputPass, '');
}

function ajaxRegister(e) {
    let emailVal = commonProject.getElementValue(ENTRY_ELEMENTS.inputEmail);
    let passVal = commonProject.getElementValue(ENTRY_ELEMENTS.inputPass);
    let passConfVal = commonProject.getElementValue(ENTRY_ELEMENTS.inputPassConf);

    if(emailVal && passVal && passConfVal === passVal) {
        let url = basicUrl + "/register-ajax";
        let ajaxdata = {
            email: emailVal,
            pass: passVal
        };
        commonProject.ajax(url, AJAX.postMethod, AJAX.contentJson, ajaxdata, ajaxResponse);
    } else {
        commonProject.setTagText(ENTRY_ELEMENTS.resultModalBody, "Invalid data for registration");
    }
    commonProject.setElementValue(ENTRY_ELEMENTS.inputEmail, '');
    commonProject.setElementValue(ENTRY_ELEMENTS.inputPass, '');
    commonProject.setElementValue(ENTRY_ELEMENTS.inputPassConf, '');
}


function ajaxResponse(data) {
    if(data.response) {
        commonProject.setTagText(ENTRY_ELEMENTS.resultModalBody, data.response);
        if((data.response).indexOf("logged in") >= 0) {
            window.location.href = basicUrl + "/welcome";
        }
    } else {
        commonProject.setTagText(ENTRY_ELEMENTS.resultModalBody, data);
    }
}



function clickButtonModal(resources) {
    let checkPassElement = $(ENTRY_ELEMENTS.divPassConf);

    commonProject.setTagText(ENTRY_ELEMENTS.mainModalLabel, resources.header);
    commonProject.setTagText(ENTRY_ELEMENTS.mainModalBtn, resources.button);
    commonProject.setElementValue(ENTRY_ELEMENTS.mainModalBtn, resources.buttonSubmit);

    checkPassElement.hide();
    if(resources.button === REGISTER.button) {
        checkPassElement.show();
    }
}

$(document).ready(function() {
    commonProject.testFunction();
    $(ENTRY_ELEMENTS.headerScrollBtn).click(() => {
        $("html, body").animate({ scrollTop: 0 }, 600);
        return false;
    });
    $(ENTRY_ELEMENTS.headerLoginBtn).on('click', buttonManager);
    $(ENTRY_ELEMENTS.headerRegistBtn).on('click', buttonManager);
    $(ENTRY_ELEMENTS.mainModalBtn).on('click', buttonManager);
    $(ENTRY_ELEMENTS.resultModalCloseBtn).on('click', buttonManager);
});


