import React from 'react'
import {Input} from 'reactstrap'
import {inputTypes} from '../../constants/inputTypes'
import PropTypes from 'prop-types'

export const FormDynamicInput = ({warn, type, options, ...rest}) => {
  const inputValidity = warn===true ? 'is-invalid' : warn===false ? 'is-valid' : '';

  if(type === inputTypes.select) {
    return (
      <Input {...rest} type={type} className={inputValidity} >
        {options && options.map((el, index) => (
          <option value={el.value} key={index}>{el.name}</option>
        ))}
      </Input>
    )
  }

  return (
    <Input {...rest}  type={type} className={inputValidity} />
  );
};

FormDynamicInput.propTypes = {
  warn: PropTypes.oneOfType([
    PropTypes.string,
    PropTypes.bool
  ]),
  type: PropTypes.string.isRequired,
  options: PropTypes.array
};