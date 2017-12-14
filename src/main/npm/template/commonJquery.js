/**
 * Common Jquery functions for the project
 */

const types = {
	fun: 'function',
	str: 'string',
	num: 'number'
};

module.exports = types;

module.exports = function ajax(url, method, datatype, ajaxdata, func) {
	if(isElementFunc(func)) {
		let request = $.ajax({
			url: url, 
			method: method,
			ContentType: datatype,
			data: ajaxdata,
		});
		request.done(data => func(data));
	    request.fail(textStatus => func("Problem with connection on the client side."));
	}
}


module.exports = function isElementFunc(func) {
	if(typeof func === types.fun) {
		return true;
	} else {
		console.log("Third argument isn't a function");
		return false
	}
}

module.exports = function getElementValue(element) {
	return ($(element).val()).trim();
}

module.exports = function setElementValue(element, value) {
	$(element).val(value);
}

module.exports = function setTagText(element, value) {
	$(element).text(value);
}

module.exports = function isEmptyElement(element) {
	return element.is(':empty');
}

