function login() {

    var jsonData = JSON.stringify({"id": 1, "name": 2, "number": 3});
    $.ajax({

        url: "/readAll",
        type: 'POST',
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
    alert(data.length);
    $(".data").remove();

    $.each(data, function (key, val) {
        var output = "";
        output = "<tr class='data'>";

        output += "<td>";
        output += val.id;
        output += "</td>";

        output += "<td>";
        output += val.name;
        output += "</td>";

        output += "<td>";
        output += val.phonenumber;
        output += "</td>";

        output += "<td>" +
            "<form>" +
            "<input type=\"button\" value=\"Править\" class=\"editButton\">" +
            "</form>" +
            "<br>" +
            "<form>" +
            "<input type=\"hidden\" name=\"action\" value=\"delete\">" +
            "<input type=\"hidden\" name=\"id\" value=\"\">" +
            "<input type=\"button\" value=\"Удалить\" class=\"deleteButton\">"+
        "</form>"+
        "</td>";

        output += "</tr>";


        $("#userT").append(output);


    });


    /*
     *




     </td>
     </tr>
     *
     *
     *
     * */


}