# Caso de Uso: Edição de Oportunidade

**Ator Principal:** Usuário

**Objetivo:** Edição de Oportunidade

**Pré-condições:**

Usuário deve estar autenticado, sendo um administrador, gerente, proprietário ou assistente responsável pela oportunidade.

**Fluxo Básico:**

1. É enviado uma requisição para o backend com os dados da oportunidade:
   1. Status
   2. Nome do cliente
   3. Email do cliente
   4. Telefone do cliente
   5. Modelo do veículo
   6. Marca do veículo
   7. versão do veículo
   8. Ano do veículo
   9. Motivo Conclusão(Opcional)
2. O backend valida os dados, como se o email é válido caso seja passado.

**Fluxo Alternativo:**

1.1.a Caso o status seja "concluído" é necessário passar o motivo da conclusão.
1.1.b Caso o status seja "concluído" e o motivo da conclusão não seja passado, o backend retorna uma resposta de erro.
1.1.c Caso o status seja "concluído" deve atualizar a data de conclusão da oportunidade.

**Pós-condições:**

Oportunidade editada com sucesso.

**Observações:**

* Sem observações.

> [< Voltar a documentação técnica](../technical-documentation.md)
