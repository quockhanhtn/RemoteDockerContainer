<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
<head>
   <title>JSP - Hello World</title>

   <!--Bootstrap CSS-->
   <link rel="stylesheet" href="./css/bootstrap.min.css">
</head>
<body>

<div class="container">
   <div class="row">
      <div class="col">
         <form action="test-servlet" method="get">
            <div class="form-group">
               <label for="cmd">Command</label>
               <textarea class="form-control" id="cmd" name="cmd" rows="3">${cmd}</textarea>
            </div>

            <input class="btn btn-primary" type="submit" value="Submit">
         </form>

         <p>Result: <br/> ${execResult}</p>
      </div>
   </div>
</div>


<!--jQuery-->
<script src="assets/jquery-3.5.1.min.js"></script>
<!--Bootstrap Js-->
<script src="./js/bootstrap.bundle.min.js"></script>
<script src="./js/bootstrap.min.js"></script>

</body>
</html>