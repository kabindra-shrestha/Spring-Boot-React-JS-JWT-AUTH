/*export const userService = {
    fetchUser
};*/

/*function logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('user');
}*/

/*
function fetchUser() {
    const requestOptions = {
        method: 'GET',
        headers: authHeader()
    };

    return dispatch => {
        dispatch(userActions.fetchUserPending());
        fetch(process.env.REACT_APP_API_ENDPOINT + `/user/info`, requestOptions)
            .then(response => response.json())
            .then(response => {
                if (!response.ok) {
                    if (response.status === 401) {
                        // auto logout if 401 response returned from api
                        logout();
                        window.location.reload(true);
                    }

                    const error = (response && response.message) || response.statusText;
                    return Promise.reject(error);
                }

                if (response.error) {
                    throw(response.error);
                }
                dispatch(userActions.fetchUserSuccess(response.users));
                return response.users;
            })
            .catch(error => {
                dispatch(userActions.fetchUserError(error));
            })
    }
}*/
