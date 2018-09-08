'use strict';

var stompClient = null;


window.onload = function() {
  connect();
};

function connect() {
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);
}


function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);
}


function onError(error) {
	alert(error);
}

function onMessageReceived(payload) {
    var data = JSON.parse(payload.body);
    var tblScore = document.getElementById("tblScore");
    while(tblScore.hasChildNodes())
    {
    	tblScore.removeChild(tblScore.firstChild);
    }
    var txtTotalScore = document.getElementById("txtTotalScore");
	txtTotalScore.value = 0;
	renderScore(data);
}

window.onload = function() {
	var xmlhttp = new XMLHttpRequest();
	var url = "../api/score/pull?matchId=2&teamId=2";
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var score = JSON.parse(this.responseText);
			renderScore(score);
		}
	};
	xmlhttp.open("GET", url, true);
	xmlhttp.send();
	connect();
}

function renderScore(data) {
	var tblScore = document.getElementById("tblScore");
	var txtTotalScore = document.getElementById("txtTotalScore");
	txtTotalScore.value = 0;
	for (var index = 0; index < data.length; index++) {
		var score = data[index];
		txtTotalScore.value = Number(txtTotalScore.value) + score.runs;
		var tr = document.createElement("tr");

		var tdName = document.createElement("td");
		var tdRuns = document.createElement("td");
		var tdBalls = document.createElement("td");

		var txtName = document.createTextNode(score.player.name);
		var txtBalls = document.createTextNode(score.balls);
		var txtRuns = document.createTextNode(score.runs);

		tdName.appendChild(txtName);
		tdRuns.appendChild(txtRuns);
		tdBalls.appendChild(txtBalls);

		tr.appendChild(tdName);
		tr.appendChild(tdRuns);
		tr.appendChild(tdBalls);
		tblScore.appendChild(tr);
	}
}