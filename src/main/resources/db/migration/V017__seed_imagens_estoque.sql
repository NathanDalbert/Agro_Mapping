-- =====================================================================
-- Seed: imagens reais + estoque para os produtos do seed (V016)
-- ---------------------------------------------------------------------
-- 1) Atualiza produto.imagem para URLs de fotos reais (loremflickr).
--    O app (image_helper.dart) renderiza URL via Image.network.
-- 2) Cria uma linha de estoque (quantidade_disponivel > 0) para cada
--    produto do feirante feirante@agromapping.com.
--
-- Idempotente:
--  - UPDATE de imagem: reescreve as URLs (seguro reexecutar).
--  - INSERT de estoque: WHERE NOT EXISTS evita duplicar estoque para
--    o mesmo produto (a relação OneToOne é 1:1).
-- =====================================================================

-- 1) Imagens reais por produto
UPDATE produto
SET imagem = CASE nome
    WHEN 'Tomate'    THEN 'https://loremflickr.com/400/400/tomato'
    WHEN 'Cenoura'   THEN 'https://loremflickr.com/400/400/carrot'
    WHEN 'Alface'    THEN 'https://loremflickr.com/400/400/lettuce'
    WHEN 'Batata'    THEN 'https://loremflickr.com/400/400/potato'
    WHEN 'Maçã'      THEN 'https://loremflickr.com/400/400/apple'
    WHEN 'Banana'    THEN 'https://loremflickr.com/400/400/banana'
    WHEN 'Laranja'   THEN 'https://loremflickr.com/400/400/orange'
    WHEN 'Mamão'     THEN 'https://loremflickr.com/400/400/papaya'
    WHEN 'Morango'   THEN 'https://loremflickr.com/400/400/strawberry'
    WHEN 'Pimentão'  THEN 'https://loremflickr.com/400/400/pepper,vegetable'
    WHEN 'Couve'     THEN 'https://loremflickr.com/400/400/kale'
    WHEN 'Abobrinha' THEN 'https://loremflickr.com/400/400/zucchini'
END
WHERE id_usuario = (SELECT id_usuario FROM usuario WHERE email = 'feirante@agromapping.com')
  AND nome IN ('Tomate','Cenoura','Alface','Batata','Maçã','Banana','Laranja','Mamão','Morango','Pimentão','Couve','Abobrinha');

-- 2) Estoque por produto (idempotente)
INSERT INTO estoque (id_produto, quantidade_disponivel)
SELECT p.id_produto, v.qtd::integer
FROM (
    VALUES
        ('Tomate',    50),
        ('Cenoura',   40),
        ('Alface',    30),
        ('Batata',    60),
        ('Maçã',      45),
        ('Banana',    55),
        ('Laranja',   50),
        ('Mamão',     25),
        ('Morango',   20),
        ('Pimentão',  35),
        ('Couve',     30),
        ('Abobrinha', 28)
) AS v(nome, qtd)
JOIN produto p
  ON p.nome = v.nome
 AND p.id_usuario = (SELECT id_usuario FROM usuario WHERE email = 'feirante@agromapping.com')
WHERE NOT EXISTS (
    SELECT 1 FROM estoque e WHERE e.id_produto = p.id_produto
);
