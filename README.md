
# Sistema de Gestão de Tarefas
## Desenvolvido como desafio prático para o ESIG GROUP.
### Este é um sistema de gestão de tarefas desenvolvido em Java Web usando Java Server Faces, PostgreSQL, Maven e JPA/Hibernate. O sistema permite a criação, edição, exclusão e visualização de tarefas.

## Funcionalidades
* Criar Tarefas: Adicionar novas tarefas com título, descrição, responsável, prioridade, data de deadline e situação.
* Editar Tarefas: Atualizar informações de tarefas existentes.
* Excluir Tarefas: Remover tarefas do banco de dados.
* Listar Tarefas: Visualizar todas as tarefas cadastradas.
* Filtrar Tarefas: Filtrar tarefas por status (completa/incompleta). 

## Tecnologias Utilizadas
* Java
* JavaServer Faces
* PostgreSQL
* Hibernate
* Maven
* JPA

## Instruções de Execução
### Serão necessários:
* Apache Maven
* PostgreSQL
* Apache Tomcat
* Eclipse

### Para conexão de um banco de dados local, abra **AplicacaoEventoSelecao\\src\\main\\java\\META-INF\\persistence.xml** e edite as seguintes linhas:
* Linha 15: usuario do banco
* Linha 16: senha do usuario do banco

### Testes unitários
#### Para Executar os testes unitários, execute o AplicacaoEventoSelecao como Maven test ou JUnit test.