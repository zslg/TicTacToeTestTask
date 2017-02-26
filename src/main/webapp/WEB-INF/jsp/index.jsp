<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Games</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js" type="text/javascript"charset="utf-8"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
</head>

<style>
    table{
        border: 1px solid black;
        table-layout: fixed;
        width: 200px;
    }

    th, td {
        border: 1px solid black;
        overflow: hidden;
        width: 150px;
    }
</style>

<script>
    $(document).ready(function () {
        $("#add-game").on("click",function(e) {
            e.preventDefault();
            $("#new-game-modal").dialog({width:"50%",height:100,modal:true});
        });
    });
    function createGame() {
        if ($("#new-game-name").val() === "") {
            alert("Game name is required field!");
            return;
        }
        data = {
            "name":$("#new-game-name").val()
        }

        $.ajax({
            type: 'POST',
            url: "/game",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: 'json'
        }).done(function(data){
            $("#new-game-name").val('');
            $("#new-game-modal").dialog("close");
            window.location.href = "/game/"+data.id;
        }).fail(function () {
            alert('Something wrong.');
        });
    }
</script>
<body>

<h2>Games list</h2>
<a href="" id="add-game"> Create new game </a><br><br><br>

<table id='games'>
    <tr>
        <th>id</th>
        <th>Name</th>
        <th>Status</th>
        <th></th>
    </tr>
    <c:if test="${not empty games}">
        <table>
            <c:forEach var="game" items="${games}">
                <tr>
                    <td>${game.id}</td>
                    <td>${game.name}</td>
                    <td>${game.status}</td>
                    <td><a href="/game/${game.id}">Show details</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</table>

<div id="new-game-modal" hidden="hidden" title="Enter new game name">
    <input type="text" id="new-game-name"/>
    <button onclick="createGame()">Create game</button>
</div>

</body>
</html>
