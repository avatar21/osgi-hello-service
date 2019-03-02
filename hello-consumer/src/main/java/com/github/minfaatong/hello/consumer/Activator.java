package com.github.minfaatong.osgi.consumer;

import com.github.minfaatong.osgi.service.HelloWorldService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {

    private ServiceTracker<HelloWorldService, HelloWorldService> helloServiceTracker;
    private ServiceRegistration consumerServiceRegistration;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        helloServiceTracker = new ServiceTracker<HelloWorldService, HelloWorldService>(bundleContext, HelloWorldService.class, null) {

            @Override
            public HelloWorldService addingService(ServiceReference<HelloWorldService> serviceReference) {
                HelloWorldService helloService = bundleContext.getService(serviceReference);

                HelloWorldConsumer consumer = new HelloWorldConsumer(helloService);
                consumer.sayHello("Alan","Woo");
                consumerServiceRegistration = bundleContext.registerService(HelloWorldConsumer.class, consumer, null);

                return helloService;
            }

            @Override
            public void removedService(ServiceReference<HelloWorldService> reference, HelloWorldService service) {
                display.destroy();

                clientServiceRegistration.unregister();
            }
        };

        helloServiceTracker.open();
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        helloServiceTracker.close();
    }
}
