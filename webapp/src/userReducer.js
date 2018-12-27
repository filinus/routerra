import * as actionTypes from './allActionTypes';

//export const STATE_KEY = 'currentUser';

export const initialState = {
    isAuthenticated: false,
    user: {}
};

export default function userReducer(state = initialState, action = {}) {
    const {type, payload} = action;

    switch(type) {
        case actionTypes.INJECT_USER:
            return {
                ...state,
                isAuthenticated: true,
                user: payload.user
            };

        case actionTypes.LOGOUT_USER:
            return {
                ...initialState
            };

        default:
            return state;
    }
};