import axios from 'axios';

async function getData (relativeUrl) {
    const config = {headers: {'Access-Control-Allow-Origin': '*'}};
    const url = "http://localhost:8080/" + relativeUrl;
    try {
        const response = await axios.get("http://localhost:8080/" + relativeUrl, config);

        if (response.status===200 && response.data && response.data.content) {
            console.log(url, response);
            return response.data;
        } else {
            console.error(url, response);
            throw "request failed";
        }
    } catch (e) {
        console.error(url, e);
        return null;
    }
}

export default getData;