import { combineReducers } from 'redux'
import match from './match';
import user from './user';

export const createReducer = (() => (
  combineReducers({
    match,
    user
  })
))();