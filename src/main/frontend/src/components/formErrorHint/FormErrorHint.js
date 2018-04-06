import React from 'react'
import {FormFeedback} from 'reactstrap'
import PropTypes from 'prop-types'

export const FormErrorHint = ({warn, txt}) => (
  warn === true ? <FormFeedback style={{display: 'block'}}>{txt}</FormFeedback> : null
);

FormErrorHint.propTypes = {
  warn: PropTypes.oneOfType([
    PropTypes.string,
    PropTypes.bool
  ]),
  txt: PropTypes.string.isRequired
};