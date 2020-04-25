# inChurch Recruitment Process 2020 - Android Developer

Nessa parte do processo de recrutamento você desenvolverá uma aplicação Android. O desafio deve ser desenvolvido em Kotlin ou Java e utilizando libs conhecidas de mercado. A aplicação será um catálogo dos filmes populares, utilizando a [API](https://developers.themoviedb.org/3/getting-started/introduction) do [TheMovieDB](https://www.themoviedb.org/).

* * *

## Requisitos

+ ### O que deve ter:
	* OK - Tela de Listagem de Filmes exibindo os filmes melhores classificados. Utilizar esse [endpoint](https://developers.themoviedb.org/3/movies/get-popular-movies).
	* OK - Loading no carregamento da listagem de filmes.
	* OK - Tratamento de erros(falta de internet e erro na api) na tela de Listagem de Filmes.
	* OK - Tela de Favoritos com a listagem dos filmes marcados como favorito. Essa tela será acessada no ícone de favoritos na toolbar da Listagem de Filmes.
	* OK - Tela de detalhe do filme. Para as informações de gêneros do filme, utilize esse [endpoint](https://developers.themoviedb.org/3/genres/get-movie-list).
	* OK - Ação de favoritar um filme na tela de detalhe. Todo o controle será em armazenamento local.

+ ### Pontos extras:
	* OK - Paginação com scroll infinito na tela de filmes.
	* OK - Filtro de busca pelo nome do filme na tela de Favoritos. Exibir uma tela diferente para quando não houver resultado na busca.
	* OK - Ação de remover o filme da lista de Favoritos.
	* NOK - Testes unitários.
	* NOK - Testes funcionais.

* * *

## Informações do Projeto

    * Linguagem - Kotlin
    * Arquitetura - MVVM
    * REST Client - Retrofi2
    * Persistência - Room
    * Scrool Infinito - Paging
    * Animações e Design - Material Design
    * JSON Parser - GSON
    * Carregamento de Imagens - Glide
    * Operações Assíncronas - Coroutines

* Arquitetura foi escolhida por se tratar de uma indicação da própria Google, por manter os componentes desacoplados e por conta da separação de camadas.
* As chamadas de API e Local são feitas atrás do ViewModel, retornando sempre um LiveData para camada de UI, desta forma não bloqueando a MainThread.
* Para paginação foi escolhida a biblioteca de Paginação da Google, tornando o processo mais consistente.
* Toda aplicação funciona em DarkMode, respeitando a escolha do usuário a nível de sistema.
