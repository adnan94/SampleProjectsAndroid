const functions = require('firebase-functions');
const admin = require('firebase-admin');

admin.initializeApp(functions.config().firebase);

exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
                                              
                                               console.log("Hello from firebase!")
                                        
                                                var token = request.body.token;

                                                var email = "Oh my god";
                                                console.log("This is token log >>>"+token);
                                                
                                                
                                                // Create a notification
                                               const payload = {
                                                notification: {
                                                title: "Adnan Ahmed",
                                                body: "Ohgod",
                                                sound: "default"
                                                }
                                                };
                                                
                                                const promises = [];
                                                 promises.push(admin.messaging().sendToDevice(token, payload));
response.end();                                                
return Promise.all(promises);
 });
