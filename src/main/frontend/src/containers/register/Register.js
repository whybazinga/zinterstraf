import React, {Component} from 'react'
import {Row, Col, Form, FormGroup, InputGroup, InputGroupAddon, FormFeedback, Label} from 'reactstrap'
import {Redirect, Link} from 'react-router-dom'
//import PropTypes from 'prop-types'

import './register.css'
import {FluidRowTitle} from '../../components/fluidRowTitle/FluidRowTitle'
import {FormDynamicInput} from "../../components/formDynamicInput/FormDynamicInput"
import {inputTypes} from "../../constants/inputTypes";
import {FormRowErrorHint} from "../../components/formRowErrorHint/FormRowErrorHint";
import {countryTypes} from '../../constants/countryTypes'
import {genderTypes} from "../../constants/genderTypes";
import {debugLogVar, fetchPostJsonResponse} from "../../constants/appGlobal";
import {registerConst} from "../../constants/registerConst";


class Register extends Component {
  constructor(props) {
    super(props);
    this.onChangeElement = this.onChangeElement.bind(this);
    this.onRegister = this.onRegister.bind(this);
    this.checkFieldsValidity = this.checkFieldsValidity.bind(this);
    this.onFocusHideStatusWarn = this.onFocusHideStatusWarn.bind(this);
    this.state = {
      inputs: {
        fName: {type: inputTypes.text, txt: 'First Name', value: '', required: true, warn: '', hintTxt: ''},
        lName: {type: inputTypes.text, txt: 'Last Name', value: '', required: true, warn: '', hintTxt: ''},
        email: {type: inputTypes.email, txt: 'Email Address', value: '', required: true, warn: '', hintTxt: ''},
        pass: {type: inputTypes.password, txt: 'Password', value: '', required: true, warn: '', hintTxt: ''},
        gender: {type: inputTypes.select, txt: 'Gender', value: '', required: true, warn: '', hintTxt: '', opt: genderTypes},
        dob: {type: inputTypes.date, txt: 'Date of Birth', value: '', required: true, warn: '', hintTxt: ''},
        cor: {type: inputTypes.select, txt: 'Country of Residence', value: '', required: true, warn: '', hintTxt: '', opt: countryTypes}
      },
      registerStatus: {value: registerConst.registerResponse.errDefault, warn: '', redirect: false}
    }
  }

  onFocusHideStatusWarn(e) {
    e.preventDefault();
    if(this.state.registerStatus.warn === true) {
      this.setState({
        registerStatus: {
          ...this.state.registerStatus,
          warn: ''
        }
      })
    }
  }

  onChangeElement(e) {
    const state = {
      inputs: {
        ...this.state.inputs
      }
    };
    state.inputs[e.target.id] = {
      ...this.state.inputs[e.target.id],
      value: e.target.value,
      warn: ''
    };
    this.setState(state);
  }

  checkFieldsValidity() {
    let isValid = true;
    Object.keys(this.state.inputs).forEach((id) => {
      if(this.state.inputs[id].required !== true) return;
      debugger;
      const state = {
        inputs: {
          ...this.state.inputs
        }
      };
      state.inputs[id] = {
        ...this.state.inputs[id],
        warn: !this.state.inputs[id].value
      };
      this.setState(state);
      if(!this.state.inputs[id].value) isValid = false;

      debugLogVar(`${id}: ${this.state.inputs[id].value}`);
    });
    return isValid;
  };

  onRegister(e) {
    e.preventDefault();

    if (!this.checkFieldsValidity()) return;

    fetchPostJsonResponse(registerConst.registerUrl, {
      'username': this.state.inputs.email.value,
      'password': this.state.inputs.pass.value,
      'type': registerConst.registerRequest.direct
    }).then((json) => {

      return false;
    }).then((isRedirect) => {

      this.setState({
        registerStatus: {
          ...this.state.registerStatus,
          redirect: isRedirect
        }
      });

    }).catch((error) => {
      this.setState({
        registerStatus: {
          ...this.state.registerStatus,
          warn: true,
          value: error.message
        }
      });
    });

  }

  render() {
    return (
      <React.Fragment>
        <FluidRowTitle title="Your Account" />
        <section className="container">
          <Row className="justify-content-center container-title pt-3 pb-3">
            <Col md={8} className="theme-blue">
              <h6>Creating a new account</h6>
            </Col>
          </Row>
          <Row className="justify-content-center pb-5">
            <Col md={8}>
              <Form className="container-block p-4 rounded-border" onSubmit={this.onRegister} onFocus={this.onFocusHideStatusWarn}>
                <FormGroup row className="justify-content-center">
                  <Col>
                    <h4>Your Personal Details</h4>
                  </Col>
                </FormGroup>
                {Object.keys(this.state.inputs).map((id, index) => (
                  <React.Fragment key={id}>
                    <FormGroup row>
                      <Label for={id} md={4} >{this.state.inputs[id].txt}{this.state.inputs[id].required && <span className="text-danger font-weight-bold">*</span>}</Label>
                      <Col md={8}>
                        <FormDynamicInput key={index} type={this.state.inputs[id].type} id={id} options={this.state.inputs[id].opt || []} value={this.state.inputs[id].value} onChange={this.onChangeElement} warn={this.state.inputs[id].warn}/>
                      </Col>
                    </FormGroup>
                    <FormRowErrorHint warn={this.state.inputs[id].warn}>{this.state.inputs[id].hintTxt}</FormRowErrorHint>
                  </React.Fragment>
                ))}
                <FormRowErrorHint warn={this.state.registerStatus.warn}>{this.state.registerStatus.value}</FormRowErrorHint>
                <FormGroup row className="justify-content-center pt-md-3">
                  <Col md={6}>
                    <button className="btn fluid-btn theme-blue">Complete Registration</button>
                  </Col>
                </FormGroup>
              </Form>
            </Col>
          </Row>
        </section>
      </React.Fragment>
    );
  }
}

export default Register;
