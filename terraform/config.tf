resource "kubernetes_deployment_v1" "test_config" {
  metadata {
    name      = "config-deployment"
  }

  spec {
    replicas = 1

    selector {
      match_labels = {
        app = "config"
      }
    }

    template {
      metadata {
        labels = {
          app = "config"
        }
      }

      spec {
        container {
          name  = "config"
          image = "amerkul/config:1.1.1"

          port {
            container_port = 8071
          }
        }
      }
    }
  }
}

resource "kubernetes_service_v1" "test_config" {
  metadata {
    name      = "config-service"
  }

  spec {
    selector = {
      app = "config"
    }
    type = "ClusterIP"
    port {
      port        = 8071
      target_port = 8071
    }
  }
}