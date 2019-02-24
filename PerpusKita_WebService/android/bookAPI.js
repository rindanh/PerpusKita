const router = require('express').Router();

const Book = require('../models/book');
const Library = require('../models/library');

router.get('/', (req, res) => {
    res.json({
        success: true,
        message: "Android service available"
    })
});

router.get('/book', (req, res) => {
    let token = req.query.token;

    if (token) {
        Book.findOne({ where: {token: token} })
        .then((book) => {
            Library.findOne({ where: {id: book.libraryId}})
            .then((library) => {
                res.json({
                    success: true,
                    data: {
                        name: book.name,
                        library: library.name
                    }
                });
            }).catch((err) => {
                res.json({
                    success: false,
                    message: "Event not found"
                }); 
            });
        }).catch((err) => {
            res.json({
                success: false,
                message: "Guest not found"
            }); 
        });
    } else {
        res.json({
            success: false,
            message: "Guest token not given"
        });
    }
});

router.post('/book', (req, res) => {
    let token = req.body.token;

    if (token) {
        Book.findOne({ where: {token: token} })
        .then((book) => {
            if (guest.attended) {
                res.json({
                    success: false,
                    message: `Error : ${book.name} already attended`
                });
            } else {
                book.update({
                    attended: true
                }).then((guest) => {
                    res.send({
                        success: true,
                        message: `Welcome ${guest.name}`
                    });
                }).catch((err) => {
                    res.send({
                        success: false,
                        message: "Failed to update guest"
                    });
                });
            }
        }).catch((err) => {
            res.json({
                success: false,
                message: "Guest not found"
            }); 
        });
    } else {
        res.json({
            success: false,
            message: "Guest token not given"
        });
    }
});

module.exports = router;