import React from 'react';
import {Col, Container, Jumbotron, Row} from "react-bootstrap";
import NavigationBar from "./NavigationBar";

export default function Welcome(props) {
    return (
        <div>
            <NavigationBar/>

            <Container>
                <Row>
                    <Col lg={12}>
                        <Jumbotron className="bg-light text-black-50 margin-top">
                            <h1>{props.heading}</h1>
                            <blockquote className="blockquote mb-0">
                                <p>
                                    {props.quote}
                                </p>
                                <footer className="blockquote-footer">
                                    {props.footer}
                                </footer>
                            </blockquote>
                        </Jumbotron>
                    </Col>
                </Row>
            </Container>
        </div>);
}