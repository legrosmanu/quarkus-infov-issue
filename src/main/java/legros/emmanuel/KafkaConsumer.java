package legros.emmanuel;

import java.time.Instant;

import javax.enterprise.context.ApplicationScoped;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class KafkaConsumer {

    @Incoming("prices")
    public Uni<Void> consume(ConsumerRecord<String, String> msg) {
        Uni<Void> result;
        // if result is initialized with "= null", we will not have anymore the problem.
        // Or even, if Instant now = Instant.now(); is before Uni<Void> result; it is ok
        // too.
        Instant now = Instant.now();

        // If I have only one variable passed to infov, it's ok
        // Log.infov("infov is ok with only one param", msg.offset());

        // If I have 2 variables at least, the type of now has changed, even if we don't
        // use it in infov
        Log.infov("infov seems changing now into a long, {0} - {1}", msg.topic(), msg.offset());

        // We don't have the issue with a .info
        // Log.info("info seems not to the not expected change %s - %d".formatted(msg.topic(), msg.offset()));

        methodThatGetAnInstant(now);

        result = Uni.createFrom().voidItem();
        return result;
    }

    private void methodThatGetAnInstant(Instant instant) {
        Log.info("What a wonderfull method.");
    }
}
