function decode() {
    const encodedURI = "https://www.fp-informatica.com";
    const decodedURI = decodeURI(encodedURI);
    return decodedURI;
}
console.log(decode());