# Micrometer
nasceu em 2017 pela pivotal Software criado para fornecer uma solução unificada para coletar e enviar métricas para diversos sistemas de monitoramento tendo sua primeira versao publicada em Jul 22, 2017 e seu lancamento oficial em Feb 20, 2018
O Micrometer fornece uma facade simples sobre os clients de instrumentação para os sistemas de observabilidade mais populares, permitindo-lhe instrumentar o seu código de aplicação baseado na JVM sem dependência do fornecedor. Pense no SLF4J, mas para observabilidade de aplicações! Os dados registados pelo Micrometer destinam-se a ser utilizados para observar, alertar e reagir ao estado operacional atual/recente do seu ambiente.

## Estrutura do Micrometer

Instrumentation SPI (Service Provider Interface): Fornece a interface básica e os componentes necessários para instrumentar a aplicação e coletar métricas.
Registros: Implementações específicas para diferentes sistemas de monitoramento (cada um chamado de "registry").

## Dimensionalidade dos Sistemas de Monitoramento
A dimensionalidade refere-se à capacidade de um sistema de monitoramento de suportar métricas enriquecidas com pares de chave/valor (tags).
Sistemas Dimensionais

- Exemplo: Prometheus, Datadog, New Relic.

  - Características:

    - Suportam métricas que possuem nomes e tags (pares chave/valor) associados.
    - Permitem consultas e agregações avançadas baseadas nas tags.
    - Exemplo de uma métrica dimensional: http_request_seconds_count{method="GET", status="200"}.

Sistemas Hierárquicos

- Exemplo: Graphite.
  - Características:
    - Não suportam tags. Trabalham com nomes de métricas "achatados" (sem estrutura de chave/valor).
    - Métricas são armazenadas com nomes simples e hierárquicos, frequentemente representando uma estrutura em árvore.
    - Para publicar métricas em sistemas hierárquicos, o Micrometer "achata" o conjunto de tags e as adiciona ao nome da métrica.
    - Exemplo de uma métrica achatada: http_request_seconds_count.GET.200.
### tipos de metricas:

- *Histograms*: Um histograma divide os dados em buckets (intervalos) e conta o número de observações que caem em cada bucket. Isso é útil para visualizar a distribuição dos dados.
- *Percentiles*: Percentiles são valores abaixo dos quais uma certa porcentagem dos dados caem. Por exemplo, o 95º percentil é o valor abaixo do qual 95% dos dados se encontram. Eles ajudam a entender a dispersão dos dados e identificar valores extremos.
- *counter*: Os contadores reportam uma única métrica: uma contagem. A interface Counter permite-lhe incrementar por um valor fixo, que tem de ser positivo.
- *Gauges*: Um medidor é um identificador para obter o valor atual. Exemplos típicos de indicadores seriam o tamanho de uma coleção ou mapa ou o número de threads num estado de execução.
### Características do Micrometer:

- Suporte a diferentes sistemas de monitoramento como:
DataDog, Prometheus, Dynatrace, OpenTelemetry(OTLP)
- Unificação de Métricas: Micrometer fornece uma API para a coleta de métricas, independentemente do backend de monitoramento.

O spring actuator da o suporte de auto configuracao para todos os sistemas de monitoramentos com micrometer

https://docs.spring.io/spring-boot/reference/actuator/metrics.html

## Micrometer Observation

> "Instrument code once, and get multiple benefits out of it."
 
 
 2.68398E-4 pode ser interpretado como 2.68398×10−42.68398×10−4, que é igual a 0.000268398.