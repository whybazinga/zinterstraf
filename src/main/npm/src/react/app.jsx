import React from 'react';
import ReactDom from 'react-dom';


class App extends React.Component {
/*
    constructor(props) {
        super(props);
        this.state = {employees: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/employees'}).done(response => {
            this.setState({employees: response.entity._embedded.employees});
        });
    }
*/
    render() {
        return (
            <p>Hello World!</p>
        )
    }
}



ReactDom.render(
<App/>,
    document.getElementById('app')
);