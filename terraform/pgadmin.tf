resource "kubernetes_persistent_volume_v1" "test_pgadmin" {
  metadata {
    name      = "pgadmin-pv-volume"
    labels    = {
      type = "local"
      app  = "pgadmin"
    }
  }

  spec {
    storage_class_name = "manual"
    access_modes       = ["ReadWriteOnce"]
    capacity           = {
      storage = "5Gi"
    }

    persistent_volume_source {
      host_path {
        path = "/mnt/pgdata"
      }
    }
  }
}

resource "kubernetes_persistent_volume_claim_v1" "test_pgadmin" {
  metadata {
    name      = "pgadmin-pv-claim"
    labels    = {
      app = "pgadmin"
    }
  }

  spec {
    storage_class_name = "manual"
    access_modes       = ["ReadWriteOnce"]

    resources {
      requests = {
        storage = "5Gi"
      }
    }
  }
}

resource "kubernetes_secret_v1" "test_pgadmin" {
  metadata {
    name      = "pg-admin-secret"
  }

  type = "Opaque"

  binary_data = {
    PGADMIN_DEFAULT_PASSWORD = "cG9zdGdyZXM="
    PGADMIN_DEFAULT_EMAIL    = "YWRtaW5AYWRtaW4uY29t"
  }
}

resource "kubernetes_deployment_v1" "test_pgadmin" {
  metadata {
    name      = "pgadmin"
  }

  spec {
    replicas = 1

    selector {
      match_labels = {
        app = "pgadmin"
      }
    }

    template {
      metadata {
        labels = {
          app = "pgadmin"
        }
      }

      spec {
        container {
          name  = "pgadmin"
          image = "dpage/pgadmin4"

          port {
            container_port = 80
          }

          env_from {
            secret_ref {
              name = "pg-admin-secret"
            }
          }

          volume_mount {
            mount_path = "/var/tmp/pgadmin"
            name       = "pgadmindb"
          }
        }

        volume {
          name = "pgadmindb"

          persistent_volume_claim {
            claim_name = "pgadmin-pv-claim"
          }
        }
      }
    }
  }
}

resource "kubernetes_service_v1" "test_pgadmin" {
  metadata {
    name      = "pgadmin-service"
  }

  spec {
    selector = {
      app = "pgadmin"
    }

    type = "NodePort"

    port {
      port        = 5050
      target_port = 80
      node_port   = 30200
    }
  }
}
