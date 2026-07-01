-- =====================================================================
-- Re-seed: recria os usuários de teste caso tenham sido apagados
-- ---------------------------------------------------------------------
-- Motivo: o Flyway não reexecuta migrações já aplicadas, então a V015
-- (que criou os usuários) não roda de novo sozinha. Se algum usuário
-- seed foi apagado (ex.: durante testes), esta migração o recria.
--
-- ON CONFLICT (email) DO NOTHING: recria apenas os que faltam; os que
-- já existem (ex.: feirante, cliente) são preservados sem duplicar.
--
-- Senha (todos): AgroMapping@2024  (hash BCrypt, mesma da V015)
--   ADMIN   -> admin@agromapping.com
--   SELLER  -> feirante@agromapping.com
--   USER    -> cliente@agromapping.com
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
