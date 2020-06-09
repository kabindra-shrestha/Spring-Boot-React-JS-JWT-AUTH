import React from 'react';

import {Nav, Navbar} from "react-bootstrap";
import {Link} from "react-router-dom";

export default function NavigationBar() {
    return (<Navbar bg="light" expand="lg">
        <Link to={""} className="navbar-brand">
            <img src="/favicon.ico"
                 width="25" height="25" alt="brand"/> Spring Boot and React JS App with JWT Auth
        </Link>
        <Navbar.Toggle aria-controls="basic-navbar-nav"/>
        <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="mr-auto">
            </Nav>
            <Link to={"/login/admin"} className="nav-link">Login</Link>
        </Navbar.Collapse>
    </Navbar>);
}