// if(typeof DEPARTMENTCODE == undefined){
    var DEPARTMENTCODE = {};
    DEPARTMENTCODE.ALL = 0;
    DEPARTMENTCODE.RD = 1;
    DEPARTMENTCODE.NETWORKS = 2;
    DEPARTMENTCODE.ART = 3;
// }

// if(typeof DEPARTMENTSTR == undefined){
    var DEPARTMENTSTR = {};
    DEPARTMENTSTR.ALL = "中心";
    DEPARTMENTSTR.RD = "研发部";
    DEPARTMENTSTR.NETWORKS = "网络部";
    DEPARTMENTSTR.ART = "美工部";
// }

// if(typeof POSITIONCODE == undefined){
    var POSITIONCODE = {};
    POSITIONCODE.STAFF = 0;
    POSITIONCODE.INSTURCTOR = 1;
    POSITIONCODE.DIRECTOR = 2;
    POSITIONCODE.HEAD = 3;
    POSITIONCODE.VICEHEAD = 4;
    POSITIONCODE.GROUP = 5;
// }

// if(typeof POSITIONSTR == undefined){
    var POSITIONSTR = {};
    POSITIONSTR.STAFF = "员工";
    POSITIONSTR.INSTURCTOR = "指导老师";
    POSITIONSTR.DIRECTOR = "总监";
    POSITIONSTR.HEAD = "部长";
    POSITIONSTR.VICEHEAD = "副部长";
    POSITIONSTR.GROUP = "组长";
// }

function getDepartmentStr(code) {
    switch (code) {
        case 0:
            return DEPARTMENTSTR.ALL;
        case 1:
            return DEPARTMENTSTR.RD;
        case 2:
            return DEPARTMENTSTR.NETWORKS;
        case 3:
            return DEPARTMENTSTR.ART;
    }
}

function getDepartmentCode(str) {
    switch (str) {
        case "中心":
            return DEPARTMENTCODE.ALL;
        case "研发":
            return DEPARTMENTCODE.RD;
        case "网络":
            return DEPARTMENTCODE.NETWORKS;
        case "美工":
            return DEPARTMENTCODE.ART;
        default :
            return DEPARTMENTCODE.ALL;
    }
}

function getPositionCode(str) {
    switch (str) {
        case "员工":
            return POSITIONCODE.STAFF;
        case "指导老师":
            return POSITIONCODE.INSTURCTOR;
        case "总监":
            return POSITIONCODE.DIRECTOR;
        case "部长":
            return POSITIONCODE.HEAD;
        case "副部长":
            return POSITIONCODE.VICEHEAD;
        case "小组长":
            return POSITIONCODE.GROUP;
    }
}

function getMonthCode(str) {
    switch (str) {

    }
}





