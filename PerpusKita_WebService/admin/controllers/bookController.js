const router = require('express').Router();
const fs = require('fs');
const zipFolder = require('zip-a-folder');

const Book = require('../../models/book');

router.post('/create', (req, res) => {
    let body = {
        name: req.body.name,
        author: req.body.author,
        libraryId: req.body.libraryId
    }

    Book.findOne({ where: { name: body.name,libraryId: body.libraryId}})
    .then((book) => {
        if (book) {
            return res.json({
                success: false,
                message: "User has already been created"
            });
        }

        Book.createBook(body, (err, data) => {
            if (err) {
                return res.json({
                    success: false,
                    message: err.message
                });
            }
    
            return res.json({
                success: true,
                message: "Book successfully created",
                data: data
            })
        });
    }).catch((err) => {
        return res.json({
            success: false,
            message: "Internal error"
        });
    });
});

router.post('/update', (req, res) => {
    let body = {
        id: req.body.id,
        name: req.body.name,
        author: req.body.author,
        libraryId: req.body.libraryId
    }

    Book.updateBook(body, (err, data) => {
        if (err) {
            return res.json({
                success: false,
                message: err.message
            });
        }

        return res.json({
            success: true,
            message: "Book data updated",
            data: data
        })
    }); 
});

router.post('/delete', (req, res) => {
    let body = {
        id: req.body.id
    }

    Book.deleteBook(body, (err) => {
        if (err) {
            return res.json({
                success: false,
                message: err.message
            });
        }

        return res.json({
            success: true,
            message: "book data deleted"
        });
    });
});

router.get('/list', (req, res) => {
    let libraryId = req.query.libraryId;

    if (!libraryId) {
        Book.findAll()
        .then((books) => {
            return res.json({
                success: true,
                data: books
            });
        }).catch((err) => {
            return res.json({
                success: false,
                message: err.message
            })
        });
    } else {
        Book.findAll({ where: {libraryId: libraryId}})
        .then((books) => {
            return res.json({
                success: true,
                data: books
            });
        }).catch((err) => {
            return res.json({
                success: false,
                message: err.message
            });
        });
    }
});

router.get('/download', (req, res) => {
    zipFolder.zipFolder(__dirname + '/../../files', __dirname + '/../zip/pdf.zip', (err) => {
        if (err) {
            return res.json({
                success: false,
                message: "Error : failed to zip file"
            });
        }

        return res.download(__dirname + '/../zip/pdf.zip');
    });
});

module.exports = router;