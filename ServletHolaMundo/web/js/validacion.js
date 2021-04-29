const formulario = document.getElementById('form');
const inputs = document.querySelectorAll('#form input');

const expresiones = {
    nombre: /^[a-zA-ZÀ-ÿ]{4,20}$/, // Letras y pueden llevar acentos.
    appat: /^[a-zA-ZÀ-ÿ]{4,20}$/,
    apmat: /^[a-zA-ZÀ-ÿ]{4,20}$/,
    correo: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+{5,29}$/,
    edad: /^\d{1,2}$/ // 1 a 2 numeros.
}

const campos = {
    nombre: false,
    appat: false,
    apmat: false,
    correo: false,
    edad: false
}

const validarFormulario = (e) => {
	switch (e.target.name) {
		case "nombre":
			validarCampo(expresiones.nombre, e.target, 'nombre');
		break;
		case "appat":
			validarCampo(expresiones.appat, e.target, 'appat');
		break;
		case "apmat":
			validarCampo(expresiones.apmat, e.target, 'apmat');
		break;
		case "correo":
			validarCampo(expresiones.correo, e.target, 'correo');
		break;
                case "edad":
			validarCampo(expresiones.tedad, e.target, 'edad');
		break;
	}
}


const validarCampo = (expresion, input, campo) => {
	if(expresion.test(input.value)){
		campos[campo] = true;
	} else {
		campos[campo] = false;
                alert("Estas llenando algun campo incorrecto");
	}
}


formulario.addEventListener('submit', (e) => {

	if(campos.nombre && campos.appat && campos.apmat && campos.correo && campos.edad){
            validarFormulario(e);
            alert("datos correctos");
	} else {
	    alert("faltan datos");
	}
});
