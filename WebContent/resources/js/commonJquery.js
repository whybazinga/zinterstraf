/**
 * Common Jquery functions for project
 */

const types = {
	fun: 'function',
	str: 'string',
	num: 'number'
};


function addEventHandler(element, event, func, parent_elem) {
	if(isElementFunc(func)) {
		if(parent_elem && !(typeof element === types.str)) {
			$(parent_elem).on(event, element, func);
		} else {
			$(element).on(event, func);
		}
	}
}



function ajax(url, method, datatype, ajaxdata, func) {
	if(isElementFunc(func)) {
		let request = $.ajax({
			url: url, 
			method: method,
			ContentType: datatype,
			data: ajaxdata
		});
		request.done(data => { func(data); });
	    request.fail(function (jqXHR, textStatus, errorThrown) { func(textStatus); });
	}
}


function isElementFunc(func) {
	if(typeof func === types.fun) {
		return true;
	} else {
		console.log("Third argument isn't a function");
		return false
	}
}


function getElementById(id) {
	return $('#' + id);
}

function getElementByClass(className) {
	return $('.' + className);
}

function getElementByName(name) {
	return $('[name="' + name + '"]');
}

function getElementValueByName(name) {
	let elementVal = getElementByName(name) ? getElementByName(name).val() : '';
	return elementVal;
}

function isExistsString(str) {
	return (str.trim()).length;
}

function isEmptyElement(element) {
	return element.is(':empty');
}

