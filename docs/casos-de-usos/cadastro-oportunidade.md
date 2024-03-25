# Caso de Uso: Cadastro de Oportunidade

**Ator Principal:** Usuário

**Objetivo:** Cadastro de Oportunidade

**Pré-condições:**

Usuário deve estar autenticado.

**Fluxo Básico:**

1. É enviado uma requisição para o backend com os dados da oportunidade:
   1. Status, sendo o default "novo"
   2. Nome do cliente
   3. Email do cliente
   4. Telefone do cliente
   5. Modelo do veículo
   6. Marca do veículo
   7. versão do veículo
   8. Ano do veículo
   9. Id do usuário que vai ser o responsável pela oportunidade(Opcional)
2. Os campos sem a flag de opcional são obrigatórios
3. O backend valida os dados, como se o email é válido.
4. Caso o id do usuário responsável não seja passado, o backend realiza a seguinte regra para atribuir automaticamente:
   1. O assistente com a menor quantidade de oportunidades atribuídas em andamento.
   2. Maior tempo sem receber uma oportunidade.
5. O backend salva os dados no banco de dados com o campo TIME_ATRIBUICAO preenchido com a data atual.
6. Atualiza o TIME_ULTIMA_ATRIBUICAO do usuário responsável com o ATRIBUIDOR sendo "SISTEMA" com o respectiovo FK da oportunidade.

**Fluxo Alternativo:**

1.9.a Caso não seja uma usuário válido, o backend retorna uma resposta de erro.
3.a Caso algum campo obrigatório não seja passado, o backend retorna uma resposta de erro.

**Pós-condições:**

Oportunidade cadastrada com sucesso.

**Observações:**

* Sem observações.
* É importante ter a ciencia de qual oportunidade foi atribuída automaticamente para eventuais atualizações.
* Necessário levantar mais o entendimento de "FILAS" de oportunidade. Vai ter uma feature para isso ou não?

> [< Voltar a documentação técnica](../technical-documentation.md)
