const router = require('express').Router();
const authRouter = require('express').Router();

const login = require('./login');
const pageAuth = require('../../middlewares/pageAuth');

var pagesDirectory = __dirname + "/pages/";

router.get('/', (req, res) => {
    res.sendFile('login.html', { root: pagesDirectory });
});

router.use(login);
router.use(authRouter);
authRouter.use(pageAuth);

authRouter.get('/dashboard', (req, res) => {
    res.sendFile('dashboard.html', { root: pagesDirectory});
});

authRouter.get('/book', (req, res) => {
    res.sendFile('book.html', { root: pagesDirectory});
});

authRouter.get('/library', (req, res) => {
    res.sendFile('library.html', { root: pagesDirectory});
});

module.exports = router;