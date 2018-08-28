<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <!-- css -->
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" 
    integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous"/>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
    integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"/>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
    integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous"/>
  <link rel="stylesheet" href="./mercari.css"/>
  <!-- script -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
    integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
  <title>Rakus Items</title>
</head>
<body>
  <!-- navbar -->
  <nav class="navbar navbar-inverse">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${pageContext.request.contextPath}/mercari/viewAll">Rakus Items</a>
    </div>
    <div id="navbar" class="collapse navbar-collapse">
      <div>
        <ul class="nav navbar-nav navbar-right">
          <li><a id="logout" href="./login.html">Logout&nbsp;<i class="fa fa-power-off"></i></a></li>
        </ul>
        <p class="navbar-text navbar-right">
          <span id="loginName">user: userName</span>
        </p>
      </div>
    </div>
  </nav>

  <!-- details -->
  <div id="input-main" class="container">
    <a type="button" class="btn btn-default" href="${pageContext.request.contextPath}/mercari/viewDetail?id=${item.id}"><i class="fa fa-reply"></i> back</a>
    <h2>Edit</h2>

    <!-- edit form -->
    <form:form modelAttribute="addForm" action="${pageContext.request.contextPath}/mercari/editItem?id=${item.id}" class="form-horizontal">
        <!-- name -->
        <div class="form-group">
          <label for="inputName" class="col-sm-2 control-label">name</label>
          <div class="col-sm-8">
            <form:input type="text" class="form-control" id="inputName" path="name"/>
          </div>
        </div>
        <!-- price -->
        <div class="form-group">
          <label for="price" class="col-sm-2 control-label">price</label>
          <div class="col-sm-8">
            <form:input type="text" class="form-control" id="price" path="price"/>
           
          </div>
        </div>
        <!-- category -->
        <!-- category -->
      <div class="form-group">
        <label for="category" class="col-sm-2 control-label">category</label>
        <div class="col-sm-8">
          <form:select class="form-control" path="parent">
          <c:forEach var="parent" items="${parentList}">
            <form:option value="${parent.parent}"></form:option>
            </c:forEach>
          </form:select>
        </div>
      </div>
      <div class="form-group">
        <label for="category" class="col-sm-2 control-label"></label>
        <div class="col-sm-8">
        
          <form:select path="child" class="form-control">
          <c:forEach var="child" items="${childList}">
            <form:option value="${child.child}"></form:option>
           </c:forEach>
          </form:select>
        </div>
      </div>
      <div class="form-group">
        <label for="category" class="col-sm-2 control-label"></label>
        <div class="col-sm-8">
          <form:select class="form-control" path="grandChild">
          <c:forEach var="grand_child" items="${grandChildList}">
            <form:option value="${grand_child.grandChild}"></form:option>
        </c:forEach>
          </form:select>
        </div>
      </div>
      <div class="form-group">
        <label for="category" class="col-sm-2 control-label"></label>
        <div class="col-sm-8">
          <span class="text-danger"></span>
        </div>
      </div>
      <!-- brand -->
        <div class="form-group">
          <label for="brand" class="col-sm-2 control-label">brand</label>
          <div class="col-sm-8">
            <input type="text" id="brand" class="form-control" name="brand"/>
          </div>
        </div>
        <!-- condition -->
        <div class="form-group">
          <label for="condition" class="col-sm-2 control-label">condition</label>
          <div class="col-sm-8">
            <label for="condition1" class="radio-inline">
              <form:radiobutton path="condition" id="condition1" value="1"/> 1
            </label>
            <label for="condition2" class="radio-inline">
              <form:radiobutton path="condition"  id="condition2" value="2"/> 2
            </label>
            <label for="condition3" class="radio-inline">
              <form:radiobutton path="condition"  id="condition3" value="3"/> 3
            </label>
          </div>
        </div>
        <!-- description -->
        <div class="form-group">
          <label for="description" class="col-sm-2 control-label">description</label>
          <div class="col-sm-8">
            <form:textarea path="description" id="description" class="form-control" rows="5"></form:textarea>
          </div>
        </div>
        <!-- submit button -->
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">Submit</button>
          </div>
        </div>
      </form:form>
    </div>
</body>


</body>
</html>