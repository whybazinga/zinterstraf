import React, {Component} from 'react';
import './App.css';
import Header from './components/header/Header';
import Footer from './components/footer/Footer';
import {Route, Redirect, withRouter, Switch} from 'react-router-dom'

import StartPage from './components/startPage/StartPage';
import Signing from './containers/signing/Signing';
import SwaggerUi from './components/swagger/Swagger';
import {appGlobal, debugLogVar} from "./constants/appGlobal";
import {signingConst} from "./constants/signingConst";
import {authenticate} from "./actions/authActions";
import {connect} from "react-redux";

class App extends Component {

  componentDidMount() {
    appGlobal.func.getCookie(signingConst.signInResponse.accessToken) && !this.props.auth.isAuthenticated && this.props.authenticate();
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
    fetch(appGlobal.func.getFullUrlByPath(signingConst.getAuthUserUrl), {
      method: appGlobal.methods.POST,
      headers: appGlobal.func.getAuthHeaderByCred(
        signingConst.tokenFlows.passwordFlow.clientId,
        signingConst.tokenFlows.passwordFlow.clientSecret
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

const Content = ({isAuthenticated}) => { return(
  <main className="content">
    <Switch>
      <Route exact path='/' component={StartPage}/>
      <Route path="/signing/" component={Signing}/>
      <Route path="/swagger-ui" component={SwaggerUi}/>
      <PrivateRoute path='/protected' component={StartPage} isAuthenticated={isAuthenticated} />
    </Switch>
  </main>
)};


const PrivateRoute = ({component: Component, ...rest}) => (
  <Route {...rest} render={(props) => (
    props.isAuthenticated === true
      ? <Component {...props} />
      : <Redirect to='/sign-in'/>
  )}/>
);