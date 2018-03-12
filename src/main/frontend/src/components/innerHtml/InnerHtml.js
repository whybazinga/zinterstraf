import React from 'react';

export const InnerHtml = (props) => (
    <div dangerouslySetInnerHTML={{ __html: props.include }}/>
);

export const InnerFormSvg = (props) => (
    <span className="input-group-text" dangerouslySetInnerHTML={{ __html: props.svg }}/>
);