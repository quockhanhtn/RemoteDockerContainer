<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="en">

<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>Dashboard - Remote Docker Container</title>

   <!--Font awesome-->
   <link rel="stylesheet" href="./assets/vendor/fontawesome-free/css/all.min.css">
   <!--Bootstrap CSS-->
   <link rel="stylesheet" type="text/css" href="./assets/vendor/bootstrap/css/bootstrap.min.css">
   <!--Custom CSS-->
   <link rel="stylesheet" href="./assets/style.css">
   <!--jQuery-->
   <script src="./assets/vendor/jquery/jquery-3.5.1.min.js"></script>
</head>

<body>

<div class="container-fluid">
   <!--Title-->
   <div class="row my-3">
      <div class="col text-uppercase text-center">
         <h1>List container</h1>
      </div>
   </div>
   <div class="row my-3">
      <div class="col text-uppercase text-center">
         <div id="wait-noti" class="d-none alert alert-primary" role="alert">
            Please wait ...
         </div>
      </div>
   </div>
   <!--Table-->
   <div class="row">
      <div class="col">
         <table class="table">
            <thead>
            <tr>
               <th scope="col">Container Id</th>
               <th scope="col">Container Name</th>
               <th scope="col">Image</th>
               <th scope="col">Created</th>
               <th scope="col">Status</th>
               <th scope="col">Port</th>
               <th scope="col text-right">Action</th>
            </tr>
            </thead>
            <tbody id="table-body">
            </tbody>
         </table>
      </div>
   </div>
</div>

<button type="button" class="d-none" id="btn-wait-modal" data-toggle="modal" data-target="#waiting-modal"></button>

<!-- Waiting modal-->
<div class="modal fade pt-5" id="waiting-modal" tabindex="-1" aria-labelledby="waiting-modal" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title text-uppercase"> Please wait !</h5>
         </div>
         <div class="modal-body text-center">
            <div class="spinner-grow text-primary" role="status">
               <span class="sr-only">Loading...</span>
            </div>
            <div class="spinner-grow text-secondary" role="status">
               <span class="sr-only">Loading...</span>
            </div>
            <div class="spinner-grow text-success" role="status">
               <span class="sr-only">Loading...</span>
            </div>
            <div class="spinner-grow text-danger" role="status">
               <span class="sr-only">Loading...</span>
            </div>
            <div class="spinner-grow text-warning" role="status">
               <span class="sr-only">Loading...</span>
            </div>
            <div class="spinner-grow text-info" role="status">
               <span class="sr-only">Loading...</span>
            </div>
            <div class="spinner-grow text-dark" role="status">
               <span class="sr-only">Loading...</span>
            </div>
         </div>
      </div>
   </div>
</div>


<script src="./assets/vendor/bootstrap/js/popper.js"></script>
<script src="./assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<script>
  function startContainer(containerName) {
    $.ajax({
      url: '/api/container',
      method: 'POST',
      data: {
        'name' : containerName,
        'action' : 'start'
      },
      cache: false,
      async: false,
      success: function (data, textStatus, jqXHR) {
        if (data === 'true') {
          alert("Start container'" + containerName + "' success");
          reload();
        }
        else {
          alert("Error while start container'" + containerName + "'");
        }
      },
      error: function (jqXHR, textStatus, errorThrown) {
        alert("Error while start container'" + containerName + "'" + errorThrown);
      }
    });
  }
  function stopContainer(containerName) {
    $.ajax({
      url: '/api/container',
      method: 'POST',
      data: {
        'name' : containerName,
        'action' : 'stop'
      },
      cache: false,
      success: function (data, textStatus, jqXHR) {
        if (data === 'true') {
          alert("Stop container'" + containerName + "' success");
          reload();
        }
        else {
          alert("Error while Stop container'" + containerName + "'");
        }
      },
      error: function (jqXHR, textStatus, errorThrown) {
        alert("Error while stop container'" + containerName + "'" + errorThrown);
      }
    });
  }
  function deleteContainer(containerName) {
    $.ajax({
      url: '/api/container',
      method: 'POST',
      data: {
        'name' : containerName,
        'action' : 'delete'
      },
      cache: false,
      async: false,
      success: function (data, textStatus, jqXHR) {
        if (data === 'true') {
          alert("Delete container'" + containerName + "' success");
          reload();
        }
        else {
          alert("Error while delete container'" + containerName + "'");
        }
      },
      error: function (jqXHR, textStatus, errorThrown) {
        alert("Error while delete container'" + containerName + "'" + errorThrown);
      }
    });
  }

  function reload() {
    $.ajax({
      url: '/api/container',
      method: 'GET',
      data: {},
      cache: false,
      success: function (data, textStatus, jqXHR) {
        let list = $.parseJSON(data);
        console.log(list);
        $('#table-body').find('tr').remove();
        $.each(list, function (index, item) {
          let html =
            '<tr>' +
            '<td>' + item.containerId + '</td>' +
            '<td>' + item.names + '</td>' +
            '<td>' + item.image + '</td>' +
            '<td>' + item.status + '</td>' +
            '<td>' + item.create + '</td>' +
            '<td>' + item.port + '</td>' +
            '<td>' +
            '<button type="button" rel="tooltip" class="m-1 btn btn-success btn-icon btn-sm" title="Start container" onclick="startContainer(\'' + item.names + '\')">' +
            '<em class="fa fa-play text-light"></em><span class="ml-1">Start</span>' +
            '</button><br/>' +
            '<button type="button" rel="tooltip" class="m-1 btn btn-danger btn-icon btn-sm" title="Stop container" onclick="stopContainer(\'' + item.names + '\')">' +
            ' <em class="fa fa-stop text-light"></em><span class="ml-1">Stop</span>' +
            '</button><br/>' +
            '<button type="button" rel="tooltip" class="m-1 btn btn-secondary btn-icon btn-sm" title="Delete container" onclick="deleteContainer(\'' + item.names + '\')">' +
            '<em class="fa fa-trash text-light"></em><span class="ml-1">Delete</span>' +
            '</button>' +
            '</td>' +
            '</tr>';
          $('#table-body').append(html);
        });
      },
      error: function (jqXHR, textStatus, errorThrown) {
        alert("Error while load data: " + errorThrown);
      }
    });
  }

  reload();
</script>

</body>

</html>
