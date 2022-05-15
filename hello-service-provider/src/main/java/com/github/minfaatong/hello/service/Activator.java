package com.github.minfaatong.hello.service;

import com.github.minfaatong.hello.service.impl.HelloWorldServiceImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

    private ServiceRegistration serviceRegistration;

    public void start(BundleContext bundleContext) throws Exception {
        // create a booking service impl instance
        HelloWorldService helloService = new HelloWorldServiceImpl();
        // registering the booking service in the service registry
        serviceRegistration = bundleContext.registerService(String.valueOf(HelloWorldService.class), helloService, null);
    }

    public void stop(BundleContext bundleContext) throws Exception {
        if (serviceRegistration != null) {
            // remove the service from the service registry
            serviceRegistration.unregister();
        }
    }
}
