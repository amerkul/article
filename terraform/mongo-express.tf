resource "kubernetes_secret_v1" "test_mongo_express" {
  metadata {
    name = "mongo-express-secret"
  }

  type = "Opaque"

  binary_data = {
    ME_CONFIG_MONGODB_ADMINUSERNAME = "cm9vdA=="
    ME_CONFIG_MONGODB_ADMINPASSWORD = "cm9vdA=="
    ME_CONFIG_MONGODB_SERVER        = "bW9uZ28="
    ME_CONFIG_MONGODB_PORT          = "MjcwMTc="
  }
}

resource "kubernetes_deployment_v1" "test_mongo_express" {
  metadata {
    name = "mongo-express"
  }

  spec {
    replicas = 1

    selector {
      match_labels = {
        app = "mongo-express"
      }
    }

    template {
      metadata {
        labels = {
          app = "mongo-express"
        }
      }

      spec {
        container {
          name  = "mongo-express"
          image = "mongo-express"

          port {
            container_port = 8081
          }

          env_from {
            secret_ref {
              name = "mongo-express-secret"
            }
          }
        }
      }
    }
  }
}

resource "kubernetes_service_v1" "test_mongo_express" {
  metadata {
    name = "mongo-express-service"
  }

  spec {
    selector = {
      app = "mongo-express"
    }
    type = "NodePort"

    port {
      port        = 8081
      target_port = 8081
      node_port   = 30001
    }
  }
}