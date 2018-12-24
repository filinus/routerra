import * as ActionTypes from './allActionTypes';

export function injectUser(user = {}) {
    return {
        type: ActionTypes.INJECT_USER,
        payload: {
            user:user
        }
    };
}

export function logoutUser() {
    return {
        type: ActionTypes.LOGOUT_USER
    }
}
