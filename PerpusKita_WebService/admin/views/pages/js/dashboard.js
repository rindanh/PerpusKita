$(document).ready(() => {
    $.ajax({
        url: "/api/admin/book/list",
        headers: {
            "Authorization": getCookie("jwtToken")
        },
        dataType: "json",
        type: "GET",
        success: (result) => {
            if (result.success) {
                $(result.data).each((_, data) => {
                    let row = $("<tr></tr>");
                    $("#body-table-book").append(row);

                    let id = document.createElement("td");
                    id.innerHTML = data.id;
                    let name = document.createElement("td");
                    name.innerHTML = data.name;
                    let author = document.createElement("td");
                    author.innerHTML = data.author;
                    let library = document.createElement("td");
                    library.innerHTML = data.libraryId;
                    let token = document.createElement("td");
                    token.innerHTML = data.token;


                    let file = document.createElement("td");
                    let fileLink = document.createElement("a");
                    fileLink.setAttribute("href", "/files/" + data.file);
                    fileLink.innerHTML = data.file;
                    file.append(fileLink);

                    let attended = document.createElement("td");
                    attended.innerHTML = data.attended;

                    let removeButton = document.createElement("button");
                    removeButton.classList.add("btn","btn-primary");
                    removeButton.val = data.id;
                    removeButton.innerHTML = "REMOVE";
                    removeButton.onclick = (e) => {
                        e.preventDefault();

                        $.ajax({
                            url: "/api/admin/book/delete",
                            method: "POST",
                            data: {
                                id: data.id
                            },
                            headers: {
                                "Authorization": getCookie("jwtToken")
                            },
                            dataType: "json",
                            success: (result) => {
                                if (result.success) {
                                    var message = `${data.name} is successfully deleted`;
                                    alert(message);
                                    location.reload();
                                } else {
                                    var message = `Failed to delete ${data.name}`;
                                    alert(message);
                                }
                            }
                        });
                    }

                    row.append(id, name, author, library, token, file, attended, removeButton);
                });
            }
        }
    });
    $.ajax({
        url: "/api/admin/library/list",
        headers: {
            "Authorization": getCookie("jwtToken")
        },
        dataType: "json",
        type: "GET",
        success: (result) => {
            if (result.success) {
                $(result.data).each((_, data) => {
                    let row = $("<tr></tr>");
                    $("#body-table-library").append(row);

                    let id = document.createElement("td");
                    id.innerHTML = data.id;
                    let name = document.createElement("td");
                    name.innerHTML = data.name;
                    let location = document.createElement("td");
                    location.innerHTML = data.location;

                    let removeButton = document.createElement("button");
                    removeButton.classList.add("btn","btn-primary");
                    removeButton.val = data.id;
                    removeButton.innerHTML = "REMOVE";
                    removeButton.onclick = (e) => {
                        e.preventDefault();

                        $.ajax({
                            url: "/api/admin/library/delete",
                            method: "POST",
                            data: {
                                id: data.id
                            },
                            headers: {
                                "Authorization": getCookie("jwtToken")
                            },  
                            dataType: "json",
                            success: (result) => {
                                console.log(result);
                                if (result.success) {
                                    var message = `${data.name} is successfully deleted`;
                                    alert(message);
                                    location.reload();
                                } else {
                                    var message = `Failed to delete ${data.name}`;
                                    alert(message);
                                }
                            }
                        });
                    }
                    
                    row.append(id, name, location, removeButton);
                });
            }
        }
    });
});

$('a#library-tab').on('click', () => {
    $('a#book-tab').removeClass("active");
    $('a#library-tab').addClass("active");
    $('#book').removeClass("active");
    $('#book').addClass("fade");
    $('#library').removeClass("fade");
    $('#library').addClass("active");
});

$('#book-tab').on('click', () => {
    $('a#library-tab').removeClass("active");
    $('a#book-tab').addClass("active");
    $('#library').removeClass("active");
    $('#library').addClass("fade");
    $('#book').removeClass("fade");
    $('#book').addClass("active");
});

$('#download-button').on('click', (e) => {
    e.preventDefault();

    window.open('/api/admin/book/download', '_blank');
});

$('#addbook-button').on('click', (e) => {
    e.preventDefault();

    window.open('/api/admin/book/download', '_blank');
});

function getCookie(cookiename) {
    var cookiestring = RegExp(""+cookiename+"[^;]+").exec(document.cookie);

    return decodeURIComponent(!!cookiestring ? cookiestring.toString().replace(/^[^=]+./,"") : "");
}
