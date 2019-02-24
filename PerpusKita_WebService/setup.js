const db = require('./models/db');
const dotenv = require('dotenv');

const User = require('./models/user');
const Library = require('./models/library');
const Book = require('./models/book');

const vars = dotenv.config();
if (vars.error) {
  throw vars.error
}

db.sync({ force: true }).then(() => {
    User.createUser({
        username: 'admin',
        password: 'satu',
        admin: true
    }, (err, token) => {
        if (err) {
            console.log(`Error : ${err}`);
        } else {
            console.log(`Token : ${token}`);
        }
    });
    User.createUser({
        username: 'android',
        password: 'galdin',
        admin: false
    }, (err, token) => {
        if (err) {
            console.log(`Error : ${err}`);
        } else {
            console.log(`Token : ${token}`);
        }
    });
    Library.createLibrary({
        name: "Perpus Pusat ITB",
        location: "di itb"
    }, (err, data) => {
        if (err) {
            console.log(`Error : ${err}`);
        } else {
            console.log(`Token : ${data.token}`);
        }
    });
    Book.createBook({
        name: "Buku Golok",
        author: "Thareq",
        libraryId: 1
    }, (err, data) => {
        if (err) {
            console.log(`Error : ${err}`);
        } else {
            console.log(`Token : ${data.token}`);
        }
    });
    Book.createBook({
        name: "Blumbayar",
        author: "Yasya",
        libraryId: 1
    }, (err, data) => {
        if (err) {
            console.log(`Error : ${err}`);
        } else {
            console.log(`Token : ${data.token}`);
        }
    });

});
