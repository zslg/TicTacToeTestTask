<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js" type="text/javascript"
        charset="utf-8"></script>
<html>
<head>
    <title>Game</title>
</head>
<script>
    $(document).ready(function () {

        var table = document.getElementById("board");
        if (table != null) {
            for (var i = 0; i < table.rows.length; i++) {
                for (var j = 0; j < table.rows[i].cells.length; j++)
                    table.rows[i].cells[j].onclick = function () {
                        onBoardCellClick(this);
                    };
            }
        }

        loadData();

    });

    function onBoardCellClick(cell) {

        if ($("#game-status").val() != 'IN_PROGRESS') {
            alert('Game is over!');
            return;
        }

        if(cell.innerText != ""){
            alert("This position already occupied!");
            return;
        }

        var column = cell.cellIndex;
        var row = cell.parentNode.rowIndex;
        var ccc = "";
        if($("#currentMark").val() === '0'){
            ccc = 1;
        }else {
            ccc = 0;
        }

        var request = {
            "gameId":${gameId},
            "stepType":ccc,
            "row":row,
            "column":column
        };

        $.ajax({
            type: 'POST',
            url: "/game/step",
            data: JSON.stringify(request),
            contentType: "application/json",
            dataType: 'json'
        }).done(function(data){

            var x = document.getElementById("board").rows[row].cells;
            if ($("#currentMark").val() === '0') {
                x[column].innerHTML = "X";
                addRowToHistory(null,1,row,column,false);
                $("#currentMark").val('1');
            }else {
                x[column].innerHTML = "O";
                addRowToHistory(null,0,row,column,false);
                $("#currentMark").val('0');
            }

            if(data.status != undefined && data.status != null){
                alert("Game is over.Status:"+data.status);
                $("#game-status").val(data.status);
                return;
            }

        }).fail(function () {
            alert('Something wrong.');
        });

    }

    function loadData() {

        $.ajax({
            type: 'GET',
            url: "/game/rest/${gameId}",
            contentType: "application/json",
            dataType: 'json'
        }).done(function(data){
            var lastStepType = -1;
            $("#game-name").val(data.name);
            $("#game-status").val(data.status);
            data.steps.forEach(function (element, index, array) {
                addRowToHistory(new Date(element.createDate),element.stepType,element.row,element.column,true);
                lastStepType = element.stepType;
            })
            if (lastStepType == -1) {
                $("#currentMark").val('0');
            }else {
                $("#currentMark").val(lastStepType);
            }
        }).fail(function () {
            alert('Something wrong.');
        });
    }

    function addRowToHistory(date,step,row,column,init) {
        var textStep = "";
        if (step == 0) {
            textStep = "O";
        }else {
            textStep = "X"
        }
        if (date == null) {
            date = new Date();
        }
        var content = '<tr>' +
                        '<td>'+date.toISOString()+'</td>' +
                        '<td>'+(row+1)+'</td>' +
                        '<td>'+(column+1)+'</td>' +
                        '<td>'+textStep+'</td>' +
                        '</tr>';
        $('#game-steps tr:last').after(content);
        if (init == true) {
            document.getElementById("board").rows[row].cells[column].innerHTML = textStep;
        }
    }

</script>
<body>

<style>
    * {
        margin: 0;
        padding: 0;
        margin-left: 5px;
    }

    table {
        border-collapse: collapse;
        border-spacing: 0;
    }

    #board {
        padding: 0px;
        margin: 0 auto;
        margin-top: 25px;
    }

    #board tr td {
        width: 80px;
        height: 80px;
        border: 1px solid #1c1c1c;
        font-family: Helvetica;
        font-size: 30px;
        text-align: center;
    }

    #board tr td:hover {
        background: #e4e4e4;
        cursor: pointer;
    }

    #game-steps table{
        border: 1px solid black;
        table-layout: fixed;
        width: 200px;
    }

    #game-steps th, td {
        border: 1px solid black;
        overflow: hidden;
        width: 100px;
    }

</style>

<a href="/">Main page</a><br><br>
Name:<input id="game-name" type="text" disabled="disabled"/><br>
Status:<input id="game-status" type="text" disabled="disabled"/><br>
<input id="currentMark" hidden="hidden"/>

<table id='board'>
    <tr>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
    </tr>
</table>

<h3>Game steps history:</h3><br>
<table id="game-steps" style="width: 50%">
    <thead>
    <tr>
        <th>Date</th>
        <th>Row</th>
        <th>Column</th>
        <th>Step</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>

</body>
</html>
