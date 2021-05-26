<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="en">
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>Remote Docker Container</title>

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
                  <label for="full-name">Full name</label>
                  <input type="text" required name="full-name" id="full-name" class="form-control"
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
               <label for="centos" class="mt-4">
                  <img src="./assets/images/centos-logo.png" alt="I'm sad"/>
               </label>
               <input type="radio" name="os-type" id="happy" class="input-hidden" disabled/>
               <label for="happy">
                  <img src="./assets/images/ubuntu-logo.png" alt="I'm happy"/>
               </label>

               <!-- Memory -->
               <div class="form-group mt-5">
                  <label for="memory">Select container memory</label>
                  <select class="form-control" id="memory" name="memory" required>
                     <%--<option value="4m">4MB</option>
                     <option value="40m">10MB</option>--%>
                     <option value="50m" selected>50MB</option>
                     <option value="50m">100MB</option>
                     <option value="250m">250MB</option>
                     <option value="500m">500MB</option>
                  </select>
               </div>
               <!-- No of CPU-->
               <div class="form-group">
                  <label for="no-cpus">CPUs</label>
                  <select class="form-control" id="no-cpus" name="no-cpus" required>
                     <!--  Range of CPUs is from 0.01 to 1.00, as there are only 1 CPUs available -->
                     <option value="0.01">0.01</option>
                     <option value="0.05">0.05</option>
                     <option value="0.1">0.1</option>
                     <option value="0.25">0.25</option>
                     <option value="0.5" selected>0.5</option>
                     <option value="0.75">0.75</option>
                     <option value="1">1.00</option>
                  </select>
               </div>

               <!-- Password -->
               <div class="form-group">
                  <label for="password">Password</label>
                  <input type="password" name="password" id="password" class="form-control">
                  <small id="password-error" class="text-danger d-none">Password can't empty</small>
               </div>

               <!-- Re password -->
               <div class="form-group">
                  <label for="re-password">Retype password</label>
                  <input type="password" name="re-password" id="re-password" class="form-control">
                  <small id="re-password-error" class="text-danger d-none">Retype password not match</small>
               </div>
            </form>

            <!--Btn create-->
            <div class="row">
               <div class="col" style="padding-left: 3.8rem; padding-right:4rem;">
                  <button id="btn-create" type="submit" form="create-container-form" class="form-submit">Create</button>
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
                  <input type="text" class="form-control" id="ssh-username" name="ssh-username" placeholder="Ex: root">
               </div>
               <div class="form-group">
                  <label for="ssh-password">Password</label>
                  <input type="password" class="form-control" id="ssh-password" name="ssh-password">
               </div>
               <div class="form-group">
                  <label for="ssh-port">Port</label>
                  <input type="number" class="form-control" id="ssh-port" name="ssh-port">
               </div>
               <a href="javascript:void(0)" data-toggle="modal" data-target="#forget-port-modal">Forget SSH info ?</a>
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

<!-- Modal create success-->
<div class="modal fade pt-5" id="success-modal" tabindex="-1" aria-labelledby="success-modal" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title text-uppercase">
               <em class="fa fa-check-circle text-success"></em>
               Container successfully created
            </h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
               <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body container">
            <div class="row mb-3 text-center">
               <div class="col">
                  <h6>Check your email to start container</h6>
               </div>
            </div>
            <div class="row mt-5 mb-3">
               <div class="col text-center">
                  <a href="https://mail.google.com/mail" target="_blank">
                     <img class="m-auto d-block rounded mail-logo" src="./assets/images/gmail-logo.png"
                          alt="g-mail icon"/>
                     <p class="mt-3">Open your Gmail</p>
                  </a>
               </div>
               <div class="col text-center">
                  <a href="https://outlook.live.com/mail" target="_blank">
                     <img class="m-auto d-block rounded mail-logo" src="./assets/images/outlook-logo.png"
                          alt="outlook-mail icon"/>
                     <p class="mt-3">Open your Outlook mail</p>
                  </a>
               </div>
            </div>

         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
         </div>
      </div>
   </div>
</div>

<script src="./assets/vendor/bootstrap/js/popper.js"></script>
<script src="./assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<script>
  let isValidate = true;

  function setVisible(selector) {
    $(selector).removeClass('d-none');
    isValidate = false;
  }

  function setInVisible(selector) {
    $(selector).addClass('d-none');
  }

  $('#create-container-form').submit(function (e) {
    e.preventDefault();

    let email = $('#email').val();
    let fullName = $('#full-name').val();
    let memory = $('#memory').val();
    let cpu = $('#no-cpus').val();
    let password = $('#password').val();
    let rePassword = $('#re-password').val();

    console.log(email);
    console.log(password);
    console.log(rePassword);


    // check email exist
    $.ajax({
      url: "/api/users/check-email",
      method: "GET",
      cache: false,
      data: {'email': email},
      async: false,   // wait until done this scope
      success: function (data) {
        if (data.toString() === 'false') {
          setVisible('#email-error');
        } else {
          setInVisible('#email-error');
        }
      }
    });  //end check email

    if (password === '') {
      setVisible('#password-error');
    } else {
      setInVisible('#password-error');
    }

    if (rePassword != password) {
      setVisible('#re-password-error');
    } else {
      setInVisible('#re-password-error');
    }

    if (isValidate) {
      $.ajax({
        url: "/register",
        method: "POST",
        cache: false,
        data: {
          'full-name': fullName,
          'email': email,
          'memory': memory,
          'cpu': cpu,
          'password': password
        },
        beforeSend: function () {
          $("#waiting-modal").modal();
        },
        success: function (data, textStatus, jqXHR) {
          let result = data.toString().split('\n');
          if (result[0] === 'true') {
            $("#create-container-form").trigger("reset");
            $("#success-modal").modal();
          } else {
            alert("Error: " + result[1]);
          }
        },
        error: function (jqXHR, textStatus, errorThrown) {
          alert("Error: " + errorThrown);
          $("#waiting-modal").modal('hide');
        },
        complete: function () {
          $("#waiting-modal").modal('hide');
        }
      });  //end send request
    }
  }); // end '#create-container-form' submit
</script>
</body>
</html>