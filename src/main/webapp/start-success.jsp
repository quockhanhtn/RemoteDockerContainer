<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Container successfully started</title>

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
        <!--Title-->
        <div class="row mt-5 text-center">
            <div class="col">
                <h5 class="text-uppercase">
                    <em class="fa fa-check-circle text-success"></em>
                    Container successfully started
                </h5>
            </div>
        </div>
        <!---->
        <div class="row mb-3 text-center">
            <div class="col">
                <h6>Check your email to get SSH info</h6>
            </div>
        </div>
        <!--Open email button-->
        <div class="row mt-5 mb-3">
            <div class="col text-center">
                <a href="https://mail.google.com/mail" target="_blank">
                    <img class="m-auto d-block rounded mail-logo" src="./assets/images/gmail-logo.png" alt="g-mail icon"/>
                    <p class="mt-3">Open your Gmail</p>
                </a>
            </div>
            <div class="col text-center">
                <a href="https://outlook.live.com/mail" target="_blank">
                    <img class="m-auto d-block rounded mail-logo" src="./assets/images/outlook-logo.png" alt="outlook-mail icon"/>
                    <p class="mt-3">Open your Outlook mail</p>
                </a>
            </div>
        </div>
    </div>
</body>

</html>