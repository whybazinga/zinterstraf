export const signingConst = Object.freeze({
  signInUrl: '/oauth/token',
  signUpUrl: '/user/register',
  getAuthUserUrl: '/user/get-current-user',
  tokenFlows: {
    passwordFlow: {
      grantType: 'password',
      clientId: 'clientIdPassword',
      clientSecret: 'secret'
    },
    clientCredFlow: {
      grantType: 'client_credentials',
    },
  },
  signInResponse: {
    accessToken: 'access_token',
    refreshToken: 'refresh_token',
    expires: 'expires_in',
    error: 'error',
    errorDescription: 'error_description',
    errorDescriptionDefaultVal: 'Something went wrong during login'
  },
  authBenefits: {
    en: ['Fantasy EGA League game',
      'Exclusive fan services',
      'Customised site content',
      'Favourite Club information and notifications'
    ]
  }
});