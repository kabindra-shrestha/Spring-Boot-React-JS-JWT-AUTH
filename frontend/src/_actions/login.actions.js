import {loginConstants} from '../_constants';
import {loginService} from '../_services';
import {alertActions} from './';
import {history} from '../_helpers';

export const loginActions = {
    login,
    logout
};

function login(username, password) {
    return dispatch => {
        dispatch(request({username}));

        loginService.login(username, password)
            .then(
                user => {
                    dispatch(success(user));
                    history.push('/dashboard');
                },
                error => {
                    dispatch(failure(error));
                    dispatch(alertActions.error(error));
                }
            );
    };

    function request(user) {
        return {type: loginConstants.LOGIN_REQUEST, user}
    }

    function success(user) {
        return {type: loginConstants.LOGIN_SUCCESS, user}
    }

    function failure(error) {
        return {type: loginConstants.LOGIN_FAILURE, error}
    }
}

function logout() {
    loginService.logout();
    return {type: loginConstants.LOGOUT};
}