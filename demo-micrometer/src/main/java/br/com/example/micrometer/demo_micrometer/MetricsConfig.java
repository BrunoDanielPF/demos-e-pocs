package br.com.example.micrometer.demo_micrometer;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.config.MeterFilterReply;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {

    @Bean
    public MeterFilter configureMetrics() {
        return new MeterFilter() {
            @Override
            public DistributionStatisticConfig configure(Meter.Id id, DistributionStatisticConfig config) {
                return DistributionStatisticConfig.builder()
                        .percentiles(0.5, 0.75, 0.95, 0.99) // Configura percentis
                        .percentilesHistogram(true) // Habilita histograma de percentis
                        .build()
                        .merge(config);
            }

            @Override
            public MeterFilterReply accept(Meter.Id id) {
                if (id.getName().equals("http.server.requests")) {
                    return MeterFilterReply.ACCEPT;
                }
                return MeterFilterReply.NEUTRAL;
            }
        };
    }
}
