resource "kubernetes_secret_v1" "test_mongo" {
  metadata {
    name      = "mongo-secret"
  }

  type = "Opaque"

  binary_data = {
    MONGO_INITDB_ROOT_USERNAME = "cm9vdA=="
    MONGO_INITDB_ROOT_PASSWORD = "cm9vdA=="
    MONGO_INITDB_DATABASE      = "YXJ0aWNsZV9kZXY="
  }
}

resource "kubernetes_stateful_set_v1" "test_mongo" {
  metadata {
    name      = "mongo"
  }

  spec {
    replicas     = 1
    service_name = "mongo"

    selector {
      match_labels = {
        app = "mongo"
      }
    }

    template {
      metadata {
        labels = {
          app = "mongo"
        }
      }

      spec {
        container {
          name  = "mongo"
          image = "mongo"

          port {
            container_port = 27017
          }

          env_from {
            secret_ref {
              name = "mongo-secret"
            }
          }

          volume_mount {
            mount_path = "/data/db"
            name       = "mongodb"
          }
        }
      }
    }

    volume_claim_template {
      metadata {
        name = "mongodb"
      }

      spec {
        access_modes = ["ReadWriteMany"]

        resources {
          requests = {
            storage = "5Gi"
          }
        }
      }
    }
  }
}

resource "kubernetes_service_v1" "test_mongo" {
  metadata {
    name      = "mongo"
  }
  spec {
    selector = {
      app = "mongo"
    }

    cluster_ip = "None"

    port {
      port        = 27017
      target_port = 27017
    }
  }
}