import axios from 'axios';

async function getData (relativeUrl) {
    const config = {headers: {'Access-Control-Allow-Origin': '*'}};
    const hostname = window && window.location && window.location.hostname;

    const baseUrl = (hostname === "localhost") ? '' : process.env.PUBLIC_URL;

    const url = baseUrl+"/mockjson/"+relativeUrl+".json";

    try {
        const response = await axios.get(url, config);

        if (response.status===200 && response.data && response.data.content) {
            console.debug(url, response);
            return response.data;
        } else {
            console.error(url, response);
        }
    } catch (e) {
        console.error(url, e);
    }
    return null;
}

export default getData;