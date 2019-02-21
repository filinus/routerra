import {combineReducers} from 'redux';

import userReducer from "./userReducer";
import deviceReducer from "./deviceReducer";

export default combineReducers({
    user: userReducer,
    device: deviceReducer
});