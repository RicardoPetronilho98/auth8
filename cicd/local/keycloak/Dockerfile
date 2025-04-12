ARG VERSION=26.1
FROM quay.io/keycloak/keycloak:${VERSION} AS builder

# Enable health and metrics support
ENV KC_HEALTH_ENABLED=true
ENV KC_METRICS_ENABLED=true

WORKDIR /opt/keycloak

# Generate a self-signed certificate (for demonstration purposes only)
RUN keytool \
  -genkeypair \
  -storepass password \
  -storetype PKCS12 \
  -keyalg RSA \
  -keysize 2048 \
  -dname "CN=keycloak" \
  -alias keycloak \
  -ext "SAN:c=DNS:keycloak,IP:127.0.0.1" \
  -keystore conf/server.keystores

# Build Keycloak
RUN /opt/keycloak/bin/kc.sh build

# Final stage
FROM quay.io/keycloak/keycloak:${VERSION}

# Copy built Keycloak from the builder stage
COPY --from=builder /opt/keycloak/ /opt/keycloak/

# Database Configuration
ENV KC_DB=postgres
ENV KC_DB_URL_HOST=postgres
ENV KC_DB_URL_DATABASE=keycloak
ENV KC_DB_USERNAME=keycloak
ENV KC_DB_PASSWORD=keycloak

# Set up Keycloak Admin
ENV KC_HTTP_RELATIVE_PATH=/auth
ENV KEYCLOAK_ADMIN=keycloak
ENV KEYCLOAK_ADMIN_PASSWORD=keycloak

# Expose Keycloak default HTTP port
EXPOSE 8080

# Run Keycloak in dev mode
ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start-dev"]
