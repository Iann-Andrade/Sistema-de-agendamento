function logar(){
    event.preventDefault();


    let usuario = {
        emailUsuario: document.getElementById("emailUsuario").value,
        senhaUsuario: document.getElementById("senhaUsuario").value
    };

    if(!emailUsuario || !senhaUsuario){
        alert("Preencha todos os campos do login.")
        return;
    }

    fetch("http://localhost:8080/agendamentos/login",{
            method: "POST",
            headers: {
                "Content-Type":"Application/json",
            },
            body: JSON.stringify(usuario)
        })
        .then(Response => {   

            return Response.json();

        })
        .then(usuario => {

            console.log("antes do if");

            if(Response = usuario){
                console.log("ficou pelo if");

                window.location.href = `http://localhost:8080/`;
            };
            console.log("passou pelo if");

        })
        
 }