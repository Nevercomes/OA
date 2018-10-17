function f()
{
	var a = $(".logo").outerHeight();
	var b = $(".board").outerHeight();
	var c = b -a;
	document.getElementById("form").style.height = c+"px";

	var d = $(".username").outerWidth();
	var e = $(".ico").outerWidth(true);
	var f = d - e;
	document.getElementById("input1").style.width = f+"px";
	document.getElementById("input2").style.width = f+"px";

	setTimeout("f()",100);
}

function guanli()
{
	$(".board").css("height","77%");
	$(".username").css("margin-top","3%");
	$("#guan").css("display","flex");
	$("#b_p").addClass("rec_n");
	$("#b_g").removeClass("rec_n");
	$("#b_g").addClass("rec_y");
}

function putong()
{
	$(".board").css("height","65%");
	$(".username").css("margin-top","3%");
	$("#guan").css("display","none");
	$("#b_g").addClass("rec_n");
	$("#b_p").removeClass("rec_n");
	$("#b_p").addClass("rec_y");
}