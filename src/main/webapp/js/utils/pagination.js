var pageMax;
var leftStatus;
var rightStatus
var volumn = 15;
var activePage;

function setPagination(max) {
	var pagination = $('#pgae-partition')[0];
	pageMax = max;
	removeAllChild(pagination);
	$('#pgae-partition').append("<li class='page-item'><a class='page-link' href='#' aria-label='Previous'><span aria-hidden='true'>&laquo;</span><span class='sr-only'>Previous</span></a></li>");
	if(max <= volumn) {
		for(var i=1; i<=max; i++) {
			$('#pgae-partition').append('<li class="page-item"><a class="page-link" href="#">'+i+'</a></li>');
			$("#pgae-partition li").eq(i).attr("class", "page-item");
		}
	} else {
		for(var i=1; i<=volumn-2; i++) {
			$('#pgae-partition').append('<li class="page-item"><a class="page-link" href="#">'+i+'</a></li>');
			$("#pgae-partition li").eq(i).attr("class", "page-item");
		}
		$('#pgae-partition').append('<li class="page-item disabled"><a class="page-link" href="#">...</a></li>');
		$('#pgae-partition').append('<li class="page-item"><a class="page-link" href="#">'+max+'</a></li>');
	}
	$('#pgae-partition').append("<li class='page-item'><a class='page-link' href='#' aria-label='Next'><span aria-hidden='true'>&raquo;</span><span class='sr-only'>Next</span></a></li>");
	$("#pgae-partition li").eq(1).attr("class", "page-item active");
	activePage = 1;
}

// 这里涉及到一个优先的概念
// 距离两端的距离来决定谁需要折叠
function updatePagination(page) {
	var pagination = $('#pgae-partition')[0];
	removeAllChild(pagination);
	if(pageMax <= volumn) {
		setPagination(pageMax);
		return;
	} else {
		setCollapse(page);
		if(!leftStatus && !rightStatus) {
			setPagination(pageMax);
		} else if(!leftStatus && rightStatus) {
			setPagination(pageMax);
		} else if(leftStatus && !rightStatus) {
			$('#pgae-partition').append("<li class='page-item'><a class='page-link' href='#' aria-label='Previous'><span aria-hidden='true'>&laquo;</span><span class='sr-only'>Previous</span></a></li>");
			$('#pgae-partition').append('<li class="page-item"><a class="page-link" href="#">1</a></li>');
			$('#pgae-partition').append('<li class="page-item disabled"><a class="page-link" href="#">...</a></li>');
			var i = pageMax - (volumn-2) + 1;
			for(i; i<=pageMax; i++) {
				$('#pgae-partition').append('<li class="page-item"><a class="page-link" href="#">'+i+'</a></li>');
			}
			$('#pgae-partition').append("<li class='page-item'><a class='page-link' href='#' aria-label='Next'><span aria-hidden='true'>&raquo;</span><span class='sr-only'>Next</span></a></li>");
		} else if(leftStatus && rightStatus) {
			$('#pgae-partition').append("<li class='page-item'><a class='page-link' href='#' aria-label='Previous'><span aria-hidden='true'>&laquo;</span><span class='sr-only'>Previous</span></a></li>");
			$('#pgae-partition').append('<li class="page-item"><a class="page-link" href="#">1</a></li>');
			$('#pgae-partition').append('<li class="page-item disabled"><a class="page-link" href="#">...</a></li>');
			var i = page - Math.floor((volumn-4)/2);
			for(i; i<=page+Math.floor((volumn-4)/2); i++) {
				$('#pgae-partition').append('<li class="page-item"><a class="page-link" href="#">'+i+'</a></li>');
			}
			$('#pgae-partition').append('<li class="page-item disabled"><a class="page-link" href="#">...</a></li>');
			$('#pgae-partition').append('<li class="page-item"><a class="page-link" href="#">'+pageMax+'</a></li>');
			$('#pgae-partition').append("<li class='page-item'><a class='page-link' href='#' aria-label='Next'><span aria-hidden='true'>&raquo;</span><span class='sr-only'>Next</span></a></li>");
		}
	}
}

$(document).ready(function (){
	$('#pgae-partition').delegate('li', 'click', function() {
		// $("li").attr("class", "page-item");
		D(activePage);
		var now = $(this).text();
		D(now);

		D(now == "«Previous");
		D(now == "»Next");

		if(now == "«Previous") {
			if(activePage == 1) {
				setLeftDisabled();
				setActivePage(1);
			} else {
				updatePagination(activePage-1);
				setActivePage(activePage-1);
			}
		} else if(now == "»Next") {
			if(activePage != pageMax) {
				setRightDisabled();
				setActivePage(pageMax);
			} else {
				updatePagination(activePage+1);
				setActivePage(activePage+1);
			}
		} else {
			var j = Number(now);
			if(j == 1) {
				setLeftDisabled();
			} else if(j == pageMax) {
				setRightDisabled();
			}
			updatePagination(j);
			setActivePage(j);
		}
		activePage = Number($("#pgae-partition li").filter(".active").text());
		D(activePage);
		// 根据page设置table page-1即为list的索引
	})
})

function getActiveIndex(page) {
	if(!leftStatus && !rightStatus) {
		return page;
	} else if(!leftStatus && rightStatus) {
		return page;
	} else if(leftStatus && !rightStatus) {
		var index = volumn - (pageMax-page);
		return index;
	} else if(leftStatus && rightStatus) {
		var index = Math.floor((volumn-4)/2) + 3;
		return index;
	}
}

function setActivePage(page) {
	activePage = page;
	var index = getActiveIndex(page);
	$("#pgae-partition li").filter(".active").attr('class', 'page-item');
	$('#pgae-partition li').eq(index).attr('class', 'page-item active');
}

function setLeftDisabled() {
	$('#pgae-partition li').eq(0).attr('class', 'page-item disabled');
}

function setRightDisabled() {
	if(pageMax >= volumn)
		$('#pgae-partition li').eq(volumn+1).attr('class', 'page-item disabled');
	else
		$('#pgae-partition li').eq(pageMax+1).attr('class', 'page-item disabled');
}

function setCollapse(page) {
	if(pageMax <= volumn) {
		leftStatus = false;
		rightStatus = false;
	} else {
		if(page+volumn-2 > pageMax+1) {
			leftStatus = true;
			rightStatus = false;
		} else if(page+volumn-2 == pageMax+1) {
			if(leftStatus==true && rightStatus==true) {
				leftStatus = true;
				rightStatus = false;
			} else if(leftStatus==true && rightStatus==false) {
				leftStatus = true;
				rightStatus = true;
			}
		} else if(page+volumn-2<pageMax && page<volumn/2) {
			leftStatus = false;
			rightStatus = true;
		} else if(page+volumn-2<pageMax && page>=volumn/2) {
			leftStatus = true;
			rightStatus = true;
		} 
	}
}

function getActivePage() {
	return activePage;
}

// 分页器显示的数目为，固定数目为十四，左和右，1和max，中间十个通过改变text值来实现
// 采取的分页策略为末尾翻页策略
// 1.判断数量值，如果小于等于12则直接显示
// 2.计算leftDis与rightDis，同时标记leftStatus与rightStatus
// 然后每点一下就判断一下和的距离
// 数量检测 这里似乎有一个有一个复杂一点的公式啊...其实就是通过计算leftDis和rightDis来判断两个status吧
// 获取点击，渲染分页器，设置active，根据active获取数字