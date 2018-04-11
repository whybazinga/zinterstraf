import React, {Component} from 'react';
import {Route, Redirect, withRouter, Switch} from 'react-router-dom'
import {connect} from "react-redux";

import './App.css';
import Header from './containers/header/Header';
import Footer from './containers/footer/Footer';
import StartPage from './containers/start/Start';
import LoginPage from './containers/login/Login';
import EmptyPage from './containers/empty/Empty'
import RegisterPage from './containers/register/Register'
import SwaggerUiPage from './containers/swagger/Swagger';
import {fetchPostJsonResponse, appGlobal, debugLogVar} from "./constants/appGlobal";
import {loginConst} from "./constants/loginConst";
import {authenticate} from "./actions/authActions";


class App extends Component {

  componentDidMount() {
    if(appGlobal.func.getCookie(loginConst.signInResponse.accessToken)) {
      !this.props.authUser.isAuthenticated && this.props.authenticate();
    }
  }

  render() {
    const {authUser} = this.props.auth;
    return (
      <div className="d-flex flex-column app">
        <Header authUser={authUser} />
        <Content authUser={authUser} />
        <Footer />
      </div>
    );
  }
}

function onAuthenticate() {
  return (dispatch) => {
    fetchPostJsonResponse(loginConst.getAuthUserUrl, {
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
  { authUser: state.auth.authUser }
);

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(App));

const Content = ({authUser}) => {
  return(
    <main className="content">
      <Switch>
        <Route exact path='/' component={StartPage} />
        <Route path="/login/" component={LoginPage} />
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
      : <Redirect to='/sign-in'/>
  )}/>
);