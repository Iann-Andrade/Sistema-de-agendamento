package com.agenda.agenda.model;
    


    public enum StatusAgendamentos{

        PENDENTE("Pendente", false),
        CONFIRMADO("Confirmado", true),
        EM_ANDAMENTO("Em andamento", true),
        CONCLUIDO("Concluído", true),
        CANCELADO("Cancelado", false),
        NAO_COMPARECEU("Não compareceu", false);

        private String descricao;
        private boolean ativo;


        StatusAgendamentos(String descricao, boolean ativo){
            this.descricao = descricao;
            this.ativo = ativo;
        }

        public String getDescricao() {
            return descricao;
        }
        
        public boolean isAtivo(){
            return ativo;
        }
        
    }




