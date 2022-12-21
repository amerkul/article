resource "kubernetes_config_map_v1" "test_article_mongo" {
  metadata {
    name = "article-mongo-config"
  }

  data = {
    SPRING_PROFILES_ACTIVE  = "dev"
    SPRING_CLOUD_CONFIG_URI = "http://config-service:8071"
    CONFIGSERVER_PORT       = "8071"
    DATABASESERVER_PORT     = "5432"
  }
}

resource "kubernetes_deployment_v1" "test_article_mongo" {
  depends_on = [kubernetes_stateful_set_v1.test_mongo]

  metadata {
    name = "article-mongo-deployment"
  }

  spec {
    replicas = 1

    selector {
      match_labels = {
        app = "article-mongo"
      }
    }

    template {
      metadata {
        labels = {
          app = "article-mongo"
        }
      }

      spec {
        container {
          name  = "article-mongo"
          image = "amerkul/article-mongo:tagname"

          port {
            container_port = 8080
          }

          env_from {
            config_map_ref {
              name = "article-mongo-config"
            }
          }
        }
      }
    }
  }
}

resource "kubernetes_service_v1" "test_article_mongo" {
  metadata {
    name = "article-mongo-service"
  }

  spec {
    selector = {
      app = "article-mongo"
    }
    type = "NodePort"

    port {
      port        = 8180
      target_port = 8080
      node_port   = 30111
    }
  }
}