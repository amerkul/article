resource "kubernetes_ingress_v1" "test_ingress_controller" {
  metadata {
    name      = "nginx-ingress"
    annotations = {

    }
  }

  spec {
    rule {
      http {
        path {
          path      = "/"
          path_type = "Prefix"

          backend {
            service {
              name = "nginx"

              port {
                number = 80
              }
            }
          }
        }
      }
    }
  }
}