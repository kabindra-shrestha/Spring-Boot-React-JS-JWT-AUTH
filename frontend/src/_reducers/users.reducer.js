import {userConstants} from '../_constants';

const initialState = {
    loading: false,
    error: '',
    usersStatus: false,
    usersMessage: '',
    usersData: {}
};

export function users(state = initialState, action) {
    switch (action.type) {
        case userConstants.GETALL_REQUEST:
            return {
                loading: true
            };
        case userConstants.GETALL_SUCCESS:
            return {
                usersStatus: action.users.status,
                usersMessage: action.users.message,
                usersData: action.users.users
            };
        case userConstants.GETALL_FAILURE:
            return {
                error: action.error
            };
        default:
            return state
    }
}