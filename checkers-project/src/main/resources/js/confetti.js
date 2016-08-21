
// confetti
var ar = 4;
var w = window.innerWidth;
var h = window.innerHeight;
var bottomBoundary = 50;
var topBoundary = h - 50;
var leftBoundary = 50;
var rightBoundary =  w - 50;


function winnerSurprise(){
	displayWinner();
	setTimeout(shootConfetti, 2000);
}

function clearSurprise(){
	$('.confetti').remove();
	$( "#winner" ).hide();
}

function confetti(x,y,rotation,id) {
	$('body').append("<div id='"+ id+"' class='confetti'></div>");
	
	this.xspeed = 0;
	this.yspeed = 0;
	this.ymax;
	this.rotation = rotation;
	this.color = getRandomColor();
	this.height = Math.floor(Math.random() * 5) + 5;
	this.width = this.height + 5;
	this.rotationSpeed = Math.floor(Math.random() * 10);
	this.x = x;
	this.y = y;
	this.id = "#" + id;
	this.intervalId;
	this.grow = false;
	this.fall = false;
	this.flipTimer = 0;
	this.flipTime;
	
	$(this.id).css('height',this.height);
	$(this.id).css('width',this.width);
	$(this.id).css('left',x);
	$(this.id).css('bottom',y);
	$(this.id).css('background-color', this.color);
	
	this.xWeight = function(){
		return Math.cos(toRadians(this.rotation));
	}
	this.yWeight = function(){
		return Math.sin(toRadians(this.rotation));
	}
	this.rotate = function(degrees){
		this.rotation = degrees;
		$(this.id).css({'transform' : 'rotate('+degrees+'deg)'});
	}
	this.move = function(){
		var xMax = 5;
		var xMin = -5;

		if(this.fall){
			this.flipTimer += 1;
			if(this.flipTimer >= this.flipTime){
				if (this.grow){
					this.height = this.width - 5;
					this.grow = false;
				}
				else{
					this.height = 1;
					this.grow = true;
				}

				this.flipTimer = 0;
				$(this.id).css('height', this.height);
			}
						
			this.xspeed += Math.floor((Math.random()*3)) - 1;
			if(this.xspeed > xMax){
				this.xspeed = xMax;
			}
			else if(this.xspeed < xMin){
				this.xspeed = xMin;
			}
		}
		else if(this.y > h/3){
			$(this.id).find('.trail').css('width', ((Math.abs(this.xspeed) + this.yspeed) * 3));
		}
		
		this.x += this.xspeed;
		this.y += this.yspeed;

		$(this.id).css("bottom", this.y);
		$(this.id).css("left", this.x);
	}
	this.remove = function(){
		$(this.id).remove();
	}
	this.decelerate = function(){
		this.yspeed -= this.yWeight() * ar;
		this.xspeed -= this.xWeight() * ar;
		
		if(this.fall == false && (this.xspeed > 0 && this.xWeight() < 0) || (this.xspeed < 0 && this.xWeight() > 0)){
			this.xspeed = 0;
		}
		if(this.yspeed <= this.ymax){
			this.yspeed = this.ymax;
		}
	}
	this.addTrail = function(){
		var confetti = $(this.id);
		
		var rgb = confetti.css('background-color');
		rgb = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
		var red=parseInt(rgb[1]);
		var green=parseInt(rgb[2]);
		var blue=parseInt(rgb[3]);

		confetti.append("<div class='trail'></div>");
		var trail = confetti.find('.trail');
		var rgb = "rgba("+red+","+green+","+blue+",1) 0%";
		var rgbAlpha = "rgba("+red+","+green+","+blue+",0) 100%";
		trail.css('background', "-webkit-linear-gradient(left, "+rgb+", "+rgbAlpha+")");
		trail.css('background', "linear-gradient(to right, "+rgb+", "+rgbAlpha+")");
		trail.css({'transform' : '-webkit-rotate('+ (180 - this.rotation) +'deg)'});
		trail.css({'transform' : 'rotate('+ (180 - this.rotation) +'deg)'});
		trail.css({'transform-origin' : 'left'});
		trail.css({'-webkit-transform-origin' : 'left'});
	}
	this.removeTrail = function(){
		$(this.id).find('.trail').remove();
	}
}

function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++ ) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

function shootConfetti(){
	var cannon = new Audio('other/cannon.wav');
	cannon.play();

	for(var i = 0; i < 50; i++){
		var c = generate(w/2, bottomBoundary, (Math.random()*90) + 45, "confetti"+ i);
		shoot(c);
	}
}

function generate(x,y,rotation,id){
	return new confetti(x,y,rotation,id);
}

function shoot(c){
	var vix;
	var viy;
	var height = (topBoundary - bottomBoundary) - Math.floor(Math.random() * 200);
	var width = (leftBoundary + rightBoundary)/2 - Math.floor(Math.random() * 200);
	
	var rad = getRadius(height, width, c.rotation)
	var mult = 1;
	if(c.xWeight() < 0){
		mult = -1;
	}
	vix = Math.floor(Math.sqrt(c.xWeight() * rad * 2 * ar*c.xWeight())) * mult;
	viy = Math.floor(Math.sqrt(c.yWeight() * rad * 2 * ar*c.yWeight()));
	
	c.xspeed = vix;
	c.yspeed = viy;
	
	moveConfetti(c);
}

function moveConfetti(c){
	var buffer = 20;
	c.ymax = -1*Math.floor(((Math.random()*10)+5));

	c.addTrail();
	c.intervalId = setInterval(function(){
		c.move();
		c.decelerate();

		if(c.xspeed <= 0 && c.yspeed <= 0 && c.fall == false){
			c.removeTrail();
			c.rotate(Math.random()*90);
			c.fall = true;
			c.flipTime = Math.floor((Math.random()*10) + 1);
		}
		else if(c.x - buffer < 0 || c.x + buffer > w || c.y - buffer < 0){
			$(c.id).remove();
			clearInterval(c.intervalId);
		}
	}, 30);
}

function getRadius(height, width, rotation){
	var rad = toRadians(rotation);
	var sin = Math.sin(rad);
	var sinSquared = sin*sin;
	var cos = Math.cos(rad);
	var cosSquared = cos*cos;
	var heightSquared = height*height;
	var widthSquared = width*width
	
	//            a*b
	//  -------------------------
	// sqrt(a^2*cos^2 + b^2*cos^2)
	return (height * width) / Math.sqrt((widthSquared * sinSquared) + (heightSquared*cosSquared));
}

function toRadians (angle) {
	return angle * (Math.PI / 180);
}

// winner text
function displayWinner(){
	var tada = new Audio('other/win31.mp3');
	tada.play();
	$( "#winner" ).show({"effect":"scale", "percent": 100});
}
