import React from 'react';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Welcome from "./components/Welcome";
import Login from "./components/login/Login";
import Dashboard from "./components/dashboard/Dashboard";

export default function App() {

    const heading = "Welcome To Spring Boot and React JS with JWT Auth";
    const quote = "This project includes simple spring boot application with spring security and react js as frontend for authentication with JWT.";
    const footer = "Kabindra Shrestha";

    return (
        <Router>
            <Switch>
                <Route path={process.env.PUBLIC_URL + '/'} exact
                       component={() => <Welcome heading={heading} quote={quote} footer={footer}/>}/>
                <Route path={process.env.PUBLIC_URL + '/login/admin'} exact component={Login}/>
                <Route path={process.env.PUBLIC_URL + '/dashboard'} exact component={Dashboard}/>
            </Switch>
        </Router>
    );

}