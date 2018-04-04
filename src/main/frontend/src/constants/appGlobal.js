import {loginConst} from "./loginConst";

export const appGlobal = (() => {
  return Object.freeze({
    methods: {
      POST: 'post',
      GET: 'get'
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
      getCurrentUTCPlusHours: (time=1) => {
        const date = new Date();
        date.setTime(date.getTime() + (time * 60 * 60 * 1000));
        return date.toUTCString()
      },
      callFuncIfParamExists: (param, func, ...args) => { if(param && typeof func === 'function') { func(args) } }
    },
    isDebug: true
  });

  function _setCookie(cName,cValue,cExp) {
    debugLogVar(cName + (cValue || ' is empty'));
    if(cName && cValue && cExp) {
      const expires = new Date();
      expires.setTime(expires.getTime() + cExp);
      document.cookie = cName + "=" + cValue + ";expires=" + expires.toUTCString() + ";path=/";
    }
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

export const fetchPostJsonResponse = (url, json, error="Connection error has occurred") => (
  fetch(appGlobal.func.getFullUrlByPath(url), {
    method: appGlobal.methods.POST,
    headers: appGlobal.func.getAuthHeaderByCred(
      loginConst.tokenFlows.passwordFlow.clientId,
      loginConst.tokenFlows.passwordFlow.clientSecret
    ),
    body: appGlobal.func.getFormEncodedParams(json)
  }).then((response) => {
    const contentType = response.headers.get("content-type");
    if(contentType && contentType.includes("application/json")) {
      return response.json();
    }
    throw new TypeError(error);
  })
);

