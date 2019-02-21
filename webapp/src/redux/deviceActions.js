import * as ActionTypes from './allActionTypes';

export const injectDeviceList = (devices = []) => {
    console.log("injecting devices", devices);
    return {
        type: ActionTypes.INJECT_DEVICES,
        payload: {
            devices:devices
        }
    };
};

export const resetDeviceList  = () => {
    return {
        type: ActionTypes.RESET_DEVICES
    }
};
