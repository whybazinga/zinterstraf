/**
 * Common Jquery functions for project
 */

const types = {
	fun: 'function',
	str: 'string',
	num: 'number'
};


function ajax(url, method, datatype, ajaxdata, func) {
	if(isElementFunc(func)) {
		let request = $.ajax({
			url: url, 
			method: method,
			ContentType: datatype,
			data: ajaxdata,
		});
		request.done(data => { func(data); });
	    request.fail(jqXHR, textStatus, errorThrown => { func("Problem with connection on the client side."); });
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

function getElementValue(element) {
	return ($(element).val()).trim();
}

function setElementValue(element, value) {
	$(element).val(value);
}

function setTagText(element, value) {
	$(element).text(value);
}

function isEmptyElement(element) {
	return element.is(':empty');
}

