const router = require('express').Router();

const Library = require('../../models/library');

router.post('/create', (req, res) => {
    let body = {
        name: req.body.name,
        location: req.body.location
    }

    Library.create({
        name: body.name,
        location: body.location
    }).then((library) => {
        return res.json({
            success: true,
            message: "library data created",
            data: library
        });
    }).catch((err) => {
        return res.json({
            success: false,
            message: err.message
        })
    });
});

router.post('/update', (req, res) => {
    let body = {
        name: req.body.name,
        location: req.body.name,
        id: req.body.id
    }

    Library.findOne({ where: {id : body.id}})
    .then((library) => {
        if (!library) {
            return res.json({
                success: false,
                message: "library not found"
            });
        }

        Library.update({
            name: body.name,
            location: body.location
        }).then((library) => {
            return res.json({
                success: true,
                message: "library successfully updated",
                data: library
            });
        }).catch((err) => {
            return res.json({
                success: false,
                message: err.message
            });
        });
    }).catch((err) => {
        return res.json({
            success: false,
            message: err.message
        });
    }); 
});

router.post('/delete', (req, res) => {
    let body = {
        id: req.body.id
    }

    Library.findOne({ where: {id : body.id}})
    .then((library) => {
        if (!library) {
            return res.json({
                success: false,
                message: "library not found"
            })
        }

        Library.destroy().
        then(() => {
            return res.json({
                success: true,
                message: "library successfully deleted"
            });
        }).catch((err) => {
            return res.json({
                success: false,
                message: err.message
            });
        });
    }).catch((err) => {
        return res.json({
            success: false,
            message: err.message
        });
    }); 
});

router.get('/list', (req, res) => {
    Library.findAll()
    .then((libraries) => {
        return res.json({
            success: true,
            data: libraries
        })
    }).catch((err) => {
        return res.json({
            success: false,
            message: err.message
        });
    });
});

module.exports = router;