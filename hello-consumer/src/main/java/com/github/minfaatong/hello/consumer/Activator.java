package com.github.minfaatong.hello.consumer;

import com.github.minfaatong.hello.service.HelloWorldService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {

    private ServiceTracker<HelloWorldService, HelloWorldService> helloServiceTracker;
    private ServiceRegistration consumerServiceRegistration;

    @Override
    public void start(final BundleContext bundleContext) throws Exception {
        helloServiceTracker = new ServiceTracker<HelloWorldService, HelloWorldService>(bundleContext, HelloWorldService.class, null) {

            @Override
            public HelloWorldService addingService(ServiceReference serviceReference) {
                HelloWorldService helloService = (HelloWorldService) bundleContext.getService(serviceReference);

                HelloWorldConsumer consumer = new HelloWorldConsumer(helloService);
                consumer.sayHello("Alan","Woo");
                consumerServiceRegistration = bundleContext.registerService(String.valueOf(HelloWorldConsumer.class), consumer, null);

                return helloService;
            }

            @Override
            public void removedService(ServiceReference reference, HelloWorldService service) {
                consumerServiceRegistration.unregister();
            }
        };

        helloServiceTracker.open();
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        helloServiceTracker.close();
    }
}
