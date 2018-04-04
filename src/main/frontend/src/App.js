import React, {Component} from 'react';
import {Route, Redirect, withRouter, Switch} from 'react-router-dom'
import {connect} from "react-redux";

import './App.css';
import Header from './containers/header/Header';
import Footer from './containers/footer/Footer';
import StartPage from './containers/start/Start';
import LoginPage from './containers/login/Login';
import EmptyPage from './containers/empty/Empty'
import SwaggerUiPage from './containers/swagger/Swagger';
import {appGlobal, debugLogVar} from "./constants/appGlobal";
import {loginConst} from "./constants/loginConst";
import {authenticate} from "./actions/authActions";


class App extends Component {

  componentDidMount() {
    appGlobal.func.getCookie(loginConst.signInResponse.accessToken) && !this.props.auth.isAuthenticated && this.props.authenticate();
  }

  render() {
    const {isAuthenticated} = this.props.auth;
    return (
      <div className="d-flex flex-column app">
        <Header isAuthenticated={isAuthenticated} />
        <Content isAuthenticated={isAuthenticated} />
        <Footer />
      </div>
    );
  }
}

function onAuthenticate() {
  return (dispatch) => {
    fetch(appGlobal.func.getFullUrlByPath(loginConst.getAuthUserUrl), {
      method: appGlobal.methods.POST,
      headers: appGlobal.func.getAuthHeaderByCred(
        loginConst.tokenFlows.passwordFlow.clientId,
        loginConst.tokenFlows.passwordFlow.clientSecret
      )
    }).then((response) => {
      debugLogVar(response);
      return response.json();
    }).then((user) => {
      debugLogVar(user);
      dispatch(authenticate(user));
    }).catch((error) => {
      debugLogVar(error.message);
    });
  }
}

const mapDispatchToProps = dispatch => (
  { authenticate: () => dispatch(onAuthenticate()) }
);

const mapStateToProps = state => (
  { auth: state.auth }
);

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(App));

const Content = ({isAuthenticated}) => {
  return(
    <main className="content">
      <Switch>
        <Route exact path='/' component={StartPage} />
        <Route path="/login/" component={LoginPage} />
        <Route path="/swagger-ui" component={SwaggerUiPage} />
        <PrivateRoute path='/protected' component={StartPage} isAuthenticated={isAuthenticated} />
        <Route component={EmptyPage} />
      </Switch>
    </main>
  )
};


const PrivateRoute = ({component: Component, ...rest}) => (
  <Route {...rest} render={(props) => (
    props.isAuthenticated === true
      ? <Component {...props} />
      : <Redirect to='/sign-in'/>
  )}/>
);