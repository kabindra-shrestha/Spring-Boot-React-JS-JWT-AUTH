import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import {connect} from 'react-redux';

import {userActions} from '../_actions';

class Dashboard extends Component {
    componentDidMount() {
        this.props.dispatch(userActions.getAll());
    }

    render() {
        const {users, items} = this.props;

        return (
            <div className="col-md-6 col-md-offset-3">
                <h1>Hi {users.loading}!</h1>
                <h1>Hi {items.status}!</h1>
                {/*<h1>Hi {items.users.firstname}!</h1>
                <p>You're logged in with React & JWT!!</p>
                <h3>Users from secure api end point:</h3>
                {users.loading && <em>Loading users...</em>}
                {users.error && <span className="text-danger">ERROR: {users.error}</span>}
                {users.status && <span className="text-danger">ERROR: {users.status}</span>}*/}
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
                    <Link to="/login">Logout</Link>
                </p>
            </div>
        );
    }
}

function mapStateToProps(state) {
    const {users} = state;
    const {items} = state.users.items;
    return {
        users,
        items
    };
}

const connectedDashboard = connect(mapStateToProps)(Dashboard);
export {connectedDashboard as Dashboard};