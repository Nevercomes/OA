var tNames = ["department", "staffName", "position", "score", "rank"];

function showResult(data) {
    var resultTBody = $('#tbody-result')[0];
    removeAllChild(resultTBody);
    console.log("data: " + data.length);
    for(var i=0; i<data.length; i++) {
        console.log("showDepResult");
        console.log("data[i]: " +  data[i].length);
        showDepResult(data[i]);
    }
}

function showDepResult(rsList) {
    var resultTBody = $('#tbody-result')[0];
    var depTr = document.createElement("tr");
    var depTd = document.createElement("td");
    depTd.rowSpan = rsList.length;
    depTd.innerHTML = rsList[0][tNames[0]];
    depTr.appendChild(depTd);
    console.log("rsList: " +  rsList.length);
    for(var k=1; k<tNames.length; k++) {
        var td = document.createElement("td");
        td.innerHTML = rsList[0][tNames[k]];
        depTr.appendChild(td);
    }
    resultTBody.appendChild(depTr);
    for(var i=1; i<rsList.length; i++) {
        var tr = getRow(rsList[i]);
        resultTBody.appendChild(tr);
    }
}

function getRow(item) {
    var tr = document.createElement("tr");
    for(var i=1; i<tNames.length; i++) {
        var td = document.createElement("td");
        td.innerHTML = item[tNames[i]];
        tr.appendChild(td);
    }
    return tr;
}

function removeAllChild(parentNode) {
    while (parentNode.hasChildNodes()) {
        parentNode.removeChild(parentNode.firstChild);
    }
}

// var resultTBody0 = $('#tbody-result')[0];
// var resultTBody = $('#tbody-result')[0];
// removeAllChild(resultTBody0);

// resultTBody.append(
//     "<tr>" +
//     "<td rowspan="+ rsList.length +">" + " + rsList[0][tNames[0]] + " +"</td>" +
//     "</tr>"
// );