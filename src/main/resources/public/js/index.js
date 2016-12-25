function getPictureWithURL() {
    var data = {};

    data["url"] = $("#url").val();
    send("/picture", "PUT", data);

}

function getrequest() {

    send("/start", "GET");

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

/*

{
    "user"
:
    {
        "id"
    :
        "2999480870", "profile_picture"
    :
        "https://scontent.cdninstagram.com/t51.2885-19/s150x150/14369117_341042442906745_1085733992_a.jpg", "bio"
    :
        "", "username"
    :
        "sav4nn4", "full_name"
    :
        "\u0410\u043d\u043d\u0430 \u0421\u0430\u0432\u0438\u043d\u043e\u0432\u0430", "website"
    :
        ""
    }
,
    "access_token"
:
    "2999480870.43f2b9f.d873416ad8ab430bbf4a8b82597a6cd7"
}
*/



 
