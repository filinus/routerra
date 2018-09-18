import devices from './devices.json';
import fleets from './fleets.json';
export default {
    get: jest.fn((url) => {
        console.debug("mocking response from "+url);
        var data = {};
        if (url.indexOf('/devices')) {
            data = devices;
        } else if (url.indexOf('/fleets')) {
            data = fleets;
        } else {
            data = { content: [] }
        }

        return Promise.resolve({
            status: 200,
            data: data
        })
    })
}