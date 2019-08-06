package ru.butik.streamsets.origin.lib;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.categories.CategoriesGrpc;
import io.grpc.categories.CategoriesGrpc.CategoriesBlockingStub;
import io.grpc.categories.CategoriesGrpc.CategoriesImplBase;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class CategoriesServiceImpl extends CategoriesImplBase {
    private final ManagedChannel channel;
    private final CategoriesBlockingStub blockingStub;

    public CategoriesServiceImpl(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext());
    }

    /**
     * Construct client for accessing RouteGuide server using the existing channel.
     */
    public CategoriesServiceImpl(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        blockingStub = CategoriesGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public Iterator<io.grpc.categories.CategoryItem> getCategories() {
        Empty request = Empty.newBuilder().build();

        return blockingStub.getCategories(request);
    }
}
