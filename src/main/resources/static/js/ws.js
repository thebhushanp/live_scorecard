'use strict';

var stompClient = null;


window.onload = function() {
  connect();
};

function connect() {
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);
        document.getElementById('txtMsg').value='';
}


function onConnected() {
	document.getElementById('txtMsg').value='Connected';
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);
}


function onError(error) {
	alert(error);
}

function onMessageReceived(payload) {
	alert(payload.body);
    //var message = JSON.parse(payload.body);
}
