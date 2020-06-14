import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import {connect} from 'react-redux';

import {userActions} from '../_actions';

class Dashboard extends Component {
    componentDidMount() {
        this.props.dispatch(userActions.getAll());
    }

    render() {
        const {user, users, usersData} = this.props;

        return (
            <div className="col-md-6 col-md-offset-3">
                <h1>Hi {user.firstname + " " + user.lastname}! From Authentication Redux</h1>
                <p>You're logged in with React & JWT!!</p>

                {users.loading && <em>Loading users...</em>}
                {users.error && <span className="text-danger">ERROR: {users.error}</span>}
                {users.usersStatus && <span className="text-danger">STATUS: {users.usersStatus}</span>}
                {users.usersMessage && <span className="text-danger">MESSAGE: {users.usersMessage}</span>}

                {usersData && <h1>Hi {user.firstname + " " + user.lastname}! From Users Redux</h1>}
                <p>
                    <Link to="">Logout</Link>
                </p>
            </div>
        );
    }
}

function mapStateToProps(state) {
    const {users, authentication} = state;
    const {user} = authentication;
    const {usersData} = users;
    return {
        user,
        users,
        usersData
    };
}

const connectedDashboard = connect(mapStateToProps)(Dashboard);
export {connectedDashboard as Dashboard};