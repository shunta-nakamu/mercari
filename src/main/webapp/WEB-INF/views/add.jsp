<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

  <meta charset="UTF-8"/>
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
    <a type="button" class="btn btn-default" href="${pageContext.request.contextPath}/mercari/viewAll"><i class="fa fa-reply"></i> back</a>
    <h2>Add</h2>

    <!-- add form -->
    <form:form modelAttribute="addForm" action="${pageContext.request.contextPath}/mercari/addItem" class="form-horizontal" name="form">
      <!-- name -->
      <div class="form-group">
        <label for="inputName" class="col-sm-2 control-label">name</label>
        <div class="col-sm-8">
         <!--<input type="text" class="form-control" id="inputName"/>-->
          <form:input path="name" class="form-control"/>
          <span class="text-danger"><form:errors path="name"/></span>
        </div>
      </div>
      <!-- price -->
      <div class="form-group">
        <label for="price" class="col-sm-2 control-label">price</label>
        <div class="col-sm-8">
          <form:input path="price" class="form-control"/>
          <span class="text-danger"></span>
        </div>
      </div>
      <!-- category -->
      <div class="form-group">
        <label for="category" class="col-sm-2 control-label">category</label>
        <div class="col-sm-8">
          <select class="form-control" name="parent">
          <c:forEach var="parent" items="${parentList}">
            <option><c:out value="${parent.parent}"></c:out></option>
            </c:forEach>
          </select>
        </div>
      </div>
      <div class="form-group">
        <label for="category" class="col-sm-2 control-label"></label>
        <div class="col-sm-8">
          <select class="form-control" name="child">
          <c:forEach var="child" items="${childList}">
            <option><c:out value="${child.child}"></c:out></option>
           </c:forEach>
          </select>
        </div>
      </div>
      <div class="form-group">
        <label for="category" class="col-sm-2 control-label"></label>
        <div class="col-sm-8">
          <select class="form-control" name="grandChild">
          <c:forEach var="grand_child" items="${grandChildList}">
            <option><c:out value="${grand_child.grandChild}"></c:out></option>
        </c:forEach>
          </select>
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
          <span class="text-danger"></span>
        </div>
      </div>
      <!-- condition -->
      
      <div class="form-group">
        <label for="condition" class="col-sm-2 control-label">condition</label>
        <div class="col-sm-8">
          <label for="condition1" class="radio-inline">
            <input type="radio" name="condition" id="condition1" value="1"/> 1
          </label>
          <label for="condition2" class="radio-inline">
            <input type="radio" name="condition" id="condition2" value="2"/> 2
          </label>
          <label for="condition3" class="radio-inline">
            <input type="radio" name="condition" id="condition3" value="3"/> 3
          </label>
        </div>
      </div>
      <div class="form-group">
        <label for="category" class="col-sm-2 control-label"></label>
        <div class="col-sm-8">
          <span class="text-danger"></span>
        </div>
      </div>
      <!-- description -->
      <div class="form-group">
        <label for="description" class="col-sm-2 control-label">description</label>
        <div class="col-sm-8">
          <textarea name="description" id="description" class="form-control" rows="5"></textarea>
          <span class="text-danger"></span>
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
</html>