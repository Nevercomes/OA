function getLastMonth() {
    var mDate = new Date();
    var lastMonth = mDate.getMonth();
    if(lastMonth === 0)
        return 12;
    else
        return lastMonth;
}

function getCurrentMonth() {
    var mDate = new Date();
    return mDate.getMonth() + 1;
}

function getCurrentDate() {
    var mDate = new Date();
    return mDate.toLocaleDateString();
}