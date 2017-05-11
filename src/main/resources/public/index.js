function getAll() {
    send("/readAll", "POST");
}


function remove(id) {
    var jsonData = getJson(id);
    send("/remove", "POST", jsonData);
}

var updateGlobalHuman = {id: null, name: null, phonenumber: null}

function updateName(argName) {
    updateGlobalHuman.name = argName;
}

function updatePhoneNumber(argPhoneNumber) {
    updateGlobalHuman.phonenumber = argPhoneNumber;
}


function update(id) {
    var jsonData = getJson(id);
    send("/update", "POST", jsonData);

}

function add() {
    var jsonData = getJson(-1);
    send("/add", "POST", jsonData)
}


function getJson(id) {
    if (id != -1) {
        var objectId = document.getElementById("id-" + id).getAttribute("value");
        var objectName = document.getElementById("name-" + id).getAttribute("value");
        var objectPhoneNumber = document.getElementById("phonenumber-" + id).getAttribute("value");
    }else objectId = -1;


    if (updateGlobalHuman.name != null) {
        objectName = updateGlobalHuman.name;
        updateGlobalHuman.name = null;
    }

    if (updateGlobalHuman.phonenumber != null) {
        objectPhoneNumber = updateGlobalHuman.phonenumber;
        updateGlobalHuman.phonenumber = null;
    }


    return "{\"id\": " + objectId + ", \"name\": \"" + objectName + "\", \"phonenumber\": \"" + objectPhoneNumber + "\"}";
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
        output += "<input type=\"text\" name=\"name\" id=\"name-" + val.id + "\" value=\"" + val.name + "\" onchange='updateName(this.value)'/>";
        output += "</td>";

        output += "<td>";
        output += "<input type=\"text\" name=\"phonenumber\" id=\"phonenumber-" + val.id + "\" value=\"" + val.phonenumber + "\" onchange='updatePhoneNumber(this.value)'/>";
        output += "</td>";

        output += "<td>" +
            "<input type=\"button\" value=\"Править\" class=\"editButton btn\"  onclick=\"update(" + val.id + ")\">" +
            "<br>" +
            "<input type=\"button\" value=\"Удалить\" class=\"deleteButton btn\" onclick=\"remove(" + val.id + ")\">" +

            "</td>";

        output += "</form> " +
            "</tr>";


        $("#userT").append(output);

    });
}