import React from 'react';

export const InnerHtml = (props) => (
    <div dangerouslySetInnerHTML={{ __html: props.include }}/>
);

export const InnerFormSvg = ({children}) => (
    <span className="input-group-text" dangerouslySetInnerHTML={{ __html: children }}/>
);