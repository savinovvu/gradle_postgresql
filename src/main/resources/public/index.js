function getAll() {
    send("/readAll", "POST");
}

function remove(id) {
    var jsonData = getJson(id);
    send("/remove", "POST", jsonData);
}

function getJson(id) {
    var objectId = document.getElementById("id-" + id).getAttribute("value");
    var objectName = document.getElementById("name-" + id).getAttribute("value");
    var objectPhoneNumber = document.getElementById("phonenumber-" + id).getAttribute("value");
    //return "{id: " + objectId + ", name: " + objectName + ", phonenumber: " + objectPhoneNumber + "}";
   return "{\"id\": " + objectId + ", \"name\": \"" + objectName + "\", \"phonenumber\": \"" + objectPhoneNumber + "\"}";
}

function send(url, type, jsonData) {
    $.ajax({

        url: url,
        type: type,
        contentType: 'application/text',
        data: jsonData,
        success: function (data) {

            // alert(data);
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
    var i = 0;
    $.each(data, function (key, val) {
        var output = "";
        output = "<tr class='data'>" +
            "<form id=\"form-" + i + "\">";

        output += "<td>";
        output += "<input type=\"text\" name=\"id\" id=\"id-" + i + "\" value=\"" + val.id + "\" readonly/>";
        output += "</td>";

        output += "<td>";
        output += "<input type=\"text\" name=\"name\" id=\"name-" + i + "\" value=\"" + val.name + "\" readonly/>";
        output += "</td>";

        output += "<td>";
        output += "<input type=\"text\" name=\"phonenumber\" id=\"phonenumber-" + i + "\" value=\"" + val.phonenumber + "\" readonly/>";
        output += "</td>";

        output += "<td>" +
            "<input type=\"button\" value=\"Править\" class=\"editButton btn\">" +
            "<br>" +
            "<input type=\"hidden\" name=\"action\" value=\"delete\">" +
            "<input type=\"hidden\" name=\"id\" value=\"\">" +
            "<input type=\"button\" value=\"Удалить\" class=\"deleteButton btn\" onclick=\"remove(" + i + ")\">" +

            "</td>";

        output += "</form> " +
            "</tr>";


        $("#userT").append(output);

        i++;
    });
}