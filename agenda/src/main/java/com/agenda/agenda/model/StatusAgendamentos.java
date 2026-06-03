package com.agenda.agenda.model;

import com.fasterxml.jackson.annotation.JsonValue;

    

//Model, cria uma lista com valores pré definidos em formato de string
    public enum StatusAgendamentos{

        PENDENTE("Pendente", false),
        CONFIRMADO("Confirmado", true),
        EM_ANDAMENTO("Em andamento", true),
        CONCLUIDO("Concluído", true),
        CANCELADO("Cancelado", false),
        NAO_COMPARECEU("Não compareceu", false);

        private String statusDescricao;
        private boolean ativo;


        StatusAgendamentos(String statusDescricao, boolean ativo){
            this.statusDescricao = statusDescricao;
            this.ativo = ativo;
        }

        @JsonValue
        public String getStatusDescricao() {
            return statusDescricao;
        }
        
        public boolean isAtivo(){
            return ativo;
        }
        
    }




