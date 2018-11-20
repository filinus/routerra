import * as actionTypes from './userActionTypes';

export const STATE_KEY = 'currentUser';

export const initialState = {
    isAuthenticated: false,
    user: {}
};

export default function userReducer(state = initialState, action = {}) {
    const {type, payload} = action;

    switch(type) {
        case actionTypes.INJECT:
            return {
                ...state,
                isAuthenticated: true,
                user: payload.user
            };

        case actionTypes.LOGOUT:
            return {
                ...initialState
            };

        default:
            return state;
    }
};