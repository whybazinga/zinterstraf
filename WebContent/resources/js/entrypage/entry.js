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


//btn-submit-action
function buttonManager(e) {
	let elementVal = $(e.target).val();
	
	switch(elementVal) {
		case 'login':
			clickButtonModal(LOGIN);
			break;
		case 'register':
			clickButtonModal(REGISTER);
			break;
		case REGISTER.buttonSubmit:
			
			
			break;
		case LOGIN.buttonSubmit:
			
			
			break;
		default:
			console.log("No such button")
	}
}

function isReadyAjaxRegistration(e, passConf) {
	let emailVal = isExistsString(getElementValueByName('email')) ? getElementValueByName('email') : '';
	let passVal = isExistsString(getElementValueByName('pass')) ? getElementValueByName('pass') : '';
	let passConfirmVal = isExistsString(getElementValueByName('passconfirm')) ? getElementValueByName('passconfirm') : '';
	const url = basicUrl + "login/";
	const method = 'POST';
	const contenttype = 'application/x-www-form-urlencoded; charset=UTF-8';
	const ajaxdata = {
		email: emailVal,
		pass: passVal
	};
	if(passConfirmVal) {
		ajaxdata.passConfirm = passConfirmVal;
	}
	
	ajax(url, method, contenttype, ajaxdata, clickButtonSubmitModal);
	
	
}


function clickButtonSubmitModal(data) {
	
	getElementById('resultModalLabel').text(data.respHeader);
	getElementByClass('resultModalBody').text(data.respBody);
	
	if(data.responseAnswer === 'true') {
		
	}
	
}



function clickButtonModal(resources) {
	let checkPassElement = getElementByClass('appendedRegister');
	
	getElementById('exampleModalLabel').text(resources.header);
	getElementByClass('btn-submit-action').text(resources.button);
	getElementByClass('btn-submit-action').val(resources.buttonSubmit);
	
	if(resources.button === LOGIN.button) {
		checkPassElement.empty();
	} else {
		if (isEmptyElement(checkPassElement)) {
			checkPassElement.append(
				$('<div>').attr('class', 'input-group').append(
					$('<span>').attr('class', 'input-group-addon').append('&nbsp;*&nbsp;</span>'),
					$('<input>').attr('type', 'password').attr('class', 'form-control').attr('placeholder', 'Repeat password').attr('name', 'passconfirm')
				), 
				$('<small>').attr('class', 'form-text text-muted').append('This password should be idential to the given above.')	
			);
		}
	}	
};


$(document).ready(function() {
	$('.scrolltop').click(function(){
		$("html, body").animate({ scrollTop: 0 }, 600);
		return false;
	});
	
	addEventHandler('.btn-login-open', 'click', buttonManager, '.header');
	addEventHandler('.btn-register-open', 'click', buttonManager, '.header');
	
});





