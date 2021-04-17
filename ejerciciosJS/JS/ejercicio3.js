function validarn(e){
    var teclado = (document.all)?e.keyCode:e.which;

    if(teclado == 8) return true;

    var patron = /\d/; //aceptaba espacios, ya no

    var prueba = String.fromCharCode(teclado);

    return patron.test(prueba);
}