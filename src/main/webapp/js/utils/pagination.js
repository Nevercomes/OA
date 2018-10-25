var pageMax;
var leftStatus;
var rightStatus;
var volume = 15;
var activePage;

function setPagination(max) {
	var pagination = $('#page-partition')[0];
	pageMax = max;
	removeAllChild(pagination);
	$('#page-partition').append("<li class='page-item'><a class='page-link' href='#' aria-label='Previous'><span aria-hidden='true'>&laquo;</span><span class='sr-only'>Previous</span></a></li>");
	if(max <= volume) {
		for(var i=1; i<=max; i++) {
			$('#page-partition').append('<li class="page-item"><a class="page-link" href="#">'+i+'</a></li>');
			$("#page-partition li").eq(i).attr("class", "page-item");
		}
	} else {
		for(var i=1; i<=volume-2; i++) {
			$('#page-partition').append('<li class="page-item"><a class="page-link" href="#">'+i+'</a></li>');
			$("#page-partition li").eq(i).attr("class", "page-item");
		}
		$('#page-partition').append('<li class="page-item disabled"><a class="page-link" href="#">...</a></li>');
		$('#page-partition').append('<li class="page-item"><a class="page-link" href="#">'+max+'</a></li>');
	}
	$('#page-partition').append("<li class='page-item'><a class='page-link' href='#' aria-label='Next'><span aria-hidden='true'>&raquo;</span><span class='sr-only'>Next</span></a></li>");
	$("#page-partition li").eq(1).attr("class", "page-item active");
	activePage = 1;
}

// 这里涉及到一个优先的概念
// 距离两端的距离来决定谁需要折叠
function updatePagination(page) {
	var pagination = $('#page-partition')[0];
	removeAllChild(pagination);
	if(pageMax <= volume) {
		setPagination(pageMax);
		return;
	} else {
		setCollapse(page);
		if(!leftStatus && !rightStatus) {
			setPagination(pageMax);
		} else if(!leftStatus && rightStatus) {
			setPagination(pageMax);
		} else if(leftStatus && !rightStatus) {
			$('#page-partition').append("<li class='page-item'><a class='page-link' href='#' aria-label='Previous'><span aria-hidden='true'>&laquo;</span><span class='sr-only'>Previous</span></a></li>");
			$('#page-partition').append('<li class="page-item"><a class="page-link" href="#">1</a></li>');
			$('#page-partition').append('<li class="page-item disabled"><a class="page-link" href="#">...</a></li>');
			var i = pageMax - (volume-2) + 1;
			for(i; i<=pageMax; i++) {
				$('#page-partition').append('<li class="page-item"><a class="page-link" href="#">'+i+'</a></li>');
			}
			$('#page-partition').append("<li class='page-item'><a class='page-link' href='#' aria-label='Next'><span aria-hidden='true'>&raquo;</span><span class='sr-only'>Next</span></a></li>");
		} else if(leftStatus && rightStatus) {
			$('#page-partition').append("<li class='page-item'><a class='page-link' href='#' aria-label='Previous'><span aria-hidden='true'>&laquo;</span><span class='sr-only'>Previous</span></a></li>");
			$('#page-partition').append('<li class="page-item"><a class="page-link" href="#">1</a></li>');
			$('#page-partition').append('<li class="page-item disabled"><a class="page-link" href="#">...</a></li>');
			var i = page - Math.floor((volume-4)/2);
			for(i; i<=page+Math.floor((volume-4)/2); i++) {
				$('#page-partition').append('<li class="page-item"><a class="page-link" href="#">'+i+'</a></li>');
			}
			$('#page-partition').append('<li class="page-item disabled"><a class="page-link" href="#">...</a></li>');
			$('#page-partition').append('<li class="page-item"><a class="page-link" href="#">'+pageMax+'</a></li>');
			$('#page-partition').append("<li class='page-item'><a class='page-link' href='#' aria-label='Next'><span aria-hidden='true'>&raquo;</span><span class='sr-only'>Next</span></a></li>");
		}
	}
}

