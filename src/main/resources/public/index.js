

     function login () {

        var jsonData = JSON.stringify({"id": 1, "name": 2, "number": 3});
        $.ajax({

         url: "/hello",


         type: 'POST',

         contentType: 'application/text',

         data: jsonData,
         success: function(data){

             alert(data);

         },

         error: function(x){
             alert('error', arguments);

         }

         });

        return false;



}
