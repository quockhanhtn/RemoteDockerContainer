<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>Document</title>

   <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
         integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossOrigin="anonymous">
   <!--Bootstrap CSS-->
   <link rel="stylesheet" type="text/css" href="./assets/vendor/bootstrap/css/bootstrap.min.css">
   <!--Custom CSS-->
   <link rel="stylesheet" href="./assets/style.css">

   <!--jQuery-->
   <script src="./assets/vendor/jquery/jquery-3.5.1.min.js"></script>
</head>

<body>
<div class="container">
   <div class="row my-5">
      <div class="col-4">
         <img class="rounded" src="./assets/images/img-01.png" alt="logo">
      </div>
      <div class="col-8">
         <div class="main-form container">
            <div class="row mb-5">
               <div class="col text-center">
                  <h2 class="display-4 text-uppercase">Remote container</h2>
               </div>
            </div>

            <!--Button Remote with SSH-->
            <div class="row">
               <div class="col text-center px-5">
                  <button type="button" class="remote-ssh" data-toggle="modal" data-target="#ssh-container-modal">
                     <em class="fab fa-google remote-ssh-icon"></em>
                     <span class="remote-ssh-text text-uppercase">Remote with SSH</span>
                  </button>
               </div>
            </div>

            <!--Or text-->
            <div class="ssh-or">
               <span class="ssh-or-text">OR</span>
            </div>

            <!--Title create new container-->
            <div class="row">
               <div class="col text-center">
                  <h6 class="display-4">Create new container</h6>
               </div>
            </div>

            <!-- Form create new container -->
            <form class="mt-5 px-5" id="create-container-form">
               <!-- Full name -->
               <div class="form-group">
                  <label for="fullname">Full name</label>
                  <input type="text" required name="fullname" id="fullname" class="form-control"
                         placeholder="Eg: John Doe">
               </div>
               <!-- Email -->
               <div class="form-group">
                  <label for="email">Email address</label>
                  <input type="email" required name="email" id="email" class="form-control"
                         placeholder="Eg: johndoe@email.com">
                  <small id="email-error" class="text-danger d-none">This email has already been registered</small>
               </div>

               <!-- OS -->
               <label for="memory" class="d-block">Select container OS</label>
               <input type="radio" name="os-type" id="centos" class="input-hidden" checked/>
               <label for="centos"class="mt-4">
                  <img src="./assets/images/centos-logo.png" alt="I'm sad" />
               </label>
               <input type="radio" name="os-type" id="happy" class="input-hidden" disabled/>
               <label for="happy">
                  <img src="./assets/images/ubuntu-logo.png" alt="I'm happy" />
               </label>

               <!-- Memory -->
               <div class="form-group mt-5">
                  <label for="memory">Select container memory</label>
                  <select class="form-control" id="memory" name="memory" required>
                     <option value="4">4M</option>
                     <option value="40">10M</option>
                     <option value="50">50M</option>
                     <option value="50">100M</option>
                     <option value="250">250M</option>
                     <option value="500">500M</option>
                  </select>
               </div>
               <!-- No of CPU-->
               <div class="form-group">
                  <label for="no-cpus">No of CPU</label>
                  <select class="form-control" id="no-cpus" name="memory" required>
                     <option value="1" selected>1</option>
                     <option value="2">2</option>
                     <option value="3">3</option>
                     <option value="4">4</option>
                     <option value="5">5</option>
                     <option value="6">6</option>
                     <option value="7">7</option>
                     <option value="8">8</option>
                  </select>
               </div>
            </form>

            <!--Btn create-->
            <div class="row">
               <div class="col" style="padding-left: 3.8rem; padding-right:4rem;">
                  <button id="btn-create" type="submit" form="create-container-form"
                          class="form-submit">Create</button>
               </div>
            </div>
         </div>
      </div>
   </div>
</div>

<!-- Modal SSH container -->
<div class="modal fade pt-5" id="ssh-container-modal" tabindex="-1" aria-labelledby="ssh-container-modal"
     aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title">Remote container using SSH</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
               <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body">
            <form id="ssh-form" action="/ssh" method="POST" class="p-3">
               <div class="form-group">
                  <label for="ssh-username">Username</label>
                  <input type="text" class="form-control" id="ssh-username" name="ssh-username"
                         placeholder="Ex: root">
               </div>
               <div class="form-group">
                  <label for="ssh-password">Password</label>
                  <input type="password" class="form-control" id="ssh-password" name="ssh-password">
               </div>
               <div class="form-group">
                  <label for="ssh-port">Port</label>
                  <input type="number" class="form-control" id="ssh-port" name="ssh-port">
                  <button type="button" data-toggle="modal" data-target="#forget-port-modal">Forget port ?</button>
               </div>
            </form>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            <button type="submit" form="ssh-form" class="btn btn-primary">Connect</button>
         </div>
      </div>
   </div>
</div>

<!-- Modal forget port-->
<div class="modal fade pt-5" id="forget-port-modal" tabindex="-1" aria-labelledby="forget-port-modal"
     aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title">Forget SSH info</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
               <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body">
            <form id='forget-ssh-form' action="/ssh" method="POST" class="p-3">
               <form id='forget-port-form'>
                  <div class="form-group">
                     <label for="forget-port-email">Email address</label>
                     <input type="email" class="form-control" id="forget-port-email" name="forget-port-email"
                            placeholder="Eg: name@example.com">
                  </div>
               </form>
            </form>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Send SSH info</button>
         </div>
      </div>
   </div>
</div>

<script src="./assets/vendor/bootstrap/js/popper.js"></script>
<script src="./assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<script>
  $('#create-container-form').submit(function (e) {
    e.preventDefault();

    // check email exist
    $.ajax({
      url: "/api/users/check-email",
      method: "GET",
      cache: false,
      data: { 'email': $('#email').val() },
      async: false,   // wait until done this scope
      success: function (data) {
        if (data.toString() === 'false') { $('#email-error').removeClass('d-none'); }
        else { $('#email-error').addClass('d-none'); }
      }
    });
  }); // end '#create-container-form' submit
</script>
</body>
</html>