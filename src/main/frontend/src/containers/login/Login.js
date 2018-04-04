import React, {Component} from 'react'
import octicons from 'octicons'
import {Row, Col, Form, FormGroup, InputGroup, InputGroupAddon, Input, FormFeedback} from 'reactstrap'
import {InnerFormSvg} from '../../components/innerHtml/InnerHtml'
import {appGlobal, debugLogVar, fetchPostJsonResponse} from '../../constants/appGlobal'
import {loginConst} from '../../constants/loginConst'
import './login.css'
import {Redirect, Link} from 'react-router-dom'
import uuidv1 from "uuid";
import FluidRowTitle from '../../components/fluidRowTitle/FluidRowTitle'


class Login extends Component {
  constructor(props) {
    super(props);
    this.onLogin = this.onLogin.bind(this);
    this.systemLogin = this.systemLogin.bind(this);
    this.onChangeName = this.onChangeName.bind(this);
    this.onChangePassword = this.onChangePassword.bind(this);
    this.checkIfSigningFieldsValid = this.checkIfSigningFieldsValid.bind(this);
    this.state = {
      username: {
        value: '',
        validInputClass: '',
        invalidHintStyle: Login.hideElementIfExists(true)
      },
      password: {
        value: '',
        validInputClass: '',
        invalidHintStyle: Login.hideElementIfExists(true)
      },
      authStatus: {
        style: Login.hideElementIfExists(true),
        redirect: false,
        value: loginConst.signInResponse.errorDescriptionDefaultVal
      }
    };
  }

  static hideElementIfExists(param) {
    return {
      display: param ? 'none' : 'block'
    }
  }

  static getHintClassByParam(param) {
    return param ? 'is-valid' : 'is-invalid'
  }

  systemLogin() {
    if (!this.checkIfSigningFieldsValid()) return;

    fetchPostJsonResponse(loginConst.signInUrl, {
      'username': this.state.username.value,
      'password': this.state.password.value,
      'grant_type': loginConst.tokenFlows.passwordFlow.grantType
    }).then((json) => {
      debugLogVar(json);

      this.setState({
        authStatus: {
          ...this.state.authStatus,
          style: Login.hideElementIfExists(!json[loginConst.signInResponse.error]),
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
          style: Login.hideElementIfExists(!error.message),
          value: error.message
        }
      });
    });
  }

  checkIfSigningFieldsValid() {
    this.setState({
      username: {
        ...this.state.username,
        validInputClass: Login.getHintClassByParam(this.state.username.value),
        invalidHintStyle:  Login.hideElementIfExists(this.state.username.value)
      }
    });
    this.setState({
      password: {
        ...this.state.password,
        validInputClass: Login.getHintClassByParam(this.state.password.value),
        invalidHintStyle: Login.hideElementIfExists(this.state.password.value)
      }
    });

    return this.state.username.value && this.state.password.value
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

  onChangeName(e) {
    this.setState({
      username: {
        ...this.state.username,
        value: e.target.value
      }
    });
  }

  onChangePassword(e) {
    this.setState({
      password: {
        ...this.state.password,
        value: e.target.value
      }
    });
  }


  render() {
    const { redirect } = this.state.authStatus;
    if (redirect) return <Redirect to='/' />;

    return (
      <div>
        <FluidRowTitle title="Your Account" />
        <section className="container">
          <Row className="justify-content-center signing-child-titles pt-3 pb-3">
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
              <Form className="p-4 signing-containers rounded-border">
                <FormGroup>
                  <InputGroup>
                    <InputGroupAddon addonType="prepend"><InnerFormSvg svg={octicons['mention'].toSVG()}/></InputGroupAddon>
                    <Input type="email" id="userName" placeholder="my-mail@gmail.com"
                           value={this.state.username.value} className={this.state.username.validInputClass}
                           onChange={this.onChangeName}/>
                  </InputGroup>
                  <FormFeedback style={this.state.username.invalidHintStyle}>The email mustn't be empty.</FormFeedback>
                </FormGroup>
                <FormGroup>
                  <InputGroup>
                    <InputGroupAddon addonType="prepend"><InnerFormSvg svg={octicons['key'].toSVG()}/></InputGroupAddon>
                    <Input type="password" id="userPassword" placeholder="my-password123"
                           value={this.state.password.value} className={this.state.password.validInputClass}
                           onChange={this.onChangePassword}/>
                  </InputGroup>
                  <FormFeedback style={this.state.password.invalidHintStyle}>The password mustn't be empty.</FormFeedback>
                </FormGroup>
                <FormGroup>
                  <FormFeedback style={this.state.authStatus.style} className="text-center font-weight-bold mb-2">{this.state.authStatus.value}</FormFeedback>
                  <Row>
                    <Col md="6">
                      <Link to='/'>Forgot login details?</Link>
                    </Col>
                    <Col md="6">
                      <button className="btn container-href-btn theme-blue" onClick={this.onLogin} value={loginConst.loginButtons.system.val}>{loginConst.loginButtons.system.name}</button>
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
                      <button className="btn container-href-btn facebook-btn" onClick={this.onLogin} value={loginConst.loginButtons.facebook.val}>{loginConst.loginButtons.facebook.name}</button>
                    </Col>
                    <Col md="6">
                      <button className="btn container-href-btn twitter-btn" onClick={this.onLogin} value={loginConst.loginButtons.twitter.val}>{loginConst.loginButtons.twitter.name}</button>
                    </Col>
                  </Row>
                  <Row>
                    <Col md="6">
                      <button className="btn container-href-btn vk-btn" onClick={this.onLogin} value={loginConst.loginButtons.vk.val}>{loginConst.loginButtons.vk.name}</button>
                    </Col>
                    <Col md="6">
                      <button className="btn container-href-btn google-btn" onClick={this.onLogin} value={loginConst.loginButtons.google.val}>{loginConst.loginButtons.google.name}</button>
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
                <button className="btn theme-blue container-href-btn">Register</button>
              </article>
            </Col>
          </Row>
        </section>
      </div>
    )
  }
}

export default Login;