import * as actionTypes from './allActionTypes';

//export const STATE_KEY = 'currentUser';

export const initialState = {
    devices: []
};

export default function deviceReducer(state = initialState, action = {}) {
    const {type, payload} = action;

    switch(type) {
        case actionTypes.INJECT_DEVICES:
            return {
                ...state,
                devices: payload.devices
            };

        case actionTypes.RESET_DEVICES:
            return {
                ...initialState
            };

        default:
            return state;
    }
};