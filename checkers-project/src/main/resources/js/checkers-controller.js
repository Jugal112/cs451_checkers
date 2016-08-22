var turn;
var color;


//runs on entering checkerboard page
checkersApp.controller('checkersController', function($scope, $sce) {
	setupGame();
	setupBoardUI();
	setupPieceMovement();
});


// Functions for checkerboard controller

function setWinner(winner){
	turn == null;

	if(winner == color){
		$('.menu #text').text("YOU WIN!");
		winnerSurprise();
	}
	else{
		$('.menu #text').text("YOU LOSE!");
	}
	
	$(".menu #restart button").show();
}

function setTurn(t){
        console.log("Turn: " + turn);
	turn = t;
	whoseTurn();
}

function switchTurn(){
    if(turn == Colors.RED) turn = Colors.BLACK;
    else turn = Colors.RED;
    whoseTurn();
}
function warn(message){
	$("#warn").text(message);
}

function highlightSquare(squareId) {
    $("#" + squareId).addClass('highlighted');
}

function unhighlightSquare(squareId) {
    $("#" + squareId).removeClass('highlighted');
}

function unhighlightAll(){
	console.log("unhighlightAll");
	var pieces = JSON.parse(javaOp.getPieces());
	var alphabet = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];
	var squareId;
	
	for(row in pieces){
		for(column in pieces[row]){
			squareId = alphabet[row] + column.toString();
            unhighlightSquare(squareId);
		}
	}
}

function highlightValidMoves(){
	console.log(javaOp.getValidMoves());
    var validMoves = JSON.parse(javaOp.getValidMoves());
    console.log(validMoves);
    for (move in validMoves) {
    	console.log(JSON.parse(validMoves[move])[1]);
        highlightSquare(JSON.parse(validMoves[move])[1]);
    }
}

function kingMe(squareId){
	$("#" + squareId).find(".piece").addClass('king');
}

function setupGame(){
	$('.menu #text').text("Waiting for Player...");
	javaOp.initializeGame(networkingRole);
}

function startGame(c){
	putPiecesOnBoard();
	turn = Colors.BLACK;
	color = Colors[c];
	whoseTurn();
        if(turn != color){
           console.log("About to start waiting for opponent turn!");
           
	}
}

function whoseTurn(){
	unhighlightAll();
	if(turn == color){
		$('.menu #text').text("Your Turn");
		$('.menu #color').text("You are " + color);
		highlightValidMoves();
		javaOp.checkGameStatus();
	}
	else{
		$('.menu #text').text("Opponent's Turn");
		$('.menu #color').text("You are " + color);
        javaOp.waitForOpponent();
	}
}

function setupPieceMovement(){
	var selecting = false;
	var selected = null;
        var orig_pos = null;
	$(document).on('click', '.piece', function(){
		console.log(color);
		if(turn == color && $(this).hasClass(color.toLowerCase())){
			if($(".selected").length == 0){
				selecting = false;
				selected = null;
				orig_pos = null;
			}
		
			if(selected != null && $(this).attr('id') == selected.attr('id')){
				selecting = false;
				selected = null;
				$(this).removeClass('selected');
				orig_pos = null;
			}
			else{
				selecting = true;
				$('.selected').removeClass('selected');
				$(this).addClass('selected');
				selected = $(this);
				orig_pos = $(this).parent().attr('id');
                                console.log(orig_pos)
			}
		}
	});
	
	$(document).on('click', '.column', function(){
		if($('.selected').length && $(this).children('.piece').length == 0){
			movePiece($('.selected').attr('id'), $(this).attr('id'))
                        javaOp.debug(orig_pos + " " + $(this).attr('id'));
                        javaOp.sendMove(orig_pos + " " + $(this).attr('id'));
			$('.selected').removeClass('selected');
		}
	});	
}
	
function setupBoardUI(){
	setPaneSize();
	
	$( window ).resize(function() {
		setPaneSize();
	});
	
	$('.pane').append(generateBoard());
	
	$(document).on('click', '#restart button', function(){
		$(this).hide();
		setupGame();
		clearSurprise();
	});
	
	$(document).ready(function(){
		putPiecesOnBoard();
	});
}

function putPiecesOnBoard(){
    console.log("putPiecesOnBoard");
	var pieces = JSON.parse(javaOp.getPieces());
	var alphabet = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];
	var color;
	var squareId;
	var bcount = 0;
	var rcount = 0;

    $(".red").remove();
	$(".black").remove();
	for(row in pieces){
		for(column in pieces[row]){
			color = pieces[row][column]
			squareId = alphabet[row] + column.toString();
			if(color == "RED"){
				createPiece("red"+rcount.toString(), "red", squareId);
				rcount += 1;
			}
			else if(color == "RED_KING"){
                createPiece("red"+rcount.toString(), "red", squareId);
                rcount += 1;
                kingMe(squareId);
			}
			else if(color == "BLACK"){
				createPiece("black"+bcount.toString(), "black", squareId);
				bcount += 1;
			}
			else if(color == "BLACK_KING"){
				createPiece("black"+bcount.toString(), "black", squareId);
				bcount += 1;
				kingMe(squareId);
			}
		}
	}
	/*
    if (turn != color) {
        console.log(javaOp.getValidMoves());
        var validMoves = JSON.parse(javaOp.getValidMoves());
        console.log(validMoves);
        for (move in validMoves) {
            console.log(JSON.parse(validMoves[move])[1]);
            highlightSquare(JSON.parse(validMoves[move])[1]);
        }
    }
    */

}

function getPiece(id){
	return $("#"+id);
}

function getSquare(id){
	return $("#"+id);
}

function movePiece(id, squareId){
	var piece = getPiece(id);
	getSquare(squareId).append(piece.clone());
	piece.remove();
}

function createPiece(id, color, squareId){
	getSquare(squareId).append("<div id='" + id + "' class='piece " + color + "'></div>");
}

function removePiece(id, color){
	$("." + color + " #"+id).remove();
}

function emptySquare(id) {
    getSquare(squareId).empty();
}

function generateBoard(){
	var board = "";
	var alphabet = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];

	for(var i = 0; i < 8; i++){
		board += "<div class='row'>\n";
		for(var j = 0; j < 8; j++){
			var location = alphabet[i] + j.toString();
			
			if((i+j)%2 == 0){
				board += "<div class='light column' id='" + location + "'></div>\n";
			}
			else{
				board += "<div class='dark column' id='" + location + "'></div>\n";
			}
		}
		board += "</div>\n";
	}
	return board
}

function setPaneSize(){
	var boardHeight = $(".board").height();
	var boardWidth = $(".board").width();
	var size;
	
	if(boardHeight > boardWidth){
		size = boardWidth * 0.99;
	}
	else{
		size = boardHeight * 0.99;
	}
	$('.pane').css({
	    'width': size + 'px'
	});
	
	$('.pane').css({
	    'height': size + 'px'
	});
}