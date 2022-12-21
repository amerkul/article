resource "kubernetes_config_map_v1" "test_article_postgres" {
  metadata {
    name      = "article-postgres-config"
  }

  data = {
    SPRING_PROFILES_ACTIVE  = "dev"
    SPRING_CLOUD_CONFIG_URI = "http://config-service:8071"
    CONFIGSERVER_PORT       = "8071"
    DATABASESERVER_PORT     = "5432"
  }
}

resource "kubernetes_deployment_v1" "test_article_postgres" {
  metadata {
    name      = "article-postgres-deployment"
  }

  spec {
    replicas = 1

    selector {
      match_labels = {
        app = "article-postgres"
      }
    }

    template {
      metadata {
        labels = {
          app = "article-postgres"
        }
      }

      spec {
        container {
          name  = "article-postgres"
          image = "amerkul/article-postgres:tagname"

          port {
            container_port = 8080
          }

          env_from {
            config_map_ref {
              name = "article-postgres-config"
            }
          }
        }
      }
    }
  }
}

resource "kubernetes_service_v1" "test_article_postgres" {
  metadata {
    name      = "article-service"
  }
  spec {
    selector = {
      app = "article-postgres"
    }
    type = "NodePort"

    port {
      port        = 8080
      target_port = 8080
      node_port   = 30003
    }
  }
}