
const functions = require('firebase-functions');
const admin = require('firebase-admin');

admin.initializeApp(functions.config().firebase);


exports.pushNotification = functions.database.ref('/messages/{pushId}').onWrite(( change,context) => {


var messageValue = change.after.val();
    var token = messageValue.token;
    var email = messageValue.email;
    console.log("This is it >>>"+token);


  // Create a notification
    const payload = {
        notification: {
            title: messageValue.name,
            body: messageValue.text,
            sound: "default"
        }
    };
  
  const promises = [];
  promises.push(admin.messaging().sendToDevice(token, payload));
  return Promise.all(promises);

//return admin.messaging().sentToDevice(token, payload).then(result => {
//console.log("Notification sent to "+email);
//});



});




