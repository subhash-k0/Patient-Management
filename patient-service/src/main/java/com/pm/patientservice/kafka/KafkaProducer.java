package com.pm.patientservice.kafka;

import com.pm.patientservice.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class KafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(
            KafkaProducer.class);
    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private final String patientTopic;

    public KafkaProducer(
            KafkaTemplate<String, byte[]> kafkaTemplate,
            @Value("${app.kafka.patient-topic:patient}") String patientTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.patientTopic = patientTopic;
    }

    public void sendEvent(Patient patient) {
        PatientEvent event = PatientEvent.newBuilder()
                .setPatientId(patient.getId().toString())
                .setName(patient.getName())
                .setEmail(patient.getEmail())
                .setEventType("PATIENT_CREATED")
                .build();

        kafkaTemplate.send(patientTopic, patient.getId().toString(), event.toByteArray())
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Error sending PatientCreated event for patientId={}", patient.getId(), ex);
                        return;
                    }

                    log.info(
                            "PatientCreated event sent for patientId={} topic={} partition={} offset={}",
                            patient.getId(),
                            result.getRecordMetadata().topic(),
                            result.getRecordMetadata().partition(),
                            result.getRecordMetadata().offset());
                });
    }
}
