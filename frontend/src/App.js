import React from 'react';
import './App.css';
import {Col, Container, Row} from "react-bootstrap";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Welcome from "./components/Welcome";
import Login from "./components/login/Login";

export default function App() {

    const heading = "Welcome To Spring Boot and React JS with JWT Auth";
    const quote = "This project includes simple spring boot application with spring security and react js as frontend for authentication with JWT.";
    const footer = "Kabindra Shrestha";

    return (
        <Router>
            <Container>
                <Row>
                    <Col lg={12} className={"margin-top"}>
                        <Switch>
                            <Route path="/" exact
                                   component={() => <Welcome heading={heading} quote={quote} footer={footer}/>}/>
                            <Route path="/login/admin" exact component={Login}/>
                        </Switch>
                    </Col>
                </Row>
            </Container>
        </Router>
    );

}