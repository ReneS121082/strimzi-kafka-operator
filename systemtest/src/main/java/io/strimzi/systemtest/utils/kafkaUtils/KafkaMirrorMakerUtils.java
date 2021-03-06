/*
 * Copyright Strimzi authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.strimzi.systemtest.utils.kafkaUtils;

import io.strimzi.api.kafka.model.KafkaMirrorMaker;
import io.strimzi.systemtest.resources.ResourceManager;
import io.strimzi.systemtest.resources.crd.KafkaMirrorMakerResource;

import static io.strimzi.test.k8s.KubeClusterResource.kubeClient;

public class KafkaMirrorMakerUtils {

    private KafkaMirrorMakerUtils() {}

    /**
     * Wait until KafkaMirrorMaker status is in desired state
     * @param clusterName name of KafkaMirrorMaker cluster
     * @param state desired state - like Ready
     */
    public static void waitForKafkaMirrorMakerStatus(String clusterName, String state) {
        KafkaMirrorMaker kafkaMirrorMaker = KafkaMirrorMakerResource.kafkaMirrorMakerClient().inNamespace(kubeClient().getNamespace()).withName(clusterName).get();
        ResourceManager.waitForResourceStatus(KafkaMirrorMakerResource.kafkaMirrorMakerClient(), kafkaMirrorMaker, state);
    }

    public static void waitForKafkaMirrorMakerReady(String clusterName) {
        waitForKafkaMirrorMakerStatus(clusterName, "Ready");
    }

    public static void waitForKafkaMirrorMakerNotReady(String clusterName) {
        waitForKafkaMirrorMakerStatus(clusterName, "NotReady");
    }
}
