import {userRoleTypes} from "../constants/userRoleTypes";


export const checkIfUserHasRole = (urlRole, authUser) => {
  const userRoleKey = 'roles';
  if(typeof urlRole !== 'string' || typeof authUser !== 'object') throw new TypeError("urlRole -> string, authUser -> object");
  return urlRole === userRoleTypes.noRole || (authUser.hasOwnProperty(userRoleKey) && authUser[userRoleKey].includes(urlRole))
};