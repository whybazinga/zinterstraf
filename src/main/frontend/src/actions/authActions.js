import {AUTH_CONST} from '../constants/actionTypes';

export const authenticate = authUser => ({ type: AUTH_CONST.AUTHENTICATE, payload: authUser });

export const logout = () => ({ type: AUTH_CONST.LOGOUT});