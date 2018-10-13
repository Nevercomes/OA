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