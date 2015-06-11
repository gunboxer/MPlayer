
<%@include file="header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Advanced search</title>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap-slider.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/advancedsearch.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap-datepicker3.css" />" rel="stylesheet">
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />"> </script>
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap-slider.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/mplayer.smartslider.js"/>"></script>
</head>
    <body>
        <center><h2>Advanced search</h2></center>
        <div class="container">
            <form class="form-horizontal" action="/performsearch?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data" method="POST">
                <div class="form-group">
                    <label class="control-label col-sm-2">Media Id:</label>
                    <div class="col-sm-10">
                        <input class="form-control" id="mediaid" name="mediaid" type="text" placeholder="Enter media ID" value="<c:out value="${selectLimitation.mediaid}"/>">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2">Song Name:</label>
                    <div class="col-sm-10">
                        <input class="form-control" id="songname" name="songname" type="text" placeholder="Enter song name" value="<c:out value="${selectLimitation.songnameTXT}"/>">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2">File Name:</label>
                    <div class="col-sm-10">
                        <input class="form-control" id="filename" name="filename" type="text" placeholder="Enter file name" value="<c:out value="${selectLimitation.filenameTXT}"/>">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2">Song Size:</label>
                    <div class="col-sm-10">
                        <div class="form-group row">
                            <div class="col-sm-4">
                                <input class="form-control col-sm-2" id="ex12c" type="text" value="${maxmediasize}"/><br/>
                            </div>
                            <label for="minsize" class="col-sm-1 control-label">from</label>
                            <div class="col-sm-3">
                                <input class="form-control" type="text" id="minsize" name="minsize" value="<c:out value="${selectLimitation.minsize}"/>" readonly/>
                            </div>
                            <label for="maxsize" class="col-sm-1 control-label">to</label>
                            <div class="col-sm-3">
                                <input class="form-control" type="text" id="maxsize" name="maxsize" value="<c:out value="${selectLimitation.maxsize}"/>" readonly/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2">Create Date:</label>
                    <div class="col-sm-10">
                        <div class="form-group row">
                            <label for="createdatefrom" class="col-sm-1 control-label">from</label>
                            <div class="col-sm-3">
                                <input type="text" id="createdatefrom" value="<c:out value="${selectLimitation.createdatefromTXT}"/>" name="createdatefrom">
                            </div>
                            <label for="createdateto" class="col-sm-1 control-label">to</label>
                            <div class="col-sm-3">
                                <input type="text" id="createdateto" value="<c:out value="${selectLimitation.createdatetoTXT}"/>" name="createdateto">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2">Change Date:</label>
                    <div class="col-sm-10">
                        <div class="form-group row">
                            <label for="changedatefrom" class="col-sm-1 control-label">from</label>
                            <div class="col-sm-3">
                                <input type="text" id="changedatefrom" value="<c:out value="${selectLimitation.changedatefromTXT}"/>" name="changedatefrom">
                            </div>
                            <label for="changedateto" class="col-sm-1 control-label">to</label>
                            <div class="col-sm-3">
                                <input type="text" id="changedateto" value="<c:out value="${selectLimitation.changedatetoTXT}"/>" name="changedateto">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group row">        
                    <div class="col-sm-offset-2 col-sm-2">
                        <button type="submit" name="submit" class="btn btn-primary btn-default"><span class="glyphicon glyphicon-search"></span>  Search</button>
                    </div>
                    <div class="col-sm-offset-6 col-sm-2">
                        <span class="pull-right"><button class="btn btn-default btn-md" onclick="history.go(-1)">Back</button></span>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
