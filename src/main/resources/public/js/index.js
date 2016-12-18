function getPictureWithURL() {
    var data = {};

    data["url"] = $("#url").val();
    send("/picture", "PUT", data);
    
}





function getAll() {
    send("/readAll", "GET");
}

function remove(id) {
    var jsonData = getJson(id);
    send("/remove", "POST", jsonData);
}


function send(url, type, jsonData) {

    $.ajax({

        url: url,
        type: type,
        contentType: 'application/json',
        data: JSON.stringify(jsonData),
        success: function (data) {

            view(JSON.parse(data));

        },
        error: function (x) {
            alert("подождите загружается, перезагрузите страницу");

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
        output += "<a  href='http://localhost:4567/getPicture/" + val.loadpath + "'  id=\"url-" + val.id + "\" value=\"" + val.url + "\" >" + val.url + "</a>";
        output += "</td>";

        output += "<td>";
        output += "<input type=\"text\" name=\"countLike\" id=\"countLike-" + val.id + "\" value=\"" + val.countLike + "\" readonly />";
        output += "</td>";


      

        output += "</form> " +
            "</tr>";


        $("#pictureT").append(output);

    });
}