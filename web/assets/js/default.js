function ddSetLargePic(id){
    var lPic = document.getElementById("dd-pic-large");
    var src = document.getElementById("dd-pic-"+id).getAttribute("src");
    lPic.setAttribute("src", src);
}