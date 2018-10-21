var tNames = ["department", "staffName", "position", "score", "rank"];


function showResult(rsList) {
    var resultTBody0 = $('#tbody-result')[0];
    var resultTBody = $('#tbody-result')[0];
    removeAllChild(resultTBody0);
    for(var i=0; i<rsList.length; i++) {
        var tr = getRow(rsList[i]);
        resultTBody.appendChild(tr);
    }
}

function removeAllChild(parentNode) {
    while (parentNode.hasChildNodes()) {
        parentNode.removeChild(parentNode.firstChild);
    }
}

function getRow(item) {
    var tr = document.createElement("tr");
    for(var i=0; i<tNames.length; i++) {
        var td = document.createElement("td");
        td.innerHTML = item[tNames[i]];
        tr.appendChild(td);
    }
    return tr;
}