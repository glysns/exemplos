DROP VIEW IF EXISTS apl_avaliacao.vw_questao;

CREATE OR REPLACE VIEW apl_avaliacao.vw_questao AS
select tq.*, tt.nome as tema_nome, tt.sigla as tema_sigla 
from apl_avaliacao.tab_questao tq inner join apl_avaliacao.tab_tema tt on tq.cd_tema = tt.id; 