function f()
{
	var a = $("#logo").outerHeight();
	var b = a * 4;
	document.getElementById("logo").style.width = b+"px";

	/*var c = $("textarea").outerWidth(true);
	var d = $(".to_fill").width();
	var h = $("textarea").attr("cols");
	if(c > d)
	{
		$("textarea").attr("cols",h - 1);
	}
	if(d > (c + 10.4))
	{
		$("textarea").attr("cols",h + 1);
	}
	if(c > 1018)
	{
		$("textarea").width("1018px");
	}*/

	var c = $(".to_fill").width();
	var d = c * 0.96 / 10.4;
	$("textarea").attr("cols",d);

	var e = $(".fill_b").width();
	var f = $("#filename").width();
	var g = (e - f) / 2;
	var h = $(".fill_b").height();
	var i = $("#filename").height();
	var j = (h - i) / 2;
	document.getElementById("filename").style.left = g + "px";
	document.getElementById("filename").style.top = j + "px";

	var time = new Date();
	var year = time.getFullYear();
	var month = time.getMonth() + 1;
	document.getElementById("date_year").innerHTML = year;
	document.getElementById("date_month").innerHTML = month;

	setTimeout("f()",100);
}

$("#left_ico").click(function(){
	$(".left").animate({left:"-17%"},500);
	$("#left_ico").css({display:"none"});
	$("#right_ico").css({display:"inline"});
	setTimeout("$('.main').addClass('main_more');",500);
})

$("#right_ico").click(function(){
	$(".left").animate({left:"0"},500);
	$("#left_ico").css({display:"inline"});
	$("#right_ico").css({display:"none"});
	$(".main").removeClass("main_more");
})

$("#toFill").click(function(){
	$("#fill").css({display:"flex"});
	$("#see").css({display:"none"});
	$("#re").css({display:"none"});
})

$("#toSee").click(function(){
	$("#fill").css({display:"none"});
	$("#see").css({display:"flex"});
	$("#re").css({display:"none"});
})

$("#toRe").click(function(){
	$("#fill").css({display:"none"});
	$("#see").css({display:"none"});
	$("#re").css({display:"flex"});
})