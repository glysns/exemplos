DROP VIEW IF EXISTS apl_avaliacao.vw_questao_print;

CREATE OR REPLACE VIEW apl_avaliacao.vw_questao_print
AS SELECT tq.pergunta,
    'A) : '::text || tq.alternativa_a::text AS alternativa_a,
    'B) : '::text || tq.alternativa_b::text AS alternativa_b,
    'C) : '::text || tq.alternativa_c::text AS alternativa_c,
    'D) : '::text || tq.alternativa_d::text AS alternativa_d,
    tq.resposta,
    tq.justificativa,
    tq.cd_tema,
    tt.nome AS tema,
    tq.id,
    tq.en_nivel as nivel
   FROM apl_avaliacao.tab_questao tq
     JOIN apl_avaliacao.tab_tema tt ON tq.cd_tema = tt.id
  ORDER BY tt.id, tq.id;

