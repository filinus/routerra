import * as ActionTypes from './allActionTypes';

export function injectDeviceList(devices = []) {
    console.log("injecting devices", devices);
    return {
        type: ActionTypes.INJECT_DEVICES,
        payload: {
            devices:devices
        }
    };
}

export function resetDeviceList() {
    return {
        type: ActionTypes.RESET_DEVICES
    }
}
