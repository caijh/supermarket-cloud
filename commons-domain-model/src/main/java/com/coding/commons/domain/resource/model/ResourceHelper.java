package com.coding.commons.domain.resource.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ResourceHelper {

    private ResourceHelper() {
        throw new AssertionError("no instance");
    }

    /**
     * 将resources排序并返回root resources.
     */
    public static List<Resource> sortAsRootResources(List<Resource> resources) {
        resources.sort(Comparator.comparingLong(Resource::getId));
        Map<Long, Resource> resourceMap = new HashMap<>();
        resources.forEach(e -> resourceMap.put(e.getId(), e));

        List<Resource> rootResources = new LinkedList<>();
        resources.parallelStream().forEach(resource -> {
            if (resource.getParentId() == null) {
                rootResources.add(resource);
            } else {
                Resource parentResource = resourceMap.get(resource.getParentId());
                if (parentResource.getSubs() == null) {
                    parentResource.setSubs(new ArrayList<>());
                }
                parentResource.getSubs().add(resource);
            }
        });

        return rootResources;
    }
}