$(document).ready(function (){
	$('#page-partition').delegate('li', 'click', function() {
		// $("li").attr("class", "page-item");
		// D(activePage);
		var now = $(this).text();
		// D(now);
		// D(now === "«Previous");
		// D(now === "»Next");

		if(now === "«Previous") {
			if(activePage === 1) {
				setLeftDisabled();
				setActivePage(1);
			} else {
				updatePagination(activePage-1);
				setActivePage(activePage-1);
			}
		} else if(now === "»Next") {
			if(activePage !== pageMax) {
				setRightDisabled();
				setActivePage(pageMax);
			} else {
				updatePagination(activePage+1);
				setActivePage(activePage+1);
			}
		} else {
			var j = Number(now);
			if(j === 1) {
				setLeftDisabled();
			} else if(j === pageMax) {
				setRightDisabled();
			}
			updatePagination(j);
			setActivePage(j);
		}
		activePage = Number($("#page-partition li").filter(".active").text());
        var nowTab = getNowTabText();
        console.log(nowTab);
        switch (nowTab) {
            case "绩效考核":
                // onFillTab();
                break;
            case "工作计划":
                // onPlanTab();
                break;
            case "查看结果":
                // onViewTab();
                break;
            case "评估考核":
                setAssessment(activePage-1);
                break;
            case "发布结果":
                // onPublicTab();
                break;
            case "审阅计划":
                setPlan(activePage-1);
                break;
            case "员工信息":
                // onRegisterTab();
                break;
            case "后台管理":
                // onBackTab();
                break;
        }
		// D(activePage);
		// 根据page设置table page-1即为list的索引
	})
});

function getActiveIndex(page) {
	if(!leftStatus && !rightStatus) {
		return page;
	} else if(!leftStatus && rightStatus) {
		return page;
	} else if(leftStatus && !rightStatus) {
		var index = volume - (pageMax-page);
		return index;
	} else if(leftStatus && rightStatus) {
		var index = Math.floor((volume-4)/2) + 3;
		return index;
	}
}

function setActivePage(page) {
	activePage = page;
	var index = getActiveIndex(page);
	$("#page-partition li").filter(".active").attr('class', 'page-item');
	$('#page-partition li').eq(index).attr('class', 'page-item active');
}

function setLeftDisabled() {
	$('#page-partition li').eq(0).attr('class', 'page-item disabled');
}

function setRightDisabled() {
	if(pageMax >= volume)
		$('#page-partition li').eq(volume+1).attr('class', 'page-item disabled');
	else
		$('#page-partition li').eq(pageMax+1).attr('class', 'page-item disabled');
}

function setCollapse(page) {
	if(pageMax <= volume) {
		leftStatus = false;
		rightStatus = false;
	} else {
		if(page+volume-2 > pageMax+1) {
			leftStatus = true;
			rightStatus = false;
		} else if(page+volume-2 == pageMax+1) {
			if(leftStatus==true && rightStatus==true) {
				leftStatus = true;
				rightStatus = false;
			} else if(leftStatus==true && rightStatus==false) {
				leftStatus = true;
				rightStatus = true;
			}
		} else if(page+volume-2<pageMax && page<volume/2) {
			leftStatus = false;
			rightStatus = true;
		} else if(page+volume-2<pageMax && page>=volume/2) {
			leftStatus = true;
			rightStatus = true;
		} 
	}
}

function getActivePage() {
	return activePage;
}

function D(obj) {
	console.log(typeof(obj) + " " + obj);
}

// 分页器显示的数目为，固定数目为十四，左和右，1和max，中间十个通过改变text值来实现
// 采取的分页策略为末尾翻页策略
// 1.判断数量值，如果小于等于12则直接显示
// 2.计算leftDis与rightDis，同时标记leftStatus与rightStatus
// 然后每点一下就判断一下和的距离
// 数量检测 这里似乎有一个有一个复杂一点的公式啊...其实就是通过计算leftDis和rightDis来判断两个status吧
// 获取点击，渲染分页器，设置active，根据active获取数字