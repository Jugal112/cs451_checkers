var turn;
var color;

//runs on entering checkerboard page
checkersApp.controller('checkersController', function($scope, $sce) {
	setupBoardUI();
	setupPieceMovement();
	setupGame();
});


// Functions for checkerboard controller

function setWinner(winner){
	turn == null;

	if(winner == color){
		$('.menu #text').text("YOU WIN!");
	}
	else{
		$('.menu #text').text("YOU LOSE!");
	}
	
	$(".menu #playAgain").show();
}

function setTurn(t){
	turn = t;
	whoseTurn();
}

function warn(message){
	$("#warn").text(message);
}

function kingMe(squareId){
	$("#" + squareId).find(".piece").addClass('king');
}

function setupGame(){
	$('.menu #text').text("Initializing...");
	javaOp.initializeGame(networkingRole);
}

function startGame(c){
	turn = Colors.BLACK;
	color = Colors[c];
	whoseTurn();
        if(turn != color){
           console.log("About to start waiting for opponent turn!");
           javaOp.waitForOpponent();
	}
}

function whoseTurn(){
	if(turn == color){
		$('.menu #text').text("Your Turn");
	}
	else{
		$('.menu #text').text("Opponent's Turn");
	}
}

function warn(){
	
}

function setupPieceMovement(){
	var selecting = false;
	var selected = null;
        var orig_pos = null;
	$(document).on('click', '.piece', function(){
		console.log(color);
		if(turn == color && $(this).hasClass(color.toLowerCase())){
			if($(".selected").length == 0){
				var selecting = false;
				var selected = null;
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

        $("[id^=red]").remove();
	$("[id^=black]").remove();
	
	for(row in pieces){
		for(column in pieces[row]){
			color = pieces[row][column]
			squareId = alphabet[row] + column.toString();

			if(color == "r"){
				createPiece("red"+rcount.toString(), "red", squareId);
				rcount += 1;
			}
			else if(color == "b"){
				createPiece("black"+bcount.toString(), "black", squareId);
				bcount += 1;
			}
		}
	}
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
