export const signingConst = Object.freeze({
    signInUrl: '/oauth/token',
    signUpUrl: '/user/register',
    getAuthUserUrl: '/user/get-current-user',
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
      errorDescriptionDefaultVal: 'Something went wrong during login'
    }
});