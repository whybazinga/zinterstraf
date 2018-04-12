import React, {Component} from 'react';
import {withRouter} from 'react-router-dom'
import {connect} from "react-redux";

import Header from './containers/header/Header';
import Footer from './containers/footer/Footer';
import {fetchPostJsonResponse, appGlobal, debugLogVar} from "./constants/appGlobal";
import {loginConst} from "./constants/loginConst";
import {authenticate, logout} from "./actions/authActions";
import {AppRouter} from "./AppRouter";
import './App.css';


class App extends Component {

  componentDidMount() {
    appGlobal.func.getCookie(loginConst.signInResponse.accessToken) && appGlobal.func.checkIfEmptyJson(props.authUser) && this.props.authenticate();
  }

  static authorizeUser() {
    return (dispatch) => {
      fetchPostJsonResponse(loginConst.getAuthUserUrl, {
      }).then((json) => {
        debugLogVar(json);

        if(json.error === 'unauthorized') { //later need a little bit refactoring
          this.logoutUser();
        } else {
          dispatch(authenticate(json));
        }
      }).catch((error) => {
        debugLogVar(error.message);
      });
    }
  }

  static logoutUser() {
    return (dispatch) => {
      appGlobal.func.deleteCookie(loginConst.signInResponse.accessToken);
      dispatch(logout());
    }
  }

  render() {
    const {authUser} = this.props.auth;
    return (
      <div className="d-flex flex-column app">
        <Header authUser={authUser} />
        <AppRouter authUser={authUser} />
        <Footer />
      </div>
    );
  }
}


const mapDispatchToProps = dispatch => (
  { authenticate: () => dispatch(App.authorizeUser()) }
);

const mapStateToProps = state => (
  { authUser: state.auth.authUser }
);

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(App));