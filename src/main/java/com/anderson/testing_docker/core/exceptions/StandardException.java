package com.anderson.testing_docker.core.exceptions;

import java.time.Instant;

public record StandardException(Instant timestamp, Integer status, String error, String path) {
}
