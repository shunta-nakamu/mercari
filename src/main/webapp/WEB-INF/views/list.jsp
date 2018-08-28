<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<script>
            $(function() {
              var page = 0;
              function draw() {
                $('#page').html(page + 1);
                $('tr').hide();
                $('tr:gt(' + page * 30 + '):lt(30)').show(); 
              }
              $('#prev').click(function() {
                if (page > 0) {
                  page--;
                  draw();
                }
              });
              $('#next').click(function() {
                if (page < ($('tr').size() - 1) / 30- 1) {
                        var target = document.getElementById("next");
                    if(page%29==0 && page>=29){
                    target.innerText = "サイトへのリンク (google)";
                        page++;
                        draw();
                    }
                   else if(page%30==0 && page>=30){
                    $('#next').attr('href','${pageContext.request.contextPath}/mercari/paging?page=' + page);
                    target.innerText = "next";
                        page++; 
                        draw();
                    }
                    else{
                    target.href="javascript:;"
                    target.innerText = "next";
                    page++;
                    draw();
                    }
                }
              });
              draw();
            });
            </script>

<title>Insert title here</title>
</head>
<body>
<span id="page"></span>
<nav class="navbar navbar-inverse">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
     
      <a class="navbar-brand" href="${pageContext.request.contextPath}/mercari/viewAll" onmouseover="popup()">Rakus Items</a>
    </div>
    <div id="navbar" class="collapse navbar-collapse">
      <div>
        <ul class="nav navbar-nav navbar-right">
          <li><a id="logout" href="./login.html">Logout <i class="fa fa-power-off"></i></a></li>
        </ul>
        <p class="navbar-text navbar-right">
          <span id="loginName">user: userName</span>
        </p>
      </div>
    </div>
  </nav>

  <div id="main" class="container-fluid">
    <!-- addItem link -->
  
    <div id="addItemButton">
      <a class="btn btn-default" href="${pageContext.request.contextPath}/mercari/viewAddPage"><i class="fa fa-plus-square-o"></i> Add New Item</a>
    </div>
    
     <a class="btn btn-default" href="${pageContext.request.contextPath}/mercari/viewRegister"><i class="fa fa-plus-square-o"></i> Register User</a>
    

    <!-- 検索フォーム -->
    <div id="forms">
      <form action="" class="form-inline" role="form">
        <div class="form-group">
          <input type="input" class="form-control" id="name" placeholder="item name"/>
        </div>
        <div class="form-group"><i class="fa fa-plus"></i></div>
         
        <div class="form-group">
          <select class="form-control">
            <option>- parentCategory -</option>
            <option>parentCategory1</option>
            <option>parentCategory2</option>
            <option>parentCategory3</option>
            <option>parentCategory4</option>
            <option>parentCategory5</option>
          </select>
          <select class="form-control">
            <option>- childCategory -</option>
            <option>childCategory1</option>
            <option>childCategory2</option>
            <option>childCategory3</option>
            <option>childCategory4</option>
            <option>childCategory5</option>
          </select>
          <select class="form-control">
            <option>- grandChild -</option>
            <option>grandChild1</option>
            <option>grandChild2</option>
            <option>grandChild3</option>
            <option>grandChild4</option>
            <option>grandChild5</option>
          </select>
        </div> 
        <div class="form-group"><i class="fa fa-plus"></i></div>
        <div class="form-group">
          <input type="text" class="form-control" placeholder="brand"/>
        </div>
        <div class="form-group"></div>
        <button type="submit" class="btn btn-default"><i class="fa fa-angle-double-right"></i> search</button>
      </form>
    </div>

    <!-- pagination -->
    <div class="pages">
   
      <nav class="page-nav">
        <ul class="pager">
         <li class="previous"><a href="#" id = "prev">&larr; prev</a></li>
              <li class="next"><a href="#" id="next">next &rarr;</a></li>
        </ul>
      </nav>
    </div>

    <!-- table -->
    <div class="table-responsive">
      <table class="table table-hover table-condensed">
        <thead>
          <tr>
            <th>name</th>
            <th>price</th>
            <th>category</th>
            <th>brand</th>
            <th>cond</th>
          </tr>
        </thead>
      <c:forEach var="item" items="${itemsList}">
        <tbody>
          <tr>
            <td class="item-name"><a href="${pageContext.request.contextPath}/mercari/viewDetail?id=${item.id}"><c:out value="${item.name}"></c:out></a></td>
            <td class="item-price"><c:out value="${item.price}"></c:out></td>
            <td class="item-category"><a href=""><c:out value="${item.name_parent}"></c:out></a>/<a href=""><c:out value="${item.name_child}"></c:out></a>/<a href=""><c:out value="${item.name_grand_child}"></c:out></a></td>
            <td class="item-brand"><a href=""><c:out value="${item.brand}"></c:out></a></td>
            <td class="item-condition"><c:out value="${item.condition}"></c:out></td>
           
          </tr>
          <tr>
          </tbody>
          </c:forEach>
      </table>
    </div>

    <!-- pagination -->
 
    <div class="pages">
      <nav class="page-nav">
        <ul class="pager">
          <li class="previous"><a href="${pageContext.request.contextPath}/mercari/paging?page=prev" onclick="plusCount();">&larr; prev</a></li>
          <li class="next"><a href="${pageContext.request.contextPath}/mercari/paging?page=next" onclick="plusCount();">next &rarr;</a></li>
          <!-- ここにpagingcontrollerに飛ばす処理をする　ページの情報を保有させたいなぜ？ -->
        </ul>
      </nav>
      <!-- ページ番号を指定して表示するフォーム -->
      <div id="select-page">
        <form class="form-inline">
          <div class="form-group">
            <div class="input-group col-xs-6">
              <label></label>
              <input type="text" class="form-control"/>
              <!-- 総ページ数 -->
              <div class="input-group-addon">/ 20</div>
            </div>
            <div class="input-group col-xs-1">
              <button type="submit" class="btn btn-default">Go</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</body>
</html>

