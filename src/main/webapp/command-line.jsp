<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>Online Command - Remote Docker Container</title>

   <link rel="stylesheet" type="text/css" href="./assets/style.css">
   <link rel="stylesheet" type="text/css" href="./assets/vendor/bootstrap/css/bootstrap.min.css">
   <script src="./assets/vendor/bootstrap/js/bootstrap.bundle.js"></script>
   <script src="./assets/vendor/jquery/jquery-3.5.1.min.js"></script>
</head>

<body>
<div class="container-fluid">
   <!--Title bar-->
   <div class="row mt-5">
      <div class="col">
         <div class="title-bar text-center">${username}</div>
      </div>
   </div>
   <!--Text area showing command line-->
   <div class="row">
      <div class="col">
         <textarea id="cmd-textarea" readonly>[${username}@${port} ~]# </textarea>
      </div>
   </div>
   <!--Input command-->
   <div class="row mt-2">
      <div class="col">
         <div class="input-group mb-3">
            <div class="input-group-prepend">
               <span class="input-group-text" id="cmd-input-icon">[${username}@${port} ~]# </span>
            </div>
            <input type="text" id="cmd-input" name="cmd-input" class="form-control" aria-label="Username" aria-describedby="cmd-input-icon" placeholder="Type your command and press 'Enter' to execute">
         </div>
      </div>
   </div>
</div>
</body>

<script>
  function addTextToCmdTextArea(text) {
    let cmdTextArea = $('#cmd-textarea');

    cmdTextArea.val(cmdTextArea.val() + text);
    if (cmdTextArea.length) { //scroll to bottom if has text
      cmdTextArea.scrollTop(cmdTextArea[0].scrollHeight - cmdTextArea.height());
    }
  }

  let prefix = '[${username}@${port} ~]# ';
  $('#cmd-input').keyup(function (e) {
    if (e.which == 13) {    // ENTER key pressed
      addTextToCmdTextArea($('#cmd-input').val() + '\n');

      $.ajax({
        url: '/ssh',
        method: 'GET',
        cache: false,
        async: false,
        data: {
          'cmd': $('#cmd-input').val()
        },
        success: function (data) {
          addTextToCmdTextArea(data);
        }
      });

      addTextToCmdTextArea(prefix);
      $('#cmd-input').val('');
    }
  });
</script>
</html>