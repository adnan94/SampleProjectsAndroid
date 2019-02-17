
const functions = require('firebase-functions');
const admin = require('firebase-admin');

admin.initializeApp(functions.config().firebase);


exports.pushNotification = functions.database.ref('/messages/{pushId}').onWrite(( change,context) => {



    console.log('Push notification event triggered');

    //  Grab the current value of what was written to the Realtime Database.
    var valueObject = change.after.val();

    if(valueObject.photoUrl !== null) {
      valueObject.photoUrl= "Sent you a photo!";
    }

  // Create a notification
    const payload = {
        notification: {
            title:valueObject.name,
            body: valueObject.text || valueObject.photoUrl,
            sound: "default"
        },
    };

  //Create an options object that contains the time to live for the notification and the priority
    const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24
    };


    return admin.messaging().sendToTopic("pushNotifications", payload, options);
});
