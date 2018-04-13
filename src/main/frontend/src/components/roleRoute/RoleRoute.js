import React from 'react'
import {Route, Redirect} from 'react-router-dom'
import PropTypes from 'prop-types'

import {routerUrls} from "../../AppRouter";
import {checkIfUserHasRole} from "../../utils/authUtils";


export const RoleRoute = ({component: Component, urlRole, authUser,...rest}) => (
  <Route {...rest} render={(props) => (
    checkIfUserHasRole(urlRole, authUser)
      ? <Component {...props} authUser={authUser}/>
      : <Redirect to={routerUrls.login.url} />
  )}/>
);

RoleRoute.propTypes = {
  component: PropTypes.func.isRequired,
  urlRole: PropTypes.string,
  authUser: PropTypes.object
};
