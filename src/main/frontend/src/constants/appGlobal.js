export const appGlobal = (() => {
  return Object.freeze({
      methods: {
        POST: 'post',
        GET: 'get'
      },
      error: {
        connectionError: 'Connection error has occurred'
      },
      func: {
        getFullUrlByPath: (path) => (window.location.protocol + '//' + window.location.host + path),
        getFormEncodedParams: (json) => ( Object.keys(json).map(key => encodeURIComponent(key) + '=' + encodeURIComponent(json[key])).join('&') ),
        setCookie: _setCookie,
        getCookie: _getCookie,
        deleteCookie: _deleteCookie,
        getAuthHeaderByCred: (clientId, clientSecret) => ({
          'Authorization': 'Basic ' + btoa(clientId + ":" + clientSecret),
          'Content-type': "application/x-www-form-urlencoded; charset=utf-8"
        }),
        callFuncIfParamExists: (param, func, ...args) => { if(param && typeof func === 'function') { func(args) } }
      },
    isDebug: true
  });

  function _setCookie(cName,cValue,cExp) {
    const expires = new Date();
    expires.setTime(expires.getTime() + cExp);
    document.cookie = cName + "=" + cValue + ";expires=" + expires.toUTCString() + ";path=/";
  }

  function _getCookie(cname) {
    const name = cname + "=";
    let cookieArray = decodeURIComponent(document.cookie).split(';');
    for(let i = 0; i < cookieArray.length; i++) {
      let cookie = cookieArray[i];
      while (cookie.charAt(0) === ' ') {
        cookie = cookie.substring(1);
      }
      if (cookie.indexOf(name) === 0) {
        return cookie.substring(name.length, cookie.length);
      }
    }
    return "";
  }

  function _deleteCookie(name) {
    document.cookie = name +'=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 UTC;';
  }

})();

export const debugLogVar = (variable) => {
  appGlobal.isDebug && console.log(variable);
};