import React, {Component} from 'react';
import octicons from 'octicons';
import {Row, Col, Form, FormGroup, InputGroup, InputGroupAddon, Input, Button, FormFeedback} from 'reactstrap';
import {InnerFormSvg} from '../../components/innerHtml/InnerHtml'
import {settings} from '../../constants/settings'

import './singIn.css'
import steamPng from './steam.png';
const steamStyle = {
  width:'40px',
  borderRadius:'20px'
};


class SignIn extends Component {

  constructor(props) {
    super(props);
    this.onLogin = this.onLogin.bind(this);
    this.onChangeName = this.onChangeName.bind(this);
    this.onChangePassword = this.onChangePassword.bind(this);
    this.state = {
      username: {
        value: '',
        validInput: '',
        validHint: {
          display: 'none'
        },
        invalidHint: {
          display: 'none'
        }
      },
      password: {
        value: '',
        validInput: '',
        validHint: {
          display: 'none'
        },
        invalidHint: {
          display: 'none'
        }
      },
    };
  }

  onLogin() {
    let error = false;
    if(this.state.username.value) {
      this.setState({
        username: {
          ...this.state.username,
          validInput: 'is-valid',
          validHint: {
            display: 'block'
          },
          invalidHint: {
            display: 'none'
          }
        }
      });
    }else{
      this.setState({
        username: {
          ...this.state.username,
          validInput: 'is-invalid',
          validHint: {
            display: 'none'
          },
          invalidHint: {
            display: 'block'
          }
        }
      });
      error = true;
    }
    if(this.state.password.value) {
      this.setState({
        password: {
          ...this.state.password,
          validInput: 'is-valid',
          validHint: {
            display: 'block'
          },
          invalidHint: {
            display: 'none'
          }
        }
      });
    }else{
      this.setState({
        password: {
          ...this.state.password,
          validInput: 'is-invalid',
          validHint: {
            display: 'none'
          },
          invalidHint: {
            display: 'block'
          }
        }
      });
      error = true;
    }
    if(error) return;

    debugger;

    fetch(settings.common.func.getFullUrlByPath(settings.auth.signInUrl), {
      method: settings.common.methods.POST,
      headers: settings.auth.getAuthHeader,
      body: settings.common.func.getFormEncodedParams({
        'username': this.state.username.value,
        'password': this.state.password.value,
        'grant_type': settings.auth.grantType
      })
    }).then((response) => {

      response.forEach((e) => console.log(e));
    }).catch((error) => {
      console.log('Error: ' + error.message)
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
                  <Input type="email" id="userName" placeholder="my-mail@gmail.com" value={this.state.username.value} className={this.state.username.validInput} onChange={this.onChangeName} />
                </InputGroup>
                <FormFeedback valid style={this.state.username.validHint}>This username is correct</FormFeedback>
                <FormFeedback style={this.state.username.invalidHint}>The username mustn't be empty.</FormFeedback>
              </FormGroup>
              <FormGroup>
                <InputGroup>
                  <InputGroupAddon addonType="prepend"><InnerFormSvg svg={octicons.key.toSVG()}/></InputGroupAddon>
                  <Input type="password" id="userPassword" placeholder="my-password123" value={this.state.password.value} className={this.state.password.validInput} onChange={this.onChangePassword} />
                </InputGroup>
                <FormFeedback valid style={this.state.password.validHint}>This password is correct</FormFeedback>
                <FormFeedback style={this.state.password.invalidHint}>The password mustn't be empty.</FormFeedback>
              </FormGroup>
              <FormGroup className="text-right">
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

export default SignIn;
