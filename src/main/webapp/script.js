var socket;
function connect(){
    var id = document.getElementById("clientid").value;
    let protocol = "ws://"
    if (window.location.protocol==="https:"){
        protocol = "wss://";
    }
    socket = new WebSocket(protocol + window.location.host + "/chat/" + id);
    socket.onmessage = (e)=>{
        document.getElementById("output").innerHTML += "<BR>" + e.data;
    }
}

function onSend(){
    var msg = document.getElementById("message").value;
    socket.send(msg);
}

