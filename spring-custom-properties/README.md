acessar : http://localhost:8080/actuator/env

exemplo de configuração :
```json
    {
      "name": "Custom-properties-source-application-initializer",
      "properties": {
        "propertie.application.name.identifier": {
          "value": ""
        }
      }
    },
    {
      "name": "Custom-properties-source-environment-post-processor",
      "properties": {
        "propertie-application-name-identifie": {
          "value": "br.com.example.metric.demo.DemoCustomMetricApplication"
        }
      }
    },
```