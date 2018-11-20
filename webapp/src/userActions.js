import * as ActionTypes from './userActionTypes';

export function injectUser(user = {}) {
    return {
        type: ActionTypes.INJECT,
        payload: {
            user
        }
    };
}

export function logoutUser() {
    return {
        type: ActionTypes.LOGOUT
    }
}
