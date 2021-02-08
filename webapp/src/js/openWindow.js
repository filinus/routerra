// From https://gist.github.com/gauravtiwari/2ae9f44aee281c759fe5a66d5c2721a2
// By https://gist.github.com/gauravtiwari

/* global window */

const loginTab = (myUrl) => {
    const windowArea = {
        width: Math.floor(window.outerWidth * 0.8),
        height: Math.floor(window.outerHeight * 0.5),
    };

    if (windowArea.width < 1000) { windowArea.width = 1000; }
    if (windowArea.height < 630) { windowArea.height = 630; }
    windowArea.left = Math.floor(window.screenX + ((window.outerWidth - windowArea.width) / 2));
    windowArea.top = Math.floor(window.screenY + ((window.outerHeight - windowArea.height) / 8));

    const sep = (myUrl.indexOf('?') !== -1) ? '&' : '?';
    const url = `${myUrl}${sep}`;
    const windowOptions =
        `toolbar=0,scrollbars=1,status=1,resizable=1,location=1,menuBar=0,
        width=${windowArea.width},height=${windowArea.height},
        left=${windowArea.left},top=${windowArea.top}`;

    const authWindow = window.open(url, '_blank', windowOptions);
    // Create IE + others compatible event handler
    const eventMethod = window.addEventListener ? 'addEventListener' : 'attachEvent';
    const eventer = window[eventMethod];
    const messageEvent = eventMethod === 'attachEvent' ? 'onmessage' : 'message';

    // Listen to message from child window
    const closeAuthWindow = () => {if (authWindow) try { authWindow.close(); } catch (e) {}}

    const authPromise = new Promise((resolve, reject) => {
        eventer(messageEvent, (msg) => {
            if (!~msg.origin.indexOf(`${window.location.protocol}//${window.location.host}`)) {
                closeAuthWindow();
                reject('Not allowed');
            }

            if (msg.data.payload) {
                try {
                    resolve(JSON.parse(msg.data.payload));
                } catch(e) {
                    resolve(msg.data.payload);
                } finally {
                    closeAuthWindow();
                }
            } else {
                closeAuthWindow();
                reject('Unauthorised');
            }
        }, false);
    });

    return authPromise;
};

export default loginTab;