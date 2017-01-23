function ddSetLargePic(id){
    var lPic = document.getElementById("dd-pic-large");
    var src = document.getElementById("dd-pic-"+id).getAttribute("src");
    lPic.setAttribute("src", src);
}

function removeFavourite(id){
    // delete list entry
    var item = document.getElementById("fav-item-"+id).outerHTML="";
    delete item;
    
    // Process coookies:
    // get cookies and split by ';'
    var cookies = document.cookie.split(';');
    // on each iteration split by '=' and compare second elem.
    for(i=0;i<cookies.length;i++){
        var ck = cookies[i].split('=');
        if(ck[1] == id){
            document.cookie = ck[0]+"=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
            break;
        }
    }
    // Adjust favourites count:
    var favSpan = document.getElementById("fav-count");
    var favCount = favSpan.innerHTML;
    favSpan.innerHTML = favCount - 1;
    
}
