import * as types from '../constants/actionTypes';

export const addUser = match => ({ type: types.ADD_USER, payload: match });