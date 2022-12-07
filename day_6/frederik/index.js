const fs = require("fs");
const data = fs.readFileSync("./input.txt", "utf8");

function findMarker(bufferSize) {
  for (let i = bufferSize; i < data.length; i++) {
    const buffer = data.substring(i - bufferSize, i);
    if (new Set(buffer).size === buffer.length) {
      return i;
    }
  }
}

console.log(findMarker(4));
console.log(findMarker(14));
