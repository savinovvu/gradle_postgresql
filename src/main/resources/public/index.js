$(function(){
    $('#my_form').on('submit', function(e){
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
            success: function(json){
                alert("gud");
                if(json){
                    $that.replaceWith(json);
                }
            },
            error(){
                alert("slyapa")
            }
        });
    });
});




    var upload = document.getElementById("upload");
    var uploaded = document.getElementById("fileformlabel");

    function getFileNames() {
        return Array.prototype.map.call(upload.files, function(file) {

            return file.name;
        });
    }

    upload.addEventListener("change", function() {
        var fileName = uploaded.innerHTML = getFileNames();
        send("/readAll", "POST", jsonData, fileName)
    });

    function sentFile() {
        send("/readAll", "POST");
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

    var input = $("#upload");
   /* var fd = new FormData;
    fd.append(input.prop('files')[0]);*/
    $.ajax({

        url: url,
        type: type,
        processData: false,
        contentType: false,
        dataType: 'json',

        data: "",
        success: function (data) {
            view(JSON.parse(data));
            alert(data);
        },

        error: function (x) {
            alert('error', arguments);


        }

    });
    return false;
}

/*
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
}*/

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