$(document).ready(() => {

    $.ajax({
        url: "/api/admin/library/list",
        method: "GET",
        dataType: "json",
        headers: {
            "Authorization": getCookie("jwtToken")
        },
        success: (result) => {
            if (result.success) {
                $(result.data).each((_, data) => {
                    let option = document.createElement("option");
                    option.setAttribute("value", data.id);
                    let name = data.name;
                    option.append(name);

                    $('#book-library').append(option);
                });
            }
        }
    });

});

$('#book-form').on("submit", (e) => {
    e.preventDefault();
    let name = $('#book-name').val();
    let author = $('#book-author').val();
    let libraryId = parseInt($('#book-library option:selected').val());

    if (libraryId !== -1) {
        let body = {
            name: name,
            author: author,
            libraryId: libraryId
        }

        $.ajax({
            url: "/api/admin/book/create",
            method: "POST",
            dataType: "json",
            headers: {
                "Authorization": getCookie("jwtToken")
            },
            data: body,
            success: (result) => {
                console.log(result);
                if (result.success) {
                    var message = `book ${name} is successfully created`;
                } else {
                    var message = `Failed to create book ${name}`;
                }

                alert(message);
            }
        });
    } 
});


function getCookie(cookiename) {
    var cookiestring = RegExp(""+cookiename+"[^;]+").exec(document.cookie);

    return decodeURIComponent(!!cookiestring ? cookiestring.toString().replace(/^[^=]+./,"") : "");
}