function receieveMessage(string){
	JSON.parse(string);
}

function sendBackMessage(string){
	javaOp.debug(string);
}

function exit(){
	javaOp.exit();
}