import React, {Component} from 'react';
import {Route, Redirect, Switch} from 'react-router-dom'

import SwaggerUiPage from "./containers/swagger/Swagger";
import EmptyPage from "./containers/empty/Empty";
import LoginPage from "./containers/login/Login";
import RegisterPage from "./containers/register/Register";
import {appGlobal} from "./constants/appGlobal";
import StartPage from "./containers/start/Start";


export const AppRouter = ({authUser}) => {
  return(
    <main className="content">
      <Switch>
        <Route exact path='/' component={StartPage} />
        <Route path="/login" component={LoginPage} />
        <Route path="/swagger-ui" component={SwaggerUiPage} />
        <Route path="/register" component={RegisterPage} />
        <PrivateRoute path='/protected' component={StartPage} authUser={authUser} />
        <Route component={EmptyPage} />
      </Switch>
    </main>
  )
};

const PrivateRoute = ({component: Component, ...rest}) => (
  <Route {...rest} render={(props) => (
    !appGlobal.func.checkIfEmptyJson(props.authUser)
      ? <Component {...props} />
      : <Redirect to='/login'/>
  )}/>
);

