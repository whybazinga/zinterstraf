import React, {Component} from 'react'
import octicons from 'octicons'
import {Row, Col, Form, FormGroup, InputGroup, InputGroupAddon} from 'reactstrap'
import {Redirect, Link} from 'react-router-dom'
import uuidv1 from "uuid";

import {InnerFormSvg} from '../../components/innerHtml/InnerHtml'
import {appGlobal, debugLogVar, fetchPostJsonResponse} from '../../constants/appGlobal'
import {loginConst} from '../../constants/loginConst'
import {FluidRowTitle} from '../../components/fluidRowTitle/FluidRowTitle'
import {FormDynamicInput} from "../../components/formDynamicInput/FormDynamicInput"
import {FormRowErrorHint} from "../../components/formRowErrorHint/FormRowErrorHint"
import {inputTypes} from "../../constants/inputTypes"
import './login.css'


class Login extends Component {
  constructor(props) {
    super(props);
    this.onLogin = this.onLogin.bind(this);
    this.systemLogin = this.systemLogin.bind(this);
    this.onChangeElement = this.onChangeElement.bind(this);
    this.checkFieldsValidity = this.checkFieldsValidity.bind(this);
    this.onFocusHideStatusWarn = this.onFocusHideStatusWarn.bind(this);
    this.state = {
      email: {value: '', warn: ''},
      password: {value: '', warn: ''},
      authStatus: {value: loginConst.signInResponse.errorDescriptionDefaultVal, warn: '', redirect: false}
    };
  }

  onFocusHideStatusWarn() {
    if(this.state.authStatus.warn === true) {
      this.setState({
        authStatus: {
          ...this.state.authStatus,
          warn: ''
        }
      })
    }
  }

  systemLogin() {
    if (!this.checkFieldsValidity()) return;

    fetchPostJsonResponse(loginConst.signInUrl, {
      [loginConst.signInRequest.username]: this.state.email.value,
      [loginConst.signInRequest.password]: this.state.password.value,
      [loginConst.signInRequest.grantType]: loginConst.tokenFlows.passwordFlow.grantType
    }).then((json) => {
      debugLogVar(json);
      this.setState({
        authStatus: {
          ...this.state.authStatus,
          warn: !!json[loginConst.signInResponse.error],
          value: json[loginConst.signInResponse.error] ? json[loginConst.signInResponse.errorDescription] : json[loginConst.signInResponse.errorDescriptionDefaultVal]
        }
      });

      appGlobal.func.setCookie(loginConst.signInResponse.accessToken, json[loginConst.signInResponse.accessToken], json[loginConst.signInResponse.expires]);
      appGlobal.func.setCookie(loginConst.signInResponse.refreshToken, json[loginConst.signInResponse.refreshToken], appGlobal.func.getCurrentUTCPlusHours(loginConst.signInResponse.refreshTokenExpires));

      return !!json[loginConst.signInResponse.accessToken];
    }).then((isRedirect) => {
      this.setState({
        authStatus: {
          ...this.state.authStatus,
          redirect: isRedirect
        }
      });
    }).catch((error) => {
      this.setState({
        authStatus: {
          ...this.state.authStatus,
          warn: true,
          value: error.message
        }
      });
    });
  }

  checkFieldsValidity() {
    this.setState({
      email: {
        ...this.state.email,
        warn: !this.state.email.value
      }
    });
    this.setState({
      password: {
        ...this.state.password,
        warn: !this.state.password.value
      }
    });

    return this.state.email.value && this.state.password.value
  };

  onLogin(e) {
    e.preventDefault();
    switch(e.target.value) {
      case loginConst.loginButtons.system.val:
        this.systemLogin();
        break;
      case loginConst.loginButtons.vk.val:
        debugLogVar('vk auth is no ready yet');
        break;
      case loginConst.loginButtons.twitter.val:
        debugLogVar('twitter auth is no ready yet');
        break;
      case loginConst.loginButtons.google.val:
        debugLogVar('google auth is no ready yet');
        break;
      case loginConst.loginButtons.facebook.val:
        debugLogVar('facebook auth is no ready yet');
        break;
      default:
        debugLogVar('No auth provided');
    }
  }

