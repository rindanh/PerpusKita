const api = require('express').Router();

const bookAPI = require('./bookAPI');


api.use(bookAPI);

module.exports = api;