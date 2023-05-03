package org.example;


import com.microsoft.azure.servicebus.ClientFactory;
import com.microsoft.azure.servicebus.ClientSettings;
import com.microsoft.azure.servicebus.IMessageReceiver;
import com.microsoft.azure.servicebus.ReceiveMode;
import com.microsoft.azure.servicebus.management.ManagementClient;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.MessagingFactory;
import com.microsoft.azure.servicebus.security.SharedAccessSignatureTokenProvider;
import com.mulesoft.connector.azure.messaging.internal.connection.AzureServiceBusConnection;
import com.mulesoft.connector.azure.messaging.internal.connection.provider.SharedAccessSignatureConnectionProvider;

public class Main {
    private static String sharedAccessKeyName="2RaMBXsPjbyL92IUhHn3yqcjJU9G+hO6X+ASbOK2K+o=";
    private static String sharedAccessKey="TopicBasedManagedKeys";
    private static String namespace="yapie-weu-dev";

    private static String destinationPath="finance-fxRate-event";
    public static void main(String[] args) throws Exception {
        listen();
    }

    public static void listen() throws Exception{
        SharedAccessSignatureTokenProvider tokenProvider=new SharedAccessSignatureTokenProvider(sharedAccessKeyName,sharedAccessKey,1200);
        ClientSettings clientSettings=new ClientSettings(tokenProvider);

        MessagingFactory messagingFactory = MessagingFactory.createFromNamespaceName(namespace, clientSettings);
        ManagementClient managementClient = new ManagementClient(new ConnectionStringBuilder(String.format("Endpoint=sb://%s.servicebus.windows.net/;SHAREDACCESSKEYNAME=%s;SharedAccessKey=%s", namespace, sharedAccessKeyName, sharedAccessKey)));

        IMessageReceiver messageReceiver = ClientFactory.createMessageReceiverFromEntityPath(messagingFactory, destinationPath);
        System.out.println("Started");
    }
}