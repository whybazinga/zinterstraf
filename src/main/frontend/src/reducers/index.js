import { combineReducers } from 'redux'
import match from './match';
import user from './user';
import auth from './auth';

export const createReducer = (() => (
  combineReducers({
    match,
    user,
    auth
  })
))();