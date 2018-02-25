package com.github.igorperikov.issue;

import org.junit.Test;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;

public class MovingFileToContainerTest {
    private static final GenericContainer container = new GenericContainer("hello-world:latest")
            .withClasspathResourceMapping("myfile", "/tmp/myfile", BindMode.READ_ONLY);

    @Test
    public void justToMakeADebugPoint() {
        container.start();
        System.out.println("I'm up");
        container.stop();
    }
}
