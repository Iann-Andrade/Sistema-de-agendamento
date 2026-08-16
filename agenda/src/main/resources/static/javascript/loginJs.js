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
                "Content-Type":"Application/json"
            },
            body: JSON.stringify(usuario)
        })
        .then(response => {
            if(!response.ok){
                throw new Error("Login inválido." + response.status);
            }

            return response.text();
        })
        .then(token => {

            localStorage.setItem("token", token);

            window.location.href = "http://localhost:8080/html/index.html";
            
        })
        
 }
