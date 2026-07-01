-- =====================================================================
-- Seed: produtos de exemplo vinculados ao SELLER do seed (V015)
-- ---------------------------------------------------------------------
-- Todos os produtos ficam associados ao feirante:
--   feirante@agromapping.com  (criado em V015__seed_users.sql)
--
-- id_feira fica NULL (não há seed de feira); a coluna é nullable.
-- imagem é TEXT (sem validação de tamanho desde V010); aqui usamos URLs
-- de placeholder (https://placehold.co). Troque por imagens reais/base64
-- quando quiser exibi-las no app.
--
-- Idempotente: WHERE NOT EXISTS evita duplicar produtos de mesmo nome
-- para o mesmo vendedor (seguro mesmo se reexecutado fora do Flyway).
-- =====================================================================

INSERT INTO produto (nome, categoria, preco, descricao, imagem, id_usuario)
SELECT
    v.nome,
    v.categoria,
    v.preco::double precision,
    v.descricao,
    v.imagem,
    u.id_usuario
FROM (
    VALUES
        ('Tomate',    'Legumes',  6.99,  'Tomate fresco, colhido na hora, ideal para saladas e molhos caseiros.',     'https://placehold.co/400x400/e74c3c/ffffff?text=Tomate'),
        ('Cenoura',   'Legumes',  4.50,  'Cenoura crocante e doce, perfeita para sucos, sopas e refogados.',          'https://placehold.co/400x400/e67e22/ffffff?text=Cenoura'),
        ('Alface',    'Verduras', 3.50,  'Alface crespa fresquinha, direto da horta para a sua cozinha.',             'https://placehold.co/400x400/27ae60/ffffff?text=Alface'),
        ('Batata',    'Legumes',  5.20,  'Batata de qualidade selecionada, ótima para fritar, cozinhar e assar.',     'https://placehold.co/400x400/f1c40f/000000?text=Batata'),
        ('Maçã',      'Frutas',   9.80,  'Maçã vermelha doce e suculenta, ideal para lanches saudáveis e sobremesas.','https://placehold.co/400x400/c0392b/ffffff?text=Maca'),
        ('Banana',    'Frutas',   5.50,  'Banana madura e doce, fonte de energia natural para o dia a dia.',          'https://placehold.co/400x400/f39c12/000000?text=Banana'),
        ('Laranja',   'Frutas',   4.99,  'Laranja suculente, perfeita para sucos naturais ricos em vitamina C.',      'https://placehold.co/400x400/f39c12/000000?text=Laranja'),
        ('Mamão',     'Frutas',   7.30,  'Mamão formoso maduro, ótimo para a digestão e para vitaminas.',              'https://placehold.co/400x400/e67e22/ffffff?text=Mamao'),
        ('Morango',   'Frutas',   12.50, 'Morango doce e fresco, colhido no ponto certo de maturação.',               'https://placehold.co/400x400/c0392b/ffffff?text=Morango'),
        ('Pimentão',  'Legumes',  8.00,  'Pimentão colorido e saboroso, ideal para saladas, refogados e grelhados.',  'https://placehold.co/400x400/e74c3c/ffffff?text=Pimentao'),
        ('Couve',     'Verduras', 3.80,  'Couve fresca, perfeita para refogados tradicionais e sucos verdes.',        'https://placehold.co/400x400/27ae60/ffffff?text=Couve'),
        ('Abobrinha', 'Legumes',  6.50,  'Abobrinha fresca e versátil, ótima para grelhar, assar e refogar.',         'https://placehold.co/400x400/2ecc71/ffffff?text=Abobrinha')
) AS v(nome, categoria, preco, descricao, imagem)
CROSS JOIN (
    SELECT id_usuario FROM usuario WHERE email = 'feirante@agromapping.com'
) u
WHERE NOT EXISTS (
    SELECT 1 FROM produto p
    WHERE p.nome = v.nome AND p.id_usuario = u.id_usuario
);
