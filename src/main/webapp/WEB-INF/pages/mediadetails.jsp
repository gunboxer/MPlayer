
<%@include file="header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<head>
    <title>Media details</title>
    <script type="text/javascript" src="<c:url value="/resources/js/flowplayer-3.2.13.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.9.1.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/audio/mplayer.playmedia.js"/>"></script>
    <link href="<c:url value="/resources/css/mediadetails.css" />" rel="stylesheet">
</head>
    <body>
        <div class="Container">
            <ul class="list-group">
                <li class="list-group-item text-right"><center><h2>Media details</h2></center></li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Media Id:</strong></span><c:out value="${media.id}"/></li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Song Name:</strong></span><c:out value="${media.songName}"/></li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">File Name:</strong></span><c:out value="${media.fileName}"/></li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Song Size:</strong></span><c:out value="${media.songSize} Kb"/></li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Create Date:</strong></span><c:out value="${media.createDateAsDate}"/></li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Change Date:</strong></span><c:out value="${media.changeDateAsDate}"/></li>
            </ul>
            <div class="form-horizontal">
                <div class="form-group row">
                    <div class="col-sm-2">
                        <button class="btn btn-primary btn-md" onclick="playMedia()"><span class="glyphicon glyphicon-play"></span> Play mp3</button>
                    </div>
                    <div class="col-sm-offset-2 col-sm-2">
                        <a id="audio" class="flowplayerwindow" href="/resources/audio/${fileName}.mp3"></a>
                    </div>
                    <div class="col-sm-offset-4 col-sm-2">
                        <span class="pull-right"><button class="btn btn-default btn-md" onclick="history.go(-1)">Back</button></span>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

