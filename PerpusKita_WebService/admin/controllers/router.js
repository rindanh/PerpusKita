const api = require('express').Router();

const bookAPI = require('./bookController');
const libraryAPI = require('./libraryController');

const adminAuth = require('../../middlewares/adminAuth');

api.use(adminAuth);
api.use('/book', bookAPI);
api.use('/library', libraryAPI);

module.exports = api;