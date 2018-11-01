import axios from 'axios';

async function getData (relativeUrl) {
    const config = {headers: {'Access-Control-Allow-Origin': '*'}};
    //const url = "http://routerra.aws.filin.us:8080/" + relativeUrl;
    //const url = "https://s3-us-west-1.amazonaws.com/routerra/"+relativeUrl+".json";
    const url = "https://filinus.github.io/routerra/mockjson/"+relativeUrl+".json";

    try {
        const response = await axios.get(url, config);

        if (response.status===200 && response.data && response.data.content) {
            console.log(url, response);
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