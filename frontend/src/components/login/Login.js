import React, {Component} from 'react';
import UtilsToast from "../utils/UtilsToast";
import {Button, Card, Col, Form} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSave, faUser} from '@fortawesome/free-solid-svg-icons';

export default class Login extends Component {

    initialState = {
        username: '', password: ''
    };

    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.state = {
            show: false
        };
        this.submitUser = this.submitUser.bind(this)
    }

    userChange = event => {
        this.setState({
            [event.target.name]: event.target.value
        })
    };

    dashboard = () => {
        return this.props.history.push("/dashboard");
    };

    submitUser = event => {
        event.preventDefault();

        const user = {
            username: this.state.username,
            password: this.state.password
        };

        const headers = new Headers();
        headers.append('Content-Type', 'application/json');

        fetch(process.env.REACT_APP_API_ENDPOINT + "/api/login", {
            method: 'POST',
            body: JSON.stringify(user),
            headers
        }).then(response => response.json())
            .then((user) => {
                if (user.status) {
                    this.setState({"show": true, "status": user.status, "message": user.message});
                    setTimeout(() => this.setState({"show": false}), 3000);
                    setTimeout(() => this.dashboard(), 3000);
                } else {
                    this.setState({"show": true, "status": user.status, "message": user.message});
                    setTimeout(() => this.setState({"show": false}), 3000);
                }
            });

        this.setState(this.initialState);
    };

    render() {
        const {username, password} = this.state;

        return (
            <div>
                <div style={{"display": this.state.show ? "block" : "none"}}>
                    <UtilsToast show={this.state.show}
                                message={this.state.message}
                                type={this.state.status ? "success" : "error"}/>
                </div>
                <Card>
                    <Card.Header><FontAwesomeIcon
                        icon={faUser}/> Login</Card.Header>
                    <Form id="userFormId" onSubmit={this.submitUser}>
                        <Card.Body>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridUsername">
                                    <Form.Label>Username</Form.Label>
                                    <Form.Control
                                        required autoComplete="off"
                                        type="text" name="username"
                                        value={username}
                                        onChange={this.userChange}
                                        className=""
                                        placeholder="Enter Username"/>
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridPassword">
                                    <Form.Label>Password</Form.Label>
                                    <Form.Control
                                        required autoComplete="off"
                                        type="password" name="password"
                                        value={password}
                                        onChange={this.userChange}
                                        className=""
                                        placeholder="Enter Password"/>
                                </Form.Group>
                            </Form.Row>
                        </Card.Body>
                        <Card.Footer style={{"textAlign": "right"}}>
                            <Button size="sm" variant="success" type="submit">
                                <FontAwesomeIcon icon={faSave}/> Login
                            </Button>
                        </Card.Footer>
                    </Form>
                </Card>
            </div>
        );
    }
}