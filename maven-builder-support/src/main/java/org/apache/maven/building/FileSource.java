/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.maven.building;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Objects;

/**
 * Wraps an ordinary {@link File} as a source.
 *
 */
public class FileSource implements Source {
    private final File file;

    private final int hashCode;

    /**
     * Creates a new source backed by the specified file.
     *
     * @param file The file, must not be {@code null}.
     */
    public FileSource(File file) {
        this.file = Objects.requireNonNull(file, "file cannot be null").getAbsoluteFile();
        this.hashCode = Objects.hash(file);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return Files.newInputStream(file.toPath());
    }

    @Override
    public String getLocation() {
        return file.getPath();
    }

    /**
     * Gets the file of this source.
     *
     * @return The underlying file, never {@code null}.
     */
    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        return getLocation();
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!FileSource.class.equals(obj.getClass())) {
            return false;
        }

        FileSource other = (FileSource) obj;
        return this.file.equals(other.file);
    }
}
