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
            //   alert("подождите загружается, перезагрузите страницу");

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
    "created_time": "1474273821", "images": {
        "thumbnail"
    :
        {
            "width"
        :
            150, "url"
        :
            "https:\/\/scontent.cdninstagram.com\/t51.2885-15\/s150x150\/e15\/14287995_1188564504522760_1926586016_n.jpg?ig_cache_key=MTM0MjYyODU4MjA2ODk2OTM1OA%3D%3D.2", "height"
        :
            150
        }
    ,
        "low_resolution"
    :
        {
            "width"
        :
            320, "url"
        :
            "https:\/\/scontent.cdninstagram.com\/t51.2885-15\/s320x320\/e15\/14287995_1188564504522760_1926586016_n.jpg?ig_cache_key=MTM0MjYyODU4MjA2ODk2OTM1OA%3D%3D.2", "height"
        :
            320
        }
    ,
        "standard_resolution"
    :
        {
            "width"
        :
            612, "url"
        :
            "https:\/\/scontent.cdninstagram.com\/t51.2885-15\/e15\/14287995_1188564504522760_1926586016_n.jpg?ig_cache_key=MTM0MjYyODU4MjA2ODk2OTM1OA%3D%3D.2", "height"
        :
            612
        }
    }
,
    "comments"
:
    {
        "count"
    :
        0
    }
,
    "users_in_photo"
:
    [], "user_has_liked"
:
    true, "link"
:
    "https:\/\/www.instagram.com\/p\/BKh-ZTzgd-O\/", "caption"
:
    {
        "created_time"
    :
        "1474273821", "from"
    :
        {
            "full_name"
        :
            "Анна Савинова", "profile_picture"
        :
            "https:\/\/scontent.cdninstagram.com\/t51.2885-19\/s150x150\/14369117_341042442906745_1085733992_a.jpg", "id"
        :
            "2999480870", "username"
        :
            "sav4nn4"
        }
    ,
        "text"
    :
        "Севастопольская набережная", "id"
    :
        "17842492660178673"
    }
,
    "type"
:
    "image", "tags"
:
    [], "filter"
:
    "Normal", "attribution"
:
    null, "location"
:
    null, "id"
:
    "1342628582068969358_2999480870", "user"
:
    {
        "full_name"
    :
        "Анна Савинова", "profile_picture"
    :
        "https:\/\/scontent.cdninstagram.com\/t51.2885-19\/s150x150\/14369117_341042442906745_1085733992_a.jpg", "id"
    :
        "2999480870", "username"
    :
        "sav4nn4"
    }
,
    "likes"
:
    {
        "count"
    :
        2
    }
}


*/
