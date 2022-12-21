resource "kubernetes_secret_v1" "test_postgres" {
  metadata {
    name      = "postgres-secret"
  }

  type = "Opaque"

  binary_data = {
    POSTGRES_USER     = "cG9zdGdyZXM="
    POSTGRES_PASSWORD = "cG9zdGdyZXM="
    POSTGRES_DB       = "YXJ0aWNsZV9kZXY="
  }
}

resource "kubernetes_stateful_set_v1" "test_postgres" {
  metadata {
    name      = "postgres"
  }

  spec {
    replicas     = 1
    service_name = "postgres"

    selector {
      match_labels = {
        app = "postgres"
      }
    }

    template {
      metadata {
        labels = {
          app = "postgres"
        }
      }

      spec {
        container {
          name              = "postgres"
          image             = "postgres:14.5"
          image_pull_policy = "IfNotPresent"

          port {
            container_port = 5432
          }

          env_from {
            secret_ref {
              name = "postgres-secret"
            }
          }

          volume_mount {
            mount_path = "/var/lib/postgresql/data"
            name       = "postgredb"
          }
        }
      }
    }
    volume_claim_template {
      metadata {
        name = "postgredb"
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

resource "kubernetes_service_v1" "test_postgres" {
  metadata {
    name      = "postgres"
    labels    = {
      app = "postgres"
    }
  }

  spec {
    selector = {
      app = "postgres"
    }

    cluster_ip = "None"

    port {
      protocol    = "TCP"
      port        = 5432
      target_port = 5432
    }
  }
}