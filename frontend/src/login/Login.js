import React, {Component} from 'react';
import UtilsToast from "../utils/UtilsToast";
import {Button, Card, Col, Container, Form, Row} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSave, faUser} from '@fortawesome/free-solid-svg-icons';
import {connect} from "react-redux";
import {loginActions} from "../_actions/login.actions";

class Login extends Component {

    constructor(props) {
        super(props);

        // reset login status
        this.props.dispatch(loginActions.logout());

        this.state = {
            username: '',
            password: '',
            submitted: false
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(e) {
        const {name, value} = e.target;
        this.setState({[name]: value});
    }

    handleSubmit(e) {
        e.preventDefault();

        this.setState({submitted: true});
        const {username, password} = this.state;
        const {dispatch} = this.props;
        if (username && password) {
            dispatch(loginActions.login(username, password));
        }
    }

    render() {
        const {loggingIn} = this.props;
        const {username, password, submitted} = this.state;

        return (
            <Container>
                <Row>
                    <Col lg={12}>
                        <div>
                            <div style={{"display": this.state.show ? "block" : "none"}}>
                                <UtilsToast show={this.state.show}
                                            message={this.state.message}
                                            type={this.state.status ? "success" : "error"}/>
                            </div>
                            <Card>
                                <Card.Header><FontAwesomeIcon
                                    icon={faUser}/> Login</Card.Header>
                                <Form id="userFormId" onSubmit={this.handleSubmit}>
                                    <Card.Body>
                                        <Form.Row>
                                            <Form.Group as={Col} controlId="formGridUsername"
                                                        className={(submitted && !username ? ' has-error' : '')}>
                                                <Form.Label>Username</Form.Label>
                                                <Form.Control
                                                    required autoComplete="off"
                                                    type="text" name="username"
                                                    value={username}
                                                    onChange={this.handleChange}
                                                    className="form-control"
                                                    placeholder="Enter Username"/>
                                                {submitted && !username &&
                                                <Form.Text>Username is required</Form.Text>
                                                }
                                            </Form.Group>
                                        </Form.Row>
                                        <Form.Row>
                                            <Form.Group as={Col} controlId="formGridPassword"
                                                        className={(submitted && !password ? ' has-error' : '')}>
                                                <Form.Label>Password</Form.Label>
                                                <Form.Control
                                                    required autoComplete="off"
                                                    type="password" name="password"
                                                    value={password}
                                                    onChange={this.handleChange}
                                                    className=""
                                                    placeholder="Enter Password"/>
                                                {submitted && !password &&
                                                <Form.Text>Password is required</Form.Text>
                                                }
                                            </Form.Group>
                                        </Form.Row>
                                    </Card.Body>
                                    <Card.Footer style={{"textAlign": "right"}}>
                                        <Button size="sm" variant="success" type="submit">
                                            <FontAwesomeIcon icon={faSave}/> Login
                                        </Button>
                                        {loggingIn &&
                                        <img
                                            src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA=="
                                            width="16" height="16" alt="loading"/>
                                        }
                                    </Card.Footer>
                                </Form>
                            </Card>
                        </div>
                    </Col>
                </Row>
            </Container>
        );
    }
}

function mapStateToProps(state) {
    const {loggingIn} = state.authentication;
    return {
        loggingIn
    };
}

const connectedLoginPage = connect(mapStateToProps)(Login);
export {connectedLoginPage as Login};