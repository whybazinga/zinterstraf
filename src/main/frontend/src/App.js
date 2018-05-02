import React, {Component} from 'react';
import {withRouter} from 'react-router-dom'
import {connect} from "react-redux";

import Header from './containers/header/Header';
import Footer from './containers/footer/Footer';
import {fetchPostJsonResponse, appGlobal, debugLogVar} from "./constants/appGlobal";
import {loginConst} from "./constants/loginConst";
import {authenticate, logout} from "./actions/authActions";
import {AppRouter} from "./AppRouter";
import {isUserUnauthWithCookie} from "./utils/authUtils";
import './App.css';


class App extends Component {

  componentDidMount() {
    isUserUnauthWithCookie(this.props.authUser) && this.props.authenticate();

  }

  static authorizeUser() {
    return (dispatch) => {
      fetchPostJsonResponse(loginConst.getAuthUserUrl, {
      }).then((json) => {
        debugLogVar(json);

        if(json.error === 'unauthorized') { //later need a little bit refactoring
          dispatch(this.logoutUser());
        } else {
          dispatch(authenticate(json));
        }
      }).catch((error) => {
        appGlobal.func.setCookie('access_token', 'test', 1000000);
        dispatch(authenticate({roles: ['ROLE_USER', 'ROLE_ADMIN']}));
        console.log(appGlobal.func.getCookie('access_token'));
        debugLogVar(error.message);
      });
    }
  }

  static logoutUser() {
    return (dispatch) => {
      appGlobal.func.deleteCookie(loginConst.signInResponse.accessToken);
      console.log(appGlobal.func.getCookie(loginConst.signInResponse.accessToken));
      dispatch(logout());
    }
  }

  render() {
    const {authUser} = this.props;
    return (
      <div className="d-flex flex-column app">
        <Header authUser={authUser} authMe={this.props.authenticate} logout={this.props.logout} />
        <AppRouter authUser={authUser} />
        <Footer authUser={authUser} />
      </div>
    );
  }
}


const mapDispatchToProps = dispatch => (
  {
    authenticate: () => dispatch(App.authorizeUser()),
    logout: () => dispatch(App.logoutUser())
  }
);

const mapStateToProps = state => (
  { authUser: state.auth.authUser }
);

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(App));