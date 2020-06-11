import React, {Component} from 'react';
import {Route, Router} from "react-router-dom";
import {connect} from "react-redux";
import {Switch} from "react-bootstrap";

import {history} from '../_helpers';
import {alertActions} from '../_actions';
import {Login} from "../login/Login";
import Dashboard from "../dashboard/Dashboard";
import {PrivateRoute} from "../_components";
import Welcome from "../main/Welcome";

import './App.css';

class App extends Component {
    constructor(props) {
        super(props);

        const {dispatch} = this.props;
        history.listen((location, action) => {
            // clear alert on location change
            dispatch(alertActions.clear());
        });
    }

    render() {
        const {alert} = this.props;
        const heading = "Welcome To Spring Boot and React JS with JWT Auth";
        const quote = "This project includes simple spring boot application with spring security and react js as frontend for authentication with JWT.";
        const footer = "Kabindra Shrestha";

        return (
            <div>
                {alert.message &&
                <div className={`alert ${alert.type}`}>{alert.message}</div>
                }
                <Router history={history}>
                    <Switch>
                        <PrivateRoute path="/" exact
                                      component={() => <Welcome heading={heading} quote={quote} footer={footer}/>}/>
                        <Route path="/login/admin" exact component={Login}/>
                        <PrivateRoute path="/dashboard" exact component={Dashboard}/>
                    </Switch>
                </Router>
            </div>
        );
    }
}

function mapStateToProps(state) {
    const {alert} = state;
    return {
        alert
    };
}

const connectedApp = connect(mapStateToProps)(App);
export {connectedApp as App};