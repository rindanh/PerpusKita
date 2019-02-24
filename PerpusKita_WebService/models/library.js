const Sequelize = require('sequelize');
const db = require('./db');

const Library = db.define('library', {
    id: { type: Sequelize.INTEGER, primaryKey: true, autoIncrement: true },
    name: { type: Sequelize.STRING, allowNull: false },
    location: { type: Sequelize.STRING, allowNull: false }
});

Library.createLibrary = (data, callback) => {
    Library.create({
        name: data.name,
        location: data.location
    }).then((library) => {
        return callback(null, library);
    }).catch((err) => {
        return callback(err);
    });
}

module.exports = Library;