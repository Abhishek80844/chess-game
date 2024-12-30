/**
 * paintSquare
 * @param document
 * @param square
 * @param hasImage
 * @param pathImage
 */
function paintSquare(document, square, piece, hasImage, pathImage) {
    var element = document.createElement("div");
    element.setAttribute("id", square);
    element.setAttribute("name", piece);
    element.onclick = this.printPiece;
    if (hasImage) {
        var myloc = new Image();
        myloc.useMap = pathImage;
        var img = document.createElement('img');
        img.setAttribute('src', myloc.useMap);
        element.appendChild(img);
    }
    document.body.appendChild(element);
}
function printPiece(sender, event) {
    alert(sender.getAttribute("id"));
}
document.title = "VulnChess";
document.body.innerHTML = "VulnChess";
var vertical = ["1", "2", "3", "4", "5", "6", "7", "8"];
var horizontal = ["a", "b", "c", "d", "e", "f", "g", "h"];
var i;
var j;
for (var _i = 0, horizontal_1 = horizontal; _i < horizontal_1.length; _i++) {
    i = horizontal_1[_i];
    for (var _a = 0, vertical_1 = vertical; _a < vertical_1.length; _a++) {
        j = vertical_1[_a];
        var square = i + j;
        var color = "w";
        var pathImage = "";
        //Choose color
        if (square.charAt(1) === '8' || square.charAt(1) === '7') {
            color = "b";
        }
        else {
            color = "w";
        }
        //paint squares
        if (square === "a8" || square === "h8" || square === "a1" || square === "h1") {
            pathImage = "../images/tower" + color + ".png";
            paintSquare(document, square, "tower", true, pathImage);
        }
        else if (square === "b8" || square === "g8" || square === "b1" || square === "g1") {
            pathImage = "../images/knight" + color + ".png";
            paintSquare(document, square, "knight", true, pathImage);
        }
        else if (square === "c8" || square === "f8" || square === "c1" || square === "f1") {
            pathImage = "../images/bishop" + color + ".png";
            paintSquare(document, square, "bishop", true, pathImage);
        }
        else if (square === "d8" || square === "d1") {
            pathImage = "../images/queen" + color + ".png";
            paintSquare(document, square, "queen", true, pathImage);
        }
        else if (square === "e8" || square === "e1") {
            pathImage = "../images/king" + color + ".png";
            paintSquare(document, square, "king", true, pathImage);
        }
        else if (square.charAt(1) === "7" || square.charAt(1) === "2") {
            pathImage = "../images/pawn" + color + ".png";
            paintSquare(document, square, "pawn", true, pathImage);
        }
        else {
            paintSquare(document, square, "", false, null);
        }
    }
}
