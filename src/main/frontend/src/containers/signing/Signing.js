import React, {Component} from 'react'
import octicons from 'octicons'
import {Row, Col, Form, FormGroup, InputGroup, InputGroupAddon, Input, Button, FormFeedback} from 'reactstrap'
import {InnerFormSvg} from '../../components/innerHtml/InnerHtml'
import {appGlobal, debugLogVar} from '../../constants/appGlobal'
import {signingConst} from '../../constants/signingConst'
import './style.css'
import {Route, Redirect, Link, Switch} from 'react-router-dom'
import steamPng from './steam.png'

const steamStyle = {
  width: '40px',
  borderRadius: '20px'
};

class Signing extends Component {

  constructor(props) {
    super(props);
    this.onLogin = this.onLogin.bind(this);
    this.onChangeName = this.onChangeName.bind(this);
    this.onChangePassword = this.onChangePassword.bind(this);
    this.state = {
      username: {
        value: '',
        validInputClass: '',
        invalidHintStyle: Signing.hideElementIfExists(true)
      },
      password: {
        value: '',
        validInputClass: '',
        invalidHintStyle: Signing.hideElementIfExists(true)
      },
      authStatus: {
        style: Signing.hideElementIfExists(true),
        redirect: false,
        value: signingConst.signInResponse.errorDescriptionDefaultVal
      },
      signUpStatus: {
        style: Signing.hideElementIfExists(true),
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

  onLogin() {
    this.setState({
      username: {
        ...this.state.username,
        validInputClass: Signing.getHintClassByParam(this.state.username.value),
        invalidHintStyle:  Signing.hideElementIfExists(this.state.username.value)
      }
    });

    this.setState({
      password: {
        ...this.state.password,
        validInputClass: Signing.getHintClassByParam(this.state.password.value),
        invalidHintStyle: Signing.hideElementIfExists(this.state.password.value)
      }
    });

    if (!this.state.username.value || !this.state.password.value) return;

    fetch(appGlobal.func.getFullUrlByPath(signingConst.signInUrl), {
      method: appGlobal.methods.POST,
      headers: appGlobal.func.getAuthHeaderByCred(
        signingConst.tokenFlows.passwordFlow.clientId,
        signingConst.tokenFlows.passwordFlow.clientSecret
      ),
      body: appGlobal.func.getFormEncodedParams({
        'username': this.state.username.value,
        'password': this.state.password.value,
        'grant_type': signingConst.tokenFlows.passwordFlow.grant_type
      })
    }).then((response) => {
      debugLogVar(response);
      return response.json();
    }).then((json) => {
      debugLogVar(json);

      this.setState({
        authStatus: {
          ...this.state.authStatus,
          style: Signing.hideElementIfExists(!json[signingConst.signInResponse.error]),
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
          style: Signing.hideElementIfExists(!error.message),
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
      <section className="container">
        <Row>
          <Col className="d-flex justify-content-center align-items-center">
            <Form className="box-shadow-element-no-border m-4 p-4">
              <FormGroup>
                <InputGroup>
                  <InputGroupAddon addonType="prepend"><InnerFormSvg svg={octicons.mail.toSVG()}/></InputGroupAddon>
                  <Input type="email" id="userName" placeholder="my-mail@gmail.com"
                         value={this.state.username.value} className={this.state.username.validInputClass}
                         onChange={this.onChangeName}/>
                </InputGroup>
                <FormFeedback style={this.state.username.invalidHintStyle}>The username or email mustn't be empty.</FormFeedback>
              </FormGroup>
              <FormGroup>
                <InputGroup>
                  <InputGroupAddon addonType="prepend"><InnerFormSvg svg={octicons.key.toSVG()}/></InputGroupAddon>
                  <Input type="password" id="userPassword" placeholder="my-password123"
                         value={this.state.password.value} className={this.state.password.validInputClass}
                         onChange={this.onChangePassword}/>
                </InputGroup>
                <FormFeedback style={this.state.password.invalidHintStyle}>The password mustn't be empty.</FormFeedback>
              </FormGroup>

              <Switch>
                <Route path='/signing/in' render={() => (<SignIn signingStatus={this.state.authStatus} signingFunc={this.onLogin}/>)} />
                <Route path='/signing/up' render={() => (<SignUp signingStatus={this.state.signUpStatus} signingFunc={this.onLogin}/>)} />
              </Switch>

            </Form>
          </Col>
        </Row>
      </section>
    )
  }
}

export default Signing;


const SignIn = ({signingStatus, signingFunc}) => (
  <div>
    <FormGroup className="text-right">
      <FormFeedback style={signingStatus.style} className="text-center font-weight-bold mb-2">{signingStatus.value}</FormFeedback>
      <Button onClick={signingFunc}>Sign in</Button>
    </FormGroup>
    <hr/>
    <div className="text-center">
      <p>
        <small className="text-muted">Sign In through the google</small>
      </p>
      <input type="image" src={steamPng} style={steamStyle} alt="steam-img"/>
    </div>
    <hr/>
    <div className="text-center">
      <Button color="link">Sign In the system</Button>
    </div>
  </div>
);

const SignUp = ({signingStatus, signingFunc}) => (
  <div>
    <FormGroup className="text-right">
      <FormFeedback style={signingStatus.style} className="text-center font-weight-bold mb-2">{signingStatus.value}</FormFeedback>
      <Button onClick={signingFunc}>Sign up</Button>
    </FormGroup>
    <hr/>
    <div className="text-center">
      <p>
        <small className="text-muted">Sign Up through the google</small>
      </p>
      <input type="image" src={steamPng} style={steamStyle} alt="steam-img"/>
    </div>
    <hr/>
    <div className="text-center">
      <Button color="link">Sign Up in the system</Button>
    </div>
  </div>
);

/*
<FormGroup className="text-right">
  <FormFeedback style={this.state.authStatus.style} className="text-center font-weight-bold mb-2">{this.state.authStatus.value}</FormFeedback>
  <Button onClick={this.onLogin}>Sign in</Button>
</FormGroup>
<hr/>
<div className="text-center">
  <p>
    <small className="text-muted">Sign in through the steam</small>
  </p>
  <input type="image" src={steamPng} style={steamStyle} alt="steam-img"/>
</div>
<hr/>
<div className="text-center">
  <Button color="link">Sign up directly in the system</Button>
</div>
 */