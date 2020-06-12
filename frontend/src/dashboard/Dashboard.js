import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import {connect} from 'react-redux';

import {userActions} from '../_actions';

class Dashboard extends Component {
    componentDidMount() {
        this.props.dispatch(userActions.getAll());
    }

    render() {
        const {user, users} = this.props;

        return (
            <div className="col-md-6 col-md-offset-3">
                <h1>Hi {user.firstname + " " + user.lastname}!</h1>
                <p>You're logged in with React & JWT!!</p>
                <h3>Users from secure api end point:</h3>
                {users.loading && <em>Loading users...</em>}
                {users.error && <span className="text-danger">ERROR: {users.error}</span>}
                {users.status && <span className="text-danger">STATUS: {users.status}</span>}
                {/*{users.items &&*/}
                {/*<ul>*/}
                {/*    {users.items.map((users, index) =>*/}
                {/*        <li key={users.id}>*/}
                {/*            {users.firstName + ' ' + users.lastName}*/}
                {/*        </li>*/}
                {/*    )}*/}
                {/*</ul>*/}
                {/*}*/}
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
    // const {items} = state.users.items;
    return {
        user,
        users,
        // items
    };
}

const connectedDashboard = connect(mapStateToProps)(Dashboard);
export {connectedDashboard as Dashboard};