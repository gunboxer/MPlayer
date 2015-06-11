
<%@include file="header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
    <head>
        <title>Upload media</title>
    </head>
    <body>
        <center>
            <h2>Upload media</h2>
        </center>
        <div class="Container">
            <div id="media-box">
                <form class="form-horizontal" name='uploadForm' action="/savemedia?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data" method="POST">
                    <div class="form-group">
                        <label class="control-label col-sm-2">Description:</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="focusedInput" type="text" name="desc" value="${songName}">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-inline">
                            <label class="control-label col-sm-2">File path:</label>
                            <div class="col-sm-10">
                                <input type="file" class="file" name="file" accept="audio/mp3"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="form-group row">
                                <div class="col-sm-2">
                                    <span class="pull-left">
                                        <button type="submit" name="submit" class="btn btn-primary btn-md">
                                            <c:choose>
                                                <c:when test="${empty id}">
                                                    <span class="glyphicon glyphicon-upload"></span> Upload
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="glyphicon glyphicon-ok"></span> Update
                                                </c:otherwise>
                                            </c:choose>
                                        </button>
                                    </span>
                                </div>
                                
                                <div class="col-sm-offset-8 col-sm-2">
                                    <span class="pull-right">
                                        <button class="btn btn-default btn-md" onclick="history.go(-1)">
                                            Back
                                        </button>
                                    </span>
                                </div>
                            </div>
                        </div>
                        
                    </div>
                        
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <input type="hidden" name="id" value="${id}">
                </form>
            </div>
        </div>
    </body>
</html>
