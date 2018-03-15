import React, {Component} from 'react';
import octicons from 'octicons';
import {Row, Col, Form, FormGroup, InputGroup, InputGroupAddon, Input, Button, FormFeedback} from 'reactstrap';
import {InnerFormSvg} from '../../components/innerHtml/InnerHtml'
import {appGlobal} from '../../constants/appGlobal'

import './style.css'
import steamPng from './steam.png';

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
        value: appGlobal.auth.signInResponse.errorDescriptionDefaultVal
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

    fetch(appGlobal.common.func.getFullUrlByPath(appGlobal.auth.signInUrl), {
      method: appGlobal.common.methods.POST,
      headers: appGlobal.auth.func.getAuthHeaderByCred(
        appGlobal.auth.tokenFlows.passwordFlow.clientId,
        appGlobal.auth.tokenFlows.passwordFlow.clientSecret
      ),
      body: appGlobal.common.func.getFormEncodedParams({
        'username': this.state.username.value,
        'password': this.state.password.value,
        'grant_type': 'client_credentials' //appGlobal.auth.tokenFlows.passwordFlow.grant_type
      })
    }).then((response) => {
      appGlobal.common.func.callFuncIfParamExists(appGlobal.isDebug,console.log,response);
      return response.json();
    }).then((json) => {
      appGlobal.common.func.callFuncIfParamExists(appGlobal.isDebug,console.log,json);

      this.setState({
        authStatus: {
          ...this.state.authStatus,
          style: Signing.hideElementIfExists(!json[appGlobal.auth.signInResponse.error]),
          value: json[appGlobal.auth.signInResponse.error] ? json[appGlobal.auth.signInResponse.errorDescription] : json[appGlobal.auth.signInResponse.errorDescriptionDefaultVal]
        }
      });

      appGlobal.common.func.callFuncIfParamExists(
        json[appGlobal.auth.signInResponse.accessToken],
        appGlobal.common.func.setCookie,
        appGlobal.auth.signInResponse.accessToken,
        json[appGlobal.auth.signInResponse.accessToken],
        json[appGlobal.auth.signInResponse.expires]
      );
      appGlobal.common.func.callFuncIfParamExists(
        json[appGlobal.auth.signInResponse.refreshToken],
        appGlobal.common.func.setCookie,
        appGlobal.auth.signInResponse.refreshToken,
        json[appGlobal.auth.signInResponse.refreshToken],
        (() => {
          const date = new Date();
          date.setTime(date.getTime() + (365 * 24 * 60 * 60 * 1000));
          return date.toUTCString()
        })()
      );
      appGlobal.common.func.callFuncIfParamExists(appGlobal.isDebug,console.log,json[appGlobal.auth.signInResponse.accessToken] || 'access token is empty');
      appGlobal.common.func.callFuncIfParamExists(appGlobal.isDebug,console.log,json[appGlobal.auth.signInResponse.refreshToken] || 'refresh token is empty');
    }).catch((error) => {
      appGlobal.common.func.callFuncIfParamExists(appGlobal.isDebug,console.log,error.message);
      this.setState({
        authStatus: {
          ...this.state.authStatus,
          style: Signing.hideElementIfExists(!error.message),
          value: 'Connection errors has occurred.'
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
    return (
      <section className="container">
        <Row>
          <Col className="d-flex justify-content-center align-items-center">
            <Form className="box-shadow-element-noborder m-4 p-4">
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
            </Form>
          </Col>
        </Row>
      </section>
    )
  }
}

export default Signing;
