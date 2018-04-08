import React from 'react'
import {FormGroup, Col, FormFeedback} from 'reactstrap'
import PropTypes from 'prop-types'

export const FormRowErrorHint = ({warn, txt}) => (
  warn === true ?
    <FormGroup row>
      <Col>
        <FormFeedback style={{display: 'block'}}>{txt}</FormFeedback>
      </Col>
    </FormGroup>
    : null
);

FormRowErrorHint.propTypes = {
  warn: PropTypes.oneOfType([
    PropTypes.string,
    PropTypes.bool
  ]),
  txt: PropTypes.string.isRequired
};