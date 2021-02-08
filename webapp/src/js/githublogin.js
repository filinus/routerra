import * as randomstring from "randomstring";
import querystring from "querystring";
import loginTab from "./openWindow";

const GITHUB_CLIENTID = process.env.REACT_APP_GITHUB_CLIENTID;
// const GITHUB_CLIENT_SECRET = process.env.REACT_APP_GITHUB_CLIENT_SECRET; FIXME: should have own server
const BASE_URL = process.env.PUBLIC_URL || process.env.REACT_APP_PUBLIC_URL;

const callBackUrl = BASE_URL + '/loginsuccess.html';
//https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/#web-application-flow
//https://github.com/settings/applications/956071

const GITHUB_AUTHORIZE_URL = "https://github.com/login/oauth/authorize"
//const GITHUB_TOKEN_URL = "https://github.com/login/oauth/access_token"
//const GITHUB_API_USER_URL = "https://api.github.com/user"

function githubUser() {
    return new Promise((resolve, reject) => {
        const nonce = randomstring.generate()
        const loginParams = {
            client_id: GITHUB_CLIENTID,
            redirect_uri: callBackUrl,
            scope: "user:email",
            state: nonce // 32 of [0-9 a-z A-Z]  https://www.npmjs.com/package/randomstring
        };
        const loginUrl = GITHUB_AUTHORIZE_URL + '?' + querystring.encode(loginParams);
        //console.log("login url: ", loginUrl);
        try {
            const msg = loginTab(loginUrl);
            msg.then(payload => {
                console.debug("received payload:", payload);
                const query = ("" + payload.search).replace(/\?/g, '');
                const parsedQuery = querystring.decode(query);
                const {state, code} = parsedQuery;
                if (nonce !== state) {
                    return reject("unknown nonce/state:", state)
                } else if (!code) {
                    console.error("code not returned from github callback, check payload.search", parsedQuery)
                    return reject("code not returned from github callback, check payload.search " + payload.search)
                }
                return resolve({user: "kinda user"}); // FIXME: code > access_token > email by api
            });
        } catch (e) {
            reject("" + e)
        }
    });
}

export default githubUser;