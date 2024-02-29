# teste-tivia-api
Teste Tivia API

O primeiro passo para a ultilização da api e gerar um usuário para se autenticar.
Segue abaixo o endpoint e o json de exemplo lembrando de usar o metodo POST.

http://localhost:8090/usuario/post

{
  "login" : "alan.barreto",
  "password" : "senha"
}

Após gerar o usuario é necessario gerar o token de autenticação.
Segue abaixo o endpoint e o json de exemplo lembrando de usar o metodo POST.

{
  "login" : "alan.barreto",
  "password" : "senha"
}

Nesse repositorio teremos o arquivo "testetivia.com-0.0.1-SNAPSHOT.jar" para rodar copie o mesmo para um diretori de sua preferencia e para rodar o projeto execute via terminal o comando java -jar testetivia.com-0.0.1-SNAPSHOT.jar.

A aplicaçao foi desenvolvida ultilizando o padrao mvc tambem foi implementadoum serviço de autenticação/autorização ultilizando o JWT - Beartoken.

Temos as pastas de Model, Controller, Repositoryu, Sevices etc.# teste-tivia-api
Teste Tivia API

O primeiro passo para a ultilização da api e gerar um usuário para se autenticar.
Segue abaixo o endpoint e o json de exemplo lembrando de usar o metodo POST.

http://localhost:8090/usuario/post

{
  "login" : "alan.barreto",
  "password" : "senha"
}

Após gerar o usuario é necessario gerar o token de autenticação.
Segue abaixo o endpoint e o json de exemplo lembrando de usar o metodo POST.

{
  "login" : "alan.barreto",
  "password" : "senha"
}

Nesse repositorio teremos o arquivo "testetivia.com-0.0.1-SNAPSHOT.jar" para rodar copie o mesmo para um diretori de sua preferencia e para rodar o projeto execute via terminal o comando java -jar testetivia.com-0.0.1-SNAPSHOT.jar.

A aplicaçao foi desenvolvida ultilizando o padrao mvc tambem foi implementadoum serviço de autenticação/autorização ultilizando o JWT - Beartoken.

Temos as pastas de Model, Controller, Repositoryu, Sevices etc.

Para listar os endpoins disponiveis apos rodar a aplicacão pasta acessar o link: http://localhost:8090/swagger-ui/index.html#/
