import React from 'react'
import {FormGroup, Col, FormFeedback} from 'reactstrap'
import PropTypes from 'prop-types'

export const FormRowErrorHint = ({warn, children}) => (
  warn === true ?
    <FormGroup row>
      <Col>
        <FormFeedback style={{display: 'block'}}>{children}</FormFeedback>
      </Col>
    </FormGroup>
    : null
);

FormRowErrorHint.propTypes = {
  warn: PropTypes.oneOfType([
    PropTypes.string,
    PropTypes.bool
  ]),
};