import {userRoleTypes} from "../constants/userRoleTypes";
import {appGlobal} from "../constants/appGlobal";
import {loginConst} from "../constants/loginConst";


export const checkUserUrlRole = (urlRole, authUser) => {
  const userRoleKey = 'roles';
  if(typeof urlRole !== 'string' || typeof authUser !== 'object') throw new TypeError("urlRole -> string, authUser -> object");
  return urlRole === userRoleTypes.noRole || (authUser.hasOwnProperty(userRoleKey) && authUser[userRoleKey].includes(urlRole) && appGlobal.func.getCookie(loginConst.signInResponse.accessToken))
};

export const isUserAuthorized = (authUser) => {
  if(typeof authUser !== 'object') throw new TypeError("authUser -> object");
  return appGlobal.func.getCookie(loginConst.signInResponse.accessToken) && !appGlobal.func.checkIfEmptyJson(authUser)
};

export const isUserUnauthWithCookie = (authUser) => {
  if(typeof authUser !== 'object') throw new TypeError("authUser -> object");
  return appGlobal.func.getCookie(loginConst.signInResponse.accessToken) && appGlobal.func.checkIfEmptyJson(authUser)
};