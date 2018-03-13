export const appGlobal = (() => {
  return Object.freeze({
    auth: {
      signInUrl: '/oauth/token',
      grantType: 'password',
      getAuthHeader: {
        'Authorization': 'Basic '+btoa("clientIdPassword:secret"),
        'Content-type': "application/x-www-form-urlencoded; charset=utf-8"
      },
      oauthSignIn: {
        accessToken: 'access_token',
        refreshToken: 'refresh_token',
        expires: 'expires_in'
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
        getCookie: getCookieFunc
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