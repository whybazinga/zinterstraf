import React, {Component} from 'react'
import octicons from 'octicons'
import {Row, Col, Form, FormGroup, InputGroup, InputGroupAddon, Input, FormFeedback} from 'reactstrap'
import {InnerFormSvg} from '../../components/innerHtml/InnerHtml'
import {appGlobal, debugLogVar, fetchPostJsonResponse} from '../../constants/appGlobal'
import {signingConst} from '../../constants/signingConst'
import './login.css'
import {Redirect, Link} from 'react-router-dom'
import uuidv1 from "uuid";


class Login extends Component {

  constructor(props) {
    super(props);
    this.onLogin = this.onLogin.bind(this);
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
        value: signingConst.signInResponse.errorDescriptionDefaultVal
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
/*
  onRegister() {
    if (!this.checkIfSigningFieldsValid(true)) return;

    fetchPostJsonResponse(signingConst.signUpUrl, {
      'username': this.state.username.value,
      'password': this.state.password.value,
      'type': 'direct'
    }).then((json) => {

    })

  }
*/
  onLogin() {

    if (!this.checkIfSigningFieldsValid()) return;

    fetchPostJsonResponse(signingConst.signInUrl, {
      'username': this.state.username.value,
      'password': this.state.password.value,
      'grant_type': signingConst.tokenFlows.passwordFlow.grantType
    }).then((json) => {
      debugLogVar(json);

      this.setState({
        authStatus: {
          ...this.state.authStatus,
          style: Login.hideElementIfExists(!json[signingConst.signInResponse.error]),
          value: json[signingConst.signInResponse.error] ? json[signingConst.signInResponse.errorDescription] : json[signingConst.signInResponse.errorDescriptionDefaultVal]
        }
      });

      appGlobal.func.callFuncIfParamExists(
        json[signingConst.signInResponse.accessToken],
        appGlobal.func.setCookie,
        signingConst.signInResponse.accessToken,
        json[signingConst.signInResponse.accessToken],
        json[signingConst.signInResponse.expires]
      );
      appGlobal.func.callFuncIfParamExists(
        json[signingConst.signInResponse.refreshToken],
        appGlobal.func.setCookie,
        signingConst.signInResponse.refreshToken,
        json[signingConst.signInResponse.refreshToken],
        (() => {
          const date = new Date();
          date.setTime(date.getTime() + (365 * 24 * 60 * 60 * 1000));
          return date.toUTCString()
        })()
      );
      debugLogVar(json[signingConst.signInResponse.accessToken] || 'access token is empty');
      debugLogVar(json[signingConst.signInResponse.refreshToken] || 'refresh token is empty');
      return !!json[signingConst.signInResponse.accessToken];
    }).then((isRedirect) => {
      this.setState({
        authStatus: {
          ...this.state.authStatus,
          redirect: isRedirect
        }
      });
    }).catch((error) => {
      debugLogVar(error.message);
      this.setState({
        authStatus: {
          ...this.state.authStatus,
          style: Login.hideElementIfExists(!error.message),
          value: appGlobal.error.connectionError
        }
      });
    });
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
        <section className="container-fluid">
          <Row className="signing-main-title theme-blue pt-5 pb-5">
            <Col className="d-flex align-items-center offset-sm-2">
              <span>Your Account</span>
            </Col>
          </Row>
        </section>
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
              <Form className="p-4 signing-containers">
                <FormGroup>
                  <InputGroup>
                    <InputGroupAddon addonType="prepend"><InnerFormSvg svg={octicons['mail'].toSVG()}/></InputGroupAddon>
                    <Input type="email" id="userName" placeholder="my-mail@gmail.com"
                           value={this.state.username.value} className={this.state.username.validInputClass}
                           onChange={this.onChangeName}/>
                  </InputGroup>
                  <FormFeedback style={this.state.username.invalidHintStyle}>The username or email mustn't be empty.</FormFeedback>
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
                <FormGroup className="pt-2">
                  <FormFeedback style={this.state.authStatus.style} className="text-center font-weight-bold mb-2">{this.state.authStatus.value}</FormFeedback>
                  <Row>
                    <Col md="6">
                      <Link to='/'>Forgot login details?</Link>
                    </Col>
                    <Col md="6">
                      <button className="btn container-href-btn theme-blue" onClick={this.onLogin}>Log in</button>
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
                      <button className="btn container-href-btn facebook-btn app-font-size">Facebook</button>
                    </Col>
                    <Col md="6">
                      <button className="btn container-href-btn twitter-btn app-font-size">Twitter</button>
                    </Col>
                  </Row>
                  <Row>
                    <Col md="6">
                      <button className="btn container-href-btn vk-btn app-font-size">VK</button>
                    </Col>
                    <Col md="6">
                      <button className="btn container-href-btn google-btn">Google</button>
                    </Col>
                  </Row>
                </div>
              </Form>
            </Col>
            <Col md="4">
              <article className="p-4 signing-containers">
                <h6>Don't have an EGA account?</h6>
                <p>In that case, you are missing out on:</p>
                <ul>
                  {signingConst.authBenefits.en.map(el => (
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