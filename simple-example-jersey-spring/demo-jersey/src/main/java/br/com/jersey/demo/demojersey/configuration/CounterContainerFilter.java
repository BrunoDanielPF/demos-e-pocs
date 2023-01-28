package br.com.jersey.demo.demojersey.configuration;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CounterContainerFilter implements ContainerResponseFilter {
    @Autowired
    MeterRegistry registry;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        Counter register = Counter
                .builder("custom-metric-counter-example")
                .description("description counter")
                .tags(Tags.of(new Tag[]{
                        Tag.of("uri", requestContext.getUriInfo().getPath().toLowerCase()),
                        Tag.of("method", requestContext.getRequest().getMethod())
                        }
                ))
                .register(registry);
        register.increment();
    }
}
