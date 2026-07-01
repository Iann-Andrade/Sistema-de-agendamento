function cadastrarUsuario(){

    event.preventDefault();
    console.log("¨66");


    let usuario = {

    emailUsuario: document.getElementById("emailUsuario").value,
    senhaUsuario: document.getElementById("senhaUsuario").value

    };
    

    fetch("http://localhost:8080/agendamentos/cadastrar-usuario", {
        method: "POST",
        headers: {
            "Content-Type":"application/json"
        },
            body: JSON.stringify(usuario),
    })
    .then(resposta =>{
        if(!resposta.ok){
            throw new Error("Erro no servidor: Status " + resposta.status);
        };
        
        return resposta.json();

    })

    .then(retornoCadastro => {
        console.log("Sucesso!", retornoCadastro);
        window.location.href = "http://localhost:8080/login.html";
     });

}