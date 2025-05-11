/**
 * Copyright (c) 2025 nadeeshan_fdz. All rights reserved.
 * <p>
 * This file is part of the Spring Boot Project and may not be
 * copied, modified, or distributed without permission.
 * <p>
 * Author: nadeeshan_fdz
 * Date: 11/05/2025
 */

package com.anton.custom_annotation.annotation;

import java.lang.annotation.*;

/**
 * Author by nadeeshan_fdz, Date = "11/05/2025"
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLogger {
}
