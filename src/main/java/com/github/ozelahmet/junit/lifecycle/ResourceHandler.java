package com.github.ozelahmet.junit.lifecycle;

public class ResourceHandler {
    private boolean resourceOpened = false;

    public void openResource() {
        resourceOpened = true;
        System.out.println("ResourceHandler: Resource opened.");
    }

    public void closeResource() {
        resourceOpened = false;
        System.out.println("ResourceHandler: Resource closed.");
    }

    public boolean isResourceOpened() {
        return resourceOpened;
    }
}
