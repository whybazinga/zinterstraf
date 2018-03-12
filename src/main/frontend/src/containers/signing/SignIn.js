import React, {Component} from 'react';
import octicons from 'octicons';
import {Row, Col, Form, FormGroup, InputGroup, InputGroupAddon, Input, Button, FormFeedback} from 'reactstrap';
import {InnerFormSvg} from '../../components/innerHtml/InnerHtml'

import './singIn.css'
import steamPng from './steam.png';
const steamStyle = {
  width:'40px',
  borderRadius:'20px'
};

//fetch('fsdf').then();

class SignIn extends React.Component {

  constructor(props) {
    super(props);
    this.login = this.login.bind(this);
    this.onChangeName = this.onChangeName.bind(this);
    this.onChangePassword = this.onChangePassword.bind(this);
    this.state = {
      email: {
        value: '',
        isValidInputClass: '',
        isValidHintStyle: {
          display: 'none'
        }
      },
      password: {
        value: '',
        isValidInputClass: '',
        isValidHintStyle: {
          display: 'none'
        }
      },
    };
  }

  login(e) {
    if(this.state.email && this.state.password) {

    }
  }

  onChangeName = (e) => {
    let currentName = e.target.value;
    e.target.getAttribute('name');
    this.setState({
      email: {
        ...this.state.email,
        value: currentName,
        isValidInputClass: currentName ? 'is-valid' : 'is-invalid',
        isValidHintStyle: !!currentName
      }
    });
  };

  onChangePassword(e) {
    let currentPassword = e.target.value;
  }

  render() {
    return (<section className="container">
        <Row>
          <Col className="d-flex justify-content-center align-items-center">
            <Form className="box-shadow-element-noborder m-4 p-4">
              <FormGroup>
                <InputGroup>
                  <InputGroupAddon addonType="prepend"><InnerFormSvg svg={octicons.mail.toSVG()}/></InputGroupAddon>
                  <Input type="email" name="email" id="userEmail" placeholder="my-mail@gmail.com" value={this.state.email.value} className={this.state.email.isValid} onChange={this.onChangeName} />
                </InputGroup>
                <FormFeedback valid>This username is correct</FormFeedback>
                <FormFeedback>The username mustn't be empty.</FormFeedback>
              </FormGroup>
              <FormGroup>
                <InputGroup>
                  <InputGroupAddon addonType="prepend"><InnerFormSvg svg={octicons.key.toSVG()}/></InputGroupAddon>
                  <Input type="password" name="password" id="userPassword" placeholder="my-password123" value={this.state.password} className={this.state.isValidPass} onChange={this.onChangePassword} />
                </InputGroup>
                <FormFeedback valid>This password is correct</FormFeedback>
                <FormFeedback>The password mustn't be empty.</FormFeedback>
              </FormGroup>
              <FormGroup className="text-right">
                <Button>Sign in</Button>
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
