# Caso de Uso: Cadastro de Revenda

**Ator Principal:** Usuário

**Objetivo:** Cadastro de revenda

**Pré-condições:**

Sem pré-condições

**Fluxo Básico:**

1. É enviado uma requisição para o backend com os dados da revenda, como nome e cnpj.
2. Os campos são obrigatórios
3. O backend valida os dados, como se o cnpj é válido.
4. O backend salva os dados no banco de dados.
5. O backend retorna uma resposta de sucesso.

**Fluxo Alternativo:**

1. O backend valida os dados e encontra um erro.
2. O backend retorna uma resposta de erro.
3. O fluxo é interrompido.

**Pós-condições:**

Revenda cadastrada com sucesso.

**Observações:**

* Futuro: Deve se levantar o fluxo de autenticação do usuário antes de realizar o cadastro da revenda.
* Verificar quem são os niveis de acesso que podem cadastrar uma revenda.

> [< Voltar a documentação técnica](../technical-documentation.md)
