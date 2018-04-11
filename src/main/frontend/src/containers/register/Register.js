import React, {Component} from 'react'
import {Row, Col, Form, FormGroup, InputGroup, InputGroupAddon, FormFeedback, Label} from 'reactstrap'
import {Redirect, Link} from 'react-router-dom'
//import PropTypes from 'prop-types'

import './register.css'
import {FluidRowTitle} from '../../components/fluidRowTitle/FluidRowTitle'
import {FormDynamicInput} from "../../components/formDynamicInput/FormDynamicInput"
import {FormRowErrorHint} from "../../components/formRowErrorHint/FormRowErrorHint";
import {countryTypes} from '../../constants/countryTypes'
import {genderTypes} from "../../constants/genderTypes";
import {fetchPostJsonResponse, appGlobal} from "../../constants/appGlobal";
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
        fName: {type: appGlobal.inputTypes.text, txt: 'First Name', value: '', required: true, warn: '', hintTxt: "First name mustn't be empty"},
        lName: {type: appGlobal.inputTypes.text, txt: 'Last Name', value: '', required: true, warn: '', hintTxt: "Last name mustn't be empty"},
        email: {type: appGlobal.inputTypes.email, txt: 'Email Address', value: '', required: true, warn: '', hintTxt: "Email mustn't be empty"},
        pass: {type: appGlobal.inputTypes.password, txt: 'Password', value: '', required: true, warn: '', hintTxt: "Password mustn't be empty"},
        gender: {type: appGlobal.inputTypes.select, txt: 'Gender', value: '', required: true, warn: '', hintTxt: "Choose your gender", opt: genderTypes},
        dob: {type: appGlobal.inputTypes.date, txt: 'Date of Birth', value: '', required: true, warn: '', hintTxt: "Choose your Date of Birth"},
        cor: {type: appGlobal.inputTypes.select, txt: 'Country of Residence', value: '', required: true, warn: '', hintTxt: "Choose your country of residence", opt: countryTypes}
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
    this.setState({
      inputs: {
        ...this.state.inputs,
        [e.target.id]: {
          ...this.state.inputs[e.target.id],
          value: e.target.value,
          warn: ''
        }
      }
    })
  }

  checkFieldsValidity() {
    let isValid = true;
    Object.keys(this.state.inputs).forEach((id) => {
      if(this.state.inputs[id].required !== true) return;
      this.setState((currentState) => ({
        inputs: {
          ...currentState.inputs,
          [id]: {
            ...currentState.inputs[id],
            warn: !currentState.inputs[id].value
          }
        }
      }));
      if(!this.state.inputs[id].value) isValid = false;
    });
    return isValid;
  };

  onRegister(e) {
    e.preventDefault();

    if (!this.checkFieldsValidity()) return;

    fetchPostJsonResponse(registerConst.registerUrl, {
      [registerConst.registerRequest.username]: this.state.inputs.email.value,
      [registerConst.registerRequest.password]: this.state.inputs.pass.value,
      [registerConst.registerRequest.type]: registerConst.registerRequest.directType
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
    if(this.state.registerStatus.redirect) return <Redirect to='/' />;

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
                <FormGroup row>
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
