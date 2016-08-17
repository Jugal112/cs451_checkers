checkersApp.controller('checkersController', function($scope, $sce) {	
	setPaneSize();
	
	$( window ).resize(function() {
		setPaneSize();
	});
	
	$(document).on('click', '.column', function(){
		if(selecting == true){
			selecting = false;
		}
	});		
	
	$('.pane').append(generateBoard());
	
	$(document).ready(function(){
		putPiecesOnBoard();
	});
	
	var selecting = false;
	var selected = null;
	$(document).on('click', '.piece', function(){
		if($(".selected").length == 0){
			var selecting = false;
			var selected = null;
		}

		if(selected != null && $(this).attr('id') == selected.attr('id')){
			selecting = false;
			selected = null;
			$(this).removeClass('selected');
		}
		else{
			selecting = true;
			$('.selected').removeClass('selected');
			$(this).addClass('selected');
			selected = $(this);
		}
	});
	
	$(document).on('click', '.column', function(){
		if($('.selected').length && $(this).children('.piece').length == 0){
			movePiece($('.selected').attr('id'), $(this).attr('id'))
			$('.selected').removeClass('selected');
		}
	});	
});


//functions specifically for checker board

function putPiecesOnBoard(){
	var pieces = JSON.parse(javaOp.getPieces());
	var alphabet = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];
	var color;
	var squareId;
	var bcount = 0;
	var rcount = 0;
	
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