
<%@include file="header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
    <head>
        <title>Media list</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="text/javascript" src="<c:url value="/resources/js/mplayer.searchbutton.js" />"> </script>
        <script type="text/javascript" src="<c:url value="/resources/js/mplayer.deleteconfirmation.js" />"></script>
        <link href="<c:url value="/resources/css/medialist.css" />" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <center>
                <h2>Media list</h2>
            </center>
            <input hidden type="text" id="fullsearch" name="fullsearch" value="<c:out value="${searchREF}"/>"/>
            <div class="form-horizontal">
                <div class="form-group">
                    <div class="form-inline">
                        <c:if test="${isAdministrator}">
                            <span class="pull-left">
                                <a name="uploadMedia" href="/upload" class="btn btn-primary btn-md" role="button"><span class="glyphicon glyphicon-upload"></span> Upload media</a>
                            </span>
                        </c:if>
                        <span class="pull-right">
                            <input type="text" name="searchField" id ="searchField" class="form-control" placeholder="Search media.." value="${search}" onchange="gethref()"/>
                            <a name="searchLink" id="searchLink" href="" onclick="gethref()" class="btn btn-primary btn-md" role="button"><span class="glyphicon glyphicon-search"></span> Search</a>
                        </span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-inline">
                        <span class="pull-right">
                            <a name="advancedSearch" href="<c:out value="${advancedsearch}"/>" role="link"><span class="glyphicon glyphicon-search"></span> Advanced search...</a>  
                        </span>
                    </div>
                </div>
            </div>
            <table class="table table-hover">
                <thead><tr> <td><b>Id</b></td> <td><b>SongName</b></td> <td><b>FileName</b></td> <td><b>SongSize</b></td> <td><b>CreateDate</b></td> <td><b>ChangeDate</b></td> </tr></thead>
                <tbody>
                <c:forEach var="media" items="${mediaList}">
                    <tr>
                        <td onclick="document.location='mediadetails?id=${media.id}';"><b><c:out value="${media.id}"/></b></td>
                        <td class="pointerrow" onclick="document.location='mediadetails?id=${media.id}';"><c:out value="${media.songName}"/></td>
                        <td class="pointerrow" onclick="document.location='mediadetails?id=${media.id}';"><c:out value="${media.fileName}"/></td>
                        <td class="pointerrow" onclick="document.location='mediadetails?id=${media.id}';"><c:out value="${media.songSize} Kb"/></td>
                        <td class="pointerrow" onclick="document.location='mediadetails?id=${media.id}';"><c:out value="${media.createDateAsDate}"/></td>
                        <td class="pointerrow" onclick="document.location='mediadetails?id=${media.id}';"><c:out value="${media.changeDateAsDate}"/></td>
                        <c:if test="${isAdministrator}">
                            <td><a href="/upload?id=<c:out value="${media.id}"/>"><button class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-edit"></span> Edit</button></a></td>
                            <td><a href="/delete?id=<c:out value="${media.id}"/>" data-confirm="Are you sure you want to delete media <c:out value="${media.fileName}"/>?"><button class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-trash"></span> Delete</button></a></td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <center>
                <ul class="pagination">
                    <c:if test="${!isFirstPage}">
                        <li><a href="${pageREF}first">&lt;&lt;</a></li>
                        <li><a href="${pageREF}previous">&lt;</a></li>
                    </c:if>
                    <c:forEach var="page_number" items="${pagesList}">
                        <c:if test="${!page_number.choosed}"><li></c:if>
                        <c:if test="${page_number.choosed}"><li class="active"></c:if>
                            <a href="${pageREF}${page_number.value}">${page_number.value}</a></li>
                    </c:forEach>
                    <c:if test="${!isLastPage}">
                        <li><a href="${pageREF}next">&gt;</a></li>
                        <li><a href="${pageREF}last">&gt;&gt;</a></li>
                    </c:if>
                </ul>
            </center>
        </div>
    </body>
</html>

