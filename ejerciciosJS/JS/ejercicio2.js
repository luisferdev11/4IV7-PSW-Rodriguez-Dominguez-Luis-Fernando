function validarn(e){
    var teclado = (document.all)?e.keyCode:e.which;

    if(teclado == 8) return true;

    var patron = /[0-9\.]/; //aceptaba espacios, ya no

    var prueba = String.fromCharCode(teclado);

    return patron.test(prueba);
}

function calculo(){
    var sueldo = document.formulario.sueldo.value;
    if(sueldo != ""){
        var total = parseFloat(sueldo);
        var venta1 = document.formulario.venta1.value;
        var venta2 = document.formulario.venta2.value;
        var venta3 = document.formulario.venta3.value;

        var comisiones = (venta1*.1) + (venta2*.1) + (venta3*.1);
        total += comisiones;

        document.formulario.comisionti.value = "$" + comisiones;
        document.formulario.sueldoti.value = "$" + total;
        return;
    }
    alert("Rellena el sueldo base, a menos que seas pobre jksjsk");
}

function borrar(){
    document.getElementById("form_ej1").reset();
}