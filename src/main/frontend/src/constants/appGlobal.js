export const appGlobal = (() => {
  return Object.freeze({
    auth: {
      signInUrl: '/oauth/token',
      tokenFlows: {
        passwordFlow: {
          grant_type: 'password',
          clientId: 'clientIdPassword',
          clientSecret: 'secret'
        },
        clientCredFlow: {
          grant_type: 'client_credentials',
        },
      },
      signInResponse: {
        accessToken: 'access_token',
        refreshToken: 'refresh_token',
        expires: 'expires_in',
        error: 'error',
        errorDescription: 'error_description',
        errorDescriptionDefaultVal: 'Unknown error, please, contact the support team'
      },
      func: {
        getAuthHeaderByCred: (clientId, clientSecret) => ({
          'Authorization': 'Basic ' + btoa(clientId + ":" + clientSecret),
          'Content-type': "application/x-www-form-urlencoded; charset=utf-8"
        })
      }
    },
    common: {
      methods: {
        POST: 'post',
        GET: 'get'
      },

      func: {
        getFullUrlByPath: (path) => (window.location.protocol + '//' + window.location.host + path),
        getFormEncodedParams: (json) => ( Object.keys(json).map(key => encodeURIComponent(key) + '=' + encodeURIComponent(json[key])).join('&') ),
        setCookie: setCookieFunc,
        getCookie: getCookieFunc,
        callFuncIfParamExists: (param, func, ...args) => { if(param && typeof func === 'function') { func(args) } }
      }
    },
    isDebug: true
  });

  function setCookieFunc(cName,cValue,cExp) {
    const expires = new Date();
    expires.setTime(expires.getTime() + cExp);
    document.cookie = cName + "=" + cValue + ";expires=" + expires.toUTCString() + ";path=/";
  }

  function getCookieFunc(cname) {
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



})();