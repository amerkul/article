resource "kubernetes_config_map_v1" "test_kafka" {
  metadata {
    name = "kafka-config"
  }

  data = {
    KAFKA_BROKER_ID            = 1
    KAFKA_ZOOKEEPER_CONNECT    = "zookeeper-cluster:2181"
    KAFKA_LISTENERS            = "PLAINTEXT://:9092"
    KAFKA_ADVERTISED_LISTENERS = "PLAINTEXT://kafka-service:9092"
  }
}

resource "kubernetes_deployment_v1" "test_kafka" {
  metadata {
    name   = "kafka-broker"
    labels = {
      app = "kafka-broker"
    }
  }

  spec {
    replicas = 1

    selector {
      match_labels = {
        app = "kafka-broker"
      }
    }

    template {
      metadata {
        labels = {
          app = "kafka-broker"
        }
      }

      spec {
        hostname = "kafka-service"

        container {
          name              = "kafka-service"
          image             = "wurstmeister/kafka"
          image_pull_policy = "IfNotPresent"

          port {
            container_port = 9092
          }

          env_from {
            config_map_ref {
              name = "kafka-config"
            }
          }
        }
      }
    }
  }
}

resource "kubernetes_service_v1" "test_kafka" {
  metadata {
    name   = "kafka-service"
    labels = {
      app = "kafka-broker"
    }
  }

  spec {
    selector = {
      app = "kafka-broker"
    }

    port {
      port = 9092
    }
  }
}