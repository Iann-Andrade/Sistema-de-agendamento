const token = localStorage.getItem("token");

                if (!localStorage.getItem("token")){
                    window.location.href = "login.html"
                }
                

                //Inicia Calendário para agendamento
                document.addEventListener('DOMContentLoaded', function() {
                    const calendarEl = document.getElementById('calendar');
    
                    const calendar = new FullCalendar.Calendar(calendarEl, {
                        initialView: 'dayGridMonth',
                        locale: 'pt-br',
    
                        //Capturar informações do click
                        dateClick: async function(info){
                            
                            dataSelecionada = info.dateStr;
    
                            const horarios = 
                                await buscarHorariosDisponiveis(
                                    dataSelecionada
                                );
    
                            renderizarHorarios(horarios);
                            console.log(info.dateStr);
                            console.log(horarios);
                        }
                    });
                    
                        calendar.render();
                            
                    });

                //variavel para o agendamento
                let dataSelecionada = "";
                let horarioSelecionado = "";
                
                
                //Cria função de verificar horários disponíveis
                async function buscarHorariosDisponiveis(data){
                    
                    const token = localStorage.getItem("token");
                    
                    console.log("Token enviado:", token);
                    
                    
                    const response = await fetch(
                        `http://localhost:8080/agendamentos/horarios-disponiveis?data=${data}`,{

                        method: "GET",
                        headers: {"Authorization": "Bearer " + token}
                    });

                        return await response.json();

                    };

                    



                    
                    function renderizarHorarios(horarios){

                       const container =
                            document.getElementById("horarios");
                        const divListaHorarios = document.getElementById("lista-horarios");
                        
                        container.innerHTML = "";

                        document.getElementById("btn-agendar").style.display = "block";
                        
                        horarios.forEach(horario => {
                            
                            const btn =
                            document.createElement("button");
                            
                            btn.textContent = horario;
                            
                            
                            btn.addEventListener("click", () => {
                                


                                horarioSelecionado = horario;
                                
                                console.log(horarioSelecionado);
                                
                            });
                            
                            container.style.display = "flex";
                            container.appendChild(btn);
                                                    
                        });


                        
                        //Botão de fechar janela de horários disponíveis
                        const fecharLista = 
                        document.createElement("button");
                        
                        fecharLista.textContent = "Fechar lista"
                        
                        fecharLista.addEventListener("click", () =>{

                            document.getElementById("horarios").style.display = "none";
                            document.getElementById("btn-agendar").style.display = "none"
                            fecharLista.style.display = "none";
                            
                        });
                        
                        
                        container.appendChild(fecharLista);


                    }
                    
                    
                    //Botão de criar agendamento
                    const btnAgendar = document.getElementById("btn-agendar");
    
                    btnAgendar.addEventListener("click", criarAgendamento);
                    
                    //Criar agendamento
                    function criarAgendamento(){
                    const agendamento = {
                        data: dataSelecionada,
                        hora: horarioSelecionado,
                    };
                
                    
                    
                fetch("http://localhost:8080/agendamentos",{
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                        "Authorization": "Bearer " + token
                    },

                    body: JSON.stringify(agendamento)
                
                })
                .then(res => res.json())
                .then(data => {
                    console.log(data);
                    listar();
                })
                .then(usuarioId => {
                    console.log("Aqui está o retorno do ID de usuario" + buscarMeusAgendamentos());
                    renderizarMeusAgendamentos();
                })
                .catch(ErrorEvent =>{
                    console.log("deu erro ao criar agendamento");
                });
            }

            async function buscarMeusAgendamentos() {

                const token = localStorage.getItem("token");
                
                const response = await fetch( 
                    "http://localhost:8080/agendamentos/buscar-meus-agendamentos", {
                    method: "GET",
                    headers: {
                        "Authorization": "Bearer " + token
                    }
                });
    
                console.log("Token do buscarmeusagendamentos" + token);

                //Verifica se a API respondeu com status 200-299
                if (!response.ok) {
                    console.error(`Erro na requisição: ${response.status} ${response.statusText}`);
                    return []; // Retorna lista vazia para evitar quebrar o forEach
                }
                
                const listaMeus = await response.json();
                

                return listaMeus;

            };

            //Criar lista dos Meus Agendamentos
            async function renderizarMeusAgendamentos(){
                
                //Captura a div do meus agendamentos
                const divMeusAgendamentos = document.getElementById("div-meus-agendamentos");
                
                //Captura ul dos meus agendamentos
                const listaAgendamentos = document.getElementById("lista-meus-agendamentos");

                //Lmpar a lista antes de renderizar ela
                listaAgendamentos.innerHTML = "";

                const lista = await buscarMeusAgendamentos();

                console.log("Print com o objeto lista: ", lista);

                lista.forEach(agendamento => {
                    
                    //Cria a lista dos agendamentos
                    const meuAgendamento = document.createElement("li");
    
                    const conteudo = document.createElement("p");
    
                    conteudo.innerText = 
                        agendamento.id + " " + 
                        agendamento.hora + " " +
                        agendamento.data;

                    const cancelarMeuAgendamento = document.createElement("button");
                     
                    cancelarMeuAgendamento.className = "cancelar-meu-agendamento";
                    cancelarMeuAgendamento.innerHTML = "Cancelar";
                    const token = localStorage.getItem("token");

                    cancelarMeuAgendamento.addEventListener('click', async function(c){
                        c.preventDefault();
                        c.stopPropagation();

                    const token = localStorage.getItem("token");


                        try{  

                            const response = await fetch(`http://localhost:8080/agendamentos/buscar-meus-agendamentos/${agendamento.id}`,
                               { 
                               method: "DELETE",
                               headers: {
                                   "Content-Type": "application/json",
                                   "Authorization": "Bearer " + token
                               }
                           });

                               console.log("Status cancelamento: " + response.status);
                               
                               if(!response.ok){
    
                                   const err = await response.text();
    
                                   console.log(
                                       "Erro ao cancelar:",
                                       response.status,
                                       err
                                   );
                                   return;
                               }  
                          
                               await renderizarMeusAgendamentos();

                            } catch(err){
                                console.log("Erro na requisição de cancelamento:", err);
                            }
                    
                      });
                
           
                       
                   meuAgendamento.appendChild(conteudo);
                   listaAgendamentos.appendChild(meuAgendamento);
                   meuAgendamento.appendChild(cancelarMeuAgendamento);

                   return listaAgendamentos;
               });

            };

            function listar(){

                fetch("http://localhost:8080/agendamentos",{
                    headers: {
                        "Authorization": "Bearer " + token
                    }
                })
                .then(res => res.json())
                .then(data => {
                    const lista = document.getElementById("lista"); 
                    lista.innerHTML = "";
                    
                    let contador = 1;
                    
                    function gerarId (){
                        return contador++;
                    };
                    
                    data.forEach(a => {

                        //Cria botão de excluir agendamento e adiciona a function
                        const excluirAgendamento = document.createElement("button");
                        excluirAgendamento.innerText = "Excluir";
                        excluirAgendamento.type = "button";

                        excluirAgendamento.onclick = function(e){
                            e.preventDefault();
                            e.stopPropagation();

                            fetch(`http://localhost:8080/agendamentos/${a.id}`, {
                                method: "DELETE",
                                headers: {
                                    "Content-Type": "application/json",
                                    "Authorization": "Bearer " + token
                                }
                            })
                            .then(res => {
                                console.log("STATUS:", res.status);
                                return res.text();
                            })
                            .then(() =>{
                                listar();
                            })
                            .catch(err => console.error(err));
                        };
                        
                        //Cria o botão de confirmar presença
                        const confirmar = document.createElement("button");
                        confirmar.innerText = "Confirmar Presença"
                        //Função para atualizar status do agendamento
                        confirmar.onclick = function(){
                        fetch (`http://localhost:8080/agendamentos/confirmar/${a.id}`, {
                            method: "PUT",
                            headers: {
                                "Authorization": "Bearer " + token
                            }
                        })
                        .then(response =>{
                            if(response.ok){
                                listar();
                            }
                        });
                    };
                        
                        //Cria um elemnto de DIV e pucha o excluirAgendamento
                        const item = document.createElement("div");
                        item.id = a.id;

                        const texto = document.createElement("p");

                        texto.innerText =
                            a.nomeCliente + " - " +
                            a.data + " " +
                            a.hora + " - " +
                            a.descricao + " - " +
                            a.statusDescricao;

                        item.appendChild(texto);
                        item.appendChild(excluirAgendamento);
                        item.appendChild(confirmar);

                        //Cria uma lista ordenada no html
                        const list = document.createElement("li");;
                        
                        //Adiciona item no list
                        list.appendChild(item);

                        //Adicina list ao lista
                        lista.appendChild(list);
                        
                        
                        
                        }
                    )});
                };

                document.addEventListener("DOMContentLoaded", () => {
                    renderizarMeusAgendamentos();
                    listar();
                 
                });