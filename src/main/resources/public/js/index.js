$(function () {
    $('#my_form').on('submit', function (e) {
        e.preventDefault();
        var $that = $(this),
            formData = new FormData($that.get(0)); // создаем новый экземпляр объекта и передаем ему нашу форму (*)
        $.ajax({
            url: "/file",
            type: "POST",
            contentType: false, // важно - убираем форматирование данных по умолчанию
            processData: false, // важно - убираем преобразование строк по умолчанию
            data: formData,
            dataType: 'json',
            success: function (json) {

                getAll()
            },
            error(){

                getAll()
            }
        });
    });
});

function getAll() {
    send("/readAll", "POST");
}

function remove(id) {
    var jsonData = getJson(id);
    send("/remove", "POST", jsonData);
}


function send(url, type, jsonData) {
    $.ajax({

        url: url,
        type: type,
        contentType: 'application/text',
        data: jsonData,
        success: function (data) {

            view(JSON.parse(data));

        },
        error: function (x) {
            alert('error', arguments);
        }

    });
    return false;
}


function view(data) {
    $(".data").remove();
    $.each(data, function (key, val) {
        var output = "";
        output = "<tr class='data'>" +
            "<form id=\"form-" + val.id + "\">";

        output += "<td>";
        output += "<input type=\"text\" name=\"id\" id=\"id-" + val.id + "\" value=\"" + val.id + "\" readonly />";
        output += "</td>";

        output += "<td>";
        output += "<a  href='http://localhost:4567/file/" + val.name + "'  id=\"name-" + val.id + "\" value=\"" + val.name + "\" >" + val.name + "</a>";
        output += "</td>";

        output += "<td>";
        output += "<input type=\"text\" name=\"loadpath\" id=\"loadpath-" + +"\" value=\"заглушка" + +"\" readonly/>";
        output += "</td>";

        output += "<td>" +

            "<input type=\"button\" value=\"Удалить\" class=\"deleteButton btn\" onclick=\"remove(" + val.id + ")\">" +

            "</td>";

        output += "</form> " +
            "</tr>";


        $("#userT").append(output);

    });
}