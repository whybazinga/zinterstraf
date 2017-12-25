/**
 * Common Jquery functions for the project
 */

let commonProject = function () {
	let me = {
		getJsTypes: jsTypes,
		isEmptyElement: isEmptyElement,
		setTagText: (element, value) => $(element).text(value),
        setElementValue: (element, value) => $(element).val(value),
		getElementValue: (element) => ($(element).val()).trim(),
        isElementFunc: isElementFunc,
		callAjax: ajax,
        testFunction: () => console.log("test successfull!")
	};

	const jsTypes = {
        fun: 'function',
        str: 'string',
        num: 'number'
	}

    function isElementFunc(func) {
        if(typeof func === types.fun) {
            return true;
        } else {
            console.log("Third argument isn't a function");
            return false
        }
    }

    function ajax(url, method, datatype, ajaxdata, func) {
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

    function isEmptyElement(element) {
        return element.is(':empty')
    }

    return me;
}

module.exports = commonProject;