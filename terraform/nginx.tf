resource "kubernetes_config_map_v1" "test_nginx" {
  metadata {
    name = "nginx-conf"
  }

  data = {
    nginx_htpasswd = file("nginx.htpasswd")
    nginx_conf     = file("nginx.conf")
  }
}

resource "kubernetes_deployment_v1" "test_nginx" {
  depends_on = [kubernetes_deployment_v1.test_article_mongo, kubernetes_deployment_v1.test_article_postgres]
  metadata {
    name = "nginx-deployment"
  }

  spec {
    replicas = 1

    selector {
      match_labels = {
        app = "nginx"
      }
    }

    template {
      metadata {
        labels = {
          app = "nginx"
        }
      }

      spec {
        container {
          name  = "nginx"
          image = "nginx"

          port {
            container_port = 80
          }

          volume_mount {
            mount_path = "/etc/nginx/nginx.conf"
            name       = "nginx-conf"
            sub_path   = "nginx.conf"
            read_only  = true
          }

          volume_mount {
            mount_path = "/etc/nginx/conf.d/nginx.htpasswd"
            name       = "nginx-conf-d"
            sub_path   = "nginx.htpasswd"
            read_only  = true
          }
        }

        volume {
          name = "nginx-conf"
          config_map {
            name = "nginx-conf"
            items {
              key  = "nginx_conf"
              path = "nginx.conf"
            }
          }
        }

        volume {
          name = "nginx-conf-d"
          config_map {
            name = "nginx-conf"
            items {
              key  = "nginx_htpasswd"
              path = "nginx.htpasswd"
            }
          }
        }
      }
    }
  }
}

resource "kubernetes_service_v1" "test_nginx" {
  metadata {
    name   = "nginx"
    labels = {
      app = "nginx"
    }
  }

  spec {
    selector = {
      app = "nginx"
    }

    port {
      protocol    = "TCP"
      port        = 80
      target_port = 80
    }
  }
}