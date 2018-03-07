import React from "react";
import { Row, Col, Button, Form, FormGroup, Label, Input, FormFeedback, InputGroupAddon, InputGroup } from 'reactstrap';
import './signIn.css';
import octicons from 'octicons';
import {InnerFormSvg} from '../../components/innerHtml/InnerHtml'

const SignIn = (props) => (
  <section className="container content">
    <Row className="justify-content-center">
      <Col className="col-md-auto box-shadow-element-no-border m-5">
        <Form className="m-3">
          <FormGroup row>
            <InputGroup>
              <InputGroupAddon addonType="prepend"><InnerFormSvg svg={octicons.mail.toSVG()} /></InputGroupAddon>
              <Input type="email" name="email" id="userEmail" placeholder="my-email@gmail.com" valid />
              <FormFeedback valid>Sweet! that name is available</FormFeedback>
            </InputGroup>
          </FormGroup>
          <FormGroup row>
            <InputGroup>
              <InputGroupAddon addonType="prepend"><InnerFormSvg svg={octicons.key.toSVG()} /></InputGroupAddon>
              <Input type="password" name="password" id="userPassword" placeholder="my-password123" valid />
              <FormFeedback valid>Sweet! that name is available</FormFeedback>
            </InputGroup>
          </FormGroup>
          <FormGroup className="text-center" check row>
              <Button color="info">Sign In</Button>
          </FormGroup>
        </Form>
      </Col>
    </Row>
  </section>
);

export default SignIn;