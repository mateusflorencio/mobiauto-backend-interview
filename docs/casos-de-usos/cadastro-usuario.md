# Caso de Uso: Cadastro de Usuário

**Ator Principal:** Usuário

**Objetivo:** Cadastro de usuário

**Pré-condições:**

Deve ter o nivel de acesso de administrador, gerente ou proprietário e estar autenticado.

**Fluxo Básico:**

1. É enviado uma requisição para o backend com os dados do usuário, como nome, email, senha e nivel de acesso e codigo da revenda.
2. Os campos são obrigatórios menos o codigo da revenda.
3. Deve se validar o nivel de acesso, que deve ser um dos seguintes: administrador, gerente, proprietário ou assistente.
4. O backend valida os dados, como se o email é válido.
5. Deve transforma a senha em hash.
6. O backend salva os dados no banco de dados.

**Fluxo Alternativo:**

2.1 Caso os campos não sejam preenchidos deve se retornar um erro.
4.1 Caso o email não estaja no formato correto deve se retornar um erro.
4.2 Caso o email já esteja cadastrado deve se retornar um erro.
6.1 Caso o gerente ou proprietário informe um código de revenda que não seja de sua propriedade deve se retorna um erro.

**Pós-condições:**

Usuário cadastrado com sucesso.

**Observações:**

* Deve se levantar o fluxo de autenticação do usuário antes de realizar o cadastro do usuário.
* O nivel de acesso de assistente é o único que não pode cadastrar outros usuários.
* Levantar padrão de senha para os usuários no futuro.
* Sugerir validação de email por token.

> [< Voltar a documentação técnica](../technical-documentation.md)
