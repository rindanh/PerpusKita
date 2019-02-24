const Sequelize = require('sequelize');
const qr = require('qr-image');
const fs = require('fs');
const crypto = require('crypto');

const Library = require('./library')
const db = require('./db');

const Book = db.define('book', {
    id: { type: Sequelize.INTEGER, primaryKey: true, autoIncrement: true },
    name: { type: Sequelize.STRING, allowNull: false },
    libraryId: {type: Sequelize.INTEGER, allowNull: false},
    token: { type: Sequelize.STRING, allowNull: false},
    file: { type: Sequelize.STRING, allowNull: false },
    attended: { type: Sequelize.BOOLEAN, allowNull: false },
    author: {type: Sequelize.STRING, allowNull: false}
});

Library.hasMany(Book, { onDelete: 'restrict', onUpdate: 'cascade'});

function createQRCode(bookName, libraryId) {
    let tokenCode = crypto.randomBytes(32).toString("hex");

    let qrcode = qr.image(tokenCode, { type : "pdf"});
    let fileName = `${bookName}-${libraryId}.pdf`;

    qrcode.pipe(fs.createWriteStream(`files/${fileName}`));

    return {
        name: bookName,
        token: tokenCode,
        libraryId: libraryId,
        file: fileName,
        attended: false
    };
}

Book.createBook = (data, callback) => {
    let bookData = createQRCode(data.name, data.libraryId);

    Book.create({
        name: bookData.name,
        token: bookData.token,
        file: bookData.file,
        libraryId: bookData.libraryId,
        attended: bookData.attended,
        author: data.author
    }).then((book) => {
        return callback(null, book);
    }).catch((err) => {
        return callback(err);
    });
}

Book.updateBook = (data, callback) => {
    Book.findOne({
        where: { id: data.id }
    }).then((book) => {
        let fileName = `${book.name}-${book.libraryId}.pdf`;

        // delete file
        fs.unlinkSync(`files/${fileName}`);
        updateData = createQRCode(data.name, data.libraryId);

        book.update({
            name: updateData.name,
            libraryId: updateData.libraryId,
            token: updateData.token,
            file: updateData.file,
            attended: updateData.attended,
            author: data.author
        }).then((book) => {
            return callback(null, book);
        }).catch((err) => {
            return callback(err);
        });
    }).catch((err) => {
        return callback(err);
    });
}

Book.deleteBook = (data, callback) => {
    Book.findOne({
        where: {id: data.id}
    }).then((book) => {
        let fileName = `${book.name}-${book.libraryId}.pdf`;

        // delete file
        fs.unlinkSync(`files/${fileName}`);

        // delete from database
        book.destroy().then(() => {
            return callback(null);
        }).catch((err) => {
            return callback(err);
        });
    }).catch((err) => {
        return callback(err);
    }); 
}

module.exports = Book;