  onChangeElement(e) {
    this.setState({
      [e.target.id]: {
        ...this.state[e.target.id],
        value: e.target.value,
        warn: ''
      }
    })
  }

  render() {
    if (this.state.authStatus.redirect) return <Redirect to='/' />;

    return (
      <React.Fragment>
        <FluidRowTitle title="Your Account" />
        <section className="container">
          <Row className="justify-content-center container-title pt-3 pb-3">
            <Col md="8">
              <Row className="theme-blue">
                <Col>
                  <h6>Log In</h6>
                </Col>
                <Col>
                  <h6>Register</h6>
                </Col>
              </Row>
            </Col>
          </Row>
          <Row className="justify-content-center pb-5">
            <Col md="4">
              <Form className="p-4 signing-containers rounded-border" onFocus={this.onFocusHideStatusWarn}>
                <FormGroup>
                  <InputGroup>
                    <InputGroupAddon addonType="prepend"><InnerFormSvg>{octicons['mention'].toSVG()}</InnerFormSvg></InputGroupAddon>
                    <FormDynamicInput type={inputTypes.email} id={inputTypes.email} value={this.state.email.value} onChange={this.onChangeElement} warn={this.state.email.warn}  placeholder="my-mail@gmail.com" />
                  </InputGroup>
                </FormGroup>
                <FormRowErrorHint warn={this.state.email.warn}>The email mustn't be empty.</FormRowErrorHint>
                <FormGroup>
                  <InputGroup>
                    <InputGroupAddon addonType="prepend"><InnerFormSvg>{octicons['key'].toSVG()}</InnerFormSvg></InputGroupAddon>
                    <FormDynamicInput type={inputTypes.password} id={inputTypes.password} value={this.state.password.value} onChange={this.onChangeElement} warn={this.state.password.warn}  placeholder="my-password123" />
                  </InputGroup>
                </FormGroup>
                <FormRowErrorHint warn={this.state.password.warn}>The password mustn't be empty.</FormRowErrorHint>
                <FormRowErrorHint warn={this.state.authStatus.warn}>{this.state.authStatus.value}</FormRowErrorHint>
                <FormGroup>
                  <Row>
                    <Col md="6">
                      <Link to='/'>Forgot login details?</Link>
                    </Col>
                    <Col md="6">
                      <button className="btn fluid-btn theme-blue" onClick={this.onLogin} value={loginConst.loginButtons.system.val}>{loginConst.loginButtons.system.name}</button>
                    </Col>
                  </Row>
                </FormGroup>
                <hr/>
                <div className="text-center">
                  <p>
                    <small className="text-muted">or login with</small>
                  </p>
                </div>
                <div>
                  <Row className="pb-2">
                    <Col md="6">
                      <button className="btn fluid-btn facebook-btn" onClick={this.onLogin} value={loginConst.loginButtons.facebook.val}>{loginConst.loginButtons.facebook.name}</button>
                    </Col>
                    <Col md="6">
                      <button className="btn fluid-btn twitter-btn" onClick={this.onLogin} value={loginConst.loginButtons.twitter.val}>{loginConst.loginButtons.twitter.name}</button>
                    </Col>
                  </Row>
                  <Row>
                    <Col md="6">
                      <button className="btn fluid-btn vk-btn" onClick={this.onLogin} value={loginConst.loginButtons.vk.val}>{loginConst.loginButtons.vk.name}</button>
                    </Col>
                    <Col md="6">
                      <button className="btn fluid-btn google-btn" onClick={this.onLogin} value={loginConst.loginButtons.google.val}>{loginConst.loginButtons.google.name}</button>
                    </Col>
                  </Row>
                </div>
              </Form>
            </Col>
            <Col md="4">
              <article className="p-4 signing-containers rounded-border">
                <h6>Don't have an EGA account?</h6>
                <p>In that case, you are missing out on:</p>
                <ul>
                  {loginConst.authBenefits.map(el => (
                    <li key={uuidv1()}>{el}</li>
                  ))}
                </ul>
                <button className="btn theme-blue fluid-btn" onClick={() => this.props.history.push('/register')}>Register</button>
              </article>
            </Col>
          </Row>
        </section>
      </React.Fragment>
    )
  }
}

export default Login;