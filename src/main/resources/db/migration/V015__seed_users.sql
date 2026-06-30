-- =====================================================================
-- Seed: um usuário de cada tipo (ADMIN, SELLER, USER)
-- Finalidade: contas de acesso para teste/demo em produção.
-- ---------------------------------------------------------------------
-- Credenciais (a mesma senha para os três, para facilitar o teste):
--   ADMIN   -> admin@agromapping.com
--   SELLER  -> feirante@agromapping.com
--   USER    -> cliente@agromapping.com
--   Senha   -> AgroMapping@2024
--
-- As senhas estão armazenadas como hash BCrypt (força 10), compatível
-- com o BCryptPasswordEncoder do Spring Security.
--
-- !! SEGURANÇA !! Em produção, faça login com cada conta e altere a senha
--    o quanto antes, ou remova estas linhas após criar usuários reais.
--
-- ON CONFLICT (email) DO NOTHING: torna o seed idempotente — reexecutar
-- a migração (ou rodar em DB já populado) não duplica nem quebra.
-- =====================================================================

INSERT INTO usuario (nome, email, senha, data_de_nascimento, user_role)
VALUES (
    'Administrador Seed',
    'admin@agromapping.com',
    '$2b$10$cE/e00Vnv3PSFedgLgDDwOGfiU68fR/dVbiRQhzBo9eN6eMJ8SfIG',
    DATE '1990-01-01',
    'ADMIN'
)
ON CONFLICT (email) DO NOTHING;

INSERT INTO usuario (nome, email, senha, data_de_nascimento, user_role)
VALUES (
    'Feirante Seed',
    'feirante@agromapping.com',
    '$2b$10$K0S6clmfMMOAzVjikrv0bOgLdeTVJE0QnoDuBV79Vq03g61S.Wl6O',
    DATE '1990-01-01',
    'SELLER'
)
ON CONFLICT (email) DO NOTHING;

INSERT INTO usuario (nome, email, senha, data_de_nascimento, user_role)
VALUES (
    'Cliente Seed',
    'cliente@agromapping.com',
    '$2b$10$aB23SU4RrwDYfB8dEHDeGuxuYjIBS9cRNh3fWrwB8pNGEwrhKMP5W',
    DATE '1990-01-01',
    'USER'
)
ON CONFLICT (email) DO NOTHING;
