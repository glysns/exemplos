CREATE or REPLACE VIEW apl_avaliacao.vw_questao_print AS
 select pergunta, 
'A) : ' || alternativa_a as alternativa_a, 
'B) : ' || alternativa_b as alternativa_b,
'C) : ' || alternativa_c as alternativa_c,
'D) : ' || alternativa_d as alternativa_d,
resposta, justificativa, cd_tema, tt.nome as tema, tq.id,
ROW_NUMBER () OVER (ORDER BY tq.id) as numero
from apl_avaliacao.tab_questao tq inner join apl_avaliacao.tab_tema tt on tq.cd_tema = tt.id
order by tt.id, tq.id; 