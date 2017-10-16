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
	let elementVal = getElementValue(e.target);
	
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
			setTagText(ENTRY_ELEMENTS.resultModalBody, "Wait a little bit...");
			break;
		default:
			console.log("No such button");
			break;
	}
}

function ajaxLogin(e) {
	let emailVal = getElementValue(ENTRY_ELEMENTS.inputEmail);
	let passVal = getElementValue(ENTRY_ELEMENTS.inputPass);
	
	if(emailVal && passVal) {
		let url = basicUrl + "/login-ajax";
		let ajaxdata = {
			email: emailVal,
			pass: passVal
		};
		ajax(url, AJAX.postMethod, AJAX.contentJson, ajaxdata, ajaxResponse);
	}
	setElementValue(ENTRY_ELEMENTS.inputEmail, '');
	setElementValue(ENTRY_ELEMENTS.inputPass, '');
}

function ajaxRegister(e) {
	let emailVal = getElementValue(ENTRY_ELEMENTS.inputEmail);
	let passVal = getElementValue(ENTRY_ELEMENTS.inputPass);
	let passConfVal = getElementValue(ENTRY_ELEMENTS.inputPassConf);
	
	if(emailVal && passVal && passConfVal === passVal) {
		let url = basicUrl + "/register-ajax";
		let ajaxdata = {
			email: emailVal,
			pass: passVal
		};
		ajax(url, AJAX.postMethod, AJAX.contentJson, ajaxdata, ajaxResponse);
	} else {
		setTagText(ENTRY_ELEMENTS.resultModalBody, "Invalid data for registration");
	}
	setElementValue(ENTRY_ELEMENTS.inputEmail, '');
	setElementValue(ENTRY_ELEMENTS.inputPass, '');
	setElementValue(ENTRY_ELEMENTS.inputPassConf, '');
}


function ajaxResponse(data) {
	if(data.response) {
		setTagText(ENTRY_ELEMENTS.resultModalBody, data.response);
		if((data.response).indexOf("logged in") >= 0) {
			window.location.href = basicUrl + "/welcome";
		}
	} else {
		setTagText(ENTRY_ELEMENTS.resultModalBody, data);
	}
}



function clickButtonModal(resources) {
	let checkPassElement = $(ENTRY_ELEMENTS.divPassConf);
	
	setTagText(ENTRY_ELEMENTS.mainModalLabel, resources.header);
	setTagText(ENTRY_ELEMENTS.mainModalBtn, resources.button);
	setElementValue(ENTRY_ELEMENTS.mainModalBtn, resources.buttonSubmit);
	
	checkPassElement.hide();
	if(resources.button === REGISTER.button) {
		checkPassElement.show();
	}
};


$(document).ready(function() {
	$(ENTRY_ELEMENTS.headerScrollBtn).click(() => {
		$("html, body").animate({ scrollTop: 0 }, 600);
		return false;
	});
	$(ENTRY_ELEMENTS.headerLoginBtn).on('click', buttonManager);
	$(ENTRY_ELEMENTS.headerRegistBtn).on('click', buttonManager);
	$(ENTRY_ELEMENTS.mainModalBtn).on('click', buttonManager);
	$(ENTRY_ELEMENTS.resultModalCloseBtn).on('click', buttonManager);
});





