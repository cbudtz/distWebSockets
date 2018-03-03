var socket;
function connect(){
    var id = document.getElementById("clientid").value;
    socket = new WebSocket("ws://" + window.location.host + "/chat/" + id);
    socket.onmessage = (e)=>{
        document.getElementById("output").innerHTML += "<BR>" + e.data;
    }
}

function onSend(){
    var msg = document.getElementById("message").value;
    socket.send(msg);
}

