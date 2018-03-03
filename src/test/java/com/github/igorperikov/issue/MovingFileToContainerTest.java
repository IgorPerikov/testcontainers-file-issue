package com.github.igorperikov.issue;

import com.github.dockerjava.api.DockerClient;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;

public class MovingFileToContainerTest {
    private static final GenericContainer container = new GenericContainer("hello-world:latest")
            .withClasspathResourceMapping("myfile", "/tmp/myfile", BindMode.READ_ONLY);

    @Test
    public void justToMakeADebugPoint() throws Exception {
        container.start();
        System.out.println("I'm up");

        DockerClient client = DockerClientFactory.instance().client();

        try (TarArchiveInputStream tar = new TarArchiveInputStream(client.copyArchiveFromContainerCmd(container.getContainerId(), "/tmp/myfile").exec())) {
            tar.getNextEntry();
            String content = IOUtils.toString(tar);

            assert content.startsWith("this\nis");
        }
    }
}
