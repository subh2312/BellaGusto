package org.subhankar.yumzoom.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {
    private static final List<String> openApiEndpoints = List.of(
            "/auth/login",
            "/auth/register",
            "/auth/validate",
            "/user/email");

    private static final List<String> userApiEndpoints = List.of(
            "/auth/logout");

    private static final List<String> adminApiEndpoints = List.of(
            "/user",
            "/auth/logout");

    private static final List<String> ownerApiEndpoints = List.of(
            "/auth/logout"
    );

    public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints
            .stream()
            .noneMatch(uri -> request.getURI().getPath().contains(uri));

    public Predicate<ServerHttpRequest> isSecuredForUser = request -> userApiEndpoints
            .stream()
            .anyMatch(uri -> request.getURI().getPath().contains(uri));

    public Predicate<ServerHttpRequest> isSecuredForAdmin = request -> adminApiEndpoints
            .stream()
            .anyMatch(uri -> request.getURI().getPath().contains(uri));

    public Predicate<ServerHttpRequest> isSecuredForOwner = request -> ownerApiEndpoints
            .stream()
            .anyMatch(uri -> request.getURI().getPath().contains(uri));
}
